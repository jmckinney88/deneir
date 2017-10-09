package com.pyrese.eq.parser.events;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SpellEvent extends CombatEvent {
    String spellName;
}
