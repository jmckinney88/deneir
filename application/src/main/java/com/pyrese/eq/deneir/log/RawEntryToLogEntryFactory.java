package com.pyrese.eq.deneir.log;


import com.google.common.collect.ImmutableMap;
import com.pyrese.eq.parser.events.LogEvent;

import java.util.Map;
import java.util.regex.Pattern;

public class RawEntryToLogEntryFactory {

    private static Map<Pattern, Class<? extends LogEvent>> patternClassMap =
            ImmutableMap.<Pattern, Class<? extends LogEvent>>builder()
            .build();

    public LogEvent createLogEntry(String rawContent){
        LogEvent logEvent = new LogEvent(); //todo: determine type
        logEvent.setRawContent(rawContent);
        return logEvent;
    }
}
