package com.pyrese.eq.model.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpellDamageEntry extends SpellEntry {
    private int amount;
    private String type;
}
