import java.util.ArrayList;

public class Army {
    private final String Leader;
    private final String Country;
    private final int Number;
    private String terrain;
    private final ArrayList<Corps> corps = new ArrayList<>();
    public Army(String leader,int number,String country,String  terrain){
        Leader = leader;
        Number = number;
        Country = country;
        this.terrain = terrain;
    }
    public Army(String leader,int number,String country){
        Leader = leader;
        Number = number;
        Country = country;
    }
    public void addCorps(Corps corps){
        this.corps.add(corps);
    }
    public int getNumber(){
        return Number;
    }
    public int getCorpsNumber(){
        return corps.size();
    }
    public String getLeader(){
        return Leader;
    }
    public void setTerrain(String terrain){
        this.terrain = terrain;
    }
    public String getTerrain(){
        return terrain;
    }
    public Corps getCorpsByNumber(String Number){
        for (Corps i:corps)
            if (i.getNumber().equals(Number))
                return i;
        return null;
    }
    public ArrayList<Corps> getCorps(){
        return corps;
    }
    public int getCorpsScore(Corps corps){
        Terrain terrain = FindTerrain(this.terrain);
        Officer officer = FindOfficer(corps.getOfficer());
        int Score = corps.getArtilleryQuantity()*terrain.getArtilleryScore() +
                corps.getCavalryQuantity()* terrain.getCavalryScore() +
                corps.getInfantryQuantity()* terrain.getInfantryScore() +
                corps.getTotalQuantity() + officer.getScore()*1000;
        return Score;
    }
    public int getScore(){
        int Score = 0;
        for (Corps i:corps) {
            Score += this.getCorpsScore(i);
        }
        return Score;
    }
    public void LostWar(){
        for (Corps corp:corps)
            corp.LostWar();
    }
    public Terrain FindTerrain(String terrain){
        for (Terrain t:Terrain.values())
            if (t.name().equals(terrain))
                return t;
        return null;
    }
    public Officer FindOfficer(String officer){
        for (Officer o:Officer.values())
            if (o.name().equals(officer))
                return o;
        return null;
    }
}
