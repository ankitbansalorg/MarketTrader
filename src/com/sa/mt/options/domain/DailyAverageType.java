package com.sa.mt.options.domain;

public enum DailyAverageType {
    PUT("PA", "PE"), CALL("CA", "CE");
    private String[] identifiers;

    DailyAverageType(String... identifiers) {
        this.identifiers = identifiers;
    }
}
