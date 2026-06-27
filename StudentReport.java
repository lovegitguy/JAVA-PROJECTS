

class Student{
   private String name;
    private int marks;
    public 
    Student(){
        this.name="Unknown";
        this.marks=0;
    }

    public Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }

     String getGrade(){
       if (marks >= 90) return "A";
        else if (marks >= 75) return "B";
        else if (marks >= 50) return "C";
        else return "Fail";
        
    }

    void updateMarks(int newMarks){
        if (newMarks >= 0 && newMarks <= 100) {
            this.marks = newMarks;
        } else {
            System.out.println("Invalid marks");
        }
    }

    void display(){
        System.out.println("Name:"+" "+name+","+" "+"Marks:"+" "+marks+"Grade:"+" "+getGrade());

    }

   
}
public class StudentReport {
    public static void main(String[] args) {
        Student S1= new Student();
        S1.display();

        Student S2= new Student("Amit",85);
        S2.getGrade();
        S2.display();

        Student S3= new Student("Anjali",45);
        S3.getGrade();
        S3.display();

        S3.updateMarks(75);
        S3.display();
    }
}
