import java.util.ArrayList;
import java.util.List;

public class Student {
    String FirstName;
    String LastName;
    String Id;
    String Year;
    String PhoneNumber;
    int PassedUnits;
    List<Course> SignedUpCourses  = new ArrayList<>();
    int SignedUpUnits;
    int SignedUpCoursesNumber;
    Student(String FirstName,String LastName,String Id){
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Id = Id;
        this.Year = "";
        this.PhoneNumber = "";
        this.PassedUnits = -1;
        this.SignedUpUnits = 0;
        this.SignedUpCoursesNumber = 0;
    }
}
