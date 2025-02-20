import java.util.ArrayList;

public class Arena {
    public int[][] CastlesHP = new int [2][3];
    public Tile[][] Tiles;
    public ArrayList<Card> HostDeck;
    public int [] HostCastleDamage = new int [3];
    public int [] GuestCastleDamage = new int [3];
    public ArrayList<Card> GuestDeck;
    public Arena(int TopLeft,int TopCenter,int TopRight,
                 int BottomLeft,int BottomCenter,int BottomRight,
                 ArrayList<Card> hostDeck,ArrayList<Card> guestDeck,int hostSideCastleDamage,
                 int hostMiddleCastleDamage,int guestSideCastleDamage,int guestMiddleCastleDamage){
        CastlesHP[1][0] = TopLeft;
        CastlesHP[1][1] = TopCenter;
        CastlesHP[1][2] = TopRight;
        CastlesHP[0][0] = BottomLeft;
        CastlesHP[0][1]= BottomCenter;
        CastlesHP[0][2] = BottomRight;
        HostCastleDamage[0] = hostSideCastleDamage;
        HostCastleDamage[2] = hostSideCastleDamage;
        HostCastleDamage[1] = hostMiddleCastleDamage;
        GuestCastleDamage[1] = guestMiddleCastleDamage;
        GuestCastleDamage[0] = guestSideCastleDamage;
        GuestCastleDamage[2] = guestSideCastleDamage;
        Tiles = new Tile[15][3];
        for (int i = 0;i < 15;i++ )
            for (int j=0;j<3;j++)
                Tiles[i][j] = new Tile();
        HostDeck = new ArrayList<>(hostDeck);
        for (Card c:HostDeck)
            c.SetOwner(true);
        GuestDeck = new ArrayList<>(guestDeck);
        for (Card c:GuestDeck)
            c.SetOwner(false);
    }
    public void TurnOver(){
        for (int i = 0 ; i < 15 ; i++)
            for (int j = 0; j < 3; j++){
                if (i==0){
                    int GuestCardsCount = Tiles[i][j].getUserCardsCount(false);
                    int HostCardsCount = Tiles[i][j].getUserCardsCount(true);
                    /*HostCastles to GuestTroops*/
                    if (GuestCardsCount!=0) {
                        Tiles[i][j].EmployDamageToOpponentCards
                                (HostCastleDamage[j] / GuestCardsCount, false);
                        /*GuestTroops to HostCastles*/
                        if (HostCardsCount == 0)
                                DamageToCastle(0, j, Tiles[i][j].getUserCardDamage(false));
                            /*GuestTroops & HostTroops*/
                        else
                                Tiles[i][j].TroopsFight();
                    }
                }
                else if (i==14){
                    int GuestCardsCount = Tiles[i][j].getUserCardsCount(false);
                    int HostCardsCount = Tiles[i][j].getUserCardsCount(true);
                    /*GuestCastles to HostTroops*/
                    if (HostCardsCount != 0) {
                        Tiles[i][j].EmployDamageToOpponentCards
                                (GuestCastleDamage[j] / HostCardsCount, true);
                        /*HostTroops to GuestCastles*/
                        if (GuestCardsCount == 0)
                            DamageToCastle(1, j, Tiles[i][j].getUserCardDamage(true));
                            /*GuestTroops & HostTroops*/
                        else
                                Tiles[i][j].TroopsFight();
                    }
                }
                else {
                    int GuestCardsCount = Tiles[i][j].getUserCardsCount(false);
                    int HostCardsCount = Tiles[i][j].getUserCardsCount(true);
                    if (GuestCardsCount != 0 & HostCardsCount != 0)
                        Tiles[i][j].TroopsFight();
                }
                Tiles[i][j].CheckCard();
            }
    }
    public void DamageToCastle(int Row,int Column,int Damage){
        if (CastlesHP[Row][Column] > Damage) {
            CastlesHP[Row][Column] -= Damage;
        }
        else
            CastlesHP[Row][Column] = 0;
    }
    public int getCastleHP(int row,int column){
        return CastlesHP[row][column];
    }
    public Card getCardFromUser(String name,boolean IsHost){
        if (IsHost) {
            for (Card c : HostDeck)
                if (c.getType().equals(name))
                    return c;
        }
        else if (!IsHost) {
                    for (Card C : GuestDeck)
                        if (C.getType().equals(name))
                            return C;
                }
        return null;
    }
    public void PrintLine(int Column,String HostName,String GuestName){
        for (int i=0; i<15 ; i++){
            if (i < 4)
                if (!Tiles[i][Column].Cards.isEmpty())
                    Tiles[i][Column].PrintLine(HostName,i);
            else if (i>10)
                    if (!Tiles[i][Column].Cards.isEmpty())
                        Tiles[i][Column].PrintLine(GuestName,i);
        }
    }
    public int getCastlesHP(boolean IsHost){
        if (IsHost)
            return CastlesHP[0][0] + CastlesHP[0][1] + CastlesHP[0][2];
        else
            return CastlesHP[1][0] + CastlesHP[1][1] + CastlesHP[1][2];
    }
    public int getCastlesCount(boolean IsHost){
        int n = 0;
        if (IsHost){
            if (CastlesHP[0][0] != 0)
                n++;
            if (CastlesHP[0][1] != 0)
                n++;
            if (CastlesHP[0][2] != 0)
                n++;
        }
        else {
            if (CastlesHP[1][0] != 0)
                n++;
            if (CastlesHP[1][1] != 0)
                n++;
            if (CastlesHP[1][2] != 0)
                n++;
        }
        return n;
    }
    public boolean CastleCheck(){
        return (CastlesHP[0][0] == 0 & CastlesHP[0][1] == 0 & CastlesHP[0][2] == 0) |
                (CastlesHP[1][0] == 0 & CastlesHP[1][1] == 0 & CastlesHP[1][2] == 0);
    }
}
