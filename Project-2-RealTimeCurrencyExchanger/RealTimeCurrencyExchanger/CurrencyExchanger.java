package RealTimeCurrencyExchanger;
import java.util.Scanner;
public class CurrencyExchanger {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to realtime Currency Exchange value provider !");
        Currency[] currencies = {
                new Currency("USD", "United States Dollar", "$"),
                new Currency("EUR", "Euro", "€"),
                new Currency("GBP", "British Pound", "£"),
                new Currency("INR", "Indian Rupee", "₹"),
                new Currency("JPY", "Japanese Yen", "¥"),
                new Currency("CNY", "Chinese Yuan", "¥"),
                new Currency("AUD", "Australian Dollar", "$"),
                new Currency("CAD", "Canadian Dollar", "$"),
                new Currency("CHF", "Swiss Franc", "₣"),
                new Currency("KRW", "South Korean Won", "₩"),

                new Currency("SGD", "Singapore Dollar", "$"),
                new Currency("NZD", "New Zealand Dollar", "$"),
                new Currency("AED", "UAE Dirham", "د.إ"),
                new Currency("SAR", "Saudi Riyal", "﷼"),
                new Currency("RUB", "Russian Ruble", "₽"),
                new Currency("BRL", "Brazilian Real", "R$"),
                new Currency("ZAR", "South African Rand", "R"),
                new Currency("MXN", "Mexican Peso", "$"),
                new Currency("TRY", "Turkish Lira", "₺"),
                new Currency("THB", "Thai Baht", "฿")

            };

            System.out.println("Enter your from choice currency : ");

            String F = sc.next();

            System.out.println("Enter your To choice currency : ");

            String T = sc.next();


            for(int i =0;i<currencies.length;i++){
                System.out.println(currencies[i]);
            }

    }
}
