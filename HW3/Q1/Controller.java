import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Controller {
    ArrayList <Country> countries = new ArrayList<>();
    ArrayList <Corps> corps = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    public void run(){
        boolean end = false;
        while (!end){
            String command = sc.nextLine();
            if (command.matches("create country (?<name>.+) " +
                    "(?<nationality>.+)")){
                Matcher matcher = getCommandMatcher(command,
                        "create country (?<name>.+) (?<nationality>.+)");
                matcher.find();
                CreateCountry(matcher);
            }
            else if (command.matches("create corps (?<infantry>.+) " +
                    "(?<cavalry>.+) (?<artillery>.+) (?<officer>.+) for " +
                    "(?<country>.+) (?<number>.+)")){
                Matcher matcher = getCommandMatcher(command,"create corps (?<infantry>.+) " +
                        "(?<cavalry>.+) (?<artillery>.+) (?<officer>.+) for " +
                        "(?<country>.+) (?<number>.+)");
                matcher.find();
                CreateCorps(matcher);
            }
            else if (command.matches("create army (?<number>.+) " +
                    "(?<leader>.+) for (?<country>.+) in (?<place>.+)")){
                Matcher matcher = getCommandMatcher(command,
                        "create army (?<number>.+) (?<leader>.+) for " +
                                "(?<country>.+) in (?<place>.+)");
                matcher.find();
                CreateArmyWithTerrain(matcher);
            }
            else if (command.matches("create army (?<number>.+) " +
                    "(?<leader>.+) for (?<country>.+)")){
                Matcher matcher = getCommandMatcher(command,
                        "create army (?<number>.+) (?<leader>.+) for " +
                                "(?<country>.+)");
                matcher.find();
                CreateArmyWithoutTerrain(matcher);
            }
            else if (command.matches("set place for (?<country>.+) " +
                    "(?<number>.+) in (?<place>.+)")){
                Matcher matcher = getCommandMatcher(command,
                        "set place for (?<country>.+) (?<number>.+) in (?<place>.+)");
                matcher.find();
                SetArmyPlace(matcher);
            }
            else if (command.matches("add corps (?<CorpsNum>.+) " +
                    "to army (?<ArmyNum>.+) of (?<country>.+)")){
                Matcher matcher = getCommandMatcher(command,
                        "add corps (?<CorpsNum>.+) to army (?<ArmyNum>.+) of (?<country>.+)");
                matcher.find();
                AddCorps(matcher);
            }
            else if (command.matches("print army with details (?<number>.+) (?<country>.+)")){
                Matcher matcher = getCommandMatcher(command,
                        "print army with details (?<number>.+) (?<country>.+)");
                matcher.find();
                PrintDetailedArmy(matcher);
            }
            else if (command.matches("print army (?<number>.+) (?<country>.+)")){
                Matcher matcher = getCommandMatcher(command,
                        "print army (?<number>.+) (?<country>.+)");
                matcher.find();
                PrintArmy(matcher);
            }
            else if (command.matches("print country with details (?<country>.+)")){
                Matcher matcher = getCommandMatcher(command,
                        "print country with details (?<country>.+)");
                matcher.find();
                PrintDetailedCountry(matcher);
            }
            else if (command.matches("print country (?<country>.+)")){
                Matcher matcher = getCommandMatcher(command,"print country (?<country>.+)");
                matcher.find();
                PrintCountry(matcher);
            }
            else if (command.matches("print score of (?<country>.+) (?<ArmyNumber>.+) " +
                    "(?<CorpsNumber>.+)")){
                Matcher matcher = getCommandMatcher(command,
                        "print score of (?<country>.+) (?<ArmyNumber>.+) (?<CorpsNumber>.+)");
                matcher.find();
                PrintCorpScore(matcher);
            }
            else if (command.matches("print score of (?<country>.+) (?<ArmyNumber>.+)")){
                Matcher matcher = getCommandMatcher(command,
                        "print score of (?<country>.+) (?<ArmyNumber>.+)");
                matcher.find();
                PrintArmyScore(matcher);
            }
            else if (command.matches("print score of (?<country>.+)")){
                Matcher matcher = getCommandMatcher(command,"print score of (?<country>.+)");
                matcher.find();
                PrintCountryScore(matcher);
            }
            else if (command.matches("(?<country>.+) join union with (?<CountryList>\\[.+)")){
                Matcher matcher = getCommandMatcher(command,
                        "(?<country>.+) join union with (?<CountryList>\\[.+)");
                matcher.find();
                MultipleUnion(matcher);
            }
            else if (command.matches("(?<country1>.+) join union with (?<country2>.+)")){
                Matcher matcher = getCommandMatcher(command,
                        "(?<country1>.+) join union with (?<country2>.+)");
                matcher.find();
                SingleUnion(matcher);
            }
            else if (command.matches("(?<country1>.+) made enemy of (?<country2>.+)")){
                Matcher matcher = getCommandMatcher(command,
                        "(?<country1>.+) made enemy of (?<country2>.+)");
                matcher.find();
                SingleEnemy(matcher);
            }
            else if (command.matches("show friends of (?<country>.+)")){
                Matcher matcher = getCommandMatcher(command,
                        "show friends of (?<country>.+)");
                matcher.find();
                ShowFriends(matcher);
            }
            else if (command.matches("show enemies of (?<country>.+)")){
                Matcher matcher = getCommandMatcher(command,
                        "show enemies of (?<country>.+)");
                matcher.find();
                ShowEnemies(matcher);
            }
            else if (command.matches("war between (?<country1>.+)" +
                    " and (?<country2>.+) in (?<place>.+)")){
                Matcher matcher = getCommandMatcher(command,
                        "war between (?<country1>.+) and (?<country2>.+) in (?<place>.+)");
                matcher.find();
                WarWithPlace(matcher);
            }
            else if (command.matches("war between (?<country1>.+) " +
                    "and (?<country2>.+)")){
                Matcher matcher = getCommandMatcher(command,
                        "war between (?<country1>.+) and (?<country2>.+)");
                matcher.find();
                WarWithoutPlace(matcher);
            }
            else if (command.matches("end"))
                end = true;
            else
                System.out.println("invalid input!");
        }
    }

    private Matcher getCommandMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }
    private void CreateCountry(Matcher matcher){
        String name = matcher.group("name");
        String nationality = matcher.group("nationality");
        if (FindCountryByName(name) == null) {
            if (NationalityValidation(nationality)){
                System.out.println("country " + name +" created");
                countries.add(new Country(name, nationality));
            }
            else
                System.out.println("invalid input!");
        }
        else
            System.out.println("country was created");

    }
    private void CreateCorps(Matcher matcher){
        String infantry = matcher.group("infantry");
        String cavalry = matcher.group("cavalry");
        String artillery = matcher.group("artillery");
        String officer = matcher.group("officer");
        String country = matcher.group("country");
        String number = matcher.group("number");
        if (infantry.matches("\\d+") & cavalry.matches("\\d+") &
                artillery.matches("\\d+") & RankCheck(officer) & CorpsNumberCheck(number)){
            int infantryNum = Integer.parseInt(infantry);
            int cavalryNum = Integer.parseInt(cavalry);
            int artilleryNum = Integer.parseInt(artillery);
            if (FindCountryByName(country) != null){
                if (FindCorp(number,country) == null){
                    if (QuantityCheck(artilleryNum,cavalryNum,infantryNum)){
                        System.out.println("corps " + number + " created successfully!");
                        corps.add(new Corps(officer,number,infantryNum,cavalryNum,artilleryNum,country));
                    }
                    else
                        System.out.println("cannot have more than 30k in a corps!");
                }
                else
                    System.out.println("this country already has this corps!");
            }
            else
                System.out.println("country was not found!");
        }
        else
            System.out.println("invalid input!");
    }
    private void CreateArmyWithoutTerrain(Matcher matcher){
        String number = matcher.group("number");
        String leader = matcher.group("leader");
        String country = matcher.group("country");
        if (FindCountryByName(country) != null){
            if (number.matches("\\d+")){
                int num = Integer.parseInt(number);
                if (FindCountryByName(country).getArmyByNumber(num) == null){
                    System.out.println("army created successfully!");
                    FindCountryByName(country).addArmy(new Army(leader,num,country));
                }
                else
                    System.out.println("this country already has this army!");
            }
            else
                System.out.println("invalid input!");
        }
        else
            System.out.println("country was not found!");
    }
    private void CreateArmyWithTerrain(Matcher matcher){
        String number = matcher.group("number");
        String leader = matcher.group("leader");
        String country = matcher.group("country");
        String place = matcher.group("place");
        if (FindCountryByName(country) != null){
            if (number.matches("\\d+")){
                int num = Integer.parseInt(number);
                if (FindCountryByName(country).getArmyByNumber(num) == null){
                    System.out.println("army created successfully!");
                    FindCountryByName(country).addArmy(new Army(leader,num,country,place));
                }
                else
                    System.out.println("this country already has this army!");
            }
            else
                System.out.println("invalid input!");
        }
        else
            System.out.println("country was not found!");
    }
    private void SetArmyPlace(Matcher matcher){
        String country = matcher.group("country");
        String number = matcher.group("number");
        String place = matcher.group("place");
        if (FindCountryByName(country) != null){
            if (number.matches("\\d+")) {
                int num = Integer.parseInt(number);
                if (FindCountryByName(country).getArmyByNumber(num) != null){
                    FindCountryByName(country).getArmyByNumber(num).setTerrain(place);
                    System.out.println("set successfully!");
                }
                else
                    System.out.println("army was not found!");
            }
            else
                System.out.println("army was not found!");
        }
        else
            System.out.println("country was not found!");
    }
    private void AddCorps(Matcher matcher){
        String country = matcher.group("country");
        String CorpsNum = matcher.group("CorpsNum");
        String ArmyNum = matcher.group("ArmyNum");
        if (FindCountryByName(country) != null){
            if (ArmyNum.matches("\\d+")){
                int armyNum = Integer.parseInt(ArmyNum);
                if (FindCountryByName(country).getArmyByNumber(armyNum) != null){
                    if (CorpsNumberCheck(CorpsNum)){
                        if (FindCorp(CorpsNum,country) != null){
                            if (FindCountryByName(country).getCorpsByNumber(CorpsNum) == null){
                                FindCountryByName(country).getArmyByNumber(armyNum)
                                        .addCorps(FindCorp(CorpsNum,country));
                                System.out.println("corps added to army successfully!");
                            }
                            else
                                System.out.println("this corps is in an army!");
                        }
                        else
                            System.out.println("corps was not found!");
                    }
                    else
                        System.out.println("corps was not found!");
                }
                else
                    System.out.println("army was not found!");
            }
            else
                System.out.println("army was not found!");
        }
        else
            System.out.println("country was not found!");
    }
    private void PrintArmy(Matcher matcher){
        String number = matcher.group("number");
        String  country = matcher.group("country");
        if (FindCountryByName(country) != null){
            if (number.matches("\\d+")){
                int Num =  Integer.parseInt(number);
                if (FindCountryByName(country).getArmyByNumber(Num) != null){
                    String Leader = FindCountryByName(country).getArmyByNumber(Num).getLeader();
                    int corps = FindCountryByName(country).getArmyByNumber(Num).getCorpsNumber();
                    System.out.println(Leader + " " + corps);
                }
                else
                    System.out.println("army was not found!");
            }
            else
                System.out.println("army was not found!");
        }
        else
            System.out.println("country was not found!");
    }
    private void PrintDetailedArmy(Matcher matcher){
        String number = matcher.group("number");
        String  country = matcher.group("country");
        if (FindCountryByName(country) != null){
            if (number.matches("\\d+")){
                int Num =  Integer.parseInt(number);
                if (FindCountryByName(country).getArmyByNumber(Num) != null){
                    String Leader = FindCountryByName(country).getArmyByNumber(Num).getLeader();
                    int corpsnumber = FindCountryByName(country).getArmyByNumber(Num).getCorpsNumber();
                    System.out.println(Leader + " " + corpsnumber);
                    ArrayList<Corps> corps1 = FindCountryByName(country).getArmyByNumber(Num).getCorps();
                    Comparator <Corps> CorpsSorter = Comparator.comparing(Corps::NumberTranslator);
                    Collections.sort(corps1,CorpsSorter);
                    if (corps1.size() > 3 )
                        for (int i = 0; i < 3 ; i++){
                            Corps C = corps1.get(i);
                            System.out.println("\t" + C.getInfantryUnits() + " " +
                                    C.getCavalryUnits() + " " + C.getArtilleryUnits() + " "
                                    + C.getOfficer() + " " + C.getTotalQuantity());
                        }
                    else
                        for (Corps C:corps1){
                            System.out.println("\t" + C.getInfantryUnits() + " " +
                                    C.getCavalryUnits() + " " + C.getArtilleryUnits() + " "
                                    + C.getOfficer() + " " + C.getTotalQuantity());
                        }
                }
                else
                    System.out.println("army was not found!");
            }
            else
                System.out.println("army was not found!");
        }
        else
            System.out.println("country was not found!");
    }
    private void PrintCountry(Matcher matcher){
        String country = matcher.group("country");
        if (FindCountryByName(country) != null){
            Country country1 = FindCountryByName(country);
            System.out.print(country1.getNationality() + " " + country1.getArmiesNumber() + " ");
            ArrayList <Army> armies = country1.getArmies();
            Comparator <Army> armyComparator = Comparator.comparing(Army::getNumber);
            Collections.sort(armies,armyComparator);
            String out = "";
            for (Army i:armies)
                out += i.getCorpsNumber() + " ";
            out = out.substring(0,out.length()-1);
            System.out.println(out);
        }
        else
            System.out.println("country was not found!");
    }
    private void PrintDetailedCountry(Matcher matcher){
        String country = matcher.group("country");
        if (FindCountryByName(country) != null){
            Country country1 = FindCountryByName(country);
            System.out.print(country1.getNationality() + " " + country1.getArmiesNumber() + " ");
            ArrayList <Army> armies = country1.getArmies();
            Comparator <Army> armyComparator = Comparator.comparing(Army::getNumber);
            Collections.sort(armies,armyComparator);
            String out0 = "";
            for (Army i:armies)
                out0 += i.getCorpsNumber() + " ";
            out0 = out0.substring(0,out0.length()-1);
            System.out.println(out0);
            String out = "";
            for (Army i:armies)
                out += i.getLeader() + " ";
            out = out.substring(0,out.length()-1);
            System.out.println(out);
        }
        else
            System.out.println("country was not found!");
    }
    private void PrintCorpScore(Matcher matcher){
        String country = matcher.group("country");
        String ArmyNumber = matcher.group("ArmyNumber");
        String CorpsNumber = matcher.group("CorpsNumber");
        if (FindCountryByName(country) != null){
            Country country1 = FindCountryByName(country);
            if (ArmyNumber.matches("\\d+")){
                int ArmyNum = Integer.parseInt(ArmyNumber);
                if (country1.getArmyByNumber(ArmyNum) != null ){
                    Army army = country1.getArmyByNumber(ArmyNum);
                    if (CorpsNumberCheck(CorpsNumber)){
                        if (FindCorp(CorpsNumber,country) != null ){
                            if (army.getCorpsByNumber(CorpsNumber) != null){
                                Corps corps = army.getCorpsByNumber(CorpsNumber);
                                System.out.println(army.getCorpsScore(corps));
                            }
                            else
                                System.out.println("this corps is not in this army!");
                        }
                        else
                            System.out.println("corps was not found!");
                    }
                    else
                        System.out.println("corps was not found!");
                }
                else
                    System.out.println("army was not found!");
            }
            else
                System.out.println("army was not found!");
        }
        else
            System.out.println("country was not found!");
    }
    private void PrintArmyScore(Matcher matcher){
        String country = matcher.group("country");
        String ArmyNumber = matcher.group("ArmyNumber");
        if (FindCountryByName(country) != null){
            Country country1 = FindCountryByName(country);
            if (ArmyNumber.matches("\\d+")){
                int ArmyNum = Integer.parseInt(ArmyNumber);
                if (country1.getArmyByNumber(ArmyNum) != null ){
                    int Score = country1.getArmyByNumber(ArmyNum).getScore();
                    System.out.println(Score);
                }
                else
                    System.out.println("army was not found!");
            }
            else
                System.out.println("army was not found!");
        }
        else
            System.out.println("country was not found!");
    }
    private void PrintCountryScore(Matcher matcher){
        String country = matcher.group("country");
        if (FindCountryByName(country) != null)
            System.out.println(FindCountryByName(country).getScore());
        else
            System.out.println("country was not found!");
    }
    private void SingleUnion(Matcher matcher){
        String country1 = matcher.group("country1");
        String country2 = matcher.group("country2");
        if (FindCountryByName(country1) != null & FindCountryByName(country2) != null){
            Country country01 = FindCountryByName(country1);
            Country country02 = FindCountryByName(country2);
            if (!country01.isEnemy(country02) & !country01.isFriend(country02) &
                    !country01.isEnemyOfFriend(country02) & !country02.isEnemyOfFriend(country01)){
                System.out.println("unionized successfully!");
                country01.addFriend(country02);
                country02.addFriend(country01);
            }
            else
                System.out.println("something went wrong!");
        }
        else
            System.out.println("country was not found!");
    }
    private void SingleEnemy(Matcher matcher){
        String country1 = matcher.group("country1");
        String country2 = matcher.group("country2");
        if (FindCountryByName(country1) != null & FindCountryByName(country2) != null){
            Country country01 = FindCountryByName(country1);
            Country country02 = FindCountryByName(country2);
            if (!country01.isEnemy(country02) & !country01.isFriend(country02) &
                    !country01.isEnemyOfFriend(country02) & !country02.isEnemyOfFriend(country01)){
                System.out.println("enemy made successfully!");
                country01.addEnemy(country02);
                country02.addEnemy(country01);
            }
            else
                System.out.println("something went wrong!");
        }
        else
            System.out.println("country was not found!");
    }
    private void MultipleUnion(Matcher matcher){
        String country = matcher.group("country");
        String CountryList = matcher.group("CountryList");
        String [] Country_List = CountryListProcessor(CountryList);
        if (FindCountryByName(country) != null){
            Country Country1 = FindCountryByName(country);
            boolean AllExist = true;
            for (String s:Country_List)
                if (AllExist)
                    AllExist = (FindCountryByName(s) != null);
            if (AllExist){
                boolean ListClear = true;
                for (int i = 0 ;i<Country_List.length;i++) {
                    Country country01 = FindCountryByName(Country_List[i]);
                    for (int j = i + 1; j < Country_List.length; j++) {
                        Country country02 = FindCountryByName(Country_List[j]);
                        if (ListClear)
                            ListClear = (!country01.isEnemy(country02));
                    }
                }
                if (ListClear){
                    boolean areFriend = false;
                    boolean areEnemyOfFriend = false;
                    for (String s:Country_List){
                        Country country1 = FindCountryByName(s);
                        if (!areFriend & !areEnemyOfFriend) {
                            areFriend = Country1.isFriend(country1);
                            areEnemyOfFriend = Country1.isEnemyOfFriend(country1);
                        }
                    }
                    if (!areFriend & !areEnemyOfFriend){
                        /*making everybody friend of each other*/
                        for (int i = 0 ;i<Country_List.length;i++) {
                            Country country01 = FindCountryByName(Country_List[i]);
                            for (int j = i + 1; j < Country_List.length; j++) {
                                Country country02 = FindCountryByName(Country_List[j]);
                                country01.addFriend(country02);
                                country02.addFriend(country01);
                            }
                        }
                        System.out.println("unionized successfully! ");
                        /*making everybody friend of main country*/
                        for (String s:Country_List){
                            Country country1 = FindCountryByName(s);
                            country1.addFriend(Country1);
                            Country1.addFriend(country1);
                        }
                    }
                    else
                        System.out.println("something went wrong!");
                }
                else
                    System.out.println("something went wrong!");
            }
            else
                System.out.println("country was not found!");
        }
        else
            System.out.println("country was not found!");
    }
    private void ShowFriends(Matcher matcher){
        String country = matcher.group("country");
        if (FindCountryByName(country)!=null){
            Country country1 = FindCountryByName(country);
            ArrayList <Country> friends = country1.getFriends();
            Comparator <Country> FriendsSorter = Comparator.comparing(Country::getScore);
            Collections.sort(friends,FriendsSorter);
            for (Country c:friends)
                System.out.println(c.getName());
        }
        else
            System.out.println("country was not found!");
    }
    private void ShowEnemies(Matcher matcher){
        String country = matcher.group("country");
        if (FindCountryByName(country)!=null){
            Country country1 = FindCountryByName(country);
            ArrayList <Country> enemies = country1.getEnemies();
            Comparator <Country> EnemiesSorter = Comparator.comparing(Country::getScore);
            Collections.sort(enemies,EnemiesSorter);
            for (Country c:enemies)
                System.out.println(c.getName());
        }
        else
            System.out.println("country was not found!");
    }
    private void WarWithoutPlace(Matcher matcher){
        String country1 = matcher.group("country1");
        String country2 = matcher.group("country2");
        if (FindCountryByName(country1) != null & FindCountryByName(country2) != null){
            Country Loser;
            if (FindCountryByName(country1).getScore() > FindCountryByName(country2).getScore()) {
                System.out.println(FindCountryByName(country1).getName());
                Loser = FindCountryByName(country2);
            }
            else {
                System.out.println(FindCountryByName(country2).getName());
                Loser = FindCountryByName(country1);
            }
            Loser.LostWarWithoutPlace();
        }
        else
            System.out.println("country was not found!");
    }
    private void WarWithPlace(Matcher matcher){
        String country1 = matcher.group("country1");
        String country2 = matcher.group("country2");
        String place = matcher.group("place");
        if (FindCountryByName(country1) != null & FindCountryByName(country2) != null){
            Country Loser;
            if (FindCountryByName(country1).getScoreByTerrain(place) >
                    FindCountryByName(country2).getScoreByTerrain(place)) {
                System.out.println(FindCountryByName(country1).getName());
                Loser = FindCountryByName(country2);
            }
            else {
                System.out.println(FindCountryByName(country2).getName());
                Loser = FindCountryByName(country1);
            }
            Loser.LostWarWithPlace(place);
        }
        else
            System.out.println("country was not found!");
    }
    private boolean NationalityValidation(String nationality){
        return nationality.equals("French") | nationality.equals("British") |
                nationality.equals("Austrian") | nationality.equals("Prussian") | nationality.equals("Russian");
    }
    private Country FindCountryByName(String name){
        for (Country i:countries)
            if (i.getName().equalsIgnoreCase(name))
                return i;
        return null;
    }
    private Corps FindCorp(String number,String country){
        for (Corps i:corps)
            if (i.getNumber().equals(number) & i.getCountry().equals(country))
                return i;
        return null;
    }
    private boolean RankCheck(String officer){
        Officer [] OfficerList = Officer.values();
        for (Officer i:OfficerList)
            if (i.name().equals(officer))
                return true;
        return false;
    }
    private boolean QuantityCheck(int artillery,int cavalry,int infantry){
        return (artillery*10 + cavalry*400 + infantry*1000 <= 30000);
    }
    private boolean CorpsNumberCheck(String number){
        return (number.equals("I") | number.equals("II") |
                number.equals("III") | number.equals("IV"));
    }
    private String [] CountryListProcessor(String input){
        String [] temp = input.substring(1,input.length()-1).split(",");
        for (int i=0;i<temp.length;i++)
            temp[i] = temp[i].substring(1,temp[i].length()-1);
        return temp;
    }
}
enum Terrain{
    Forest(2,5,2),
    Plain(1,0,2),
    Hill(1,-1,3),
    Mountain(1,-2,1);
    private final int ArtilleryScore;
    private final int CavalryScore;
    private final int InfantryScore;
    Terrain(int artilleryScore,int cavalryScore,int infantryScore){
        this.ArtilleryScore = artilleryScore;
        this.CavalryScore = cavalryScore;
        this.InfantryScore = infantryScore;
    }
    public int getArtilleryScore(){
        return ArtilleryScore;
    }
    public int getCavalryScore(){
        return CavalryScore;
    }
    public int getInfantryScore(){return InfantryScore;}
}
enum Officer{
    corporal(1),sergent(4),lieutenant(7),
    capitan(12),colonel(18),general(22),marshal(25);
    private final int Score;
    Officer(int Score){
        this.Score = Score;
    }
    public int getScore(){
        return Score;
    }
}
