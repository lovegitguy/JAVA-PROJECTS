class Box{
    double width;
    double length;
    double depth;

    public Box(double width, double length, double depth) {
        this.width = width;
        this.length = length;
        this.depth = depth;
    }

    double printvol(){
        return length*width*depth;
    }
}
public class Classdemo {
    public static void main(String[] args) {
        Box B= new Box(45, 56, 12);
        double r= B.printvol();
        System.err.println(r);
    }
}
