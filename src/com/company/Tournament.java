package com.company;

import com.google.gson.Gson;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * <p><b>Tournament class</b> is where all the game simulation takes place
 * create List of Player objects with Json data for names</p>
 * Calculate rounds (int) by using logarithm with number of players(int)
 * Play tournament / Play rounds / Output winners
 * <p>Iterate through rounds down to Champion with a series of loops and random scores</p>
 */


public class Tournament {

    /**
     * Initialise variables to assign values to attributes
     */

    static int numberOfPlayers;
    static int numberOfRounds;
    static int currentRound = 1; //Start with first round and iterate up ++

    /**
     * Create ArrayList of Player Objects
     */
    public List<Player> playersList = new ArrayList<Player>();

    /**
     * Tournament class constructor, to set initial attributes
     */
    public Tournament(int numberOfPlayers) throws IOException {

        createPlayerListArray(numberOfPlayers);

        numberOfRounds = calculateRounds(numberOfPlayers);

    }



    /**
     * Method for creating List of Player objects with the names populated by JSON data
     * The names are randomised with Collections.shuffle method.
     */

    public void createPlayerListArray(int numberOfPlayers) throws IOException {
        //Generate string from JSON data
        String jsonFile = "data/players.json";
        //Instantiate new Gson class
        Gson gson = new Gson();
        FileReader fileReader = new FileReader(jsonFile);
        JsonReader jsonReader = new JsonReader(fileReader);
        ReadJson[] data = gson.fromJson(jsonReader, ReadJson[].class);

        for (int i = 0; i < numberOfPlayers; i++) {

            String firstName = data[i].getFirst_name();
            String lastName = data[i].getLast_name();

            this.playersList.add(new Player(firstName, lastName));
            //shuffle the arrayList for randomness
            Collections.shuffle(playersList);

        }
    }

    /**
     * calcNumbRounds() Method for Calculating number of rounds method
     * Take the argument numberOfPlayers int and returns int.
     * Logarithm (Math log method) takes numberOfPlayers natural logarithm and divides it by 2, which returns the numberOfRounds
     * Math.log calculates how 2s to multiply to to get the numberOfPlayers, example 16 players (2 x 2 x 2 x 2 = 16) = 4 Rounds
     */

    public int calculateRounds(int numberOfPlayers) {
        //logarithm to find how many 2s to multiply to get numberOfPlayers 32 players (2 x 2 x 2 x 2 x 2 = 32) = 5 Rounds
        return (int) (Math.log(numberOfPlayers) / Math.log(2));
    }

    //Playing with time function, calculate length of tournament in milliseconds
    private final long delay = 60;
    private long start, end;
    private long timeTaken = 0;

    //Instantiation of Date class
    Date date = new Date(null);


    public void lineDivider() {
        System.out.println("______________________________________________");
    }

    //Not the most efficient methods to add titles depending on whether a standard round or the final
    public void roundTitle() {
        lineDivider();
        System.out.println("\n--------------------- R O U N D ---------------------");
        System.out.println("\t            " + currentRound++ + "            \n");
    }

    public void finalTitle() {
        lineDivider();
        System.out.println("\n-*-*-*-*-*-*-*-   F I N A L  R O U N D   -*-*-*-*-*-*-*-\n");
    }



    /**
     * playRound() Method takes List of Player object as arguments
     * new list of winners created
     * Timer function, which sets the current time and an end time and outputs timeTaken
     * <p>
     * The core 'for' loop, iterates(i++) for numberOfRounds
     * takes winners List, passed as players list and players is then passed back as winners to run loop until numberOfRounds == 0
     * Score (Player) is calculated at this stage so each round outputs new score for each player
     * Additional if statement appends additional output when last round
     */
    public void playRound(List<Player> players) {

        //winners ArrayList initializer
        List<Player> winners = new ArrayList<>();

        //When the tournament started
        start = System.currentTimeMillis();

        //Main loop for running rounds, loop x numberOfRounds
        for (int i = 0; i < numberOfRounds; i++) {
            //Loop for titles, if not the last iteration then roundTitle
            if (i != numberOfRounds - 1) {
                roundTitle();
                //else display finalTitle
            } else {
                finalTitle();
            }
            //winners List is passed as the players to run through the round
            winners = playMatch(players);
            players = winners;

            //for each loop that calls on Player object method calculateScore, this happens with each round iteration
            for (Player player : players) {
                player.calculateScore();
            }

            //Output for the champion
            if (i == numberOfRounds - 1) {
                lineDivider();
                //With numberOfRounds - 1, append remaining winner in the List output as Champion
                String champion = "\n" + winners.get(0).printName() + " is the champion !!";
                //Convert string to uppercase for increased impact
                String championUpper = champion.toUpperCase();
                System.out.println(championUpper);
                lineDivider();
                System.out.println("******************************************************");

            }


        }
        //Small method to calculate/sys out time taken to complete the tournament
        end = System.currentTimeMillis();
        timeTaken = start - end;
        System.out.println("\n\n This Tournament took" + timeTaken + " milliseconds to complete");

    }


