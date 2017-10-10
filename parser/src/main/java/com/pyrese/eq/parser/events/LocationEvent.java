package com.pyrese.eq.parser.events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationEvent extends LogEvent {
    double y;
    double x;
    double z;
}
