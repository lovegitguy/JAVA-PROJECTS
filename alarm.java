import java.time.LocalTime;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.Duration;


public class alarm{
    public static void main(String[] args) {
        System.out.println("**** WELCOME TO SET ALARM ****");

        // Declaring the time objects
        LocalDate dt= LocalDate.now();
        LocalTime tm= LocalTime.now(ZoneId.of("Asia/Kolkata"));
        DateTimeFormatter formatter1=DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatter2= DateTimeFormatter.ofPattern("hh:mm a");



        String formateddate=  dt.format(formatter1);
        String formatedtime= tm.format(formatter2);


        // Using the time objects
        System.out.println("Date : "+ formateddate);
        System.out.println("Time : "+ formatedtime);

        System.out.println("Set your alarm");
        System.out.print("Enter your hour (1-12) : ");
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();

        //Taking inputs from the users
        System.out.print("Enter your minutes (0-59) : ");
        int m= sc.nextInt();

        System.out.print("Enter am/pm : ");
        String a=sc.next();


           int hour24 = h;
        if (a.equalsIgnoreCase("pm") && h < 12) {
            hour24 += 12;
        } else if (a.equalsIgnoreCase("am") && h == 12) {
            hour24 = 0;
        }

        // taking the start and end of the alarm to cal left time

        LocalTime Start = LocalTime.now(ZoneId.of("Asia/Kolkata"));

        LocalTime Set = LocalTime.of(hour24,m ,0);
        Duration duration= Duration.between(Start,Set);

        if(duration.isNegative()){
            duration = duration.plusDays(1);
        }

        //converting left duration in hours and minutes

        long hoursLeft = duration.toHours();
        long minutesLeft = duration.toMinutesPart();

        //Printing the information

        System.out.println("Your alarm is set for : "+ h + ":" + m + ":" + a);
        System.out.println("Your alarm is set for : "+ hoursLeft +"hours and "+ minutesLeft+ "minutes");
        System.out.println("This is a 12 hour format setting !");
        System.out.println("\nMonitoring clock... Do not close this window.");

        while (true)
         { 
            LocalTime freshStart = LocalTime.now(ZoneId.of("Asia/Kolkata"));
            Duration remaining = Duration.between(freshStart, Set);

            if(remaining.isNegative() && Set.isBefore(freshStart)){
                remaining= remaining.plusDays(1);
            }

            if(remaining.isZero() || remaining.isNegative()){
                System.out.println("Wake Up Wake up !");
                java.awt.Toolkit.getDefaultToolkit().beep();

                // Beeps 5 times when the alarm goes off
                for (int i = 0; i < 5; i++) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                try { Thread.sleep(500); } catch (Exception e) {

                 } 
                }
                break;
            }
                try {
                Thread.sleep(1000);
                    } catch (InterruptedException e) {
             System.out.println("Sleep was interrupted!");
            }

        }
        sc.close();
    }
}