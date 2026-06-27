package RealTimeCurrencyExchanger;
import java.time.LocalDate;
import java.util.Scanner;
public class CurrencyExchanger {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Currency[] currencies = {


                new Currency("USD","United States Dollar","$"),
                new Currency("EUR","Euro","€"),
                new Currency("GBP","British Pound","£"),
                new Currency("INR","Indian Rupee","₹"),
                new Currency("JPY","Japanese Yen","¥"),
                new Currency("CNY","Chinese Yuan","¥"),
                new Currency("AUD","Australian Dollar","$"),
                new Currency("CAD","Canadian Dollar","$"),
                new Currency("CHF","Swiss Franc","₣"),
                new Currency("KRW","South Korean Won","₩"),

                new Currency("SGD","Singapore Dollar","$"),
                new Currency("NZD","New Zealand Dollar","$"),
                new Currency("AED","UAE Dirham","د.إ"),
                new Currency("SAR","Saudi Riyal","﷼"),
                new Currency("RUB","Russian Ruble","₽"),
                new Currency("BRL","Brazilian Real","R$"),
                new Currency("ZAR","South African Rand","R"),
                new Currency("MXN","Mexican Peso","$"),
                new Currency("TRY","Turkish Lira","₺"),
                new Currency("THB","Thai Baht","฿")

        };

        System.out.println("=================================");
        System.out.println(" Welcome to Currency Exchanger ");
        System.out.println("=================================");

        System.out.println("\nAvailable Currencies:");

        for(Currency c : currencies){

            System.out.println(c);

        }

        System.out.println("\nEnter From Currency Code:");

        String from = sc.next().toUpperCase();



        System.out.println("Enter To Currency Code:");

        String to = sc.next().toUpperCase();



        System.out.println("Enter Amount:");

        double amount = sc.nextDouble();

        /*
        Temporary exchange rate
        Later this will come from API/database
        */

        double rate = 83.2;



        ExchangeRate exchange =
                new ExchangeRate(
                        from,
                        to,
                        rate,
                        LocalDate.now()
                );



        double result = exchange.convert(amount);



        System.out.println("\nExchange Details:");

        System.out.println(exchange);



        System.out.println(
                amount + " " + from +
                " = " +
                result + " " + to
        );


        sc.close();

    }

}