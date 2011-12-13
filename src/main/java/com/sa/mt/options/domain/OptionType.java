package com.sa.mt.options.domain;

import org.apache.commons.lang.ArrayUtils;

public enum OptionType {
    PUT("PA", "PE", "Put", "PUT"), CALL("CA", "CE", "Call", "CALL");
    private String[] identifiers;

    OptionType(String... identifiers) {
        this.identifiers = identifiers;
    }

    public static OptionType identify(String identifier) {
        for(OptionType optionType : OptionType.values()) {
           if(ArrayUtils.contains(optionType.identifiers, identifier)) {
              return optionType;
           }
        }
        return null;
    }
}
