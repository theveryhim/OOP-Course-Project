import java.util.ArrayList;

public class User {
    private final String Name;
    private String Password;
    private int Level;
    private int Exp;
    private int Gold;
    private ArrayList<Card> Deck = new ArrayList<>();
    private ArrayList<Card> Cards = new ArrayList<>();
    public User(String name,String password){
        Name = name;
        Password = password;
        Level = 1;
        Exp = 0;
        Gold = 80;
        Deck.add(new Card("Fireball",0,1400,80));
        Deck.add(new Card("Archer",1900,800,80));
        Cards.add(Deck.get(0));
        Cards.add(Deck.get(1));
    }
    public String getName(){
        return Name;
    }
    public String getPassword(){
        return Password;
    }
    public void setPassword(String password){
        Password = password;
    }
    public int getLevel(){
        return Level;
    }
    public int getExp(){
        return Exp;
    }
    public int getGold(){
        return Gold;
    }
    public void addGold(int gold){
        Gold += gold;
    }
    public void addExp(int exp){
        Exp += exp;
        while (Exp >= Level*Level*150) {
            Exp -= Level*Level*150;
            Level++;
        }
    }
    public int getSideCastleDamage(){
        return 2200*Level;
    }
    public int getMiddleCastleDamage(){
        return 3400*Level;
    }
    public int getMiddleCastleHP(){
        return this.Level * 3400;
    }
    public int getSideCastleHP(){
        return this.Level * 2200;
    }
    public ArrayList<Card> getDeck(){
        return Deck;
    }
    public int getDeckSize(){
        return Deck.size();
    }
    public Card getCardFromCards(String name){
        for (Card c:this.Cards)
            if (c.getType().equals(name))
                return c;
        return null;
    }
    public Card getCardFromDeck(String name){
        for (Card c:this.Deck)
            if (c.getType().equals(name))
                return c;
        return null;
    }
    public void AddCardToDeck(Card card){
        Deck.add(card);
    }
    public void RemoveCardFromDeck(Card card){
        Deck.remove(card);
    }
    public void AddCardToCards(Card card){
        Cards.add(card);
    }
    public void RemoveCardFromCards(Card card){
        Cards.remove(card);
    }
    public void AddGold(int gold){
        Gold += gold;
    }
}
