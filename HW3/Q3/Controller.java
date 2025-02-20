import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Comparator;
public class Controller {
    Scanner sc = new Scanner(System.in);
    ArrayList <User> Users = new ArrayList<>();
    public ArrayList<Card> Cards = new ArrayList<>();
    public User CurrentUser;
    public User Opponent;
    public Arena arena;
    public int Menu = 0;
    public boolean HasPlayedCard = false;
    public int RemainingMoves = 3;
    public int BattleState = 0;
    public int turn;
    public boolean GameOver = false;
    public void run(){
        boolean end = false;
        while (!end){
            String command = sc.nextLine();
            if (Menu == 0){/*Register*/
                if(command.matches("register username (?<username>.+)" +
                        " password (?<password>.+)")){
                    Matcher matcher = getCommandMatcher(command,"register username " +
                            "(?<username>.+) password (?<password>.+)");
                    matcher.find();
                    SignIn(matcher);
                }
                else if (command.matches("login username (?<username>.+) password " +
                        "(?<password>.+)")){
                    Matcher matcher = getCommandMatcher(command,"login username " +
                            "(?<username>.+) password (?<password>.+)");
                    matcher.find();
                    Login(matcher);
                }
                else if (command.matches("Exit"))
                    end = true;
                else if (command.matches("show current menu"))
                    System.out.println("Register/Login Menu");
                else
                    System.out.println("Invalid command!");
            }
            else if (Menu == 1){/*Main*/
                if (command.matches("list of users"))
                    PrintList();
                else if (command.matches("scoreboard"))
                    ScoreBoard();
                else if (command.matches("logout")){
                    System.out.println("User " + CurrentUser.getName() + " logged out successfully!");
                    CurrentUser = null;
                    Menu = 0;
                }
                else if (command.matches("profile menu")){
                    System.out.println("Entered profile menu!");
                    Menu = 3;
                }
                else if (command.matches("shop menu")){
                    System.out.println("Entered shop menu!");
                    Menu = 2;
                }
                else if (command.matches("start game turns count " +
                        "(?<TurnsCount>.+) username (?<username>.+)")){
                    Matcher matcher = getCommandMatcher(command,
                            "start game turns count (?<TurnsCount>.+) username (?<username>.+)");
                    matcher.find();
                    StartGame(matcher);
                }
                else if (command.matches("show current menu"))
                    System.out.println("Main Menu");
                else
                    System.out.println("Invalid command!");
            }
            else if (Menu == 2){/*Shop*/
                if (command.matches("buy card (?<name>.+)")){
                    Matcher matcher = getCommandMatcher(command,"buy card (?<name>.+)");
                    matcher.find();
                    BuyCard(matcher);
                }
                else if (command.matches("sell card (?<name>.+)")){
                    Matcher matcher = getCommandMatcher(command,"sell card (?<name>.+)");
                    matcher.find();
                    SellCard(matcher);
                }
                else if (command.matches("back")){
                    System.out.println("Entered main menu!");
                    Menu = 1;
                }
                else if (command.matches("show current menu"))
                    System.out.println("Shop Menu");
                else
                    System.out.println("Invalid command!");
            }
            else if (Menu == 3){/*Profile*/
                if (command.matches("change password old password" +
                        " (?<oldpassword>.+) new password (?<newpassword>.+)")){
                    Matcher matcher = getCommandMatcher(command,
                            "change password old password (?<oldpassword>.+) " +
                                    "new password (?<newpassword>.+)") ;
                    matcher.find();
                    ChangePassword(matcher);
                }
                else if (command.matches("Info")){
                    Info();
                }
                else if (command.matches("remove from battle deck (?<name>.+)")){
                    Matcher matcher = getCommandMatcher(command,
                            "remove from battle deck (?<name>.+)");
                    matcher.find();
                    RemoveFromDeck(matcher);
                }
                else if (command.matches("add to battle deck (?<name>.+)")){
                    Matcher matcher = getCommandMatcher(command,
                            "add to battle deck (?<name>.+)");
                    matcher.find();
                    AddToDeck(matcher);
                }
                else if (command.matches("show battle deck"))
                    ShowDeck();
                else if (command.matches("back")){
                    System.out.println("Entered main menu!");
                    Menu = 1;
                }
                else if (command.matches("show current menu"))
                    System.out.println("Profile Menu");
                else
                    System.out.println("Invalid command!");
            }
            else {/*Game*/
                if (BattleState == 1){/*User turn*/
                    if (command.matches("show the hitpoints left of my opponent"))
                        ShowCastleHP(1);
                    else if (command.matches("show line info (?<direction>.+)")){
                        Matcher matcher = getCommandMatcher(command,
                                "show line info (?<direction>.+)");
                        matcher.find();
                        PrintLine(matcher);
                    }
                    else if (command.matches("number of cards to play")){
                        if (!HasPlayedCard)
                            System.out.println("You can play 1 cards more!");
                        else
                            System.out.println("You can play 0 cards more!");
                    }
                    else if (command.matches("number of moves left"))
                        System.out.println("You have " + RemainingMoves + " moves left!");
                    else if (command.matches("move troop in line (?<Column>.+) " +
                            "and row (?<row>.+) (?<direction>.+)")){
                        Matcher matcher = getCommandMatcher(command,
                                "move troop in line (?<Column>.+) and row (?<row>.+) (?<direction>.+)");
                        matcher.find();
                        MoveCard(matcher,true);
                    }
                    else if (command.matches("deploy troop (?<name>.+)" +
                            " in line (?<line>.+) and row (?<row>.+)")){
                        Matcher matcher = getCommandMatcher(command,
                                "deploy troop (?<name>.+) in line (?<line>.+) and row (?<row>.+)");
                        matcher.find();
                        DeployTroops(matcher,true);
                    }
                    else if (command.matches("deploy spell Fireball in line (?<line>.+)")){
                        Matcher matcher = getCommandMatcher(command,
                                "deploy spell Fireball in line (?<line>.+)");
                        matcher.find();
                        DeploySpell(matcher,true);
                    }
                    else if (command.equals("next turn")){
                        System.out.println("Player " + Opponent.getName() + " is now playing!");
                        HasPlayedCard = false;
                        RemainingMoves = 3;
                        BattleState = 2;
                    }
                    else if (command.matches("show current menu"))
                        System.out.println("Game Menu");
                    else
                        System.out.println("Invalid command!");
                }
                else if (BattleState == 2){/*Opponent turn*/
                    if (command.matches("show the hitpoints left of my opponent"))
                        ShowCastleHP(0);
                    else if (command.matches("show line info (?<direction>.+)")){
                        Matcher matcher = getCommandMatcher(command,
                                "show line info (?<direction>.+)");
                        matcher.find();
                        PrintLine(matcher);
                    }
                    else if (command.matches("number of cards to play")){
                        if (!HasPlayedCard)
                            System.out.println("You can play 1 cards more!");
                        else
                            System.out.println("You can play 0 cards more!");
                    }
                    else if (command.matches("number of moves left"))
                        System.out.println("You have " + RemainingMoves + " moves left!");
                    else if (command.matches("move troop in line (?<Column>.+) " +
                            "and row (?<row>.+) (?<direction>.+)")){
                        Matcher matcher = getCommandMatcher(command,
                                "move troop in line (?<Column>.+) and row (?<row>.+) (?<direction>.+)");
                        matcher.find();
                        MoveCard(matcher,false);
                    }
                    else if (command.matches("deploy troop (?<name>.+)" +
                            " in line (?<line>.+) and row (?<row>.+)")){
                        Matcher matcher = getCommandMatcher(command,
                                "deploy troop (?<name>.+) in line (?<line>.+) and row (?<row>.+)");
                        matcher.find();
                        DeployTroops(matcher,false);
                    }
                    else if (command.matches("deploy spell Fireball in line (?<line>.+)")){
                        Matcher matcher = getCommandMatcher(command,
                                "deploy spell Fireball in line (?<line>.+)");
                        matcher.find();
                        DeploySpell(matcher,false);
                    }
                    else if (command.equals("next turn")){
                        HasPlayedCard = false;
                        RemainingMoves = 3;
                        BattleState = 1;
                        turn--;
                        arena.TurnOver();
                        GameOver = arena.CastleCheck();
                        if (turn == 0 | GameOver) {
                            Menu = 1;
                            EndGame();
                        }
                        else
                            System.out.println("Player " + CurrentUser.getName() + " is now playing!");
                    }
                    else if (command.matches("show current menu"))
                        System.out.println("Game Menu");
                    else
                        System.out.println("Invalid command!");
                }
            }
        }
    }
    private Matcher getCommandMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }
    private void SignIn(Matcher matcher){
        String username = matcher.group("username");
        String password = matcher.group("password");
        if (isValidUserName(username)) {
            if (isValidPassword(password)) {
                if (getUserByName(username) == null){
                    Users.add(new User(username,password));
                    System.out.println("User " + username + " created successfully!");
                }
                else
                    System.out.println("Username already exists!");
            }
            else
                System.out.println("Incorrect format for password!");
        }
        else
            System.out.println("Incorrect format for username!");
    }
    private void CreateCards(){
        Cards.add(new Card("Fireball",0,1400,80));
        Cards.add(new Card("Archer",1900,800,80));
        Cards.add(new Card("Wizard",3300,1400,140));
        Cards.add(new Card("Dragon",3200,1100,160));
    }
    private void Login(Matcher matcher){
        String username = matcher.group("username");
        String password = matcher.group("password");
        if (isValidUserName(username)){
            if (isValidPassword(password)){
                if (getUserByName(username) != null ){
                    if (getUserByName(username).getPassword().equals(password)){
                        CurrentUser = getUserByName(username);
                        System.out.println("User " + username + " logged in!");
                        Menu = 1;
                        CreateCards();
                    }
                    else
                        System.out.println("Password is incorrect!");
                }
                else
                    System.out.println("Username doesn't exist!");
            }
            else
                System.out.println("Incorrect format for password!");
        }
        else
            System.out.println("Incorrect format for username!");
    }
    private void PrintList(){
        for (int i = 0; i < Users.size() ; i++)
            System.out.println("user " + (i+1) + ": " + Users.get(i).getName());
    }
    private void ScoreBoard(){
        ArrayList <User> users = new ArrayList<>(Users);
        if (isSorted(users))
            ListSorter(users);
        if (users.size()<5) {
            int i =0;
            for (User u : users) {
                i++;
                System.out.println(i + "- username: " + u.getName() + " level: "
                        + u.getLevel() + " experience: " + u.getExp());
            }
        }
        else {
            for (int i = 1;i<6;i++){
                for (User u : users) {
                    System.out.println(i + "- username: " + u.getName() + " level: "
                            + u.getLevel() + " experience: " + u.getExp());
                }
            }
        }
    }
    private void StartGame(Matcher matcher){
        String TurnsCount = matcher.group("TurnsCount");
        String username = matcher.group("username");
        if (isValidTurnsCount(TurnsCount)){
            if (isValidUserName(username)){
                if (getUserByName(username) != null){
                    BattleState = 1;
                    Opponent = getUserByName(username);
                    turn = Integer.parseInt(TurnsCount);
                    System.out.println("Battle started with user " + username);
                    arena = new Arena(Opponent.getSideCastleHP(),
                            Opponent.getMiddleCastleHP(),Opponent.getSideCastleHP(),
                            CurrentUser.getSideCastleHP(),CurrentUser.getMiddleCastleHP(),
                            CurrentUser.getSideCastleHP(),CurrentUser.getDeck(),Opponent.getDeck(),
                            CurrentUser.getSideCastleDamage(),CurrentUser.getMiddleCastleDamage(),
                            Opponent.getSideCastleDamage(),Opponent.getMiddleCastleDamage());
                    Menu = 4;
                }
                else
                    System.out.println("Username doesn't exist!");
            }
            else
                System.out.println("Incorrect format for username!");
        }
        else
            System.out.println("Invalid turns count!");
    }
    private void BuyCard(Matcher matcher){
        String name = matcher.group("name");
        if (getCardByName(name) != null){
            Card card = getCardByName(name);
            if (CurrentUser.getGold() >= card.Price){
                if (CurrentUser.getCardFromCards(name) == null){
                    CurrentUser.AddCardToCards(card.Clone());
                    System.out.println("Card " + name + " bought successfully!");
                }
                else
                    System.out.println("You have this card!");
            }
            else
                System.out.println("Not enough gold to buy " + name + "!");
        }
        else
            System.out.println("Invalid card name!");
    }
    private void SellCard(Matcher matcher){
        String name = matcher.group("name");
        if (getCardByName(name) != null){
            if ( getCardByName(name) != null ){
                if (CurrentUser.getCardFromCards(name) != null){
                    if (CurrentUser.getCardFromDeck(name) == null){
                        Card card = CurrentUser.getCardFromCards(name);
                        CurrentUser.RemoveCardFromCards(card);
                        System.out.println("Card " + name + " sold successfully!");
                        CurrentUser.AddGold( card.Price*3/4);
                    }
                    else
                        System.out.println("You cannot sell a card from your battle deck!");
                }
                else
                    System.out.println("You don't have this card!");
            }
            else
                System.out.println("You don't have this card!");
        }
        else
            System.out.println("Invalid card name!");
    }
    private void ChangePassword(Matcher matcher){
        String oldpassword = matcher.group("oldpassword");
        String newpassword = matcher.group("newpassword");
        if (CurrentUser.getPassword().equals(oldpassword)){
            if (isValidPassword(newpassword)){
                CurrentUser.setPassword(newpassword);
                System.out.println("Password changed successfully!");
            }
            else
                System.out.println("Incorrect format for new password!");
        }
        else
            System.out.println("Incorrect password!");
    }
    private void Info(){
        ArrayList <User> users = new ArrayList<>(Users);
        if (isSorted(users))
            ListSorter(users);
        int rank = 0;
            for (User u:users) {
                if (u.getName().equals(CurrentUser.getName()))
                    rank = users.indexOf(CurrentUser);
            }
            System.out.println("username: " + CurrentUser.getName());
            System.out.println("password: " + CurrentUser.getPassword());
            System.out.println("level: " + CurrentUser.getLevel());
            System.out.println("experience: " + CurrentUser.getExp());
            System.out.println("gold: " + CurrentUser.getGold());
            System.out.println("rank: " + (rank + 1));
    }
    private void RemoveFromDeck(Matcher matcher){
        String name = matcher.group("name");
        if (getCardByName(name) != null){
            if (CurrentUser.getCardFromDeck(name) != null){
                if (CurrentUser.getDeckSize() > 1){
                    CurrentUser.RemoveCardFromDeck(CurrentUser.getCardFromDeck(name));
                    System.out.println("Card " + name + " removed successfully!");
                }
                else
                    System.out.println("Invalid action: your battle deck will be empty!");
            }
            else
                System.out.println("This card isn't in your battle deck!");
        }
        else
            System.out.println("Invalid card name!");
    }
    private void AddToDeck(Matcher matcher){
        String name = matcher.group("name");
        if ( getCardByName(name) != null ){
            if (CurrentUser.getCardFromCards(name) != null){
                if (CurrentUser.getCardFromDeck(name) == null){
                    if (CurrentUser.getDeckSize() < 4){
                        CurrentUser.AddCardToDeck(CurrentUser.getCardFromCards(name));
                        System.out.println("Card " + name + " added successfully!");
                    }
                    else
                        System.out.println("Invalid action: your battle deck is full!");
                }
                else
                    System.out.println("This card is already in your battle deck!");
            }
            else
                System.out.println("You don't have this card!");
        }
        else
            System.out.println("Invalid card name!");
    }
    private void ShowDeck(){
        ArrayList <Card> cards = new ArrayList<>(CurrentUser.getDeck());
        Collections.sort(cards,Comparator.comparing(Card::getType));
        for (Card c:cards)
            System.out.println(c.getType());
    }
    private void ShowCastleHP(int Row){
        int MiddleCastle;
        int RightCastle;
        int LeftCastle;
        MiddleCastle = arena.CastlesHP[Row][1];
        if (MiddleCastle == 0)
            MiddleCastle = -1;
        RightCastle = arena.CastlesHP[Row][2];
        if (RightCastle == 0)
            RightCastle = -1;
        LeftCastle = arena.CastlesHP[Row][0];
        if (LeftCastle == 0)
            LeftCastle = -1;
        System.out.println("middle castle: " + MiddleCastle);
        System.out.println("left castle: " + LeftCastle);
        System.out.println("right castle: " + RightCastle);
    }
    private void PrintLine(Matcher matcher){
        String direction = matcher.group("direction");
        if (isValidLine(direction)) {
            System.out.println(direction + " line:");
            LineData(direction);
        }
        else
            System.out.println("Incorrect line direction!");
    }
    private void LineData(String direction){
        int Column = LineTranslator(direction);
        arena.PrintLine(Column,CurrentUser.getName(),Opponent.getName());
    }
    private void MoveCard(Matcher matcher,boolean IsHost){
        String column = matcher.group("Column");
        String row = matcher.group("row");
        String direction = matcher.group("direction");
        if (isValidLine(column)){
            if (row.matches("\\d+")){
                int Row = Integer.parseInt(row);
                if (Row < 16 & Row > 0){
                    if (isValidDirection(direction)){
                        if (RemainingMoves > 0){
                            int Column = LineTranslator(column);
                            if (arena.Tiles[Row - 1][Column].getCardFromUser(IsHost) != null){
                                int Direction = DirectionTranslator(direction);
                                if (Row + Direction < 16 & Row + Direction > 0){
                                    Card C1 = arena.Tiles[Row - 1][Column].getCardFromUser(IsHost);
                                    String Name = C1.getType();
                                    System.out.println(Name + " moved successfully to row "
                                            + (Row + Direction) + " in line " + column);
                                    arena.Tiles[Row + Direction - 1][Column].AddCard(C1);
                                    arena.Tiles[Row - 1][Column].RemoveCard(C1);
                                    RemainingMoves--;
                                }
                                else
                                    System.out.println("Invalid move!");
                            }
                            else
                                System.out.println("You don't have any troops in this place!");
                        }
                        else
                            System.out.println("You are out of moves!");
                    }
                    else
                        System.out.println("you can only move troops upward or downward!");
                }
                else
                    System.out.println("Invalid row number!");
            }
            else
                System.out.println("Invalid row number!");
        }
        else
            System.out.println("Incorrect line direction!");
    }
    private void DeployTroops(Matcher matcher,boolean IsHost){
        String name = matcher.group("name");
        String line = matcher.group("line");
        String row = matcher.group("row");
        if (isValidTroopName(name)){
            if (arena.getCardFromUser(name,IsHost) != null){
                if (isValidLine(line)){
                    if (row.matches("\\d+")){
                        int Row = Integer.parseInt(row);
                        if (Row <16 & Row >0){
                            if (IsHost){
                                if (Row < 5){
                                    if (!HasPlayedCard){
                                        int Column = LineTranslator(line);
                                        Card c = arena.getCardFromUser(name,IsHost);
                                        arena.Tiles[Row-1][Column].AddCard(c);
                                        System.out.println("You have deployed " + name +" successfully!");
                                        HasPlayedCard = true;
                                    }
                                    else
                                        System.out.println("You have deployed a troop or spell this turn!");
                                }
                                else
                                    System.out.println("Deploy your troops near your castles!");
                            }
                            else {
                                if (Row > 11){
                                    if (!HasPlayedCard){
                                        int Column = LineTranslator(line);
                                        Card c = arena.getCardFromUser(name,IsHost);
                                        arena.Tiles[Row+1][Column].AddCard(c);
                                        System.out.println("You have deployed " + name +" successfully!");
                                        HasPlayedCard = true;
                                    }
                                    else
                                        System.out.println("You have deployed a troop or spell this turn!");
                                }
                                else
                                    System.out.println("Deploy your troops near your castles!");
                            }
                        }
                        else
                            System.out.println("Invalid row number!");
                    }
                    else
                        System.out.println("Invalid row number!");
                }
                else
                    System.out.println("Incorrect line direction!");
            }
            else
                System.out.println("You don't have " + name + " card in your battle deck!");
        }
        else
            System.out.println("Invalid troop name!");
    }
    private void DeploySpell(Matcher matcher,boolean IsHost){
        String line = matcher.group("line");
        if (isValidLine(line)){
                if (arena.getCardFromUser("Fireball",IsHost) != null){
                    if (!HasPlayedCard){
                        int column = LineTranslator(line);
                        int row = 0;
                        if (IsHost)
                            row = 1;
                        if (arena.getCastleHP(row,column) > 0){
                            Card c = arena.getCardFromUser("Fireball",IsHost);
                            arena.DamageToCastle(row,column,c.Damage);
                            GameOver = arena.CastleCheck();
                            System.out.println("You have deployed Fireball successfully!");
                            HasPlayedCard = true;
                        }
                        else
                            System.out.println("This castle is already destroyed!");
                    }
                    else
                        System.out.println("You have deployed a troop or spell this turn!");
            }
                else
                    System.out.println("You don't have Fireball card in your battle deck!");
        }
        else
            System.out.println("Incorrect line direction!");
    }
    private void EndGame(){
        int HostRemainingHP = arena.getCastlesHP(true);
        int GuestRemainingHP = arena.getCastlesHP(false);
        int HostRemainingCastles = arena.getCastlesCount(true);
        int GuestRemainingCastles = arena.getCastlesCount(false);
            CurrentUser.addExp(HostRemainingHP);
            Opponent.addExp(GuestRemainingHP);
            CurrentUser.addGold(20*(3-GuestRemainingCastles));
            Opponent.addGold(20*(3-HostRemainingCastles));
            if (HostRemainingHP > GuestRemainingHP)
                System.out.println("Game has ended. Winner: " + CurrentUser.getName());
            else if (HostRemainingHP < GuestRemainingHP)
                System.out.println("Game has ended. Winner: " + Opponent.getName());
            else
                System.out.println("Game has ended. Result: Tie");
    }
    public boolean isValidUserName(String name){
        return name.matches("[a-zA-Z]+");
    }
    public  boolean isValidPassword(String password) {
        // Check length (minimum 8, maximum 20)
        if (password.length() < 8 || password.length() > 20) {
            return false;
        }
        // Check for spaces
        if (password.contains(" ")) {
            return false;
        }
        // Check for at least one uppercase letter
        boolean hasUppercase = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
                break;
            }
        }
        if (!hasUppercase) {
            return false;
        }
        // Check for at least one lowercase letter
        boolean hasLowercase = false;
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                hasLowercase = true;
                break;
            }
        }
        if (!hasLowercase) {
            return false;
        }
        // Check for at least one digit
        boolean hasDigit = false;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
                break;
            }
        }
        if (!hasDigit) {
            return false;
        }
        // Check for at least one special character
        boolean hasSpecialChar = false;
        String specialChars = "!@#$%^&*";
        for (char c : password.toCharArray()) {
            if (specialChars.contains(String.valueOf(c))) {
                hasSpecialChar = true;
                break;
            }
        }
        if (!hasSpecialChar) {
            return false;
        }
        // Check if not starting with a digit
        if (Character.isDigit(password.charAt(0))) {
            return false;
        }
        // All checks passed
        return true;
    }
    public boolean isValidTurnsCount(String TurnsCount){
        if (!TurnsCount.matches("\\d+"))
            return false;
        else {
            int Turns_Count = Integer.parseInt(TurnsCount);
            return  (5 <= Turns_Count) & (Turns_Count <= 30);
        }
    }
    public boolean isValidTroopName(String name){
        return name.equals("Dragon") | name.equals("Archer") | name.equals("Wizard");
    }
    public boolean isValidDirection(String direction){
        return (direction.equals("upward") | direction.equals("downward"));
    }
    public boolean isValidLine(String line){
        return (line.equals("left") | line.equals("right") | line.equals("middle"));
    }
    public User getUserByName(String Name){
        for (User u:Users)
            if (u.getName().equals(Name))
                return u;
        return null;
    }
    public Card getCardByName(String name){
        for (Card c:Cards)
            if (c.getType().equals(name))
                return c;
        return null;
    }
    public int LineTranslator(String Line){
        if(Line.equals("right"))
            return 2;
        else if (Line.equals("middle"))
            return 1;
        else
            return 0;
    }
    public int DirectionTranslator(String direction){
        if (direction.equals("upward"))
            return 1;
        else
            return -1;
    }
    public void ListSorter(ArrayList<User> users){
        Comparator <User> userComparator = Comparator.comparing(User::getLevel).reversed();
        userComparator = userComparator.thenComparing(User::getExp).reversed();
        userComparator = userComparator.thenComparing(User::getName);
        users.sort(userComparator);
    }
    public boolean isSorted(ArrayList<User> users) {
        for (int i = 0; i < users.size() - 1; i++) {
            User current = users.get(i);
            User next = users.get(i + 1);

            if (current.getLevel() < next.getLevel()) {
                return true;
            } else if (current.getLevel() == next.getLevel()) {
                if (current.getExp() < next.getExp()) {
                    return true;
                } else if (current.getExp() == next.getExp()) {
                    if (current.getName().compareTo(next.getName()) > 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
