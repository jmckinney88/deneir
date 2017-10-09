package com.pyrese.eq.parser.events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpellDamageEvent extends SpellEvent {
    private int amount;
    private String type;
}
