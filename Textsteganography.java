import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Textsteganography {

    static final char ZERO = '\u200B';
    static final char ONE = '\u200C';
    static final char END = '\u200D';

    static String charToBinary(char c) {
        int ascii = c;
        String bits = "";

        for (int i = 0; i < 8; i++) {
            bits = (ascii % 2) + bits;
            ascii /= 2;
        }

        return bits;
    }

    static char binaryToChar(String binary) {
        int value = 0;

        for (int i = 0; i < binary.length(); i++) {
            value = value * 2 + (binary.charAt(i) - '0');
        }

        return (char) value;
    }

    static String encode(String coverText, String secretMessage) {

        String hiddenBits = "";

        for (int i = 0; i < secretMessage.length(); i++) {
            hiddenBits += charToBinary(secretMessage.charAt(i));
        }

        String hiddenData = "";

        for (int i = 0; i < hiddenBits.length(); i++) {
            if (hiddenBits.charAt(i) == '0') {
                hiddenData += ZERO;
            } else {
                hiddenData += ONE;
            }
        }

        hiddenData += END;

        int pos = coverText.indexOf(' ');

        if (pos == -1) {
            pos = coverText.length();
        }

        return coverText.substring(0, pos)
                + hiddenData
                + coverText.substring(pos);
    }

    static String decode(String encodedText) {

        String bits = "";

        for (int i = 0; i < encodedText.length(); i++) {
            char ch = encodedText.charAt(i);

            if (ch == END) {
                break;
            }

            if (ch == ZERO) {
                bits += "0";
            } else if (ch == ONE) {
                bits += "1";
            }
        }

        if (bits.length() == 0) {
            return "No hidden message found";
        }

        String secret = "";

        for (int i = 0; i + 8 <= bits.length(); i += 8) {
            String part = bits.substring(i, i + 8);
            secret += binaryToChar(part);
        }

        return secret;
    }

    static String getVisibleText(String text) {

        String result = "";

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (ch != ZERO && ch != ONE && ch != END) {
                result += ch;
            }
        }

        return result;
    }

    static void saveToFile(String fileName, String content) {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            bw.write(content);
            bw.close();

            System.out.println("File saved successfully.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static String loadFromFile(String fileName) {

        String content = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            String line;

            while ((line = br.readLine()) != null) {
                content += line + "\n";
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return content;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String encodedText = "";
        int choice;

        System.out.println("TEXT STEGANOGRAPHY SYSTEM");

        do {

            System.out.println("\n1. Encode Message");
            System.out.println("2. Decode Message");
            System.out.println("3. Save Encoded Text");
            System.out.println("4. Load and Decode");
            System.out.println("5. Verify Visible Text");
            System.out.println("6. Run Test Cases");
            System.out.println("7. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter cover text: ");
                    String cover = sc.nextLine();

                    System.out.print("Enter secret message: ");
                    String secret = sc.nextLine();

                    encodedText = encode(cover, secret);

                    System.out.println("\nMessage encoded successfully.");
                    System.out.println(encodedText);

                    break;

                case 2:

                    if (encodedText.equals("")) {
                        System.out.println("No encoded text available.");
                    } else {
                        System.out.println("Hidden message: " +
                                decode(encodedText));
                    }

                    break;

                case 3:

                    if (encodedText.equals("")) {
                        System.out.println("Nothing to save.");
                    } else {
                        System.out.print("Enter file name: ");
                        String file = sc.nextLine();
                        saveToFile(file, encodedText);
                    }

                    break;

                case 4:

                    System.out.print("Enter file name: ");
                    String file = sc.nextLine();

                    String loaded = loadFromFile(file);

                    if (!loaded.equals("")) {
                        System.out.println("Hidden message: "
                                + decode(loaded));
                    }

                    break;

                case 5:

                    if (encodedText.equals("")) {
                        System.out.println("No encoded text available.");
                    } else {
                        System.out.println("Visible Text:");
                        System.out.println(getVisibleText(encodedText));
                    }

                    break;

                case 6:

                    String[] covers = {
                            "The weather is nice today.",
                            "Please check the document.",
                            "Happy birthday to you."
                    };

                    String[] secrets = {
                            "HELLO",
                            "JAVA",
                            "SECRET123"
                    };

                    System.out.println("\nRunning Test Cases...\n");

                    for (int i = 0; i < covers.length; i++) {

                        String enc = encode(covers[i], secrets[i]);
                        String dec = decode(enc);

                        System.out.println("Test " + (i + 1));
                        System.out.println("Decoded: " + dec);
                        System.out.println("Passed: "
                                + dec.equals(secrets[i]));
                        System.out.println();
                    }

                    break;

                case 7:
                    System.out.println("Program closed.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 7);

        sc.close();
    }
}