package com.pyrese.eq.parser;

import com.pyrese.eq.parser.events.CombatEvent;
import com.pyrese.eq.parser.events.EnterZoneEvent;
import com.pyrese.eq.parser.events.LocationEvent;
import com.pyrese.eq.parser.events.LogEvent;
import com.pyrese.eq.parser.events.SpellDamageEvent;
import com.pyrese.eq.parser.events.SpellEffectEvent;
import com.pyrese.eq.parser.events.SpellEvent;

import java.util.function.Consumer;

/**
 * Observer for EverquestLogParser events.
 */
public interface LogEventListener {
    /**
     * Called whenever a line from a log is parsed that cannot otherwise be converted
     * to an event.
     * @return
     */
    Consumer<LogEvent> onUnknown();

    /**
     * Called whenever any event, known or unknown, is parsed.
     * @return
     */
    Consumer<LogEvent> onAny();

    /**
     * Called when any combat event is parsed
     * @return
     */
    Consumer<CombatEvent> onCombat();

    /**
     * Called whenever any spell event occurs: Damage Tick, Cast, Buff/Debuff
     * gained/lost
     * @return
     */
    Consumer<SpellEvent> onSpell();

    /**
     * Called whenever a spell does damage.
     * @return
     */
    Consumer<SpellDamageEvent> onSpellDamage();

    /**
     * Called whenever a non damaging spell effect is gained or lost.
     * @return
     */
    Consumer<SpellEffectEvent> onSpellEffect();

    /**
     * Called whenever a location value is parsed.
     * @return
     */
    Consumer<LocationEvent> onLocation();

    /**
     * Called whenever a zone is entered;
     * @return
     */
    Consumer<EnterZoneEvent> onEnterZone();
}
