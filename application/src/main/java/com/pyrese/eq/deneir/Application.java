package com.pyrese.eq.deneir;

import com.pyrese.eq.parser.EverquestLogParser;
import com.pyrese.eq.parser.LogEventListener;
import com.pyrese.eq.parser.events.CombatEvent;
import com.pyrese.eq.parser.events.EnterZoneEvent;
import com.pyrese.eq.parser.events.LocationEvent;
import com.pyrese.eq.parser.events.LogEvent;
import com.pyrese.eq.parser.events.SpellDamageEvent;
import com.pyrese.eq.parser.events.SpellEffectEvent;
import com.pyrese.eq.parser.events.SpellEvent;
import com.pyrese.io.TailInputStream;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Consumer;

/**
 * Created by jonathan.mckinney on 10/5/17.
 */
public class Application {
    public static void main(String[] args) throws IOException {
        final File file = new File(args[0]);

        System.out.println("Welcome: starting read");
        Thread readerThread = new Thread(new Runnable() {
            public void run() {
                try {
                    EverquestLogParser parser = new EverquestLogParser(file, EverquestLogParser.Mode.Tail);
                    parser.registerEventListener(new LogEventListener() {
                        public Consumer<LogEvent> onUnknown() {
                            return null;
                        }

                        public Consumer<LogEvent> onAny() {
                            return null;
                        }

                        public Consumer<CombatEvent> onCombat() {
                            return null;
                        }

                        public Consumer<SpellEvent> onSpell() {
                            return null;
                        }

                        public Consumer<SpellDamageEvent> onSpellDamage() {
                            return null;
                        }

                        public Consumer<SpellEffectEvent> onSpellEffect() {
                            return null;
                        }

                        public Consumer<LocationEvent> onLocation() {
                            return (LocationEvent event) -> {
                                System.out.println(
                                        "Your location was "
                                        + event.getY() + ", "
                                                + event.getX() + ", "
                                                + event.getZ()
                                );
                            };
                        }

                        public Consumer<EnterZoneEvent> onEnterZone() {
                            return (EnterZoneEvent event) -> {
                                System.out.println(
                                        "You entered a new zone: " + event.getZoneName()
                                );
                            };
                        }
                    });

                    while(true) {
                        //spin
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        readerThread.setDaemon(false);
        readerThread.start();


    }
}
