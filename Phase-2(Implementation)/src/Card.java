class Card {
    String Name;
    int Ability;
    int Price;
    int Level;
    int UpgradeLevel;
    int UpgradeCost;
    int MaxLevel;
    int AttackDefence;
    int Duration;
    int PlayerDamage;
    boolean IsSpell;
    boolean IsBoosted;
    boolean IsBuffed;
    String Style;

    public Card(String name,boolean isSpell,String style,int attackDefence
                ,int ability,int duration,int playerDamage,
                int upgradeLevel,int upgradeCost,int price,int maxLevel) {
        this.Name = name;
        this.IsSpell = isSpell;
        this.Style = style;
        this.AttackDefence = attackDefence;
        this.Ability = ability;
        this.Duration = duration;
        this.PlayerDamage = playerDamage;
        this.UpgradeLevel = upgradeLevel;
        this.UpgradeCost = upgradeCost;
        this.Price = price;
        this.Level = 1;
        this.MaxLevel = maxLevel;
        this.IsBoosted = false;
        this.IsBuffed = false;
    }

    public Card(Card card) {
        this.Name = card.Name;
        this.IsSpell = card.IsSpell;
        this.Style = card.Style;
        this.AttackDefence = card.AttackDefence;
        this.Ability = card.Ability;
        this.Duration = card.Duration;
        this.PlayerDamage = card.PlayerDamage;
        this.UpgradeLevel = card.UpgradeLevel;
        this.UpgradeCost = card.UpgradeCost;
        this.Price = card.Price;
        this.Level = card.Level;
        this.MaxLevel = card.MaxLevel;
        this.IsBoosted = false;
        this.IsBuffed = false;
    }

    public void Upgrade(){
        Level++;
        UpgradeCost*=1.25;
    }

    public void Info(){
        System.out.println("Name: " + Name);
        System.out.println("Duration: " + Duration);
        System.out.println("Level: " + Level);
        if (AttackDefence>200)
            System.out.println("AttackDefence: Infinite");
        else
            System.out.println("AttackDefence: " + AttackDefence);
        System.out.println("PlayerDamage: " + PlayerDamage);
        System.out.println("Style: " + Style);
        System.out.println("Buffed : " + IsBuffed);
        System.out.println("Boosted: " + IsBoosted);
        if (IsSpell)
            System.out.println("Type: Spell" );
        else
            System.out.println("Type: not Spell" );
        switch (Ability){
            case 1:/*Shields*/
                System.out.println("Defends any card with any AttackDefence but has no Damage");
                break;
            case 2:/*Heal*/
                System.out.println("+30 HP whenever reached in time-line (can't be defended)");
                break;
            case 3:/*Power Booster*/
                System.out.println("Boosts one of the played cards randomly");
                break;
            case 4:/*Broken Tile Randomizer*/
                System.out.println("Randomly relocate the broken tile to an empty tile for both players");
                break;
            case 5:/*Broken Tile Fixer*/
                System.out.println("Fixes the player's broken tile");
                break;
            case 6:/*Round Reducer*/
                System.out.println("Reduces the remaining rounds");
                break;
            case 7:/*Card Robber*/
                System.out.println("Steals one of the opponent's cards randomly");
                break;
            case 8:/*Card Weakener*/
                System.out.println("Randomly decreases one of the opponents' " +
                        "card's Damage and another one's AttackDefence ");
                break;
            case 9:/*Card Copier*/
                System.out.println("Duplicates one of the player's desired cards");
                break;
            case 10:/*Card Masker*/
                System.out.println("Masks opponents' cards for next round");
                break;
            case 11:
                System.out.println("Standard card");
                break;
            case 12:/*Track Magnet*/
                System.out.println("Placed in 1 duration gap to steal opposing card");
                break;
            case 13:/*Covert Strike*/
                System.out.println("+60 Damage when placed opposite a gap");
                break;
            case 14:/*Sonar Strike*/
                System.out.println("+5 AttackDefence & +45 Damage if placed in first tile(Left most)");
                break;
            case 15:/*Tracker Laser*/
                System.out.println("+2 AttackDefence & +8 Damage per tile for any 2 Duration card in Deck");
        }
    }

    public void EmployBuff(double BuffAmount){
        AttackDefence += (int) (Level*2*BuffAmount);
        PlayerDamage += (int) (Level*2*BuffAmount) * Duration;
    }
}
