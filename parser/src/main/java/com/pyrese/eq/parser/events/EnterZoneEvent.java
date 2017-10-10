package com.pyrese.eq.parser.events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnterZoneEvent extends LogEvent{
    String zoneName;
}
