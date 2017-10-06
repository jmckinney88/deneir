package com.pyrese.eq.model.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeaponEntry extends LogEntry {
    private String source;
    private String target;
    private int amount;
}
