import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class ProgramController {
    private Library library;
    public void run(){
        Scanner sc = new Scanner(System.in);
        boolean end = false;
        boolean Library = false;
        while (!end){
            String command = sc.nextLine();
            if (Library & command.matches("end"))
                end = true;
            else if (command.matches("Create Library")){
                System.out.println("Library created successfully");
                library = new Library();
                Library = true;
            }
            else if (Library & command.matches("Add account Account-Name (?<Name>[a-zA-Z\\s]+)" +
                    " Account-Number (?<ID>\\d{5})")) {
                Matcher matcher = getCommandMatcher(command, "Add account Account-Name" +
                        " (?<Name>[a-zA-Z\\s]+) Account-Number (?<ID>\\d{5})");
                matcher.find();
                addAccount(matcher);
            }
            else if (Library & command.matches("Increase balance Account-Number (?<ID>\\d{5})" +
                    " Amount (?<Amount>\\d+)")){
                Matcher matcher = getCommandMatcher(command,"Increase balance Account-" +
                        "Number (?<ID>\\d{5}) Amount (?<Amount>\\d+)");
                matcher.find();
                increaseBalance(matcher);
            }
            else if(Library & command.matches("Cashout Account-Number (?<ID>\\d{5})")){
                Matcher matcher = getCommandMatcher(command, "Cashout " +
                        "Account-Number (?<ID>\\d{5})");
                matcher.find();
                cashOut(matcher);
            }
            else if (Library & command.matches("Add book Book-Name (?<Name>[a-zA-Z0-9\\s]+)" +
                    " ISBN (?<ISBN>\\d+) Author (?<Author>[a-zA-Z\\s]+) Language (?<Language>[a-zA-Z]+) " +
                    "Price (?<Price>\\d+) Amount (?<Amount>\\d+)")){
                Matcher matcher = getCommandMatcher(command,
                        "Add book Book-Name (?<Name>[a-zA-Z0-9\\s]+) " +
                                "ISBN (?<ISBN>\\d+) Author (?<Author>[a-zA-Z\\s]+) Language " +
                                "(?<Language>[a-zA-Z]+) Price (?<Price>\\d+) Amount (?<Amount>\\d+)");
                matcher.find();
                addBook(matcher);
            }
            else if (Library & command.matches("Add magazine Magazine-Name (?<Name>[a-zA-Z0-9\\s]+)" +
                " ISSN (?<ISSN>\\d+) Author (?<Author>[a-zA-Z\\s]+) Language (?<Language>[a-zA-Z]+) " +
                    "Price (?<Price>\\d+) Amount (?<Amount>\\d+) Number (?<Number>\\d+)")){
                Matcher matcher = getCommandMatcher(command,
                        "Add magazine Magazine-Name (?<Name>[a-zA-Z0-9\\s]+)" +
                                " ISSN (?<ISSN>\\d+) Author (?<Author>[a-zA-Z\\s]+) Language (?<Language>[a-zA-Z]+) " +
                                "Price (?<Price>\\d+) Amount (?<Amount>\\d+) Number (?<Number>\\d+)");
                matcher.find();
                addMagazine(matcher);
            }
            else if(Library & command.matches("Borrow book ISBN (?<ISBN>\\d+) Account-Number " +
                    "(?<ID>\\d{5})")){
                Matcher matcher = getCommandMatcher(command,"Borrow book ISBN " +
                        "(?<ISBN>\\d+) Account-Number (?<ID>\\d{5})");
                matcher.find();
                borrowBook(matcher);
            }
            else if(Library & command.matches("Borrow magazine ISSN (?<ISSN>\\d+) Account-Number " +
                    "(?<ID>\\d{5}) Number (?<Num>\\d+)")){
                Matcher matcher = getCommandMatcher(command,"Borrow magazine ISSN (?<ISSN>\\d+) Account-Number " +
                        "(?<ID>\\d{5}) Number (?<Num>\\d+)");
                matcher.find();
                borrowMagazine(matcher);
            }
            else if (Library & command.matches("Return book ISBN (?<ISBN>\\d+) Account-Number " +
                    "(?<ID>\\d{5})")) {
                Matcher matcher = getCommandMatcher(command,"Return book ISBN (?<ISBN>\\d+)" +
                        " Account-Number (?<ID>\\d{5})");
                matcher.find();
                returnBook(matcher);
            }
            else if (Library & command.matches("Return magazine ISSN (?<ISSN>\\d+) Account-Number " +
                    "(?<ID>\\d{5}) Number (?<Num>\\d+)")){
                Matcher matcher = getCommandMatcher(command,"Return magazine ISSN (?<ISSN>\\d+) Account-Number " +
                        "(?<ID>\\d{5}) Number (?<Num>\\d+)");
                matcher.find();
                returnMagazine(matcher);
            }
            else if (Library & command.matches("Donate book Account-Number (?<ID>\\d{5})" +
                    " Book-Name (?<Name>[a-zA-Z0-9\\s]+) ISBN (?<ISBN>\\d+) Author (?<Author>[a-zA-Z\\s]+)" +
                    " Language (?<Language>[a-zA-Z]+) Price (?<Price>\\d+) Amount (?<Amount>\\d+)")) {
                Matcher matcher = getCommandMatcher(command, "Donate book Account-Number" +
                        " (?<ID>\\d{5}) Book-Name (?<Name>[a-zA-Z0-9\\s]+) ISBN (?<ISBN>\\d+) Author" +
                        " (?<Author>[a-zA-Z\\s]+) Language (?<Language>[a-zA-Z]+) Price (?<Price>\\d+)" +
                        " Amount (?<Amount>\\d+)");
                matcher.find();
                donateBook(matcher);
            }
            else if (Library & command.matches("Return magazine and borrow next ISSN (?<ISSN>\\d+)" +
                    " Account-Number (?<ID>\\d{5}) Number (?<Num>\\d+)")){
                Matcher matcher = getCommandMatcher(command,"Return magazine and borrow next" +
                        " ISSN (?<ISSN>\\d+) Account-Number (?<ID>\\d{5}) Number (?<Num>\\d+)");
                matcher.find();
                returnMagazineAndBorrowNext(matcher);
            }
            else if (Library & command.matches("Print books"))
                printBooks();
            else if (Library & command.matches("Print magazines"))
                printMagazines();
            else if(Library)
                System.out.println("invalid command");
            else
                System.out.println("You have to create the library first");
        }
    }
    private Matcher getCommandMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }
    private void addAccount(Matcher matcher) {
        int tempID = Integer.parseInt(matcher.group("ID"));
        if (library.getMemberByID(tempID) != null) {
            System.out.println("A member with this ID already exists");
            return;
        }
        library.addMember(new Member(matcher.group("Name"), Integer.parseInt(matcher.group("ID"))));
        System.out.println("Account created successfully");
    }
    private void increaseBalance(Matcher matcher){
        int tempID = Integer.parseInt(matcher.group("ID"));
        if (library.getMemberByID(tempID) == null) {
            System.out.println("No member with this ID exists");
            return;
        }
        library.getMemberByID(tempID).addBalance(Integer.parseInt(matcher.group("Amount")));
        System.out.println("Balance increased successfully");
    }
    private void cashOut(Matcher matcher){
        int tempID = Integer.parseInt(matcher.group("ID"));
        if (library.getMemberByID(tempID) == null) {
            System.out.println("No member with this ID exists");
            return;
        }
        String out = library.getMemberByID(tempID).getName() +
                " cashed out successfully. Amount: " +
                library.getMemberByID(tempID).getBalance();
        System.out.println(out);
        library.getMemberByID(tempID).nullifyBalance();
    }
    private void addBook(Matcher matcher){
        String Name = matcher.group("Name");
        String Author = matcher.group("Author");
        String Language = matcher.group("Language");
        int ISBN = Integer.parseInt(matcher.group("ISBN"));
        int Price = Integer.parseInt(matcher.group("Price"));
        int Amount = Integer.parseInt(matcher.group("Amount"));
        for (int i = 0;i<Amount;i++)
            library.addBook(new Book(Name , ISBN , Author , Language , Price));
        System.out.println(Amount + " books were added to the library successfully");
    }
    private void addMagazine(Matcher matcher){
        String Name = matcher.group("Name");
        String Author = matcher.group("Author");
        String Language = matcher.group("Language");
        int ISSN = Integer.parseInt(matcher.group("ISSN"));
        int Price = Integer.parseInt(matcher.group("Price"));
        int Amount = Integer.parseInt(matcher.group("Amount"));
        int Number = Integer.parseInt(matcher.group("Number"));
        for (int i = 0;i<Amount;i++)
            library.addMagazine(new Magazine(Name , ISSN , Author , Language , Price ,Number));
        System.out.println(Amount + " magazines were added to the library successfully");
    }
    private void borrowBook(Matcher matcher){
        int tempID = Integer.parseInt(matcher.group("ID"));
        int tempISBN = Integer.parseInt(matcher.group("ISBN"));
        if (library.getMemberByID(tempID) == null)
            System.out.println("No member with this ID exists");
        else if (library.getBook(tempISBN) == null)
            System.out.println("No book with this ISBN was found in the library");
        else {
            int tempPrice = library.getBook(tempISBN).getPrice();
            int tempBalance = library.getMemberByID(tempID).getBalance();
            if (tempBalance < tempPrice) {
                System.out.println("The member's balance is not enough");
                return;
            }
            System.out.println( library.getBook(tempISBN).getName() + " was borrowed by " +
                    library.getMemberByID(tempID).getName() + " successfully");
            library.getMemberByID(tempID).addBalance(-tempPrice);
            library.getMemberByID(tempID).borrowBook(library.getBook(tempISBN));
            library.removeBook(library.getBook(tempISBN));
        }
    }
    private void borrowMagazine(Matcher matcher){
        int tempID = Integer.parseInt(matcher.group("ID"));
        int tempISSN = Integer.parseInt(matcher.group("ISSN"));
        int tempNum = Integer.parseInt(matcher.group("Num"));
        if (library.getMemberByID(tempID) == null)
            System.out.println("No member with this ID exists");
        else if (library.getMagazine(tempISSN,tempNum) == null)
            System.out.println("No magazine with this ISSN and number was found in the library");
        else {
            int tempPrice = library.getMagazine(tempISSN,tempNum).getPrice();
            int tempBalance = library.getMemberByID(tempID).getBalance();
            if (tempBalance < tempPrice) {
                System.out.println("The member's balance is not enough");
                return;
            }
            System.out.println( library.getMagazine(tempISSN,tempNum).getName() + " was borrowed by " +
                    library.getMemberByID(tempID).getName() + " successfully");
            library.getMemberByID(tempID).addBalance(-tempPrice);
            library.getMemberByID(tempID).borrowMagazine(library.getMagazine(tempISSN,tempNum));
            library.removeMagazine(library.getMagazine(tempISSN,tempNum));
        }
    }
    private void returnBook(Matcher matcher){
        int tempID = Integer.parseInt(matcher.group("ID"));
        int tempISBN = Integer.parseInt(matcher.group("ISBN"));
        if (library.getMemberByID(tempID) == null)
            System.out.println("No member with this ID exists");
        else if (library.getMemberByID(tempID).getBookFromBorrowedBooks(tempISBN) == null)
            System.out.println("This member has never borrowed this book or has returned it");
        else {
            int tempPrice = library.getMemberByID(tempID).getBookFromBorrowedBooks(tempISBN).getPrice();
            library.getMemberByID(tempID).addBalance((90*tempPrice/100));
            library.addBook(library.getMemberByID(tempID).getBookFromBorrowedBooks(tempISBN));
            library.getMemberByID(tempID).returnBook(library.getMemberByID(tempID).
                    getBookFromBorrowedBooks(tempISBN));
            String MemberName = library.getMemberByID(tempID).getName();
            String BookName = library.getBook(tempISBN).getName();
            System.out.println(MemberName + " returned " + BookName + " successfully");
        }
    }
    private void returnMagazine(Matcher matcher){
        int tempISSN = Integer.parseInt(matcher.group("ISSN"));
        int tempID = Integer.parseInt(matcher.group("ID"));
        int tempNum = Integer.parseInt(matcher.group("Num"));
        if (library.getMemberByID(tempID) == null)
            System.out.println("No member with this ID exists");
        else if (library.getMemberByID(tempID).getMagazineFromBorrowedMagazines
                (tempISSN,tempNum) == null)
            System.out.println("This member has never borrowed this magazine or has returned it");
        else {
            int tempPrice = library.getMemberByID(tempID).getMagazineFromBorrowedMagazines
                    (tempISSN,tempNum).getPrice();
            library.getMemberByID(tempID).addBalance( (80*tempPrice/100));
            library.addMagazine(library.getMemberByID(tempID)
                    .getMagazineFromBorrowedMagazines(tempISSN,tempNum));
            library.getMemberByID(tempID).returnMagazine(library.getMemberByID(tempID).getMagazineFromBorrowedMagazines(tempISSN,tempNum));
            String MemberName = library.getMemberByID(tempID).getName();
            String MagazineName = library.getMagazine(tempISSN,tempNum).getName();
            System.out.println( MemberName + " returned " + MagazineName + " successfully");
        }
    }
    private void donateBook(Matcher matcher){
        int tempID = Integer.parseInt(matcher.group("ID"));
        if (library.getMemberByID(tempID) == null)
            System.out.println("No member with this ID exists");
        else {
            String tempName = matcher.group("Name");
            String tempAuthor = matcher.group("Author");
            String tempLanguage = matcher.group("Language");
            int tempPrice = Integer.parseInt(matcher.group("Price"));
            int tempISBN = Integer.parseInt(matcher.group("ISBN"));
            int tempAmount = Integer.parseInt(matcher.group("Amount"));
            for (int i = 0;i < tempAmount;i++) {
                library.addBook(new Book(tempName, tempISBN, tempAuthor, tempLanguage, tempPrice));
            }
            library.getMemberByID(tempID).addBalance(40 * tempAmount * tempPrice/100);
            String MemberName = library.getMemberByID(tempID).getName();
            System.out.println( MemberName + " donated " + tempAmount +  " books successfully");
        }
    }
    private void returnMagazineAndBorrowNext(Matcher matcher){
        int tempID = Integer.parseInt(matcher.group("ID"));
        int tempISSN = Integer.parseInt(matcher.group("ISSN"));
        int tempNum = Integer.parseInt(matcher.group("Num"));
        if (library.getMemberByID(tempID) == null)
            System.out.println("No member with this ID exists");
        else if (library.getMemberByID(tempID).
                getMagazineFromBorrowedMagazines(tempISSN,tempNum) == null)
            System.out.println("This member has never borrowed this magazine or has returned it");
        else if (library.getMagazine(tempISSN,tempNum+1) == null)
            System.out.println("The library does not have the next issue of this magazine");
        else {
            library.getMemberByID(tempID).borrowMagazine
                    (library.getMagazine(tempISSN,tempNum+1));
            library.removeMagazine(library.getMagazine(tempISSN,tempNum+1));
            library.addMagazine(library.getMemberByID(tempID).
                    getMagazineFromBorrowedMagazines(tempISSN,tempNum));
            library.getMemberByID(tempID).returnMagazine
                    (library.getMemberByID(tempID).getMagazineFromBorrowedMagazines
                            (tempISSN,tempNum));
            String MemberName = library.getMemberByID(tempID).getName();
            String MagazineName = library.getMagazine(tempISSN,tempNum).getName();
            System.out.println( MemberName + " returned " + MagazineName + " and borrowed the next issue");
        }
    }
    private void printBooks(){
        ArrayList<Book> SortedBooks = library.getBooks();
        System.out.println("List of all books:");
        for (Book i:SortedBooks)
            System.out.println(i.getISBN() + ": " + i.getName());
    }
    private void printMagazines(){
        ArrayList<Magazine> SortedMagazines = library.getMagazines();
        System.out.println("List of all magazines:");
        for (Magazine i:SortedMagazines)
            System.out.println(i.getISSN() + ": " + i.getName() + " " + i.getNumber());
    }
}