    /**
     * playMatch() method is List type and runs a for loop that takes arrayListPlayers Player and iterates though pairs of players
     * Sys.out paired players: player 0 vs player 1...
     * The pairs (i) and (i + 1) are then run through if statements to calculate winning scores and add winning player to winners List
     * Sys.out List of winners to console
     * Return winners to pass on to playRound() method. and repeat for each round
     */
    public List<Player> playMatch(List<Player> arrayListPlayers) {

        //instantiate Lists for winners / losers
        List<Player> winners = new ArrayList<>();
        List<Player> losers = new ArrayList<>();

        //Pairing up - each Player with the next Player, iterator runs for every 2
        for (int i = 0; i < arrayListPlayers.size(); i += 2) {

            System.out.println(arrayListPlayers.get(i).printName() + " (" + arrayListPlayers.get(i).score + ")  vs  "
                    + arrayListPlayers.get((i + 1) % arrayListPlayers.size()).printName() + " (" + arrayListPlayers.get(i + 1).score + ")");

            //Extra layer of random scoring, so calculateScore is run with each round
            //Without this, players get an initial score that stay with them through the tournament
            arrayListPlayers.get(i).calculateScore();

            //Use score to decipher winner, if (i) score is greater than (i +1) score then add (i) to winners List
            if (arrayListPlayers.get(i).score > arrayListPlayers.get(i + 1).score) {
                winners.add(arrayListPlayers.get(i));
            }
            //And if (i) score is less than (i + 1) score add (i + 1) to winners List
            if (arrayListPlayers.get(i).score < arrayListPlayers.get(i + 1).score) {
                winners.add(arrayListPlayers.get(i + 1));


                //extra if statement to handle draws, if score is equal add player [0] to winners List, could randomise this?
            } else if
            (arrayListPlayers.get(i).score == arrayListPlayers.get(i + 1).score) {
                winners.add(arrayListPlayers.get(i));
            }

            /**
             * Additional if statements for adding Player objects to new List 'losers'
             */
            //Create List of losers (not output)
            if (arrayListPlayers.get(i).score < arrayListPlayers.get(i + 1).score) {
                winners.remove(arrayListPlayers.get(i));
                losers.add(arrayListPlayers.get(i));

            } else if (arrayListPlayers.get(i).score > arrayListPlayers.get(i + 1).score) {
                winners.remove(arrayListPlayers.get(i + 1));
                losers.add(arrayListPlayers.get(i + 1));
            }
        }

        /**
         * This section of the playRound method outputs the list of winners for each round
         * A sleep function was added in attempt to slow down the output
         */

        System.out.println("\n-x-x-x-x-x-x-x     W I N N E R S     x-x-x-x-x-x-x-\n");
        //Loop through winners and attach names
        try {
            sleep(10);
            for (int i = 0; i < winners.size(); i++) {
                //SysOut to console the winners
                System.out.println(winners.get(i).printName() + " won with " + winners.get(i).score + " points");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Execution completed return winners List<>
        return winners;

    }


  /*  public void arrayListPlayers() {
        for (Player thePlayer : playersList) {
            //thePlayer: displays the address in memory to demonstrate that these are Objects
            System.out.println(thePlayer.printName() + " Skill: " + thePlayer.skill);
        }
    }*/


}
