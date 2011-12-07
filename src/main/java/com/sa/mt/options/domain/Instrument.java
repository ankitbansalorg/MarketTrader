package com.sa.mt.options.domain;

import java.util.Date;

public class Instrument {
    private String symbol;
    private InstrumentType instrumentType;
    private double strikePrice;
    private DailyAverageType type;
    private double openPrice;
    private double lowPrice;
    private double highPrice;
    private double closePrice;
    private double settlePrice;
    private long contractsTraded;
    private double tradedAmount;
    private long openInterest;
    private long changeInOI;
    private Date date;
    private Date expiryDate;


    public Instrument(String symbol, InstrumentType instrumentType, double strikePrice, DailyAverageType type,
                        double openPrice, double lowPrice, double highPrice,
                        double closePrice, double settlePrice, long contractsTraded,
                        double tradedAmount, long openInterest, long changeInOI, Date date,
                        Date expiryDate) {
        this.symbol = symbol;
        this.instrumentType = instrumentType;
        this.strikePrice = strikePrice;
        this.type = type;
        this.openPrice = openPrice;
        this.lowPrice = lowPrice;
        this.highPrice = highPrice;
        this.closePrice = closePrice;
        this.settlePrice = settlePrice;
        this.contractsTraded = contractsTraded;
        this.tradedAmount = tradedAmount;
        this.openInterest = openInterest;
        this.changeInOI = changeInOI;
        this.date = date;
        this.expiryDate = expiryDate;
    }

    public Instrument() {

    }

    public Instrument(String symbol, InstrumentType instrumentType, double strikePrice, DailyAverageType type,
                        Date date, Date expiryDate) {
        this(symbol, instrumentType, strikePrice, type, 0, 0, 0, 0, 0, 0, 0, 0, 0, date, expiryDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Instrument that = (Instrument) o;

        if (Double.compare(that.strikePrice, strikePrice) != 0) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (expiryDate != null ? !expiryDate.equals(that.expiryDate) : that.expiryDate != null) return false;
        if (instrumentType != that.instrumentType) return false;
        if (symbol != null ? !symbol.equals(that.symbol) : that.symbol != null) return false;
        if (type != that.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = symbol != null ? symbol.hashCode() : 0;
        result = 31 * result + (instrumentType != null ? instrumentType.hashCode() : 0);
        temp = strikePrice != +0.0d ? Double.doubleToLongBits(strikePrice) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (expiryDate != null ? expiryDate.hashCode() : 0);
        return result;
    }

    public String getSymbol() {
        return symbol;
    }

    public InstrumentType getInstrumentType() {
        return instrumentType;
    }

    public double getStrikePrice() {
        return strikePrice;
    }

    public DailyAverageType getType() {
        return type;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public double getLowPrice() {
        return lowPrice;
    }

    public double getHighPrice() {
        return highPrice;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public double getSettlePrice() {
        return settlePrice;
    }

    public long getContractsTraded() {
        return contractsTraded;
    }

    public double getTradedAmount() {
        return tradedAmount;
    }

    public long getOpenInterest() {
        return openInterest;
    }

    public long getChangeInOI() {
        return changeInOI;
    }

    public Date getDate() {
        return date;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    @Override

    public String toString() {
        return "DailyAverage{" +
                "symbol='" + symbol + '\'' +
                ", instrumentType=" + instrumentType +
                ", strikePrice=" + strikePrice +
                ", type=" + type +
                ", openPrice=" + openPrice +
                ", lowPrice=" + lowPrice +
                ", highPrice=" + highPrice +
                ", closePrice=" + closePrice +
                ", settlePrice=" + settlePrice +
                ", contractsTraded=" + contractsTraded +
                ", tradedAmount=" + tradedAmount +
                ", openInterest=" + openInterest +
                ", changeInOI=" + changeInOI +
                ", date=" + date +
                ", expiryDate=" + expiryDate +
                '}';
    }
}
