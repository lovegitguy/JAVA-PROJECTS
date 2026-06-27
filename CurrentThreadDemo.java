public class CurrentThreadDemo {
    public static void main(String[] args) {
    Thread t = Thread.currentThread();
    System.out.println("Current Thread :"+t);

    //Changing the name of the thread
    t.setName("My Thread");
        System.out.println("After name changed");

        try {
            for(int n=5;n>0;n--){
                System.out.print( n );
                Thread.sleep(1000);
            }
        } catch(InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }

    }
}
