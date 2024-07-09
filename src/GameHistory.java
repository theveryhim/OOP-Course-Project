import java.time.LocalDateTime;

class GameHistory {
    LocalDateTime date;
    String result;
    String rivalName;
    int rivalLevel;
    String awardsOrPenalties;

    GameHistory(LocalDateTime date, String result, String rivalName, int rivalLevel, String awardsOrPenalties) {
        this.date = date;
        this.result = result;
        this.rivalName = rivalName;
        this.rivalLevel = rivalLevel;
        this.awardsOrPenalties = awardsOrPenalties;
    }
    public LocalDateTime getDate() {
        return date;
    }

    public String getResult() {
        return result;
    }

    public String getRivalName() {
        return rivalName;
    }

    public int getRivalLevel() {
        return rivalLevel;
    }
}
