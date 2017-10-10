package com.pyrese.eq.parser.events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpellEffectEvent extends SpellEvent{
    Type type;

    public enum Type {
        gain,
        lose
    }
}
