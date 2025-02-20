public class Card{
    private final String Type;
    public int HP;
    public final int Damage;
    public final int Price;
    public boolean OwnerIsHost;
    public Card(String type,int hp,int damage,int price){
        Type = type;
        HP = hp;
        Damage = damage;
        Price = price;
    }
    public void AddHP(int hp){
        this.HP += hp;
    }
    public int getHP(){
        return HP;
    }
    public void setHP(int hp){
        HP = hp;
    }
    public String  getType(){
        return this.Type;
    }
    public void SetOwner(boolean ownerIsHost){
        OwnerIsHost = ownerIsHost;
    }
    public Card Clone(){
        return new Card(Type,HP,Damage,Price);
    }
}
