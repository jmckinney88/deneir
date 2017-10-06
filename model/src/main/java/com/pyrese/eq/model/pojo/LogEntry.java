package com.pyrese.eq.model.pojo;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class LogEntry {
    private String rawContent;
    private Date timestamp;
}
