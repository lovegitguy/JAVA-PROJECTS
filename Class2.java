class human{
    int age;
    String race;

    public human(int a, String r){
        this.age= a;
        this.race= r;
    }

    void about(){
     System.out.println("Age"+" "+age+"," +"race"+" "+race);
    }
}

class male extends human{
    String gender;

    public male(String gender, int a, String r) {
        super(a, r);
        this.gender = gender;
    }

    @Override
    void about(){
     System.out.println("Age"+" "+age+"," +"race"+" "+race);
     System.out.println("Gender"+" "+gender);
    }
    
}

class female extends male{
    String pregnant;
    public female(String pregnant,String gender, int a, String r)
    {
        super(gender, a, r);
        this.pregnant= pregnant;
    }
     @Override
    void about(){
     System.out.println("Age"+" "+age+"," +"race"+" "+race);
     System.out.println("Gender"+" "+gender);
     System.out.println("Pregnant"+" "+pregnant);
    }
}


public class Class2 {
    public static void main(String[] args) {
        human h= new human(45, "African");
        h.about();

        male m= new male("Male", 56, "American;") ;
        m.about();

        female f= new female("y","Female", 50, "American");
        f.about();

    }
}
