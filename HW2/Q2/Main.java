import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Course> Courses = new ArrayList<>();
        List<Student> Students = new ArrayList<>();
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            String[] s = sc.nextLine().split("\\,+");
            Courses.add(new Course(s[0], Integer.parseInt(s[1]),
                    s[2], Integer.parseInt(s[3]), Integer.parseInt(s[4]), s[5]));
        }
        boolean end = false;
        boolean LoggedIn = false;
        Student CurrentStudent = new Student("", "", "");
        while (!end) {
            String[] s = sc.nextLine().split("\\s+");
            String out = "";
            boolean invalid = false;
            switch (s[0]) {
                case "-end": {
                    if (s.length == 1) end = true;
                    else invalid = true;
                }
                break;
                case "-signin": {
                    if (s.length == 4) {
                        String FirstName = s[1];
                        if (65 <= (int) FirstName.charAt(0) & (int) FirstName.charAt(0) <= 90) {
                            String LastName = s[2];
                            if (65 <= (int) LastName.charAt(0) & (int) LastName.charAt(0) <= 90) {
                                String Id = s[3];
                                if (Id.length() == 9 & Id.matches("[0-9]+")) {
                                    boolean found = false;
                                    for (Student i : Students)
                                        if (i.FirstName.equals(FirstName) & i.LastName.equals(LastName) & i.Id.equals(Id)) {
                                            out = "A student with this name exist !";
                                            found = true;
                                            break;
                                        }
                                    if (!found) {
                                        out = "Student with name \"" + FirstName + " " + LastName+ "\" and ID number \"" + Id + "\" created !";
                                        Students.add(new Student(FirstName, LastName, Id));
                                    }
                                }
                                else
                                    out = "The ID number contains characters other than numbers or is too short !";
                            }
                            else
                                out = "Last name first character should be uppercase !";
                        } else
                            out = "First name first character should be uppercase !";
                    }
                    else invalid = true;
                }
                break;
                case "-login": {
                    if (s.length == 4) {
                        String FirstName = s[1];
                        String LastName = s[2];
                        String Id = s[3];
                        boolean found = false;
                        for (Student i : Students)
                            if (i.FirstName.equals(FirstName) & i.LastName.equals(LastName) & i.Id.equals(Id)) {
                                out = "Welcome " + "\"" + FirstName + "\"";
                                CurrentStudent = i;
                                LoggedIn = true;
                                found = true;
                                break;
                            }
                        if (!found)
                            out = "There is no student with this name and ID !";
                    }
                    else invalid = true;
                }
                break;
                case "-logout": {
                    if (LoggedIn & s.length == 1) {
                        out = "Logged out successfully !";
                        LoggedIn = false;
                        CurrentStudent = null;
                    }
                    else
                        invalid = true;
                }
                break;
                case "-edit": {
                    if (s[1].equals("profile") & s.length == 5 & LoggedIn) {
                        String Year = s[2];
                        String PhoneNumber = s[3];
                        String PassedUnits = s[4];
                        if (Year.matches("[0-9]+")) {
                            if (Year.equals("1402") | Year.equals("1401")) {
                                if (PhoneNumber.matches("[0-9]+") & PhoneNumber.length() == 10) {
                                    if (PassedUnits.matches("[0-9]+")) {
                                        if (10 < Integer.parseInt(PassedUnits) & Integer.parseInt(PassedUnits) < 90) {
                                            out = "Profile edited successfully !";
                                            CurrentStudent.Year = Year;
                                            CurrentStudent.PhoneNumber = PhoneNumber;
                                            CurrentStudent.PassedUnits = Integer.parseInt(PassedUnits);
                                        }
                                        else
                                            out = "Units must be between 10 and 90 !";
                                    }
                                    else
                                        out = "Units must be a number !";
                                }
                                else
                                    out = "Phone must be a number and it's length should be 10 !";
                            }
                            else
                                out = "Year must be 1401 or 1402 !";
                        }
                        else
                            out = "Year must be a number !";
                    }
                    else
                        invalid = true;
                }
                break;
                case "-show": {
                    switch (s[1]) {
                        case "department": {
                            if (s[2].equals("list") & s.length == 3) {
                                out = "Department List is :\n";
                                int j = 0;
                                for (Course i : Courses) {
                                    j++;
                                    out = out + j + ". " + i.Name + " - "
                                            + i.Code + " - " + i.Instructor + " - Time : ";
                                    if (i.Hour == 1) {
                                        out = out + "7:30 to 9:00 - ";
                                        if (i.Day == 1) {
                                            out = out + "Day : Saturday / Monday\n";
                                        }
                                        else if (i.Day == 2) {
                                            out = out + "Day : Sunday / Tuesday\n";
                                        }
                                    }
                                    else if (i.Hour == 2) {
                                        out = out + "9:00 to 10:30 - ";
                                        if (i.Day == 1) {
                                            out = out + "Day : Saturday / Monday\n";
                                        }
                                        else if (i.Day == 2) {
                                            out = out + "Day : Sunday / Tuesday\n";
                                        }
                                    }
                                    else if (i.Hour == 3) {
                                        out = out + "10:30 to 12:00 - ";
                                        if (i.Day == 1) {
                                            out = out + "Day : Saturday / Monday\n";
                                        }
                                        else if (i.Day == 2) {
                                            out = out + "Day : Sunday / Tuesday\n";
                                        }
                                    }
                                }
                                out = out.substring(0, out.length() - 1);
                            }
                            else
                                invalid = true;
                        }
                        break;
                        case "my": {
                            if (s[2].equals("list") & s.length == 3 & LoggedIn) {
                                if (CurrentStudent.SignedUpUnits == 0)
                                    out = "List is empty !";
                                else {
                                    out = "My List is :\n";
                                    int j = 0;
                                    for (Course i : CurrentStudent.SignedUpCourses) {
                                        j++;
                                        out = out + j + ". " + i.Name + " - " + i.Code + "\n";
                                    }
                                    out = out.substring(0, out.length() - 1);
                                }
                            }
                            else
                                invalid = true;
                        }
                        break;
                        case "profile": {
                            if (s.length == 2 & LoggedIn) {
                                if (CurrentStudent.PassedUnits == -1)
                                    out = "Please first edit your profile !";
                                else {
                                    out = "First name : " + CurrentStudent.FirstName + "\n" +
                                            "Last name : " + CurrentStudent.LastName + "\n" +
                                            "Student ID : " + CurrentStudent.Id + "\n" +
                                            "Entrance year : " + CurrentStudent.Year + "\n" +
                                            "Phone number : " + CurrentStudent.PhoneNumber + "\n" +
                                            "Passed units : " + CurrentStudent.PassedUnits + "\n" +
                                            "Units limit : " + (20 - CurrentStudent.SignedUpUnits) + "\n" +
                                            "Courses number : " + CurrentStudent.SignedUpCoursesNumber;
                                }
                            }
                            else
                                invalid = true;
                        }
                    }
                }
                break;
                case "-select": {
                    if (s.length == 2 & LoggedIn) {
                        if (CurrentStudent.PassedUnits != -1) {
                            boolean found = false;
                            for (Course i : Courses) {
                                if (i.Code.equals(s[1])) {
                                    found = true;
                                    if (CurrentStudent.SignedUpUnits + i.Unit > 20) {
                                        out = "Unit Limits Error !";
                                        break;
                                    }
                                    else {
                                        for (Course j : CurrentStudent.SignedUpCourses) {
                                            if (s[1].substring(0, 5).equals(j.Code.substring(0, 5))) {
                                                out = "Same Course Code Error !";
                                                break;
                                            }
                                            else if (j.Day == i.Day & j.Hour == i.Hour) {
                                                out = "Same Time Error !";
                                                break;
                                            }
                                        }
                                        if (out.equals("")) {
                                                out = "Course added successfully !";
                                                CurrentStudent.SignedUpCourses.add(i);
                                                CurrentStudent.SignedUpUnits += i.Unit;
                                                CurrentStudent.SignedUpCoursesNumber++;
                                                break;
                                        }
                                    }
                                }
                            }
                            if (!found)
                                out = "Invalid Code !";
                        }
                        else
                            out = "Please first edit your profile !";
                    }
                    else if (LoggedIn & s[1].equals("all")) {
                        if (CurrentStudent.PassedUnits != -1) {
                            if (CurrentStudent.SignedUpUnits == 20)
                                out = "You have selected 20 units already !";
                            else {
                                String[] x = new String[s.length - 2];
                                System.arraycopy(s, 2, x, 0, s.length - 2);
                                List<String> DesiredCourseCodes = new ArrayList<>(Arrays.asList(x));
                                List<String> Invalid = new ArrayList<>();
                                List<String> Limit = new ArrayList<>();
                                List<String> SameCode = new ArrayList<>();
                                List<String> SameTime = new ArrayList<>();
                                for (String i:DesiredCourseCodes) {
                                    if (!Valid(Courses,i))
                                        Invalid.add(i);
                                    else if (Limit(Courses, i, CurrentStudent.SignedUpUnits))
                                        Limit.add(i);
                                    else if (SameCode(CurrentStudent.SignedUpCourses, i))
                                        SameCode.add(i);
                                    else if (SameTime(CurrentStudent.SignedUpCourses,Courses,i))
                                        SameTime.add(i);
                                    else {
                                        for (Course j:Courses){
                                            if (j.Code.equals(i)){
                                                CurrentStudent.SignedUpCourses.add(j);
                                                CurrentStudent.SignedUpCoursesNumber++;
                                                CurrentStudent.SignedUpUnits += j.Unit;
                                                out = out + "Course " + i + " added successfully !\n";
                                                break;
                                            }
                                        }
                                    }
                                }
                                if (Invalid.size() != 0) {
                                    out = out + "Invalid Codes -> ";
                                    for (String i : Invalid)
                                        out = out + i + " ";
                                    out = out + "\n";
                                }
                                if (Limit.size() != 0) {
                                    out = out + "Unit Limit Codes -> ";
                                    for (String i : Limit)
                                        out = out + i + " ";
                                    out = out + "\n";
                                }
                                if (SameCode.size() != 0) {
                                    out = out + "Same Course Codes -> ";
                                    for (String i : SameCode)
                                        out = out + i + " ";
                                    out = out + "\n";
                                }
                                if (SameTime.size() != 0) {
                                    out = out + "Same Time Codes -> ";
                                    for (String i : SameTime)
                                        out = out + i + " ";
                                    out = out + "\n";
                                }
                                out = out.substring(0, out.length() - 1);
                            }
                        }
                        else
                            out = "Please first edit your profile !";
                    }
                    else invalid = true;
                }
                break;
                case "-delete": {
                    if (s.length == 3 & LoggedIn & s[1].equals("course")) {
                        boolean found = false;
                        for (Course i : CurrentStudent.SignedUpCourses)
                            if (i.Code.equals(s[2])) {
                                out = "Course deleted successfully !";
                                CurrentStudent.SignedUpCourses.remove(i);
                                CurrentStudent.SignedUpUnits -= i.Unit;
                                CurrentStudent.SignedUpCoursesNumber--;
                                found = true;
                                break;
                            }
                        if (!found)
                            out = "Course with this code does not exist in your list !";
                    }
                    else
                        invalid = true;
                }
                break;
                default:invalid = true;
            }
            if (invalid & out.equals(""))
                out = "Invalid command !";
            if (!end)
                System.out.println(out);
        }
    }

    public static boolean Valid(List<Course> Courses,String DesiredCourseCode) {
        boolean Valid = true;
        boolean found = false;
        for (Course j : Courses)
            if (j.Code.equals(DesiredCourseCode)) {
                found = true;
                break;
            }
        if (!found)
            Valid = false;
        return Valid;
    }

    public static boolean Limit(List<Course> Courses,String DesiredCourseCode, int SignedUpUnits) {
        boolean Limit = false;
            for (Course j : Courses)
                if (j.Code.equals(DesiredCourseCode) & j.Unit + SignedUpUnits > 20) {
                    Limit = true;
                    break;
                }
        return Limit;
    }

    public static boolean SameCode(List<Course> SignedUpCourses,String DesiredCourseCode) {
        boolean SameCode = false;
            for (Course j : SignedUpCourses)
                if (j.Code.substring(0, 5).equals(DesiredCourseCode.substring(0, 5))) {
                    SameCode = true;
                    break;
                }
        return SameCode;
    }

    public static boolean SameTime(List<Course> SignedUpCourses, List<Course> Courses,String DesiredCourseCode) {
        boolean SameTime = false;
            for (Course j : Courses)
                if (j.Code.equals(DesiredCourseCode))
                    for (Course k : SignedUpCourses) {
                        if (j.Day == k.Day & j.Hour == k.Hour) {
                            SameTime = true;
                            break;
                        }
                    }
        return SameTime;
    }
}