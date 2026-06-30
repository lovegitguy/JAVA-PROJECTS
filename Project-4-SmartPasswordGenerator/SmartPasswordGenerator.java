import java.util.Scanner;

public class SmartPasswordGenerator{

    static String generatePassword(String name, String PAN, String DOB){

        String namePart = "";

        if(name.length() >= 3){
            namePart = name.substring(0,3).toUpperCase();
        }

        else{
            namePart = name.toUpperCase();
        }

        String PANpart = "";

        if(PAN.length() >= 4){
            PANpart = PAN.substring(PAN.length()-4);
        }

        else{
            PANpart = PAN;
        }

        String DOBcleaning = DOB.replace("/","").replace("-","");

        String DOBpart = "";

        if(DOBcleaning.length() >= 6){
            DOBpart = DOBcleaning.substring(0,2) + DOBcleaning.substring(4,6);
        }

        else{
            DOBpart = DOBcleaning;
        }

        String specialPart = "";

        int len = name.length();

        if(len % 3 == 0){
            specialPart = "@#";
        }

        else if(len % 3 == 1){
            specialPart = "&*";
        }

        else{
            specialPart = "^!";
        }

        String password = namePart + PANpart + DOBpart + specialPart;

        return password;
    }

    static String checkStrength(String password){

        int score = 0;

        if(password.length() >= 12){
            score = score + 2;
        }

        else if(password.length() >= 8){
            score = score + 1;
        }

        boolean hasUpper = false;

        for(int i = 0; i < password.length(); i++){

            if(Character.isUpperCase(password.charAt(i))){
                hasUpper = true;
            }
        }

        if(hasUpper){
            score = score + 1;
        }

        boolean hasLower = false;

        for(int i = 0; i < password.length(); i++){

            if(Character.isLowerCase(password.charAt(i))){
                hasLower = true;
            }
        }

        if(hasLower){
            score = score + 1;
        }

        boolean hasDigit = false;

        for(int i = 0; i < password.length(); i++){

            if(Character.isDigit(password.charAt(i))){
                hasDigit = true;
            }
        }

        if(hasDigit){
            score = score + 1;
        }

        String specials = "!@#$%^&*()-_=+[]{}|;:,.<>?";

        boolean hasSpecial = false;

        for(int i = 0; i < password.length(); i++){

            if(specials.indexOf(password.charAt(i)) != -1){
                hasSpecial = true;
            }
        }

        if(hasSpecial){
            score = score + 2;
        }

        if(score >= 6){
            return "Strong";
        }

        else if(score >= 4){
            return "Medium";
        }

        else{
            return "Weak";
        }
    }

    static void displayResult(String name, String password){

        System.out.println("--------------------------------------------");
        System.out.println("User       : " + name);
        System.out.println("Password   : " + password);
        System.out.println("Strength   : " + checkStrength(password));
        System.out.println("--------------------------------------------");
    }

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        System.out.println("============================================");
        System.out.println("     SMART PASSWORD GENERATOR - JAVA        ");
        System.out.println("============================================");

        System.out.print("How many users do you want to test? : ");
        int n = sc.nextInt();

        sc.nextLine();

        for(int i = 1; i <= n; i++){

            System.out.println("\n--- User " + i + " ---");

            System.out.print("Enter Name          : ");
            String name = sc.nextLine();

            System.out.print("Enter PAN Number    : ");
            String pan = sc.nextLine();

            System.out.print("Enter Date of Birth (DD/MM/YYYY) : ");
            String dob = sc.nextLine();

            String password = generatePassword(name, pan, dob);

            displayResult(name, password);
        }

        System.out.println("\nThank you for using Smart Password Generator!");

        sc.close();
    }
}