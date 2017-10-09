package com.pyrese.eq.parser.events;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class LogEvent {
    private String rawContent;
    private Date timestamp;
}
