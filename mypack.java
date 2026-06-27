public class mypack {
    static class Balance{
        String name;
        double bal;

        Balance(String n, double b){
            name=n;
            bal=b;
        }
        void show () {
            if (bal<0)
                System.out.print("-->");
            System.out.println(name+":$"+bal);
        }
    }

    class AccountBalance{
        public static void main(String[] args) {
            Balance current [] = new Balance[3];
            current [0]= new Balance("Narendra Modi", 123.5);
            current [1]= new Balance("Amit Shah", 157.02);

            for (int i = 0; i < 2; i++) {
                current[i].show();
            }
        }
    }

}
