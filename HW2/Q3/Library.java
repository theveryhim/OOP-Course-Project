import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Library {
    private final ArrayList<Book> books = new ArrayList<>();
    private final ArrayList<Magazine> magazines = new ArrayList<>();
    private  final ArrayList<Member> members = new ArrayList<>();
    public void addBook(Book book){
        books.add(book);
    }
    public void addMagazine(Magazine magazine){
        magazines.add(magazine);
    }
    public void addMember(Member member){
        members.add(member);
    }
    public Member getMemberByID(int ID){
        for (Member i:members)
            if (i.getID() == ID)
                return i;
        return null;
    }
    public Book getBook(int ISBN){
        for (Book i:books)
            if (i.getISBN() == ISBN)
                return i;
        return null;
    }
    public Magazine getMagazine(int ISSN,int number){
        for (Magazine i:magazines)
            if (i.getISSN() == ISSN & i.getNumber() == number)
                return i;
        return null;
    }
    public void removeBook(Book book){
        books.remove(book);
    }
    public void removeMagazine(Magazine magazine){
        magazines.remove(magazine);
    }
    public ArrayList<Book> getBooks(){
        Comparator<Book> customComparator = Comparator.comparing(Book::getName)
            .thenComparing(Book::getAuthor)
            .thenComparing(Book::getLanguage);
        Collections.sort(books, customComparator);
        return books;
        }
    public ArrayList<Magazine> getMagazines() {
        Comparator<Magazine> customComparator = Comparator.comparing(Magazine::getName)
                .thenComparing(Magazine::getNumber)
                .thenComparing(Magazine::getAuthor)
                .thenComparing(Magazine::getLanguage);
        Collections.sort(magazines, customComparator);
        return magazines;
    }
}
