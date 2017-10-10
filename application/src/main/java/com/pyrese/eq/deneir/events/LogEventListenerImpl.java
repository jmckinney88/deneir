package com.pyrese.eq.deneir.events;

import com.pyrese.eq.parser.LogEventListener;
import com.pyrese.eq.parser.events.CombatEvent;
import com.pyrese.eq.parser.events.EnterZoneEvent;
import com.pyrese.eq.parser.events.LocationEvent;
import com.pyrese.eq.parser.events.LogEvent;
import com.pyrese.eq.parser.events.SpellDamageEvent;
import com.pyrese.eq.parser.events.SpellEffectEvent;
import com.pyrese.eq.parser.events.SpellEvent;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;


public class LogEventListenerImpl implements LogEventListener {


    private <T_EVENT extends LogEvent> void invoke(Set<Consumer<T_EVENT>> consumers, T_EVENT event){
        for(Consumer<T_EVENT> consumer : consumers) {
            try {
                consumer.accept(event);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    /**
     * Called whenever a line from a log is parsed that cannot otherwise be converted
     * to an event.
     *
     * @return
     */
    @Override
    public Consumer<LogEvent> onUnknown() {
        return (LogEvent event) -> { invoke(onUnknownHandlers, event);};
    }

    private final Set<Consumer<LogEvent>> onUnknownHandlers = ConcurrentHashMap.newKeySet();

    public void addOnUnknownHandler(Consumer<LogEvent> consumer){
        onUnknownHandlers.add(consumer);
    }

    public void removeOnUnknownHandler(Consumer<LogEvent> consumer){
        onUnknownHandlers.remove(consumer);
    }

    /**
     * Called whenever any event, known or unknown, is parsed.
     *
     * @return
     */
    @Override
    public Consumer<LogEvent> onAny() {
        return (LogEvent event) -> { invoke(onAnyHandlers, event);};
    }

    private final Set<Consumer<LogEvent>> onAnyHandlers = ConcurrentHashMap.newKeySet();

    public void addOnAnyHandler(Consumer<LogEvent> consumer){
        onAnyHandlers.add(consumer);
    }

    public void removeOnAnyHandler(Consumer<LogEvent> consumer){
        onAnyHandlers.remove(consumer);
    }

    /**
     * Called when any combat event is parsed
     *
     * @return
     */
    @Override
    public Consumer<CombatEvent> onCombat() {
        return (CombatEvent event) -> { invoke(onCombatHandlers, event);};
    }

    private final Set<Consumer<CombatEvent>> onCombatHandlers = ConcurrentHashMap.newKeySet();

    public void addOnCombatHandler(Consumer<CombatEvent> consumer){
        onCombatHandlers.add(consumer);
    }

    public void removeOnCombatHandler(Consumer<CombatEvent> consumer){
        onCombatHandlers.remove(consumer);
    }

    /**
     * Called whenever any spell event occurs: Damage Tick, Cast, Buff/Debuff
     * gained/lost
     *
     * @return
     */
    @Override
    public Consumer<SpellEvent> onSpell() {
        return (SpellEvent event) -> { invoke(onSpellHandlers, event);};
    }

    private final Set<Consumer<SpellEvent>> onSpellHandlers = ConcurrentHashMap.newKeySet();

    public void addOnSpellHandler(Consumer<SpellEvent> consumer){
        onSpellHandlers.add(consumer);
    }

    public void removeOnSpellHandler(Consumer<SpellEvent> consumer){
        onSpellHandlers.remove(consumer);
    }

    /**
     * Called whenever a spell does damage.
     *
     * @return
     */
    @Override
    public Consumer<SpellDamageEvent> onSpellDamage() {
        return (SpellDamageEvent event) -> { invoke(onSpellDamageHandlers, event);};
    }

    private final Set<Consumer<SpellDamageEvent>> onSpellDamageHandlers = ConcurrentHashMap.newKeySet();

    public void addOnSpellDamageHandler(Consumer<SpellDamageEvent> consumer){
        onSpellDamageHandlers.add(consumer);
    }

    public void removeOnSpellDamageHandler(Consumer<SpellDamageEvent> consumer){
        onSpellDamageHandlers.remove(consumer);
    }

    /**
     * Called whenever a non damaging spell effect is gained or lost.
     *
     * @return
     */
    @Override
    public Consumer<SpellEffectEvent> onSpellEffect() {
        return (SpellEffectEvent event) -> { invoke(onSpellEffectHandlers, event);};
    }

    private final Set<Consumer<SpellEffectEvent>> onSpellEffectHandlers = ConcurrentHashMap.newKeySet();

    public void addOnSpellEffectHandler(Consumer<SpellEffectEvent> consumer){
        onSpellEffectHandlers.add(consumer);
    }

    public void removeOnSpellEffectHandler(Consumer<SpellEffectEvent> consumer){
        onSpellEffectHandlers.remove(consumer);
    }

    /**
     * Called whenever a location value is parsed.
     *
     * @return
     */
    @Override
    public Consumer<LocationEvent> onLocation() {
        return (LocationEvent event) -> { invoke(onLocationHandlers, event);};
    }

    private final Set<Consumer<LocationEvent>> onLocationHandlers = ConcurrentHashMap.newKeySet();

    public void addOnLocationHandler(Consumer<LocationEvent> consumer){
        onLocationHandlers.add(consumer);
    }

    public void removeOnLocationHandler(Consumer<LocationEvent> consumer){
        onLocationHandlers.remove(consumer);
    }

    /**
     * Called whenever a zone is entered;
     *
     * @return
     */
    @Override
    public Consumer<EnterZoneEvent> onEnterZone() {
        return (EnterZoneEvent event) -> { invoke(onEnterZoneHandlers, event);};
    }

    private final Set<Consumer<EnterZoneEvent>> onEnterZoneHandlers = ConcurrentHashMap.newKeySet();

    public void addOnEnterZoneHandler(Consumer<EnterZoneEvent> consumer){
        onEnterZoneHandlers.add(consumer);
    }

    public void removeOnEnterZoneHandler(Consumer<EnterZoneEvent> consumer){
        onEnterZoneHandlers.remove(consumer);
    }
}
