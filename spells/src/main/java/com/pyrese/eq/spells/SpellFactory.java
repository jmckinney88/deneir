package com.pyrese.eq.spells;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SpellFactory {

    public List<Spell> readSpells(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream)
        );
        List<Spell> spells = new ArrayList<>();
        while(reader.ready()) {
            String spellDescription = reader.readLine();
            spells.add(Spell.deserialize(spellDescription));
        }
        return spells;
    }
}
