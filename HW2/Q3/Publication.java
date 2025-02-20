public class Publication {
    private final String name;
    private final String author;
    private final int price;
    private final String language;
    public Publication(String name,String author,String language,int price){
        this.name = name;
        this.author = author;
        this.language = language;
        this.price = price;
    }
    public String getName(){return name;}
    public String getAuthor(){return author;}
    public String getLanguage(){return language;}
    public int getPrice(){return price;}
}
