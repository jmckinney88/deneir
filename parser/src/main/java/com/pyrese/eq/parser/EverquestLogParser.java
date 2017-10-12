package com.pyrese.eq.parser;

import com.pyrese.eq.parser.events.CombatEvent;
import com.pyrese.eq.parser.events.EnterZoneEvent;
import com.pyrese.eq.parser.events.LocationEvent;
import com.pyrese.eq.parser.events.LogEvent;
import com.pyrese.eq.parser.events.SpellDamageEvent;
import com.pyrese.eq.parser.events.SpellEffectEvent;
import com.pyrese.eq.parser.events.SpellEvent;
import com.pyrese.io.TailInputStream;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Given a Log file and mode to operate in, parses a whole log file or tails a
 * log file for Everquest and emits events based on the lines consumed. In the
 * observer pattern, the EverquestLogParser is the subject.
 */
public class EverquestLogParser implements Closeable {

    /**
     * Closes this stream and releases any system resources associated
     * with it. If the stream is already closed then invoking this
     * method has no effect.
     * <p>
     * <p> As noted in {@link AutoCloseable#close()}, cases where the
     * close may fail require careful attention. It is strongly advised
     * to relinquish the underlying resources and to internally
     * <em>mark</em> the {@code Closeable} as closed, prior to throwing
     * the {@code IOException}.
     *
     * @throws IOException if an I/O error occurs
     */
    public void close() throws IOException {
        streamToEventReader.stop();
    }

    public enum Mode {
        Full,
        Tail
    }

    private Set<LogEventListener> listeners;

    private StreamToEventReader streamToEventReader;

    public EverquestLogParser(File file, Mode mode) throws IOException {
        listeners = ConcurrentHashMap.newKeySet();
        streamToEventReader = new StreamToEventReader(file, mode, listeners);
    }

    public void start() {
        Thread queueReaderThread = new Thread(streamToEventReader);
        queueReaderThread.start();
    }

    /**
     * Add a unique LogEventListener such that any callbacks on
     * the listener will be called as documented in LogEventListener. N
     * calls to register a unique LogEventListener should still only add
     * that listener once.
     * @param listener
     */
    public void registerEventListener(LogEventListener listener) {
        listeners.add(listener);
    }

    /**
     * Remove the given LogEventListener.
     * Events will no longer be passed to this listener.
     * @return <tt>true</tt> if an element was removed as a result of this call
     */
    public boolean removeEventListener(LogEventListener listener){
        return listeners.remove(listener);
    }

    /**
     * Reads raw content from the rawInputQueue and converts it into and fires
     * events.
     */
    private static class StreamToEventReader implements Runnable {

        private Set<LogEventListener> listeners;
        private boolean stop = false;
        private InputStream logInputStream;

        public StreamToEventReader(File file, Mode mode, Set<LogEventListener> listeners) throws IOException {
            switch(mode) {
                case Full:
                    logInputStream = new FileInputStream(file);
                    break;
                case Tail:
                    logInputStream = new TailInputStream(file, false);
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected mode: " + mode);
            }
            this.listeners = listeners;
        }

