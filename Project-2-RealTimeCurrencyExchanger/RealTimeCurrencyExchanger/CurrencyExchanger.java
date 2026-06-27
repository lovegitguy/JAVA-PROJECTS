package RealTimeCurrencyExchanger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CurrencyExchanger {

    private static final List<ExchangeRecord> history = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("hh:mm a");

    // Supported currencies for display
    private static final String[][] CURRENCIES = {
        {"USD", "United States Dollar",  "$"},
        {"EUR", "Euro",                  "€"},
        {"GBP", "British Pound",         "£"},
        {"INR", "Indian Rupee",          "₹"},
        {"JPY", "Japanese Yen",          "¥"},
        {"CNY", "Chinese Yuan",          "¥"},
        {"AUD", "Australian Dollar",     "$"},
        {"CAD", "Canadian Dollar",       "$"},
        {"CHF", "Swiss Franc",           "₣"},
        {"KRW", "South Korean Won",      "₩"},
        {"SGD", "Singapore Dollar",      "$"},
        {"HKD", "Hong Kong Dollar",      "$"},
        {"AED", "UAE Dirham",            "د.إ"},
        {"SAR", "Saudi Riyal",           "﷼"},
        {"RUB", "Russian Ruble",         "₽"},
        {"BRL", "Brazilian Real",        "R$"},
        {"ZAR", "South African Rand",    "R"},
        {"MXN", "Mexican Peso",          "$"},
        {"TRY", "Turkish Lira",          "₺"},
        {"THB", "Thai Baht",             "฿"},
        {"NZD", "New Zealand Dollar",    "$"},
    };

    public static void main(String[] args) {
        printHeader();

        boolean running = true;
        while (running) {
            printMenu();
            System.out.print(">> ");
            String input = sc.nextLine().trim();

            switch (input) {
                case "1" -> showInrComparison();
                case "2" -> exchangeAmount();
                case "3" -> showHistory();
                case "4" -> showCurrencyHistory();
                case "5" -> {
                    System.out.println("\n Thank you for using Real Time Currency Exchanger !");
                    running = false;
                }
                default  -> System.out.println(" Invalid option. Choose 1–5.");
            }
        }

        sc.close();
    }

    // ─────────────────────────────────────────
    //  OPTION 1 — INR Comparison Table
    // ─────────────────────────────────────────
    private static void showInrComparison() {
        System.out.println("\n========================================");
        System.out.println("         INR COMPARISON TABLE           ");
        System.out.println("========================================");
        System.out.printf("  %-6s %-24s %s%n", "CODE", "CURRENCY", "1 UNIT = INR");
        System.out.println("  ----------------------------------------");

        for (String[] c : CURRENCIES) {
            String code = c[0];
            if (code.equals("INR")) continue;
            try {
                double rate = RateService.getRate(code, "INR");
                System.out.printf("  %-6s %-24s ₹ %.2f%n", code, c[1], rate);
            } catch (IllegalArgumentException ignored) {}
        }

        System.out.println("========================================");
        System.out.println(" Rates approximate — June 2025 mid-market");
        System.out.println("========================================\n");
    }

    // ─────────────────────────────────────────
    //  OPTION 2 — Exchange Amount
    // ─────────────────────────────────────────
    private static void exchangeAmount() {
        System.out.println("\n---- AVAILABLE CURRENCIES ----");
        for (String[] c : CURRENCIES) {
            System.out.printf("  %s - %s%n", c[0], c[1]);
        }

        System.out.print("\nEnter your name : ");
        String name = sc.nextLine().trim();

        System.out.print("Enter From currency : ");
        String from = sc.nextLine().trim().toUpperCase();

        System.out.print("Enter To currency   : ");
        String to = sc.nextLine().trim().toUpperCase();

        if (!RateService.isSupported(from)) {
            System.out.println(" Unsupported currency: " + from);
            return;
        }
        if (!RateService.isSupported(to)) {
            System.out.println(" Unsupported currency: " + to);
            return;
        }

        System.out.print("Enter Amount        : ");
        double amount;
        try {
            amount = Double.parseDouble(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(" Invalid amount.");
            return;
        }

        double rate   = RateService.getRate(from, to);
        double result = amount * rate;

        System.out.println("\n----------------------------");
        System.out.printf(" Exchange value : | %.2f %s = %.4f %s |%n", amount, from, result, to);
        System.out.println("----------------------------\n");

        history.add(new ExchangeRecord(name, from, to, amount, result));
    }

    // ─────────────────────────────────────────
    //  OPTION 3 — Exchange History
    // ─────────────────────────────────────────
    private static void showHistory() {
        if (history.isEmpty()) {
            System.out.println("\n No exchange history yet.\n");
            return;
        }
        System.out.println("\n======================================");
        System.out.println("          EXCHANGE HISTORY            ");
        System.out.println("======================================");
        for (int i = 0; i < history.size(); i++) {
            System.out.println("\n  #" + (i + 1));
            for (String line : history.get(i).toString().split("\n")) {
                System.out.println("  " + line);
            }
            System.out.println("  --------------------------------------");
        }
        System.out.println();
    }

    // ─────────────────────────────────────────
    //  OPTION 4 — Currency History by Year
    // ─────────────────────────────────────────
    private static void showCurrencyHistory() {
        System.out.println("\n  Note: Historical data available for USD ↔ INR (2000–2025)");
        System.out.print("Enter From currency : ");
        String from = sc.nextLine().trim().toUpperCase();

        System.out.print("Enter To currency   : ");
        String to = sc.nextLine().trim().toUpperCase();

        // Only USD↔INR supported historically
        boolean usdInr = (from.equals("USD") && to.equals("INR"));
        boolean inrUsd = (from.equals("INR") && to.equals("USD"));

        if (!usdInr && !inrUsd) {
            System.out.println(" Historical data only available for USD ↔ INR.\n");
            return;
        }

        System.out.print("Enter Year (2000–2025) : ");
        int year;
        try {
            year = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(" Invalid year.");
            return;
        }

        if (!RateService.hasHistoricalYear(year)) {
            System.out.println(" No data for year " + year + ". Supported: 2000–2025.\n");
            return;
        }

        double usdInrRate = RateService.getHistoricalUsdInr(year);
        double rate = usdInr ? usdInrRate : 1.0 / usdInrRate;

        System.out.println("\n----------------------");
        System.out.printf(" Value of 1 %s = %.4f %s  (%d average)%n", from, rate, to, year);
        System.out.println("----------------------\n");
    }

    // ─────────────────────────────────────────
    //  UI helpers
    // ─────────────────────────────────────────
    private static void printHeader() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("=======================================");
        System.out.println(" WELCOME TO REAL TIME CURRENCY CONVERTER");
        System.out.println("=======================================");
        System.out.println("     *** AVAILABLE CURRENCIES ***");
        for (String[] c : CURRENCIES) {
            System.out.printf("  %-6s - %s%n", c[0], c[1]);
        }
        System.out.printf("Date : %s%n", now.format(DATE_FMT));
        System.out.printf("Time : %s%n", now.format(TIME_FMT));
    }

    private static void printMenu() {
        System.out.println("\n------ Select Operation ------");
        System.out.println("1. See INR comparison");
        System.out.println("2. Exchange amount");
        System.out.println("3. Exchange History");
        System.out.println("4. Currency History");
        System.out.println("5. Exit");
    }
}