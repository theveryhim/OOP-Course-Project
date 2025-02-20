import java.util.ArrayList;

public class Member {
    private final String name;
    private final int ID;
    private int balance;
    private final ArrayList<Book> borrowedBooks = new ArrayList<>();
    private final ArrayList<Magazine> borrowedMagazines = new ArrayList<>();
    Member(String name,int ID){
        this.name = name;
        this.ID = ID;
        this.balance = 0;
    }
    public void addBalance(int amount) {
        balance += amount;
    }
    public int getBalance(){return balance;}
    public void nullifyBalance(){
        balance = 0;
    }
    public String getName(){return name;}
    public int getID(){return ID;}
    public void borrowBook(Book book){
        borrowedBooks.add(book);
    }
    public void borrowMagazine(Magazine magazine){
        borrowedMagazines.add(magazine);
    }
    public Book getBookFromBorrowedBooks(int ISBN){
        for (Book i:borrowedBooks)
            if (i.getISBN() == ISBN)
                return i;
        return null;
    }
    public Magazine getMagazineFromBorrowedMagazines(int ISSN,int number){
        for (Magazine i:borrowedMagazines)
            if (i.getNumber() == number & i.getISSN() == ISSN)
                return i;
        return null;
    }
    public void returnBook(Book book){
        borrowedBooks.remove(book);
    }
    public void returnMagazine(Magazine magazine){
        borrowedMagazines.remove(magazine);
    }
}
