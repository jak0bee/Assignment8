package code;

import java.util.ArrayList;

/**
 * The Game class manages the state of the game, including players, their positions, scores, and their penalty status.
 */
public class Game {
    private final ArrayList<Player> PLAYERS;
    private final QuestionManager QUESTION_MANAGER;
    private int currentPlayerIndex;
    private boolean isGettingOutOfPenaltyBox;


    public Game() {
        this.PLAYERS = new ArrayList<>();
        this.QUESTION_MANAGER = new QuestionManager();
        this.currentPlayerIndex = 0;
        this.isGettingOutOfPenaltyBox = false;
    }


    /**
     * Adds a player to the game.
     * Won't add more players if there is already 6.
     *
     * @param playerName Name of the player to be added
     */
    public void addPlayer(String playerName) {
        if(howManyPlayers()<6){
            PLAYERS.add(new Player(playerName));
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + PLAYERS.size());
        }else
            System.out.println("Cannot add more players");
    }

    /**
     * Returns the number of players in the game.
     *
     * @return The number of players
     */
    public int howManyPlayers() {
        return PLAYERS.size();
    }

     /**
     * Rolls the dice for the current player and updates the player's position.
     * If the player is in the penalty box, decides whether they get out or not.
     *
     * @param roll Number on the dice roll
     */
    public void roll(int roll) {
        System.out.println(PLAYERS.get(currentPlayerIndex).getName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (PLAYERS.get(currentPlayerIndex).isInPenaltyBox()) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                System.out.println(PLAYERS.get(currentPlayerIndex).getName() + " is getting out of the penalty box");
                updatePlayerPosition(roll);
                askQuestion();
            } else {
                System.out.println(PLAYERS.get(currentPlayerIndex).getName() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }
        } else {
            updatePlayerPosition(roll);
            askQuestion();
        }
    }

     /**
     * Updates the position of the current player based on the dice roll.
     *
     * @param roll Number on the dice roll
     */
    private void updatePlayerPosition(int roll) {
        Player player = PLAYERS.get(currentPlayerIndex);
        int newPosition = player.getPlace() + roll;
        if (newPosition > 11) {
            newPosition -= 12;
        }
        player.setPlace(newPosition);

        System.out.println(player.getName() + "'s new location is " + player.getPlace());
        System.out.println("The category is " + currentCategory());
    }

    /**
     * Asks the current question based on the current player's position.
     */
    private void askQuestion() {
        String category = currentCategory();
        System.out.println(QUESTION_MANAGER.getQuestion(category));
    }

     /**
     * Determines the category of the current question based on the current player's position.
     *
     * @return The category of the current question
     */
    private String currentCategory() {
        int place = PLAYERS.get(currentPlayerIndex).getPlace();
        if (place == 0 || place == 4 || place == 8) return "Pop";
        if (place == 1 || place == 5 || place == 9) return "Science";
        if (place == 2 || place == 6 || place == 10) return "Sports";
        return "Rock";
    }

    /**
     * Checks if the answer provided by the player was correct and updates the player's score accordingly.
     *
     * @return True if the player hasn't won yet, false if they have.
     */
    public boolean wasCorrectlyAnswered() {
        if (PLAYERS.get(currentPlayerIndex).isInPenaltyBox()) {
            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                addGoldCoinToPlayer();
                return checkWinningCondition();
            } else {
                currentPlayerIndex++;
                if (currentPlayerIndex == PLAYERS.size()) currentPlayerIndex = 0;
                return true;
            }
        } else {
            System.out.println("Answer was correct!!!!");
            addGoldCoinToPlayer();
            return checkWinningCondition();
        }
    }

    /**
     * Adds a coin to the current player's purse.
     */
    private void addGoldCoinToPlayer() {
        Player player = PLAYERS.get(currentPlayerIndex);
        player.setPurse(player.getPurse() + 1);
        System.out.println(player.getName() + " now has " + player.getPurse() + " Gold Coins.");
    }

    /**
     * Checks if the current player has won.
     *
     * @return True if the player hasn't won yet, false if they have.
     */
    private boolean checkWinningCondition() {
        boolean winner = !(PLAYERS.get(currentPlayerIndex).getPurse() == 6);
        currentPlayerIndex++;
        if (currentPlayerIndex == PLAYERS.size()) currentPlayerIndex = 0;
        return winner;
    }

    /**
     * Handles the case when a player answers a question incorrectly.
     *
     * @return Always returns true.
     */
    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(PLAYERS.get(currentPlayerIndex).getName() + " was sent to the penalty box");
        PLAYERS.get(currentPlayerIndex).setInPenaltyBox(true);

        currentPlayerIndex++;
        if (currentPlayerIndex == PLAYERS.size()) currentPlayerIndex = 0;
        return true;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public ArrayList<Player> getPLAYERS() {
        return PLAYERS;
    }
}
