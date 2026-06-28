import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Alarm {
    // Volatile boolean allows threads to safely share the stop signal
    private static volatile boolean alarmRunning = true;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("**** WELCOME TO SET ALARM ****");

        // Declaring the time objects
        LocalDate dt = LocalDate.now();
        LocalTime tm = LocalTime.now(ZoneId.of("Asia/Kolkata"));
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("hh:mm a");

        String formateddate = dt.format(formatter1);
        String formatedtime = tm.format(formatter2);

        // Using the time objects
        System.out.println("Date : " + formateddate);
        System.out.println("Time : " + formatedtime);

        // Ask user for MP3 path
        System.out.print("Enter MP3 path (or press Enter to skip) : ");
        String mp3Path = sc.nextLine().trim();
        File mp3File = null;
        if (!mp3Path.isEmpty()) {
            mp3File = new File(mp3Path);
            if (!mp3File.exists() || !mp3Path.toLowerCase().endsWith(".mp3")) {
                System.out.println("Invalid file, using system beep instead.");
                mp3File = null;
            } else {
                System.out.println("MP3 loaded: " + mp3File.getName());
            }
        }

        System.out.println("Set your alarm");
        System.out.print("Enter your hour (1-12) : ");
        
        int h = sc.nextInt();
        if (h > 12 || h < 1) {
            System.out.println("Invalid hour input.");
            sc.close();
            return;
        }

        // Taking inputs from the users
        System.out.print("Enter your minutes (0-59) : ");
        int m = sc.nextInt();
        if (m > 59 || m < 0) {
            System.out.println("Invalid Minutes input");
            sc.close();
            return;
        }

        System.out.print("Enter am/pm : ");
        String a = sc.next();
        if (!a.equalsIgnoreCase("am") && !a.equalsIgnoreCase("pm")) {
            System.out.println("Invalid AM/PM.");
            sc.close();
            return;
        }

        int hour24 = h;
        if (a.equalsIgnoreCase("pm") && h < 12) {
            hour24 += 12;
        } else if (a.equalsIgnoreCase("am") && h == 12) {
            hour24 = 0;
        }

        // taking the start and end of the alarm to cal left time
        LocalTime Start = LocalTime.now(ZoneId.of("Asia/Kolkata"));
        LocalTime Set = LocalTime.of(hour24, m, 0);
        Duration duration = Duration.between(Start, Set);

        if (duration.isNegative()) {
            duration = duration.plusDays(1);
        }

        // converting left duration in hours and minutes
        long hoursLeft = duration.toHours();
        long minutesLeft = duration.toMinutesPart();

        // Printing the information
        System.out.println("Your alarm is set for : " + h + ":" + String.format("%02d", m) + " " + a.toUpperCase());
        System.out.println("Your alarm is set for : " + hoursLeft + " hours and " + minutesLeft + " minutes");
        System.out.println("This is a 12 hour format setting!");
        System.out.println("\nMonitoring clock... Do not close this window.");

        // Clear residual characters from the scanner stream
        sc.nextLine();

        // needed so the lambda can use mp3File
        final File finalMp3 = mp3File;

        while (true) { 
            LocalTime freshStart = LocalTime.now(ZoneId.of("Asia/Kolkata"));
            LocalTime freshStartSec = freshStart.withNano(0);

            if (freshStartSec.equals(Set)) {
                System.out.println("\nWake Up Wake Up!");
                
                // Create a background thread to handle the continuous beeping
                Thread beepThread = new Thread(() -> {
                    while (alarmRunning) {
                        if (finalMp3 != null) {
                            playMp3Once(finalMp3);
                        } else {
                            java.awt.Toolkit.getDefaultToolkit().beep();
                            try { 
                                Thread.sleep(600); // Wait 0.6 seconds between beeps
                            } catch (Exception e) {
                                // Thread interrupted on stop signal
                            }
                        }
                    }
                });
                
                // Start the background beep loop
                beepThread.start();

                // Main thread stops here and waits for the user to hit Enter
                System.out.println("Press [ENTER] to turn off the alarm...");
                sc.nextLine(); 
                
                // Stop signal received: update flag and close thread execution
                alarmRunning = false;
                beepThread.interrupt();
                System.out.println("Alarm stopped successfully!");
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

    private static void playMp3Once(File mp3File) {
        try (FileInputStream fis = new FileInputStream(mp3File)) {
            javazoom.jl.player.Player player = new javazoom.jl.player.Player(fis);
            player.play();
        } catch (javazoom.jl.decoder.JavaLayerException e) {
            System.err.println("MP3 playback error: " + e.getMessage());
            java.awt.Toolkit.getDefaultToolkit().beep();
        } catch (Exception e) {
            System.err.println("Unexpected playback error: " + e.getMessage());
        }
    }
}