import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class User {
    String username;
    String password;
    String nickname;
    String email;
    String securityQuestion;
    String securityAnswer;
    String Character;
    int unsuccessfulAttempts;
    int Level;
    long lockoutEndTime;
    ArrayList<Card> cards;
    boolean isFirstLogin;
    int coins;
    int XP;
    List<GameHistory> gameHistory = new ArrayList<>();

    public User(String username, String password, String nickname, String email) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.Character = "";
        this.email = email;
        this.unsuccessfulAttempts = 0;
        this.lockoutEndTime = 0;
        this.cards = new ArrayList<>();
        this.isFirstLogin = true;
        this.coins = 100; // Start with 100 coins
        this.Level = 1;
        this.XP = 0;
    }

    public Card getCardByName(String name){
        for (Card c:cards)
            if (c.Name.equals(name))
                return c;
        return null;
    }

    public void AddXP(int xp){
        if (XP+xp>=100*Level){
            Level++;
            XP = 0;
        }
        else
            XP += xp;
    }
}
