package com.pyrese.eq.parser.events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationEvent extends LogEvent {
    int y;
    int x;
    int z;
}
