package com.pyrese.eq.parser.events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CombatEvent extends LogEvent {
    private String source;
    private String target;
}
