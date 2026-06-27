class area{
    int l;
    int b;

    public area(int l, int b) {
        this.l = l;
        this.b = b;
    }

    public area() {
       this.l=0;
       this.b=0;
    }

    
    void calArea(){
        System.out.println(l*b);
    }
}

public class Constructors {
public static void main(String[] args) {
    area a= new area(40,10);
    a.calArea();
    
}
}
