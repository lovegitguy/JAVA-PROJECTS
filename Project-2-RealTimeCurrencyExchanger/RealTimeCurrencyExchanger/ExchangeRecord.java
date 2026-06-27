package RealTimeCurrencyExchanger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExchangeRecord {

    private String name;
    private String fromCurrency;
    private String toCurrency;
    private double amount;
    private double result;
    private LocalDateTime timestamp;

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("hh:mm a");

    public ExchangeRecord(String name, String fromCurrency, String toCurrency,
                          double amount, double result) {
        this.name         = name;
        this.fromCurrency = fromCurrency;
        this.toCurrency   = toCurrency;
        this.amount       = amount;
        this.result       = result;
        this.timestamp    = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return String.format(
            "Date : %s\nTime : %s\nName : %s\nExchanged : %s to %s\nAmount : %.2f %s = %.4f %s",
            timestamp.format(DATE_FMT),
            timestamp.format(TIME_FMT),
            name,
            fromCurrency, toCurrency,
            amount, fromCurrency,
            result, toCurrency
        );
    }
}