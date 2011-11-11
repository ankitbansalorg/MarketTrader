package com.sa.mt.options.domain;

public class DailyAverage {
    private String symbol;
    private int strikePrice;
    private DailyAverageType type;

    public DailyAverage(String symbol, int strikePrice, DailyAverageType type) {
        this.symbol = symbol;
        this.strikePrice = strikePrice;
        this.type = type;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DailyAverage that = (DailyAverage) o;

        if (strikePrice != that.strikePrice) return false;
        if (symbol != null ? !symbol.equals(that.symbol) : that.symbol != null) return false;
        if (type != that.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = symbol != null ? symbol.hashCode() : 0;
        result = 31 * result + strikePrice;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
