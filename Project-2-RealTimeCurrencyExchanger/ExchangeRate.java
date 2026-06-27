package RealTimeCurrencyExchanger;

import java.time.LocalDate;

public class ExchangeRate {

    private String fromCurrency;
    private String toCurrency;
    private double rate;
    private LocalDate date;

    public ExchangeRate(String fromCurrency, String toCurrency, double rate, LocalDate date) {
        this.fromCurrency = fromCurrency;
        this.toCurrency   = toCurrency;
        this.rate         = rate;
        this.date         = date;
    }

    public String    getFromCurrency() { return fromCurrency; }
    public String    getToCurrency()   { return toCurrency; }
    public double    getRate()         { return rate; }
    public LocalDate getDate()         { return date; }

    public double convert(double amount) { return amount * rate; }

    @Override
    public String toString() {
        return "1 " + fromCurrency + " = " + rate + " " + toCurrency + " | Date : " + date;
    }
}