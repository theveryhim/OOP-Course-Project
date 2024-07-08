import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.security.SecureRandom;


class Controller {
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Card> gameCards = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    SecureRandom random = new SecureRandom();
    User loggedInUser = null;
    User SecondUser = null;
    public boolean isAdmin = false;
    private static final String ADMIN_PASSWORD = "adminpass"; // Change to desired admin password

    public void run() {
        AddInitialCards();
        boolean end = false;
        while (!end) {
            String command = sc.nextLine();
            if (command.matches("user create -u (?<username>\\S+) -p (?<password>\\S+) (?<passwordconfirmation>\\S+) -email (?<email>\\S+) -n (?<nickname>\\S+)")) {
                Matcher matcher = getCommandMatcher(command,
                        "user create -u (?<username>\\S+) -p (?<password>\\S+) (?<passwordconfirmation>\\S+) -email (?<email>\\S+) -n (?<nickname>\\S+)");
                if (matcher.find()) {
                    signUp(matcher);
                }
            }
            else if (command.matches("question pick -q (?<question>\\d+) -a (?<answer>\\S+) -c (?<confirm>\\S+)")) {
                Matcher matcher = getCommandMatcher(command,
                        "question pick -q (?<question>\\d+) -a (?<answer>\\S+) -c (?<confirm>\\S+)");
                if (matcher.find()) {
                    setSecurityQuestion(matcher);
                }
            }
            else if (command.matches("user login -u (?<username>\\S+) -p (?<password>\\S+)")) {
                Matcher matcher = getCommandMatcher(command,
                        "user login -u (?<username>\\S+) -p (?<password>\\S+)");
                if (matcher.find())
                    login1(matcher);
            }
            else if (command.matches("-login admin (?<password>\\S+)")) {
                Matcher matcher = getCommandMatcher(command, "-login admin (?<password>\\S+)");
                matcher.find();
                adminLogin(matcher);
            }
            else if (command.matches("Forgot my password -u (?<username>\\S+)")) {
                Matcher matcher = getCommandMatcher(command,
                        "Forgot my password -u (?<username>\\S+)");
                if (matcher.find()) {
                    forgotPassword(matcher);
                }
            }
            else if (command.equals("log out")) {
                logout();
            }
            else if (command.equals("Show information")) {
                showInformation();
            }
            else if (command.matches("Profile change -u (?<username>\\S+)")) {
                Matcher matcher = getCommandMatcher(command,
                        "Profile change -u (?<username>\\S+)");
                if (matcher.find()) {
                    changeUsername(matcher);
                }
            }
            else if (command.matches("Profile change -n (?<nickname>\\S+)")) {
                Matcher matcher = getCommandMatcher(command,
                        "Profile change -n (?<nickname>\\S+)");
                if (matcher.find()) {
                    changeNickname(matcher);
                }
            }
            else if (command.matches("profile change password -o (?<oldPassword>\\S+) -n (?<newPassword>\\S+)")) {
                Matcher matcher = getCommandMatcher(command,
                        "profile change password -o (?<oldPassword>\\S+) -n (?<newPassword>\\S+)");
                if (matcher.find()) {
                    changePassword(matcher);
                }
            }
            else if (command.matches("profile change -e (?<email>\\S+)")) {
                Matcher matcher = getCommandMatcher(command,
                        "profile change -e (?<email>\\S+)");
                if (matcher.find()) {
                    changeEmail(matcher);
                }
            }
            else if (command.equals("Start"))
                SelectGameMode();
            else if (command.equals("Seeing the Cards")) {
                seeCards();
            }
            else if (command.equals("Profile")) {
                showInformation();
            }
            else if (command.equals("Store")) {
                storeMenu();
            }
            else {
                System.out.println("Invalid command. Please try again.");
            }
            System.out.println("Initial page:");
        }
    }

