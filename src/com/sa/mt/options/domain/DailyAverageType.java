package com.sa.mt.options.domain;

import org.apache.commons.lang.ArrayUtils;

public enum DailyAverageType {
    PUT("PA", "PE"), CALL("CA", "CE");
    private String[] identifiers;

    DailyAverageType(String... identifiers) {
        this.identifiers = identifiers;
    }

    public static DailyAverageType identify(String identifier) {
        for(DailyAverageType dailyAverageType : DailyAverageType.values()) {
           if(ArrayUtils.contains(dailyAverageType.identifiers, identifier)) {
              return dailyAverageType; 
           }
        }
        return null;
    }
}
