import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Arena {
    Tile [] HostTiles = new Tile[21];
    Tile [] GuestTiles = new Tile[21];
    ArrayList<Card> HostCards = new ArrayList<>(20);
    ArrayList<Card> GuestCards = new ArrayList<>(20);
    ArrayList<Card> HostDeck= new ArrayList<>(6);
    ArrayList<Card> GuestDeck = new ArrayList<>(6);
    int HostHP;
    int GuestHP;
    int HostRounds;
    int GuestRounds;
    int HostDamage;
    int GuestDamage;
    int TotalRounds;
    int HostBrokenTile;
    int GuestBrokenTile;
    boolean MaskHostDeck;
    boolean MaskGuestDeck;
    boolean EndGame;
    boolean WinnerIsHost;
    Scanner sc;
    String HostCharacter;
    String GuestCharacter;
    public Arena(ArrayList<Card> hostCards,ArrayList<Card>guestCards,
                 String hostCharacter,String guestCharacter){
        HostHP = 250;
        GuestHP = 250;
        HostDamage = 0;
        GuestDamage = 0;
        HostRounds = 4;
        GuestRounds = 4;
        TotalRounds = 4;
        HostCards.addAll(hostCards);
        GuestCards.addAll(guestCards);
        HostCharacter = hostCharacter;
        GuestCharacter = guestCharacter;
        MaskHostDeck = false;
        MaskGuestDeck = false;
        EndGame = false;
        WinnerIsHost = false;
        sc = new Scanner(System.in);
    }
    public void Start(ArrayList<Card> hostDeck,ArrayList<Card> guestDeck,
                      int hostBrokenTile,int guestBrokenTile){
        HostBrokenTile = hostBrokenTile;
        GuestBrokenTile = guestBrokenTile;
        HostDeck.addAll(hostDeck);
        GuestDeck.addAll(guestDeck);
        for (int i = 0; i < 21 ;i++){
            HostTiles[i] = new Tile(false);
            GuestTiles[i] = new Tile(false);
            if ( i == GuestBrokenTile)
                HostTiles[i].IsBroken = true;
            if ( i == HostBrokenTile)
                HostTiles[i].IsBroken = true;
        }
    }

    public void PlayerTurn(boolean isHost){
        Display(isHost);
        if (isHost){
            System.out.println("Host turn...");
            boolean Played = false;
            while (!Played){
                String command = sc.nextLine();
                if (command.matches("Select card number (?<n>[1-6]) player (?<p>[12])")){
                    Matcher matcher = getCommandMatcher(command,
                            "Select card number (?<n>[1-6]) player (?<p>[12])");
                    matcher.find();
                    int n = Integer.parseInt(matcher.group("n"));
                    int p = Integer.parseInt(matcher.group("p"));
                    ShowCardInfo(n,p==1);
                }
                else if (command.matches("Placing card n (?<n>[1-6]) in tile (?<t>[1-9]|1[1-9]|20|21)")){
                    Matcher matcher = getCommandMatcher(command,
                            "Placing card n (?<n>[1-6]) in tile (?<t>[1-9]|1[1-9]|20|21)");
                    matcher.find();
                    int n = Integer.parseInt(matcher.group("n"));
                    int t = Integer.parseInt(matcher.group("t"));
                    boolean Valid = true;
                    if (getCardFromDeck(n,true).Duration != 0)
                        Valid = ValidSelection(getCardFromDeck(n,true),t,true);
                    if (Valid) {
                        Card card = getCardFromDeck(n,true);
                        HostRounds--;
                        Played =  PlayCard(t - 1, true,card);
                        if (Played)
                            System.out.println("Host Played card " + card.Name + " successfully");
                    }
                }
                else
                    System.out.println("invalid command");
            }
        }
        else {
            System.out.println("Guest turn...");
            boolean Played = false;
            while (!Played){
                String command = sc.nextLine();
                if (command.matches("Select card number (?<n>[1-6]) player (?<p>[12])")){
                    Matcher matcher = getCommandMatcher(command,
                            "Select card number (?<n>[1-6]) player (?<p>[12])");
                    matcher.find();
                    int n = Integer.parseInt(matcher.group("n"));
                    int p = Integer.parseInt(matcher.group("p"));
                    ShowCardInfo(n,p==1);
                }
                else if (command.matches("Placing card n (?<n>[1-6]) in tile (?<t>[1-9]|1[1-9]|20|21)")){
                    Matcher matcher = getCommandMatcher(command,
                            "Placing card n (?<n>[1-6]) in tile (?<t>[1-9]|1[1-9]|20|21)");
                    matcher.find();
                    int n = Integer.parseInt(matcher.group("n"));
                    int t = Integer.parseInt(matcher.group("t"));
                    boolean Valid = true;
                    if (getCardFromDeck(n,false).Duration != 0)
                        Valid = ValidSelection(getCardFromDeck(n,false),t,false);
                    if (Valid) {
                        Card card = getCardFromDeck(n,false);
                        GuestRounds--;
                        Played =  PlayCard(t - 1, false,card);
                        if (Played)
                            System.out.println("Guest Played card " + card.Name + " successfully");
                    }
                }
                else
                    System.out.println("invalid command");
            }
        }
        UpdateData();
    }

    public void Display(boolean isHost){
        System.out.println("    Host Character: " + HostCharacter);
        System.out.println("    Guest Character: " + GuestCharacter);
        System.out.println("Host HP: " + HostHP);
        System.out.println("Guest HP: " + GuestHP);
        System.out.println("Host Rounds: " + HostRounds);
        System.out.println("Guest Rounds: " + GuestRounds);
        System.out.println("Host Damage: " + HostDamage);
        System.out.println("Guest Damage: " + GuestDamage);
        System.out.println("Host Broken Tile: " + (HostBrokenTile + 1));
        System.out.println("Guest Broken Tile: " + (GuestBrokenTile + 1));
        System.out.println("    Host Current Deck:");
        DisplayDeck(true,isHost);
        System.out.println("    Guest Current Deck:");
        DisplayDeck(false,isHost);
        System.out.println("    Host Tiles:");
        DisplayTiles(true);
        System.out.println("    Guest Tiles:");
        DisplayTiles(false);
    }

    public void DisplayDeck(boolean isHost,boolean HostTurn){
        if (isHost) {
            for (int i = 0; i < HostDeck.size(); i++) {
                if (HostTurn & MaskHostDeck)
                    System.out.println((i + 1) + ": " + "------");
                else
                    System.out.println((i + 1) + ": " + HostDeck.get(i).Name);
            }
            if (HostTurn & MaskHostDeck)
                MaskHostDeck = false;
        }
        else {
            for (int i = 0; i < GuestDeck.size(); i++) {
                if (!HostTurn & MaskGuestDeck)
                    System.out.println((i + 1) + ": " + "------");
                else
                    System.out.println((i + 1) + ": " + GuestDeck.get(i).Name);
            }
            if (!HostTurn & MaskGuestDeck)
                MaskGuestDeck = false;
        }
    }

    public void DisplayTiles(boolean isHost){
        if (isHost)
            for (int i = 0; i < 21; i++)
                if (!HostTiles[i].CardName.equals("")) {
                    HostTiles[i].Info(i);
                }
        else
            for (int j = 0; j < 21; j++)
                if (!GuestTiles[j].CardName.equals("")) {
                    GuestTiles[j].Info(j);
                }
    }

    public Matcher getCommandMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }

    public void ShowCardInfo(int n,boolean OwnerIsHost){
        if (OwnerIsHost) {
            if (n==6 & HostDeck.size()<6)
                System.out.println("Invalid card number");
            else
                HostDeck.get(n - 1).Info();
        }
        else {
            if (n==6 & GuestDeck.size()<6)
                System.out.println("Invalid card number");
            else
                GuestDeck.get(n - 1).Info();
        }
    }

    public boolean ValidSelection(Card card,int tile,boolean isHost){
        if (tile + card.Duration > 22){
            System.out.println("Error : Out of Boundary!");
            return false;
        }
        for (int i = tile - 1; i < tile + card.Duration - 1; i++) {
            if (isHost) {
                if (HostTiles[i].IsBroken & card.Ability != 12 /*:Track Magnet Exception*/) {
                    System.out.println("Error : Broken tile!");
                    return false;
                }
                else if (!HostTiles[i].CardName.equals("")) {
                    System.out.println("Error : Occupied tile!");
                    return false;
                }
            }
            else {
                if (GuestTiles[i].IsBroken & card.Ability != 12 /*:Track Magnet Exception*/) {
                    System.out.println("Error : Broken tile!");
                    return false;
                }
                else if (!GuestTiles[i].CardName.equals("")) {
                    System.out.println("Error : Occupied tile!");
                    return false;
                }
            }
        }
        return true;
    }

    public Card getCardFromDeck(int n,boolean isHost){
        if (isHost)
            return HostDeck.get(n - 1);
        else
            return GuestDeck.get(n - 1);
    }

    public boolean PlayCard(int n,boolean isHost,Card card){
        boolean Played = false;
        Random random = new Random();
        double [] MiddleCartBuffer = new double[4];
        for (int i=0;i<4;i++)
            if (random.nextDouble() < 0.1*(i+1))
                MiddleCartBuffer[i] = 1.3 - (i+1)*0.05;
        if (isHost) {
            if (HostDeck.size()==5 &
                    HostDeck.get(2).Style.equals(card.Style)
                    & HostDeck.indexOf(card) != 2)/*MiddleCartBuffer*/ {
                HostDeck.get(2).IsBuffed = true;
                HostDeck.get(2).EmployBuff(MiddleCartBuffer[getCharacterNumber(card.Style)]);
            }
            switch (card.Ability) {
                case 1:/*Shields*/
                case 2:/*Heal*/
                    for (int i = n; i < n + card.Duration; i++) {
                        if (card.Style.equals(HostCharacter))/*Buffed*/
                            HostTiles[i].IsBuffed = true;
                        HostTiles[i].CardName = card.Name;
                        HostTiles[i].PlayerDamage = card.PlayerDamage / card.Duration;
                        HostTiles[i].AttackDefence = card.AttackDefence;
                        Played = true;
                    }
                break;
                case 3:/*Power Booster*/{
                    int blocks = BlockCounter(true);
                    if (blocks==0)
                        System.out.println("Can't play this card now!");
                    else {
                        int b = random.nextInt(blocks+1);
                        int j = 0;
                        for (int i = 0; i < 20; i++)
                            if (!HostTiles[i].CardName.equals("")) {
                                if (!HostTiles[i].CardName.equals(HostTiles[i + 1].CardName))
                                    j++;
                                if (j==b) {/*Buffer*/
                                    HostTiles[i + 1].PlayerDamage = (int) (1.25 * HostTiles[i + 1].PlayerDamage);
                                    if (HostCharacter.equals(card.Style))/*Buffed*/
                                        HostTiles[i + 1].PlayerDamage =  (int) (1.5 * HostTiles[i + 1].PlayerDamage);
                                    Played = true;
                                    HostTiles[i+1].IsBuffed = true;
                                }
                            }

                    }
                }
                break;
                case 4:/*Broken Tile Randomizer*/{
                    if (HostBrokenTile != -1){
                        HostTiles[HostBrokenTile].IsBroken = false;
                        int r0 = random.nextInt(21);
                        while (!HostTiles[r0].CardName.equals(""))
                            r0 = random.nextInt(21);
                        HostBrokenTile = r0;
                        if (HostCharacter.equals(card.Style))/*Buffed*/
                            if (random.nextDouble() < 0.3) {
                                HostTiles[HostBrokenTile].IsBroken = false;
                                HostBrokenTile = -1;
                            }
                        Played = true;
                    }
                    if (GuestBrokenTile != -1){
                        GuestTiles[GuestBrokenTile].IsBroken = false;
                        int r1 = random.nextInt(21);
                        while (!GuestTiles[r1].CardName.equals(""))
                            r1 = random.nextInt(21);
                        GuestBrokenTile = r1;
                        GuestTiles[GuestBrokenTile].IsBroken = true;
                        Played = true;
                    }
                    if (GuestBrokenTile ==-1 & HostBrokenTile==-1)
                        System.out.println("No Broken tile found");
                }
                break;
                case 5: /*Broken Tile Fixer*/{
                    if (HostBrokenTile != -1) {
                        HostTiles[HostBrokenTile].IsBroken = false;
                        HostBrokenTile = -1;
                        Played = true;
                        if (HostCharacter.equals(card.Style))/*Buffed*/
                            if (random.nextDouble() < 0.2) {
                                int r0 = random.nextInt(21);
                                while (!GuestTiles[r0].CardName.equals(""))
                                    r0 = random.nextInt(21);
                                GuestBrokenTile = r0;
                                GuestTiles[r0].IsBroken = true;
                            }
                    }
                    else
                        System.out.println("No Broken tile found");
                }
                break;
                case 6:/*Round Reducer*/ {
                    TotalRounds--;
                    HostRounds--;
                    GuestRounds--;
                    if (HostCharacter.equals(card.Style)){
                        if (random.nextDouble() < 0.2)/*Buffed*/ {
                            TotalRounds--;
                            HostRounds--;
                            GuestRounds--;
                        }
                    }
                    Played = true;
                }
                    break;
                case 7:/*Card Robber*/{
                    if (HostDeck.size() < 6){
                        int r = random.nextInt(GuestDeck.size()+1);
                        HostDeck.add(GuestDeck.get(r));
                        GuestDeck.remove(GuestDeck.get(r));
                        Played = true;
                    }
                    else
                        System.out.println("Can't play this card now");
                }
                break;
                case 8:/*Card Weakener*/{
                    int r0 = random.nextInt(GuestDeck.size());
                    int r1 = random.nextInt(GuestDeck.size());
                    GuestDeck.get(r0).PlayerDamage = (int) (0.8*GuestDeck.get(r0).PlayerDamage);
                    GuestDeck.get(r1).AttackDefence = (int) (0.8*GuestDeck.get(r1).AttackDefence);
                    if (HostCharacter.equals(card.Style))/*Buffed*/{
                        GuestDeck.get(r0).PlayerDamage = (int) (0.8*GuestDeck.get(r0).PlayerDamage);
                        GuestDeck.get(r1).AttackDefence = (int) (0.8*GuestDeck.get(r1).AttackDefence);
                    }
                    Played = true;
                }
                break;
                case 9:/*Card Copier*/{
                    if (HostDeck.size()<6){
                        int r = random.nextInt(HostDeck.size()+1);
                        HostDeck.add(new Card(HostDeck.get(r)));
                        Played = true;
                    }
                    else
                        System.out.println("Can't play this card now");
                }
                break;
                case 10:/*Card Masker*/ {
                    MaskGuestDeck = true;
                    Played = true;
                }
                break;
                case 11:/*Standard Card*/{
                    for (int i = n; i < n + card.Duration; i++) {
                        if (card.Style.equals(HostCharacter))/*Buffed*/ {
                            HostTiles[i].IsBuffed = true;
                            card.EmployBuff(1.2);
                        }
                        HostTiles[i].CardName = card.Name;
                        HostTiles[i].PlayerDamage = card.PlayerDamage / card.Duration;
                        HostTiles[i].AttackDefence = card.AttackDefence;
                        Played = true;
                    }
                }
                break;
                case 12:/*Track Magnet*/{
                    if (HostBrokenTile==n & HostBrokenTile != GuestBrokenTile){
                        if (n<20)
                            if (GuestTiles[n].CardName.equals(GuestTiles[n+1].CardName))
                                System.out.println("This card can only steal 1 Duration card");
                        else
                            if (GuestTiles[n].CardName.equals(GuestTiles[n-1].CardName))
                                System.out.println("This card can only steal 1 Duration card");
                        else {
                            HostTiles[n] = GuestTiles[n];
                            HostTiles[n].IsBoosted = true;
                            GuestTiles[n] = new Tile(false);
                            if (HostCharacter.equals(card.Style))/*Buffed*/ {
                                HostTiles[n].IsBuffed = true;
                                HostTiles[n].AttackDefence = (int) (1.2*HostTiles[n].AttackDefence);
                                HostTiles[n].PlayerDamage += (int) (1.2*HostTiles[n].PlayerDamage);
                            }
                            Played = true;
                            }
                    }
                    else
                        System.out.println("Can only played in gap opposite to non-gap");
                }
                break;
                case 13: /*Covert Strike*/{
                    if (card.Style.equals(HostCharacter))/*Buffed*/ {
                        HostTiles[n].IsBuffed = true;
                        card.EmployBuff(1.2);
                    }
                    HostTiles[n].CardName = card.Name;
                    HostTiles[n].PlayerDamage = card.PlayerDamage / card.Duration;
                    HostTiles[n].AttackDefence = card.AttackDefence;
                    if (GuestBrokenTile == n) /*Boosted*/{
                        HostTiles[n].PlayerDamage += 60;
                        HostTiles[n].IsBoosted = true;
                    }
                    Played = true;
                }
                break;
                case 14:/*Sonar Strike*/{
                    if (card.Style.equals(HostCharacter))/*Buffed*/ {
                        HostTiles[n].IsBuffed = true;
                        card.EmployBuff(1.2);
                    }
                    HostTiles[n].CardName = card.Name;
                    HostTiles[n].PlayerDamage = card.PlayerDamage / card.Duration;
                    HostTiles[n].AttackDefence = card.AttackDefence;
                    if (n == 0) /*Boosted*/{
                        HostTiles[n].PlayerDamage += 45;
                        HostTiles[n].AttackDefence += 5;
                        HostTiles[n].IsBoosted = true;
                    }
                    Played = true;
                }
                break;
                case 15:/*Tracker Laser*/{
                    if (card.Style.equals(HostCharacter))/*Buffed*/ {
                        HostTiles[n].IsBuffed = true;
                        card.EmployBuff(1.2);
                    }
                    HostTiles[n].CardName = card.Name;
                    HostTiles[n].PlayerDamage = card.PlayerDamage / card.Duration;
                    HostTiles[n].AttackDefence = card.AttackDefence;
                    for (Card c:HostDeck)/*Boosted*/
                        if (c.Duration == 2){
                            c.PlayerDamage += 16;
                            c.AttackDefence += 2;
                            c.IsBoosted = true;
                        }
                    Played = true;
                }
            }
            if (Played)
                ReplaceCard(card,true);
        }
        else {
            if (GuestDeck.size()==5 &
                    GuestDeck.get(2).Style.equals(card.Style)
                    & GuestDeck.indexOf(card) != 2) {
                GuestDeck.get(2).IsBuffed = true;
                GuestDeck.get(2).EmployBuff(MiddleCartBuffer[getCharacterNumber(card.Style)]);
            }
            switch (card.Ability) {
                case 1:/*Shields*/
                case 2:/*Heal*/
                    for (int i = 0; i < n + card.Duration; i++) {
                        if (card.Style.equals(GuestCharacter))/*Buffed*/ {
                            GuestTiles[i].IsBuffed = true;
                            card.EmployBuff(1.2);
                        }
                        GuestTiles[i].CardName = card.Name;
                        GuestTiles[i].PlayerDamage = card.PlayerDamage / card.Duration;
                        GuestTiles[i].AttackDefence = card.AttackDefence;
                        Played = true;
                    }
                    break;
                case 3:/*Power Booster*/{
                    int blocks = BlockCounter(true);
                    if (blocks==0)
                        System.out.println("Can't play this card now!");
                    else {
                        int b = random.nextInt(blocks+1);
                        int j = 0;
                        for (int i = 0; i < 20; i++)
                            if (!GuestTiles[i].CardName.equals("")) {
                                if (!GuestTiles[i].CardName.equals(GuestTiles[i + 1].CardName))
                                    j++;
                                if (j==b) {/*Buffer*/
                                    GuestTiles[i + 1].PlayerDamage = (int) (1.25 * GuestTiles[i + 1].PlayerDamage);
                                    if (GuestCharacter.equals(card.Style))/*Buffed*/
                                        GuestTiles[i + 1].PlayerDamage =  (2 * GuestTiles[i + 1].PlayerDamage);
                                    Played = true;
                                    GuestTiles[i+1].IsBuffed = true;
                                }
                            }

                    }
                }
                break;
                case 4:/*Broken Tile Randomizer*/{
                    if (GuestBrokenTile != -1){
                        GuestTiles[GuestBrokenTile].IsBroken = false;
                        int r0 = random.nextInt(21);
                        while (!GuestTiles[r0].CardName.equals(""))
                            r0 = random.nextInt(21);
                        GuestBrokenTile = r0;
                        if (GuestCharacter.equals(card.Style))/*Buffed*/
                            if (random.nextDouble() < 0.3) {
                                GuestTiles[GuestBrokenTile].IsBroken = false;
                                GuestBrokenTile = -1;
                            }
                        Played = true;
                    }
                    if (HostBrokenTile != -1){
                        HostTiles[HostBrokenTile].IsBroken = false;
                        int r1 = random.nextInt(21);
                        while (!HostTiles[r1].CardName.equals(""))
                            r1 = random.nextInt(21);
                        HostBrokenTile = r1;
                        HostTiles[HostBrokenTile].IsBroken = true;
                        Played = true;
                    }
                    if (HostBrokenTile ==-1 & GuestBrokenTile==-1)
                        System.out.println("No Broken tile found");
                }
                break;
                case 5: /*Broken Tile Fixer*/{
                    if (GuestBrokenTile != -1) {
                        GuestTiles[GuestBrokenTile].IsBroken = false;
                        GuestBrokenTile = -1;
                        Played = true;
                        if (GuestCharacter.equals(card.Style))/*Buffed*/
                            if (random.nextDouble() < 0.2) {
                                int r0 = random.nextInt(21);
                                while (!HostTiles[r0].CardName.equals(""))
                                    r0 = random.nextInt(21);
                                HostBrokenTile = r0;
                                HostTiles[r0].IsBroken = true;
                            }
                    }
                    else
                        System.out.println("No Broken tile found");
                }
                break;
                case 6:/*Round Reducer*/ {
                    TotalRounds--;
                    HostRounds--;
                    GuestRounds--;
                    if (GuestCharacter.equals(card.Style)){
                        if (random.nextDouble() < 0.2)/*Buffed*/ {
                            TotalRounds--;
                            HostRounds--;
                            GuestRounds--;
                        }
                    }
                    Played = true;
                }
                break;
                case 7:/*Card Robber*/{
                    if (GuestDeck.size() < 6){
                        int r = random.nextInt(HostDeck.size()+1);
                        GuestDeck.add(HostDeck.get(r));
                        HostDeck.remove(HostDeck.get(r));
                        Played = true;
                    }
                    else
                        System.out.println("Can't play this card now");
                }
                break;
                case 8:/*Card Weakener*/{
                    int r0 = random.nextInt(HostDeck.size());
                    int r1 = random.nextInt(HostDeck.size());
                    HostDeck.get(r0).PlayerDamage = (int) (0.8*HostDeck.get(r0).PlayerDamage);
                    HostDeck.get(r1).AttackDefence = (int) (0.8*HostDeck.get(r1).AttackDefence);
                    if (GuestCharacter.equals(card.Style))/*Buffed*/{
                        HostDeck.get(r0).PlayerDamage = (int) (0.8*HostDeck.get(r0).PlayerDamage);
                        HostDeck.get(r1).AttackDefence = (int) (0.8*HostDeck.get(r1).AttackDefence);
                    }
                    Played = true;
                }
                break;
                case 9:/*Card Copier*/{
                    if (GuestDeck.size()<6){
                        int r = random.nextInt(GuestDeck.size()+1);
                        GuestDeck.add(new Card(GuestDeck.get(r)));
                        Played = true;
                    }
                    else
                        System.out.println("Can't play this card now");
                }
                break;
                case 10:/*Card Masker*/ {
                    MaskHostDeck = true;
                    Played = true;
                }
                break;
                case 11:/*Standard Card*/{
                    for (int i = 0; i < n + card.Duration; i++) {
                        if (card.Style.equals(GuestCharacter))/*Buffed*/ {
                            GuestTiles[i].IsBuffed = true;
                            card.EmployBuff(1.2);
                        }
                        GuestTiles[i].CardName = card.Name;
                        GuestTiles[i].PlayerDamage = card.PlayerDamage / card.Duration;
                        GuestTiles[i].AttackDefence = card.AttackDefence;
                        Played = true;
                    }
                }
                break;
                case 12:/*Track Magnet*/{
                    if (GuestBrokenTile==n & GuestBrokenTile != HostBrokenTile){
                        if (n<20)
                            if (HostTiles[n].CardName.equals(HostTiles[n+1].CardName))
                                System.out.println("This card can only steal 1 Duration card");
                            else
                            if (HostTiles[n].CardName.equals(HostTiles[n-1].CardName))
                                System.out.println("This card can only steal 1 Duration card");
                            else {
                                GuestTiles[n] = HostTiles[n];
                                GuestTiles[n].IsBoosted = true;
                                HostTiles[n] = new Tile(false);
                                if (GuestCharacter.equals(card.Style))/*Buffed*/ {
                                    GuestTiles[n].IsBuffed = true;
                                    GuestTiles[n].AttackDefence = (int) (1.2*GuestTiles[n].AttackDefence);
                                    GuestTiles[n].PlayerDamage += (int) (1.2*GuestTiles[n].PlayerDamage);
                                }
                                Played = true;
                            }
                    }
                    else
                        System.out.println("Can only played in gap opposite to non-gap");
                }
                break;
                case 13: /*Covert Strike*/{
                    if (card.Style.equals(GuestCharacter))/*Buffed*/ {
                        GuestTiles[n].IsBuffed = true;
                        card.EmployBuff(1.2);
                    }
                    GuestTiles[n].CardName = card.Name;
                    GuestTiles[n].PlayerDamage = card.PlayerDamage / card.Duration;
                    GuestTiles[n].AttackDefence = card.AttackDefence;
                    if (HostBrokenTile == n) /*Boosted*/{
                        GuestTiles[n].PlayerDamage += 60;
                        GuestTiles[n].IsBoosted = true;
                    }
                    Played = true;
                }
                break;
                case 14:/*Sonar Strike*/{
                    if (card.Style.equals(GuestCharacter))/*Buffed*/ {
                        GuestTiles[n].IsBuffed = true;
                        card.EmployBuff(1.2);
                    }
                    GuestTiles[n].CardName = card.Name;
                    GuestTiles[n].PlayerDamage = card.PlayerDamage / card.Duration;
                    GuestTiles[n].AttackDefence = card.AttackDefence;
                    if (n == 0) /*Boosted*/{
                        GuestTiles[n].PlayerDamage += 45;
                        GuestTiles[n].AttackDefence += 5;
                        GuestTiles[n].IsBoosted = true;
                    }
                    Played = true;
                }
                break;
                case 15:/*Tracker Laser*/{
                    if (card.Style.equals(GuestCharacter))/*Buffed*/ {
                        GuestTiles[n].IsBuffed = true;
                        card.EmployBuff(1.2);
                    }
                    GuestTiles[n].CardName = card.Name;
                    GuestTiles[n].PlayerDamage = card.PlayerDamage / card.Duration;
                    GuestTiles[n].AttackDefence = card.AttackDefence;
                    for (Card c:GuestDeck)/*Boosted*/
                        if (c.Duration == 2){
                            c.PlayerDamage += 16;
                            c.AttackDefence += 2;
                            c.IsBoosted = true;
                        }
                    Played = true;
                }
            }
            if (Played)
                ReplaceCard(card,false);
        }
        return Played;
    }

    public int BlockCounter(boolean isHost){
        int blocks = 0;
        if (isHost)
            for (int i = 0;i < 20; i++)
                if (!HostTiles[i].CardName.equals(""))
                    if (!HostTiles[i].CardName.equals(HostTiles[i + 1].CardName))
                        blocks++;

        else
            for (int j = 0;j < 20; j++)
                if (!GuestTiles[j].CardName.equals(""))
                    if (!GuestTiles[j].CardName.equals(GuestTiles[j + 1].CardName))
                        blocks++;

        return blocks;
    }

    public int returnSpellCount(ArrayList<Card> cards){
        int SpellCount = 0;
            for (Card c:cards)
                if (c.IsSpell)
                    SpellCount++;
        return SpellCount;
    }

    public void ReplaceCard(Card card,boolean isHost){
        Random random = new Random();
        if (isHost) {
            if (returnSpellCount(HostDeck) > 2) {
                int r = random.nextInt(21);
                while (HostCards.get(r).IsSpell)
                    r = random.nextInt(21);
                HostDeck.set(HostDeck.indexOf(card),HostCards.get(r));
            }
            else{
                int r = random.nextInt(21);
                HostDeck.set(HostDeck.indexOf(card),HostCards.get(r));
            }
        }
        else {
            if (returnSpellCount(GuestDeck) > 2) {
                int r = random.nextInt(20);
                while (GuestCards.get(r).IsSpell)
                    r = random.nextInt(20);
                GuestDeck.set(GuestDeck.indexOf(card),GuestCards.get(r));
            }
            else{
                int r = random.nextInt(20);
                GuestDeck.set(GuestDeck.indexOf(card),GuestCards.get(r));
            }
        }
    }

    public void UpdateData(){
        GuestDamage = 0;
        HostDamage = 0;
        for (int i = 0; i < 21 ; i++){
            if (HostTiles[i].AttackDefence > 300 & GuestTiles[i].AttackDefence>300) {
                GuestTiles[i].IsDefended = true;
                HostTiles[i].IsDefended = true;
            }
            else if ( HostTiles[i].AttackDefence>GuestTiles[i].AttackDefence ) {
                HostTiles[i].IsDefended = false;
                GuestTiles[i].IsDefended = true;
            }
            else if ( HostTiles[i].AttackDefence<GuestTiles[i].AttackDefence ) {
                HostTiles[i].IsDefended = true;
                GuestTiles[i].IsDefended = false;
            }
            else {
                GuestTiles[i].IsDefended = true;
                HostTiles[i].IsDefended = true;
            }
            if (!GuestTiles[i].IsDefended)
                GuestDamage += GuestTiles[i].PlayerDamage;
            if (!HostTiles[i].IsDefended)
                 HostDamage+= HostTiles[i].PlayerDamage;
        }
    }

    public int getCharacterNumber(String character){
        if (character.equals("ALPHA LUPEX"))
            return 0;
        if (character.equals("HELIO CELON"))
            return 1;
        if (character.equals("A.N.F.O."))
            return 2;
        return 3;
    }

    public void TimeLine(){
        MaskGuestDeck = false;
        MaskHostDeck = false;
        Display(true);
        System.out.println("TimeLine started!");
        for (int i = 0; i < 21 & (HostHP > 0) & (GuestHP > 0); i++){
            System.out.println("Tile " + (i+1) + ":");
            System.out.println("Host HP: " + HostHP);
            System.out.println("Guest HP: " + GuestHP);
            System.out.println("Host card: " + HostTiles[i].CardName);
            System.out.println("Guest card: " + GuestTiles[i].CardName);
            System.out.println("Host card: " + HostTiles[i].AttackDefence);
            System.out.println("Guest card: " + GuestTiles[i].AttackDefence);
            if (!HostTiles[i].IsDefended)
                GuestHP -= HostTiles[i].PlayerDamage;
            if (!GuestTiles[i].IsDefended)
                HostHP -= GuestTiles[i].PlayerDamage;
            if (HostTiles[i].CardName.equals("Heal")){
                if (HostTiles[i].IsBuffed)
                    HostHP += 10;
                HostHP += 30;
            }
            if (GuestTiles[i].CardName.equals("Heal")){
                if (GuestTiles[i].IsBuffed)
                    GuestHP += 10;
                GuestHP += 30;
            }
        }
        if (HostHP <= 0 | GuestHP <= 0 )
            EndGame = true;
        if (EndGame)
            if (HostHP> GuestHP)
                WinnerIsHost = true;
    }
}