    private Matcher getCommandMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }

    void AddInitialCards(){
        /*Shields*/
        gameCards.add(new Card("Shield1",true,"ALPHA LUPEX",
                Integer.MAX_VALUE/100,1,1,0,5,20,10,5));
        gameCards.add(new Card("Shield2",true,"HELIO CELON",
                Integer.MAX_VALUE/100,1,2,0,5,20,10,5));
        gameCards.add(new Card("Shield3",true,"A.N.F.O.",
                Integer.MAX_VALUE/100,1,3,0,5,20,10,5));
        /*Heal*/
        gameCards.add(new Card("Heal",true,"TAG PUNKS",
                Integer.MAX_VALUE/100,2,1,0,5,20,10,5));
        /*5:Power Booster*/
        gameCards.add(new Card("PowerBooster",true,"ALPHA LUPEX",
                0,3,0,0,5,20,10,5));
        /*Broken Tile Randomizer*/
        gameCards.add(new Card("Broken Tile Randomizer",true,"HELIO CELON",
                0,4,0,0,5,20,10,5));
        /*Broken Tile Fixer*/
        gameCards.add(new Card("Broken Tile Fixer",true,"A.N.F.O.",
                0,5,0,0,5,20,10,5));
        /*Round Reducer*/
        gameCards.add(new Card("Round Reducer",true,"TAG PUNKS",
                0,6,0,0,5,20,10,5));
        /*Card Robber*/
        gameCards.add(new Card("Card Robber",true,"ALPHA LUPEX",
                0,7,0,0,5,20,10,5));
        /*10:Card Weakener*/
        gameCards.add(new Card("Card Weakener",true,"HELIO CELON",
                0,8,0,0,5,20,10,5));
        /*Card Copier*/
        gameCards.add(new Card("Card Copier",true,"A.N.F.O.",
                0,9,0,0,5,20,10,5));
        /*Card Masker*/
        gameCards.add(new Card("Card Masker",true,"TAG PUNKS",
                0,10,0,0,5,20,10,5));
        /*From the HIP*/
        gameCards.add(new Card("From the HIP",false,"ALPHA LUPEX",
                28,11,1,28,5,20,10,5));
        /*Extreme Frenzy*/
        gameCards.add(new Card("Extreme Frenzy",false,"HELIO CELON",
                41,11,3,60,5,20,10,5));
        /*15:Multi Fire*/
        gameCards.add(new Card("Multi Fire",false,"A.N.F.O.",
                30,11,2,26,5,20,10,5));
        /*Piercer Bullets*/
        gameCards.add(new Card("Piercer Bullets",false,"TAG PUNKS",
                29,11,3,27,5,20,10,5));
        /*Flash Pellets*/
        gameCards.add(new Card("Flash Pellets",false,"ALPHA LUPEX",
                29,11,4,28,5,20,10,5));
        /*Diving Fire */
        gameCards.add(new Card("Diving Fire",false,"HELIO CELON",
                32,11,3,42,5,20,10,5));
        /*Crouching Blast*/
        gameCards.add(new Card("Crouching Blast",false,"A.N.F.O.",
                31,11,1,40,5,20,10,5));
        /*20:Rapid Fire*/
        gameCards.add(new Card("Rapid Fire",false,"TAG PUNKS",
                33,11,2,42,5,20,10,5));
        /*Leaping Fury*/
        gameCards.add(new Card("Leaping Fury",false,"ALPHA LUPEX",
                35,11,5,45,5,20,10,5));
        /*Hidden Shot*/
        gameCards.add(new Card("Hidden Shot",false,"HELIO CELON",
                34,11,4,40,5,20,10,5));
        /*Trigger Happy*/
        gameCards.add(new Card("Trigger Happy",false,"A.N.F.O.",
                39,11,4,48,5,20,10,5));
        /*Sudden  Impact*/
        gameCards.add(new Card("Sudden Impact",false,"TAG PUNKS",
                37,11,1,48,5,20,10,5));
        /*25:Swift Justice*/
        gameCards.add(new Card("Swift Justice",false,"ALPHA LUPEX",
                36,11,2,52,5,20,10,5));
        /*Mercurial Reload*/
        gameCards.add(new Card("Mercurial Reload",false,"HELIO CELON",
                38,11,3,45,5,20,10,5));
        /*Spray & Pray*/
        gameCards.add(new Card("Spray & Pray",false,"A.N.F.O.",
                35,11,5,55,5,20,10,5));
        /*Ultra Sonic Blast*/
        gameCards.add(new Card("Ultra Sonic Blast",true,"TAG PUNKS",
                Integer.MAX_VALUE/100,11,5,40,5,20,10,5));
        /*Track Magnet*/
        gameCards.add(new Card("Track Magnet",true,"ALPHA LUPEX",
                0,12,1,0,5,20,10,5));
        /*30:Covert Strike*/
        gameCards.add(new Card("Covert Strike",true,"HELIO CELON",
                25,13,1,5,5,20,10,5));
        /*Sonar Strike*/
        gameCards.add(new Card("Sonar Strike",true,"A.N.F.O.",
                28,14,1,15,5,20,10,5));
        /*Tracker Laser*/
        gameCards.add(new Card("Tracker Laser",true,"TAG PUNKS",
                31,15,2,32,5,20,10,5));
    }

    private void signUp(Matcher matcher) {
        String username = matcher.group("username");
        String password = matcher.group("password");
        String passwordConfirmation = matcher.group("passwordconfirmation");
        String email = matcher.group("email");
        String nickname = matcher.group("nickname");

        if (NotValidUsername(username)) {
            System.out.println("Invalid username. It must contain only letters, numbers, and underscores.");
            return;
        }

        if (isUsernameTaken(username)) {
            System.out.println("Username already taken.");
            return;
        }

        if (NotValidEmail(email)) {
            System.out.println("Invalid email format.");
            return;
        }

        if (password.equals("random")) {
            password = generateRandomPassword();
            System.out.println("Your random password: " + password);
            System.out.print("Please enter your password: ");
            String confirmationPassword = sc.nextLine();
            if (!password.equals(confirmationPassword)) {
                System.out.println("Password confirmation does not match.");
                return;
            }
        }
        else if (!isValidPassword(password)) {
            return;
        }
        else if (!password.equals(passwordConfirmation)) {
            System.out.println("Passwords do not match.");
            return;
        }

        User user = new User(username, password, nickname, email);
        users.add(user);
        System.out.println("User created successfully. Please choose a security question :");
        System.out.println("• 1-What is your father’s name ?");
        System.out.println("• 2-What is your favourite color ?");
        System.out.println("• 3-What was the name of your first pet?");
    }

    private void setSecurityQuestion(Matcher matcher) {
        int questionNumber = Integer.parseInt(matcher.group("question"));
        String answer = matcher.group("answer");
        String confirmAnswer = matcher.group("confirm");

        if (!answer.equals(confirmAnswer)) {
            System.out.println("Answer confirmation does not match.");
            return;
        }

        String question = getSecurityQuestion(questionNumber);
        if (question == null) {
            System.out.println("Invalid question number.");
            return;
        }

        User lastUser = users.get(users.size() - 1);
        lastUser.securityQuestion = question;
        lastUser.securityAnswer = answer;
        System.out.println("Security question set successfully.");
    }

    private String getSecurityQuestion(int number) {
        switch (number) {
            case 1:
                return "What is your father’s name ?";
            case 2:
                return "What is your favourite color ?";
            case 3:
                return "What was the name of your first pet?";
            default:
                return null;
        }
    }

    private boolean NotValidUsername(String username) {
        return !username.matches("[A-Za-z0-9_]+");
    }

    private boolean isUsernameTaken(String username) {
        for (User user : users) {
            if (user.username.equals(username)) {
                return true;
            }
        }
        return false;
    }

    private boolean NotValidEmail(String email) {
        return !email.matches("\\S+@\\S+\\.com");
    }

    private boolean isValidPassword(String password) {
        if (password.length() < 8) {
            System.out.println("Password must be at least 8 characters long.");
            return false;
        }
        if (!password.matches(".*[A-Z].*")) {
            System.out.println("Password must contain at least one uppercase letter.");
            return false;
        }
        if (!password.matches(".*[a-z].*")) {
            System.out.println("Password must contain at least one lowercase letter.");
            return false;
        }
        if (!password.matches(".*[^A-Za-z0-9].*")) {
            System.out.println("Password must contain at least one special character.");
            return false;
        }
        return true;
    }

    private String generateRandomPassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }
        return password.toString();
    }

    private void login1(Matcher matcher) {
        String username = matcher.group("username");
        String password = matcher.group("password");

        User user = getUserByUsername(username);
        if (user == null) {
            System.out.println("Username doesn't exist!");
            return;
        }

        long currentTime = System.currentTimeMillis();
        if (user.lockoutEndTime > currentTime) {
            long remainingLockoutTime = (user.lockoutEndTime - currentTime) / 1000;
            System.out.println("Try again in " + remainingLockoutTime + " seconds.");
            return;
        }

        if (!user.password.equals(password)) {
            user.unsuccessfulAttempts++;
            user.lockoutEndTime = currentTime + user.unsuccessfulAttempts * 5 * 1000;
            System.out.println("Password and Username don’t match!");
            return;
        }

        user.unsuccessfulAttempts = 0;
        user.lockoutEndTime = 0;
        loggedInUser = user;
        System.out.println("User logged in successfully!");

        if (loggedInUser.isFirstLogin) {
            loggedInUser.cards = generateStarterPack();
            loggedInUser.isFirstLogin = false;
            System.out.println("Awarded StarterPack with 20 random cards!");
        }
    }

    private void login2(Matcher matcher) {
        String username = matcher.group("username");
        String password = matcher.group("password");

        User user = getUserByUsername(username);
        if (user == null) {
            System.out.println("Username doesn't exist!");
            return;
        }

        long currentTime = System.currentTimeMillis();
        if (user.lockoutEndTime > currentTime) {
            long remainingLockoutTime = (user.lockoutEndTime - currentTime) / 1000;
            System.out.println("Try again in " + remainingLockoutTime + " seconds.");
            return;
        }

        if (!user.password.equals(password)) {
            user.unsuccessfulAttempts++;
            user.lockoutEndTime = currentTime + user.unsuccessfulAttempts * 5 * 1000;
            System.out.println("Password and Username don’t match!");
            return;
        }

        user.unsuccessfulAttempts = 0;
        user.lockoutEndTime = 0;
        SecondUser = user;
        System.out.println("User logged in successfully!");

        if (SecondUser.isFirstLogin) {
            SecondUser.cards = generateStarterPack();
            SecondUser.isFirstLogin = false;
            System.out.println("Awarded StarterPack with 20 random cards!");
        }
    }

    private void adminLogin(Matcher matcher) {
        String password = matcher.group("password");
        if (ADMIN_PASSWORD.equals(password)) {
            isAdmin = true;
            System.out.println("Admin logged in successfully!");
            displayAdminMenu();
        }
        else {
            System.out.println("Incorrect admin password.");
        }
    }

    private void displayAdminMenu() {
        System.out.println("Admin Menu:");
        System.out.println("1. Add Cards");
        System.out.println("2. Edit Card");
        System.out.println("3. Delete Card");
        System.out.println("4. See All Players");
        System.out.println("5. Log out");

        String command = sc.nextLine();
        switch (command) {
            case "1":
                addCard();
                break;
            case "2":
                editCardMenu();
                break;
            case "3":
                deleteCard();
                break;
            case "4":
                seeAllPlayers();
                break;
            case "5":
                adminLogout();
                break;
            default:
                System.out.println("Invalid command. Please try again.");
                displayAdminMenu();
                break;
        }
    }

    private void addCard() {
        System.out.println("Enter card name:");
        String name = sc.nextLine();

        if (gameCards.stream().anyMatch(card -> card.Name.equals(name))) {
            System.out.println("Card name already exists. Please try again.");
            displayAdminMenu();
        }

        System.out.println("Enter card type:");
        String type = sc.nextLine();
        boolean isSpell = type.equals("Spell");

        System.out.println("Enter card style:");
        String style = sc.nextLine();

        int attackDefence;
        if(!isSpell){
            System.out.println("Enter card attackDefence:");
            attackDefence = sc.nextInt();
        }
        else
            attackDefence = 0;

        System.out.println("Enter card ability:");
        int ability = sc.nextInt();

        System.out.println("Enter card duration:");
        int duration = sc.nextInt();

        System.out.println("Enter player damage:");
        int playerDamage = sc.nextInt();

        System.out.println("Enter upgrade level:");
        int upgradeLevel = sc.nextInt();

        System.out.println("Enter price:");
        int price = sc.nextInt();

        System.out.println("Enter upgrade cost:");
        int upgradeCost = sc.nextInt();


        System.out.println("Enter max level:");
        int maxLevel = sc.nextInt();

        gameCards.add(new Card(name,isSpell,style,attackDefence,ability,duration,playerDamage,upgradeLevel,upgradeCost,price,maxLevel));
        System.out.println("Card added successfully!");
        displayAdminMenu();
    }

    private void editCardMenu() {
        System.out.println("All cards:");
        for (int i = 0; i < gameCards.size(); i++) {
            Card card = gameCards.get(i);
            System.out.println((i + 1) + ". " + card.Name);
        }

        System.out.println("Enter the number of the card you want to edit:");
        int cardNumber = sc.nextInt();
        sc.nextLine(); // Consume newline

        if (cardNumber < 1 || cardNumber > gameCards.size()) {
            System.out.println("Invalid card number. Please try again.");
            editCardMenu();
            return;
        }

        Card cardToEdit = gameCards.get(cardNumber - 1);
        editCard(cardToEdit);
    }

    private void editCard(Card card) {
        System.out.println("Editing card: " + card.Name);
        System.out.println("1. Name: " + card.Name);
        System.out.println("2. Attack/Defense: " + card.AttackDefence);
        System.out.println("3. Duration: " + card.Duration);
        System.out.println("4. Player Damage: " + card.PlayerDamage);
        System.out.println("5. Upgrade Level: " + card.UpgradeLevel);
        System.out.println("6. Upgrade Cost: " + card.UpgradeCost);
        System.out.println("Enter the number of the field you want to edit, or 'back' to return to the previous menu:");

        String command = sc.nextLine();
        switch (command) {
            case "1":
                System.out.println("Enter new name:");
                String newName = sc.nextLine();
                if (gameCards.stream().anyMatch(c -> c.Name.equals(newName))) {
                    System.out.println("Card name already exists. Please try again.");
                }
                else {
                    card.Name = newName;
                }
                break;
            case "2":
                System.out.println("Enter new attack/defense (10-100):");
                card.AttackDefence = sc.nextInt();
                sc.nextLine(); // Consume newline
                break;
            case "3":
                System.out.println("Enter new duration (1-5):");
                card.Duration = sc.nextInt();
                sc.nextLine(); // Consume newline
                break;
            case "4":
                System.out.println("Enter new player damage (10-50):");
                card.PlayerDamage = sc.nextInt();
                sc.nextLine(); // Consume newline
                break;
            case "5":
                System.out.println("Enter new upgrade level:");
                card.UpgradeLevel = sc.nextInt();
                sc.nextLine(); // Consume newline
                break;
            case "6":
                System.out.println("Enter new upgrade cost:");
                card.UpgradeCost = sc.nextInt();
                sc.nextLine(); // Consume newline
                break;
            case "back":
                displayAdminMenu();
                return;
            default:
                System.out.println("Invalid command. Please try again.");
                editCard(card);
                return;
        }

        System.out.println("Are you sure you want to edit this card? (y/n)");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.println("Card edited successfully!");
        } else {
            System.out.println("Card not saved.");
        }
        displayAdminMenu();
    }

    private void deleteCard() {
        System.out.println("All cards:");
        for (int i = 0; i < gameCards.size(); i++) {
            Card card = gameCards.get(i);
            System.out.println((i + 1) + ". " + card.Name);
        }

        System.out.println("Enter the number of the card you want to delete:");
        int cardNumber = sc.nextInt();
        sc.nextLine(); // Consume newline

        if (cardNumber < 1 || cardNumber > gameCards.size()) {
            System.out.println("Invalid card number. Please try again.");
            deleteCard();
            return;
        }

        Card cardToDelete = gameCards.get(cardNumber - 1);
        System.out.println("Are you sure you want to delete this card? (y/n)");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            gameCards.remove(cardToDelete);
            System.out.println("Card deleted successfully!");
        } else {
            System.out.println("Card not deleted.");
        }
        displayAdminMenu();
    }

    private void seeAllPlayers() {
        System.out.println("All players:");
        for (User user : users) {
            System.out.println("Username: " + user.username + ", Level: " + user.Level + ", Coins: " + user.coins);
        }
        displayAdminMenu();
    }

    private void adminLogout() {
        isAdmin = false;
        System.out.println("Admin logged out.");
        run();
    }

    private void forgotPassword(Matcher matcher) {
        String username = matcher.group("username");

        User user = getUserByUsername(username);
        if (user == null) {
            System.out.println("Username doesn’t exist!");
            return;
        }

        System.out.println("Answer the security question to reset your password:");
        System.out.println(user.securityQuestion);

        String answer = sc.nextLine();
        if (answer.equals(user.securityAnswer)) {
            System.out.println("Enter new password:");
            String newPassword = sc.nextLine();
            if (isValidPassword(newPassword)) {
                System.out.println("Confirm new password:");
                String confirmPassword = sc.nextLine();
                if (newPassword.equals(confirmPassword)) {
                    user.password = newPassword;
                    System.out.println("Password changed successfully!");
                } else {
                    System.out.println("Password confirmation does not match.");
                }
            }
        }
        else {
            System.out.println("Security answer is incorrect.");
        }
    }

    private void logout() {
        if (loggedInUser != null) {
            System.out.println("User logged out successfully!");
            loggedInUser = null;
        }
        else {
            System.out.println("No user is currently logged in.");
        }
    }

    private User getUserByUsername(String username) {
        for (User user : users) {
            if (user.username.equals(username)) {
                return user;
            }
        }
        return null;
    }

    private void showInformation() {
        if (loggedInUser == null) {
            System.out.println("No user is currently logged in.");
            return;
        }
        System.out.println("Username: " + loggedInUser.username);
        System.out.println("Nickname: " + loggedInUser.nickname);
        System.out.println("Email: " + loggedInUser.email);
        System.out.println("Password: " + loggedInUser.password);
        System.out.println("Security Question: " + loggedInUser.securityQuestion);
    }

    private void changeUsername(Matcher matcher) {
        if (loggedInUser == null) {
            System.out.println("No user is currently logged in.");
            return;
        }
        String newUsername = matcher.group("username");

        if (NotValidUsername(newUsername)) {
            System.out.println("Invalid username. It must contain only letters, numbers, and underscores.");
            return;
        }

        if (isUsernameTaken(newUsername)) {
            System.out.println("Username already taken.");
            return;
        }

        loggedInUser.username = newUsername;
        System.out.println("Username changed successfully!");
    }

    private void changeNickname(Matcher matcher) {
        if (loggedInUser == null) {
            System.out.println("No user is currently logged in.");
            return;
        }
        loggedInUser.nickname = matcher.group("nickname");
        System.out.println("Nickname changed successfully!");
    }

    private void changePassword(Matcher matcher) {
        if (loggedInUser == null) {
            System.out.println("No user is currently logged in.");
            return;
        }
        String oldPassword = matcher.group("oldPassword");
        String newPassword = matcher.group("newPassword");

        if (!loggedInUser.password.equals(oldPassword)) {
            System.out.println("Current password is incorrect!");
            return;
        }

        if (newPassword.equals(oldPassword)) {
            System.out.println("Please enter a new password!");
            return;
        }

        if (!isValidPassword(newPassword)) {
            return;
        }

        System.out.println("Please enter your new password again:");
        String confirmPassword = sc.nextLine();
        if (!newPassword.equals(confirmPassword)) {
            System.out.println("Password confirmation does not match.");
            return;
        }

        loggedInUser.password = newPassword;
        System.out.println("Password changed successfully!");
    }

    private void changeEmail(Matcher matcher) {
        if (loggedInUser == null) {
            System.out.println("No user is currently logged in.");
            return;
        }
        String newEmail = matcher.group("email");

        if (NotValidEmail(newEmail)) {
            System.out.println("Invalid email format.");
            return;
        }

        loggedInUser.email = newEmail;
        System.out.println("Email changed successfully!");
    }

    private ArrayList<Card> generateStarterPack() {
        ArrayList<Card> starterPack = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < gameCards.size(); i++) {
            indices.add(i);
        }
        // Shuffle the indices
        Collections.shuffle(indices);
        // Copy 20 random Card objects from the original list to the new list with less than 9 spell cards
        int SpellCount = 0;
        for (int i = 0; i < gameCards.size() & starterPack.size() < 20; i++) {
            if (SpellCount < 9) {
                starterPack.add(gameCards.get(indices.get(i)));
                if (gameCards.get(i).IsSpell)
                    SpellCount++;
            }
            else
                if (!gameCards.get(i).IsSpell)
                    starterPack.add(gameCards.get(indices.get(i)));
        }

        return starterPack;
    }

    private void MainMenu() {
        System.out.println("Welcome to City Wars: Tokyo Reign!");
        System.out.println("1. Start");
        System.out.println("2. Seeing the Cards");
        System.out.println("3. Profile");
        System.out.println("4. Store");
        System.out.println("5. History of the Games");
        System.out.println("6. Log out");

        String command = sc.nextLine();
        switch (command) {
            case "1":
                SelectGameMode();
                break;
            case "2":
                seeCards();
                break;
            case "3":
                showInformation();
                break;
            case "4":
                storeMenu();
                break;
            case "5":
                historyMenu(1, Comparator.comparing(GameHistory::getDate).reversed());
                break;
            case "6":
                logout();
                break;
            default:
                System.out.println("Invalid command. Please try again.");
                MainMenu();
                break;
        }
    }

    private void SelectGameMode() {
        System.out.println("Choose a mode to start the game:");

        System.out.println("1: Winner takes all");

        System.out.println("2: 1 vs 1");

        System.out.println("Back");

        String command = sc.nextLine();

        switch (command) {
            case "Back":
                return;
            case "1":
            {
                WinnerTakesAll();
                MainMenu();
            }
            break;
            case "2":
            {
                OneOnOne();
                MainMenu();
            }
                break;
            default:
                System.out.println("Invalid command. Please try again.");
        }
    }

    private void seeCards() {
        if (loggedInUser == null) {
            System.out.println("No user is currently logged in.");
            return;
        }
        System.out.println("Your Cards:");
        for (Card c : loggedInUser.cards) {
            System.out.println(c.Name + " (Level: " + c.Level + ")");
        }

        System.out.println("Back");

        String command = sc.nextLine();
        switch (command) {
            case "Back":
                return;
            default:
                System.out.println("Invalid command. Please try again.");
        }

    }

    private void storeMenu() {
        if (loggedInUser == null) {
            System.out.println("No user is currently logged in.");
            return;
        }

        System.out.println("Welcome to the Store!");
        System.out.println("1. Buy new cards");
        System.out.println("2. Upgrade existing cards");
        System.out.println("3. Back to main menu");

        String command = sc.nextLine();
        switch (command) {
            case "1":
                buyCardsMenu();
                break;
            case "2":
                upgradeCardsMenu();
                break;
            case "3":
                MainMenu();
                break;
            default:
                System.out.println("Invalid command. Please try again.");
                storeMenu();
                break;
        }
    }

    private void buyCardsMenu() {
        System.out.println("Available cards for purchase:");
        for (Card card : gameCards)
            if (loggedInUser.getCardByName(card.Name) == null)
                System.out.println(card.Name + " - " + card.Ability + " - Price: " + card.Price + " coins");

        System.out.println("Enter the name of the card you want to buy:");
        String cardName = sc.nextLine();
        Card cardToBuy = null;
        for (Card card : gameCards) {
            if (card.Name.equals(cardName) && loggedInUser.getCardByName(card.Name) != null) {
                cardToBuy = new Card(card);
                break;
            }
        }

        if (cardToBuy == null) {
            System.out.println("Invalid card name or you already own this card.");
            buyCardsMenu();
            return;
        }

        if (loggedInUser.coins < cardToBuy.Price) {
            System.out.println("You don't have enough coins to buy this card.");
            buyCardsMenu();
            return;
        }

        loggedInUser.coins -= cardToBuy.Price;
        loggedInUser.cards.add(cardToBuy);
        System.out.println("Card purchased successfully! Remaining coins: " + loggedInUser.coins);
        storeMenu();
    }

    private void upgradeCardsMenu() {
        System.out.println("Your eligible cards for upgrade:");
        for (Card c : loggedInUser.cards) {
            if (c.Level < c.MaxLevel) {
                System.out.println(c.Name + " - Current Level: " + c.Level + " - Upgrade Cost: " + c.UpgradeCost + " coins");
            }
        }

        System.out.println("Enter the name of the card you want to upgrade:");
        String cardName = sc.nextLine();
        Card cardToUpgrade = getCardByName(cardName);

        if (cardToUpgrade == null || loggedInUser.getCardByName(cardToUpgrade.Name)== null) {
            System.out.println("Invalid card name.");
            upgradeCardsMenu();
            return;
        }

        int currentLevel = loggedInUser.getCardByName(cardToUpgrade.Name).Level;
        if (currentLevel >= cardToUpgrade.MaxLevel) {
            System.out.println("This card is already at the maximum level.");
            upgradeCardsMenu();
            return;
        }

        if (loggedInUser.coins < cardToUpgrade.UpgradeCost) {
            System.out.println("You don't have enough coins to upgrade this card.");
            upgradeCardsMenu();
            return;
        }

        loggedInUser.coins -= cardToUpgrade.UpgradeCost;
        loggedInUser.getCardByName(cardToUpgrade.Name).Upgrade();
        System.out.println("Card upgraded successfully! Remaining coins: " + loggedInUser.coins);
        storeMenu();
    }

    private Card getCardByName(String cardName) {
        for (Card card : gameCards) {
            if (card.Name.equals(cardName)) {
                return card;
            }
        }
        return null;
    }

    private void historyMenu(int page, Comparator<GameHistory> comparator) {
        List<GameHistory> history = loggedInUser.gameHistory;
        history.sort(comparator);

        int totalPages = (int) Math.ceil(history.size() / 5.0);
        int start = (page - 1) * 5;
        int end = Math.min(start + 5, history.size());

        System.out.println("History of the Games (Page " + page + " of " + totalPages + ")");
        for (int i = start; i < end; i++) {
            GameHistory entry = history.get(i);
            System.out.println((i + 1) + ". Date: " + entry.date + ", Result: " + entry.result +
                    ", Rival: " + entry.rivalName + " (Level: " + entry.rivalLevel + "), Awards/Penalties: " + entry.awardsOrPenalties);
        }

        System.out.println("1. Back to main menu");
        System.out.println("2. Sort the table");
        if (page > 1) {
            System.out.println("3. Previous page");
        }
        if (page < totalPages) {
            System.out.println("4. Next page");
        }

        String command = sc.nextLine();
        switch (command) {
            case "1":
                MainMenu();
                break;
            case "2":
                sortHistoryMenu(page);
                break;
            case "3":
                if (page > 1) {
                    historyMenu(page - 1, comparator);
                } else {
                    System.out.println("No previous page.");
                    historyMenu(page, comparator);
                }
                break;
            case "4":
                if (page < totalPages) {
                    historyMenu(page + 1, comparator);
                } else {
                    System.out.println("No next page.");
                    historyMenu(page, comparator);
                }
                break;
            default:
                System.out.println("Invalid command. Please try again.");
                historyMenu(page, comparator);
                break;
        }
    }

    private void sortHistoryMenu(int currentPage) {
        System.out.println("Sort the table by:");
        System.out.println("1. Date");
        System.out.println("2. Win/Lose");
        System.out.println("3. Name of the rival");
        System.out.println("4. Rival's level");
        System.out.println("Choose ascending (a) or descending (d):");

        String sortOption = sc.nextLine();
        Comparator<GameHistory> comparator;

        switch (sortOption) {
            case "1a":
                comparator = Comparator.comparing(GameHistory::getDate);
                break;
            case "1d":
                comparator = Comparator.comparing(GameHistory::getDate).reversed();
                break;
            case "2a":
                comparator = Comparator.comparing(GameHistory::getResult);
                break;
            case "2d":
                comparator = Comparator.comparing(GameHistory::getResult).reversed();
                break;
            case "3a":
                comparator = Comparator.comparing(GameHistory::getRivalName);
                break;
            case "3d":
                comparator = Comparator.comparing(GameHistory::getRivalName).reversed();
                break;
            case "4a":
                comparator = Comparator.comparing(GameHistory::getRivalLevel);
                break;
            case "4d":
                comparator = Comparator.comparing(GameHistory::getRivalLevel).reversed();
                break;
            default:
                System.out.println("Invalid option. Sorting by date (descending) by default.");
                comparator = Comparator.comparing(GameHistory::getDate).reversed();
                break;
        }

        historyMenu(currentPage, comparator);
    }

    private void OneOnOne(){
        System.out.println("1 vs 1 game");
        System.out.println("2nd Player Login: user login -u username -p password");
        System.out.println("Back");
        boolean done = false;
        while (!done){
            String command = sc.nextLine();
            if (command.matches("user login -u (?<username>\\S+) -p (?<password>\\S+)")) {
                Matcher matcher = getCommandMatcher(command,
                        "user login -u (?<username>\\S+) -p (?<password>\\S+)");
                if (matcher.find()) {
                    login2(matcher);
                }
                if (SecondUser!=null) {
                    SelectCharacter(false,0);
                    OneOnOne();
                }
            }
            else if (command.equals("Back"))
                done = true;
            else
                System.out.println("invalid command! try something else");
        }
        SecondUser = null;
    }

    private void WinnerTakesAll(){
        System.out.println("Winner Takes All game");
        System.out.println("2nd Player Login: user login -u username -p password");
        System.out.println("Back");
        boolean done = false;
        while (!done){
            String command = sc.nextLine();
            if (command.matches("user login -u (?<username>\\S+) -p (?<password>\\S+)")) {
                Matcher matcher = getCommandMatcher(command,
                        "user login -u (?<username>\\S+) -p (?<password>\\S+)");
                if (matcher.find()) {
                    login2(matcher);
                }
                if (SecondUser!=null)
                    done = true;
            }
            else if (command.equals("Back"))
                return;
            else
                System.out.println("invalid command! try something else");
        }
        int bet;
        System.out.println("Select your bet: ");
        System.out.println("10");
        System.out.println("25");
        System.out.println("50");
        System.out.println("100");
        System.out.println("250");
        System.out.println("1000");
        String s = sc.nextLine();
        bet = Integer.parseInt(s);
        if (bet != 10 & bet != 25 & bet != 50 & bet != 100 & bet != 250 & bet != 1000)
            System.out.println("invalid input");
        else {
            if (SecondUser.coins < bet)
                System.out.println("Not enough coins from Guest!");
            else if (loggedInUser.coins < bet)
                System.out.println("Not enough coins from Host!");
            else {
                SelectCharacter(true, bet);
                WinnerTakesAll();
            }
        }
        SecondUser = null;
    }

    private void SelectCharacter(boolean isWinnerTakesAll,int Bet){
        System.out.println("Players (Host-Guest) please Select Characters:(Character-Character2)");
        System.out.println("ALPHA LUPEX");
        System.out.println("HELIO CELON");
        System.out.println("A.N.F.O.");
        System.out.println("TAG PUNKS");
        System.out.println("Back");
        boolean done0 = false;
        String command = sc.nextLine();
        if (!command.matches("Back")){
            loggedInUser.Character = command.split("-")[0];
            SecondUser.Character = command.split("-")[1];
            done0 = true;
        }
        if (done0)
            GameOn(isWinnerTakesAll,Bet);
    }

    private void GameOn(boolean isWinnerTakesAll,int Bet){
        Random random = new Random();
        Arena arena = new Arena(loggedInUser.cards,SecondUser.cards,
                loggedInUser.Character,SecondUser.Character);
        /*0.6:Host chance to start*/
        boolean HostStarts = (random.nextDouble() < 0.6);
        int f = 1;
        while (!arena.EndGame){
            System.out.println("Frame " + f + ": Fight!!!");
            arena.Start(GetInitialDeck(loggedInUser.cards),GetInitialDeck(SecondUser.cards),
                    random.nextInt(21),random.nextInt(21));
            for (int i = 0; i < arena.TotalRounds; i++){
                System.out.println("Round " + (i + 1) + " of " + arena.TotalRounds);
                arena.PlayerTurn(HostStarts);
                arena.PlayerTurn(!HostStarts);
            }
            arena.TimeLine();
            f++;
            HostStarts = !HostStarts;
        }
        System.out.println("Winner is :");
        if (arena.WinnerIsHost){
            System.out.println("Host!");
            loggedInUser.AddXP(arena.HostHP*5);
            loggedInUser.coins += arena.HostHP*10;
            SecondUser.AddXP(250 - arena.HostHP*5);
            if (isWinnerTakesAll) {
                loggedInUser.coins += Bet;
                SecondUser.coins -= Bet;
            }
        }
        else {
            System.out.println("Guest!");
            SecondUser.AddXP(arena.HostHP*5);
            SecondUser.coins += arena.HostHP*10;
            loggedInUser.AddXP(250 - arena.HostHP*5);
            if (isWinnerTakesAll) {
                SecondUser.coins += Bet;
                loggedInUser.coins -= Bet;
            }
        }
    }

    private ArrayList<Card> GetInitialDeck(ArrayList<Card> Cards){
        ArrayList<Card> Deck = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < Cards.size(); i++)
            indices.add(i);
        // Shuffle the indices
        Collections.shuffle(indices);
        // Get 5 random cards from total cards for round deck(less than 3 spells)
        int SpellCount = 0;
        for (int i = 0; i < Cards.size() & Deck.size() < 5; i++) {
            if (SpellCount < 2) {
                Deck.add(Cards.get(indices.get(i)));
                if (Cards.get(i).IsSpell)
                    SpellCount++;
            }
            else
            if (!Cards.get(i).IsSpell)
                Deck.add(Cards.get(indices.get(i)));
        }
        return Deck;
    }
}