public class Corps {
    private final Infantry infantry;
    private final Cavalry cavalry;
    private final Artillery artillery;
    private final String Number;
    private final String Officer;
    private final String Country;
    public Corps(String officer,String  number,int infantry,int cavalry,int artillery,String country){
        Officer = officer;
        Number = number;
        Country = country;
        this.artillery = new Artillery(artillery);
        this.cavalry = new Cavalry(cavalry);
        this.infantry =new Infantry(infantry);
    }
    public String getNumber(){
        return Number;
    }
    public String getCountry(){
        return Country;
    }
    public String getOfficer(){
        return Officer;
    }
    public int getInfantryUnits(){
        return this.infantry.returnNumber();
    }
    public int getCavalryUnits(){
        return this.cavalry.returnNumber();
    }
    public int getArtilleryUnits(){
        return this.artillery.returnNumber();
    }
    public int getInfantryQuantity(){
        return infantry.returnNumber() * 1000;
    }
    public int getCavalryQuantity(){
        return cavalry.returnNumber() * 400;
    }
    public int getArtilleryQuantity(){
        return artillery.returnNumber() * 10;
    }
    public void LostWar(){
        if (infantry.returnNumber() % 2 == 0)
            infantry.setNumber(infantry.returnNumber()/2);
        else
            infantry.setNumber((infantry.returnNumber() - 1)/2);
        if (cavalry.returnNumber() % 2 == 0)
            cavalry.setNumber(cavalry.returnNumber()/2);
        else
            cavalry.setNumber((cavalry.returnNumber() - 1)/2);
    }
    public int getTotalQuantity(){
        return infantry.returnNumber() * 1000 + cavalry.returnNumber() * 400 +
                artillery.returnNumber() * 10;
    }
    public int NumberTranslator(){
        switch (Number) {
            case "I":
                return 1;
            case "II":
                return 2;
            case "III":
                return 3;
        }
        return 4;
    }
}
