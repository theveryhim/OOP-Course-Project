import java.util.ArrayList;

public class Country {
    private final String Name;
    private final String Nationality;
    private final ArrayList<Army> armies = new ArrayList<>();
    private final ArrayList<Country> enemies = new ArrayList<>();
    private final ArrayList<Country> friends = new ArrayList<>();
    public Country(String name,String nationality){
        this.Name = name;
        this.Nationality = nationality;
    }
    public String getName(){
        return Name;
    }
    public String getNationality(){
        return Nationality;
    }
    public void addEnemy(Country country){
        if (!enemies.contains(country))
            enemies.add(country);
    }
    public void addFriend(Country country){
        if (!friends.contains(country))
            friends.add(country);
    }
    public void addArmy(Army army){
        armies.add(army);
    }
    public Army getArmyByNumber(int Number){
        for (Army i:armies)
            if (i.getNumber() == Number)
                return i;
        return null;
    }
    public int getArmiesNumber(){
        return armies.size();
    }
    public Corps getCorpsByNumber(String Number){
        for (Army i:armies)
            if (i.getCorpsByNumber(Number) != null)
                return i.getCorpsByNumber(Number);
        return null;
    }
    public ArrayList<Country> getEnemies(){
        return enemies;
    }
    public ArrayList<Country> getFriends(){
        return friends;
    }
    public boolean isEnemy (Country country){
        return enemies.contains(country);
    }
    public boolean isFriend (Country country){
        return friends.contains(country);
    }
    public void LostWarWithoutPlace(){
        for (Army army:armies)
            army.LostWar();
    }
    public void LostWarWithPlace(String place){
        for (Army army:armies)
            if (army.getTerrain().equals(place))
                army.LostWar();
    }
    public boolean isEnemyOfFriend(Country country){
        for (Country c:friends)
            if (c.isEnemy(country))
                return true;
        return false;
    }
    public ArrayList<Army> getArmies(){
        return armies;
    }
    public int getScore(){
        int Score = 0;
        for (Army i:armies)
            Score += i.getScore();
        return Score;
    }
    public int getScoreByTerrain(String terrain){
        int Score = 0 ;
        for (Army army:armies)
            if (army.getTerrain().equals(terrain))
                Score += army.getScore();
        return Score;
    }
}
