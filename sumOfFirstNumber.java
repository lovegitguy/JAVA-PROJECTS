import java.util.Scanner;
public class sumOfFirstNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        int sum=0;
        System.out.println("Enter the number of elements");

        n= sc.nextInt();

        for (int i = 0; i < n; i++) {
             sum+=i;
        }

        System.out.println(sum);

    }
}
