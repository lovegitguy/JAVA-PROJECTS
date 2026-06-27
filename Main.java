// import java.util.Scanner;

// class Son{
//     public static void main(String[] args) {
//         int a, b;
//         Scanner sc= new Scanner(System.in);

//         System.out.println("Enter first number :");
//         a = sc.nextInt();

//         System.out.println("Enter second number :");
//         b = sc.nextInt();

//         try {
//             int c =a/b;
//             System.out.println("Result :"+ c);
//         } catch (ArithmeticException e) {
//             System.out.println("Divison by zero not allowed");
//         }
//         System.out.println("Program continues .");

//         sc.close();
//     }
    
// }

// class Son{
//     public static void main(String[] args) {
//         // int[] arr={1,2,3};

//         // try {
//         //     System.out.println(arr[5]);
//         // } catch (Exception e) {
//         //     System.out.println("Array request out of index");
//         // }
//         // System.out.println("Program executed successfully");

//         // String name= null;
        
//         // System.out.println(name.length());
//         // try {
//         //     System.out.println(name.length());
//         // } catch (Exception e) {
//         //     System.out.println("Null pointer so error!");
//         // }
//         // System.out.println("Program Continues");
//     }
// }

class voteRegistry{
   public int age;
   public void CheckAge(int age) throws ArithmeticException{
    if(age<18){
        System.out.println("You are not eligible to vote :");
    }
    System.out.println("You are eligible to vote :");
   }

}
public class Main {
    public static void main(String[] args) {
        voteRegistry account = new voteRegistry();
        
        try {
            account.CheckAge(11); // Calling the risky method
        } catch (ArithmeticException e) {
            System.out.println("Invalid age");
        }
    }
}

