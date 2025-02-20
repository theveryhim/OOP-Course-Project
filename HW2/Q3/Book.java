public class Book extends Publication{
    private final int ISBN;
    public Book(String name,int ISBN,String author,String language,int price){
        super(name,author,language,price);
        this.ISBN = ISBN;
    }
    public int getISBN(){return ISBN;}
}
