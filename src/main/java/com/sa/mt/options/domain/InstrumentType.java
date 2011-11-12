package com.sa.mt.options.domain;

import org.apache.commons.lang.ArrayUtils;

import java.util.Arrays;

public enum InstrumentType {
    OPTION("OPTSTK", "OPTIDX"), FUTURE("FUTINT", "FUTSTK", "FUTIDX");
    
    private String[] identifiers;

    InstrumentType(String... identifiers) {
        this.identifiers = identifiers;
    }

    public static InstrumentType identify(String identifier) {
        for(InstrumentType instrumentType : InstrumentType.values()) {
           if(ArrayUtils.contains(instrumentType.identifiers, identifier)) {
              return instrumentType; 
           }
        }
        return null;
    }
}
