public class Tile {
    int AttackDefence;
    int PlayerDamage;
    boolean IsBuffed;
    boolean IsBoosted;/*only for special abilities*/
    boolean IsBroken;
    boolean IsDefended;
    String CardName;
    public Tile(boolean isBroken){
        AttackDefence = 0;
        PlayerDamage = 0;
        IsBuffed = false;
        IsDefended = false;
        IsBroken = isBroken;
        CardName = "";
        IsBoosted = false;
    }

    public void Info(int i){
            System.out.println("Tile " + (i+1) + ":");
            System.out.println("Card Name : " + CardName);
            if (AttackDefence>200)
                System.out.println("AttackDefence: Infinite");
            else
                System.out.println("AttackDefence: " + AttackDefence);
            System.out.println("Damage : " + PlayerDamage);
            System.out.println("Defended : " + IsDefended);
            System.out.println("Buffed : " + IsBuffed);
            System.out.println("Boosted : " + IsBoosted);
            }
    }
