package com.pyrese.eq.parser.events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpellEffectEvent extends SpellEvent{
    boolean positive;
    String effect;
}
