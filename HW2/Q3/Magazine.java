public class Magazine extends Publication{
    private final int ISSN;
    private final int number;
    public Magazine(String name,int ISSN,String author,String language,int price,int number){
        super(name,author,language,price);
        this.ISSN = ISSN;
        this.number = number;
    }
    public int getISSN() {
        return ISSN;
    }
    public int getNumber() {
        return number;
    }
}
