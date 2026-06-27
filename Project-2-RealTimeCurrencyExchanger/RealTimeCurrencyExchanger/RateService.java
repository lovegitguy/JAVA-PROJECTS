package RealTimeCurrencyExchanger;

import java.util.HashMap;
import java.util.Map;

public class RateService {

    // All rates relative to 1 USD (as of June 2025 mid-market approximations)
    private static final Map<String, Double> RATES_TO_USD = new HashMap<>();

    // Historical USD→INR rates by year
    private static final Map<Integer, Double> HISTORICAL_USD_INR = new HashMap<>();

    static {
        RATES_TO_USD.put("USD", 1.0);
        RATES_TO_USD.put("INR", 83.50);
        RATES_TO_USD.put("EUR", 0.92);
        RATES_TO_USD.put("GBP", 0.79);
        RATES_TO_USD.put("JPY", 157.20);
        RATES_TO_USD.put("AUD", 1.53);
        RATES_TO_USD.put("CAD", 1.37);
        RATES_TO_USD.put("CHF", 0.90);
        RATES_TO_USD.put("CNY", 7.25);
        RATES_TO_USD.put("SGD", 1.35);
        RATES_TO_USD.put("HKD", 7.82);
        RATES_TO_USD.put("KRW", 1360.0);
        RATES_TO_USD.put("AED", 3.67);
        RATES_TO_USD.put("SAR", 3.75);
        RATES_TO_USD.put("RUB", 89.50);
        RATES_TO_USD.put("BRL", 5.10);
        RATES_TO_USD.put("ZAR", 18.60);
        RATES_TO_USD.put("MXN", 17.15);
        RATES_TO_USD.put("TRY", 32.50);
        RATES_TO_USD.put("THB", 36.20);
        RATES_TO_USD.put("NZD", 1.63);

        // USD → INR historical
        HISTORICAL_USD_INR.put(2000, 44.94);
        HISTORICAL_USD_INR.put(2001, 47.19);
        HISTORICAL_USD_INR.put(2002, 48.61);
        HISTORICAL_USD_INR.put(2003, 46.58);
        HISTORICAL_USD_INR.put(2004, 45.32);
        HISTORICAL_USD_INR.put(2005, 44.10);
        HISTORICAL_USD_INR.put(2006, 45.31);
        HISTORICAL_USD_INR.put(2007, 41.35);
        HISTORICAL_USD_INR.put(2008, 43.51);
        HISTORICAL_USD_INR.put(2009, 48.41);
        HISTORICAL_USD_INR.put(2010, 45.73);
        HISTORICAL_USD_INR.put(2011, 46.67);
        HISTORICAL_USD_INR.put(2012, 53.44);
        HISTORICAL_USD_INR.put(2013, 58.60);
        HISTORICAL_USD_INR.put(2014, 61.03);
        HISTORICAL_USD_INR.put(2015, 64.15);
        HISTORICAL_USD_INR.put(2016, 67.20);
        HISTORICAL_USD_INR.put(2017, 65.12);
        HISTORICAL_USD_INR.put(2018, 68.40);
        HISTORICAL_USD_INR.put(2019, 70.42);
        HISTORICAL_USD_INR.put(2020, 74.10);
        HISTORICAL_USD_INR.put(2021, 73.92);
        HISTORICAL_USD_INR.put(2022, 78.60);
        HISTORICAL_USD_INR.put(2023, 82.70);
        HISTORICAL_USD_INR.put(2024, 83.40);
        HISTORICAL_USD_INR.put(2025, 83.50);
    }

    /**
     * Convert any currency pair using USD as the pivot.
     * Rate = (toRate / fromRate)
     */
    public static double getRate(String from, String to) {
        if (!RATES_TO_USD.containsKey(from)) {
            throw new IllegalArgumentException("Unsupported currency: " + from);
        }
        if (!RATES_TO_USD.containsKey(to)) {
            throw new IllegalArgumentException("Unsupported currency: " + to);
        }
        double fromRate = RATES_TO_USD.get(from);
        double toRate   = RATES_TO_USD.get(to);
        return toRate / fromRate;
    }

    public static boolean isSupported(String code) {
        return RATES_TO_USD.containsKey(code);
    }

    public static Map<String, Double> getAllRates() {
        return RATES_TO_USD;
    }

    /**
     * Returns historical USD→INR rate for a given year.
     * Only supports years 2000–2025.
     */
    public static double getHistoricalUsdInr(int year) {
        if (!HISTORICAL_USD_INR.containsKey(year)) {
            throw new IllegalArgumentException("No historical data for year: " + year + ". Supported range: 2000–2025.");
        }
        return HISTORICAL_USD_INR.get(year);
    }

    public static boolean hasHistoricalYear(int year) {
        return HISTORICAL_USD_INR.containsKey(year);
    }
}