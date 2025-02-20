import java.util.ArrayList;

public class Tile {
    ArrayList<Card> Cards;
    public Tile(){
        Cards = new ArrayList<>();
    }
    public void AddCard(Card card){
        Cards.add(card);
    }
    public void RemoveCard(Card card){
        Cards.remove(card);
    }
    public void PrintLine(String User,int row){
        if (Cards != null) {
            System.out.print("row " + row + ": ");
            for (Card c : Cards)
                System.out.print(c.getType() + ": " + User + " ");
        }
    }
    public Card getCardFromUser(boolean IsHost){
        for (Card C:Cards) {
            if (IsHost)
                if (C.OwnerIsHost)
                    return C;
            else
                if (!C.OwnerIsHost)
                    return C;
        }
        return null;
    }
    public int getUserCardsCount(boolean IsHost){
        int n = 0;
        for (Card c:Cards)
            if (c.OwnerIsHost == IsHost)
                n++;
        return n;
    }
    public void EmployDamageToOpponentCards(int Damage, boolean IsHost){
        for (Card c:Cards)
            if (c.OwnerIsHost==IsHost) {
                if (c.getHP() > Damage)
                    c.AddHP(-Damage);
                else
                    c.setHP(0);
            }
    }
    public int getUserCardDamage(boolean IsHost){
        int n = 0;
        for (Card c:Cards)
            if (c.OwnerIsHost==IsHost)
                n += c.Damage;
        return n;
    }
    public void TroopsFight(){
        Card HostCard = null;
        Card GuestCard = null;
        for (Card c:Cards) {
            if (c.OwnerIsHost)
                HostCard = c;
            else
                GuestCard = c;
        }
        if (GuestCard != null & HostCard != null){
            if (GuestCard.Damage > HostCard.Damage) {
                if (HostCard.HP > GuestCard.Damage)
                    HostCard.AddHP(-GuestCard.Damage);
                else
                    HostCard.setHP(0);
            }
            else {
                if (GuestCard.HP > HostCard.Damage)
                    GuestCard.AddHP(-HostCard.Damage);
                else
                    GuestCard.setHP(0);
            }
        }
    }
    public void CheckCard(){
        for (int i =0;i<Cards.size();i++)
            if (Cards.get(i).getHP()==0)
                Cards.remove(Cards.get(i));
    }
}