        public void stop() {
            stop = true;
        }

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        public void run() {
            EventFactory logEventFactory = new EventFactory();
            while(!stop){
                String rawContent = null;
                try {

                    rawContent = readLine(logInputStream);
                    List<LogEvent> events = logEventFactory.parseLogString(rawContent);
                    for (LogEvent event : events) {
                        broadcastEvent(event);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private byte[] lineSeparatorBytes = System.lineSeparator().getBytes();
        private byte[] readLineBuffer = new byte[8192];
        private int lineLength = 0;

        private String readLine(InputStream inputStream) throws IOException {
            lineLength = 0;
            while(
                    !(
                            bufferEndsWith(readLineBuffer, lineSeparatorBytes, lineLength)
                            || readLineBuffer[lineLength] == (byte)-1
                    )

                    ) {
                if(lineLength >= readLineBuffer.length){
                    readLineBuffer = Arrays.copyOf(readLineBuffer, readLineBuffer.length * 2);
                }
                readLineBuffer[lineLength] = (byte)inputStream.read();
                lineLength++;
            }

            int trimLength = bufferEndsWith(readLineBuffer, lineSeparatorBytes, lineLength) ? lineSeparatorBytes.length : 1;
            return new String(readLineBuffer, 0, lineLength-trimLength);
        }

        private boolean bufferEndsWith(byte[] buffer, byte[] subarray, int bufferLength){
            if(subarray.length > bufferLength){
                return false;
            }
            int offset = bufferLength - subarray.length;
            for (int i = 0; i < subarray.length; i++){
                if(buffer[offset+i] != subarray[i]){
                    return false;
                }
            }
            return true;
        }

        private void broadcastEvent(LogEvent event){
            if(event.getClass().equals(LogEvent.class)){
                onUnknown(event);
            } else {
                onAny(event);
                if(CombatEvent.class.isInstance(event)) {
                    onCombat((CombatEvent)event);
                }
                if(SpellEvent.class.isInstance(event)) {
                    onSpell((SpellEvent)event);
                }
                if(SpellDamageEvent.class.isInstance(event)) {
                    onSpellDamage((SpellDamageEvent)event);
                }
                if(SpellEffectEvent.class.isInstance(event)) {
                    onSpellEffect((SpellEffectEvent) event);
                }
                if(LocationEvent.class.isInstance(event)) {
                    onLocation((LocationEvent)event);
                }
                if(EnterZoneEvent.class.isInstance(event)) {
                    onEnterZone((EnterZoneEvent)event);
                }
            }
        }

        protected final void onAny(LogEvent event){
            for(LogEventListener listener : listeners){
                if(listener.onAny() != null){
                    //TODO: These calls should be spun off into other threads to avoid handlers blocking processing.
                    //TODO: Handlers will need to check the timestamp as FIFO will not be guaranteed with the multithreaded handling.
                    //TODO: Is this worthwhile?
                    listener.onAny().accept(event);
                }
            }
        }

        protected final void onCombat(CombatEvent event){
            for(LogEventListener listener : listeners){
                if(listener.onCombat() != null){
                    listener.onCombat().accept(event);
                }
            }
        }

        protected final void onSpell(SpellEvent event){
            for(LogEventListener listener : listeners){
                if(listener.onSpell() != null){
                    listener.onSpell().accept(event);
                }
            }
        }

        protected final void onSpellDamage(SpellDamageEvent event){
            for(LogEventListener listener : listeners){
                if(listener.onSpellDamage() != null){
                    listener.onSpellDamage().accept(event);
                }
            }
        }

        protected final void onSpellEffect(SpellEffectEvent event){
            for(LogEventListener listener : listeners){
                if(listener.onSpellEffect() != null){
                    listener.onSpellEffect().accept(event);
                }
            }
        }

        protected final void onLocation(LocationEvent event){
            for(LogEventListener listener : listeners){
                if(listener.onLocation() != null){
                    listener.onLocation().accept(event);
                }
            }
        }

        protected final void onEnterZone(EnterZoneEvent event){
            for(LogEventListener listener : listeners){
                if(listener.onEnterZone() != null){
                    listener.onEnterZone().accept(event);
                }
            }
        }

        protected final void onUnknown(LogEvent event){
            for(LogEventListener listener : listeners){
                if(listener.onUnknown() != null){
                    listener.onUnknown().accept(event);
                }
            }
        }
    }

    private static class EventFactory {


        List<EventPatternFactory> eventPatternFactories = new ArrayList<>();
        EventPatternFactory unknownFactory;
        String previousLine = "";

        public EventFactory() {
            eventPatternFactories.add(new LocationEventFactory());
            eventPatternFactories.add(new EnterZoneEventFactory());
            unknownFactory = new UnknownEventFactory();
        }

        public List<LogEvent> parseLogString(String logString){
            boolean handled = false;
            List<LogEvent> list = new ArrayList<>();
            for(EventPatternFactory eventPatternFactory : eventPatternFactories) {
                try {
                    if (eventPatternFactory.isMatch(logString)) {
                        try {
                            list.add(eventPatternFactory.parseLogString(logString));
                            handled = true;
                        } catch (Exception e) {
                            System.err.println("Previous Line: " + previousLine);
                            System.err.println("Failed parsing line: " + logString);
                            e.printStackTrace(); //todo: Logging
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Previous Line: " + previousLine);
                    System.err.println("Failed parsing line: " + logString);
                    e.printStackTrace();
                }
            }
            if(!handled) {
                try {
                    list.add(unknownFactory.parseLogString(logString));
                } catch (Exception e) {
                    System.err.println("Previous Line: " + previousLine);
                    System.err.println("Failed parsing line: " + logString);
                    e.printStackTrace(); //todo: logging
                }
            }
            previousLine = logString;
            return list;
        }

        private interface EventPatternFactory {
            boolean isMatch(String rawContent);
            LogEvent parseLogString(String logString) throws ParseException;
        }

        private static abstract class EventPatternFactoryImpl<T_EVENT extends LogEvent> implements
                EventPatternFactory {
            protected Pattern pattern;
            private SimpleDateFormat dateFormatter;

            protected EventPatternFactoryImpl() {
                dateFormatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss YYYY");
            }

            public boolean isMatch(String rawContent) {
                return pattern.matcher(stripLogDate(rawContent)).matches();
            }

            protected Date getDateTime(String rawContent) throws ParseException {
                String dateString = rawContent.substring(1, rawContent.indexOf("]"));
                return dateFormatter.parse(dateString);
            }

            @Override
            public final T_EVENT parseLogString(String logString) throws ParseException {
                if(pattern != null) {
                    if (!isMatch(logString)) {
                        throw new IllegalArgumentException("Invalid logString; Must match pattern " + pattern.toString());
                    }
                }
                T_EVENT event = getLogEvent();
                event.setTimestamp(getDateTime(logString));
                event.setRawContent(logString);
                populateEvent(event, stripLogDate(logString));
                return event;
            }

            private String stripLogDate(String logString) {
                return logString.substring(logString.indexOf("]") + 2);
            }

            protected abstract void populateEvent(T_EVENT event, String logString) throws ParseException;

            protected abstract T_EVENT getLogEvent();
        }

        private static class UnknownEventFactory extends EventPatternFactoryImpl<LogEvent> {

            @Override
            protected void populateEvent(LogEvent event, String logString) throws ParseException {
                //do nothing
            }

            @Override
            protected LogEvent getLogEvent() {
                return new LogEvent();
            }
        }

        private static class LocationEventFactory extends EventPatternFactoryImpl<LocationEvent> {

            public LocationEventFactory(){
                this.pattern = Pattern.compile("^Your Location is (?<Y>-?[0-9]+(\\.[0-9]*)?), (?<X>-?[0-9]+(\\.[0-9]*)?), (?<Z>-?[0-9]+(\\.[0-9]*)?)");
            }

            @Override
            public void populateEvent(LocationEvent event, String logString) throws ParseException {
                Matcher matcher = pattern.matcher(logString);
                matcher.find();
                event.setY(Double.valueOf(matcher.group("Y")));
                event.setX(Double.valueOf(matcher.group("X")));
                event.setZ(Double.valueOf(matcher.group("Z")));
            }

            @Override
            protected LocationEvent getLogEvent() {
                return new LocationEvent();
            }
        }

        private static class EnterZoneEventFactory extends EventPatternFactoryImpl<EnterZoneEvent> {

            public EnterZoneEventFactory(){
                this.pattern = Pattern.compile("^You have entered (?<Zone>[a-zA-Z\\s]+).");
            }

            @Override
            public void populateEvent(EnterZoneEvent event, String logString) throws ParseException {
                Matcher matcher = pattern.matcher(logString);
                matcher.find();
                event.setZoneName(matcher.group("Zone"));
            }

            @Override
            protected EnterZoneEvent getLogEvent() {
                return new EnterZoneEvent();
            }
        }
    }
}
