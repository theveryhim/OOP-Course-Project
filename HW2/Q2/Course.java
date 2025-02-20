public class Course {
    String Name;
    String Instructor;
    String Code;
    int Day;
    int Hour;
    int Unit;
    Course(String Name,int Unit,String Instructor,int Day,int Hour,String Code){
        this.Name = Name;
        this.Instructor = Instructor;
        this.Code = Code;
        this.Day = Day;
        this.Hour = Hour;
        this.Unit = Unit;
    }
}
