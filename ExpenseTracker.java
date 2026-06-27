import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Expense {
    double amount;
    String category;
    String date;

    Expense(double amount, String category, String date) {
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    String display() {
        return "Date: " + date +
                " | Category: " + category +
                " | Amount: Rs." + amount;
    }

    String toCSV() {
        return amount + "," + category + "," + date;
    }
}

class ExpenseManager {

    ArrayList<Expense> expenses = new ArrayList<>();

    void addExpense(double amount, String category, String date) {
        expenses.add(new Expense(amount, category, date));
        System.out.println("Expense added successfully.");
    }

    void displayAll() {
        if (expenses.size() == 0) {
            System.out.println("No expenses found.");
            return;
        }

        System.out.println("\nAll Expenses:");

        for (int i = 0; i < expenses.size(); i++) {
            System.out.println((i + 1) + ". " + expenses.get(i).display());
        }
    }

    void monthlyReport(String monthYear) {
        double total = 0;
        int count = 0;

        System.out.println("\nMonthly Report for " + monthYear);

        for (Expense e : expenses) {
            String expMonth = e.date.substring(3);

            if (expMonth.equals(monthYear)) {
                System.out.println(e.display());
                total += e.amount;
                count++;
            }
        }

        if (count == 0) {
            System.out.println("No records found.");
        } else {
            System.out.println("Total Expense: Rs." + total);
            System.out.println("Number of Entries: " + count);
        }
    }

    void highestCategory() {
        if (expenses.size() == 0) {
            System.out.println("No expenses available.");
            return;
        }

        ArrayList<String> categories = new ArrayList<>();

        for (Expense e : expenses) {
            boolean exists = false;

            for (String c : categories) {
                if (c.equalsIgnoreCase(e.category)) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                categories.add(e.category);
            }
        }

        String highestCat = "";
        double highestAmount = 0;

        System.out.println("\nCategory Wise Spending:");

        for (String cat : categories) {
            double total = 0;

            for (Expense e : expenses) {
                if (e.category.equalsIgnoreCase(cat)) {
                    total += e.amount;
                }
            }

            System.out.println(cat + " : Rs." + total);

            if (total > highestAmount) {
                highestAmount = total;
                highestCat = cat;
            }
        }

        System.out.println("Highest Spending Category: " +
                highestCat + " (Rs." + highestAmount + ")");
    }

    void saveToFile(String fileName) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));

            for (Expense e : expenses) {
                bw.write(e.toCSV());
                bw.newLine();
            }

            bw.close();
            System.out.println("Data saved successfully.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    void loadFromFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            expenses.clear();

            String line;
            int count = 0;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length == 3) {
                    double amount = Double.parseDouble(data[0]);
                    String category = data[1];
                    String date = data[2];

                    expenses.add(new Expense(amount, category, date));
                    count++;
                }
            }

            br.close();
            System.out.println(count + " expense(s) loaded.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

public class ExpenseTracker {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ExpenseManager tracker = new ExpenseManager();

        String fileName = "expenses.csv";
        int choice;

        System.out.println("Personal Expense Tracker");

        do {
            System.out.println("\n1. Add Expense");
            System.out.println("2. Display Expenses");
            System.out.println("3. Monthly Report");
            System.out.println("4. Highest Expense Category");
            System.out.println("5. Save Data");
            System.out.println("6. Load Data");
            System.out.println("7. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter amount: ");
                    double amount = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Enter category: ");
                    String category = sc.nextLine();

                    System.out.print("Enter date (DD/MM/YYYY): ");
                    String date = sc.nextLine();

                    tracker.addExpense(amount, category, date);
                    break;

                case 2:
                    tracker.displayAll();
                    break;

                case 3:
                    System.out.print("Enter month and year (MM/YYYY): ");
                    String monthYear = sc.nextLine();
                    tracker.monthlyReport(monthYear);
                    break;

                case 4:
                    tracker.highestCategory();
                    break;

                case 5:
                    tracker.saveToFile(fileName);
                    break;

                case 6:
                    tracker.loadFromFile(fileName);
                    break;

                case 7:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 7);

        sc.close();
    }
} 
