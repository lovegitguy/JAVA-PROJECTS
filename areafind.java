
@SuppressWarnings("unused")
 class class1 {
    private int length;
    private int breadth;
    private int height;

    class1(int l, int b, int h){
        this.length= l;
        this.breadth= b;
        this.height =h;
    }

    class1(int l){
        this.length=l;
        this.breadth=l;
    }

    int rectanglearea(){
        return length*breadth*height;
    }

    int squarearea(){
        return length*breadth;
    }
}

public class areafind
{
    public static void main(String[] args) {
        class1 C1= new class1(20);
        class1 C2= new class1(10,20,30);
        System.out.println(C1.squarearea());
        System.out.println(C2.rectanglearea());
    }
}
