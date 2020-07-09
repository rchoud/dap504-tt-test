package com.company;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Thread.sleep;

public class Tournament {

    /**
     * Initialise variables to assign values to attributes
     */

    static int numberOfRounds;
    static int numberOfPlayers;
    static int currentRound = 1; //Start with first round and iterate up ++

    /**
     * Create a ListArray of <Player> Objects
     */
    public List<Player> playersList = new ArrayList<Player>();


    /**
     * Tournament class constructor, to set initial attributes
     */
    public Tournament(int numberOfPlayers) throws IOException {

        numberOfPlayers = numberOfPlayers;

        createPlayerListArray(numberOfPlayers);

        numberOfRounds = calcNumbRounds(numberOfPlayers);

    }

    /**
     * Method for creating List of Player objects with the names populated by JSON data
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
     * Take the argument numberOfPlayers int and returns int by dividing the numberOfPlayers by 2
     */

    public int calcNumbRounds(int numberOfPlayers) {
        return (int) (Math.log(numberOfPlayers) / Math.log(2));
    }

    //Playing with time function, calculate length of tournament in milliseconds
    private final long delay = 60;
    private long start, end;
    private long timeTaken = 0;

    //Instantiation of Date class
    Date date = new Date(null);

   // Gui gui = new Gui();


    public void lineDivider() {
        System.out.println("\n______________________________________________\n");

    }

    //Not the most efficient methods to add titles depending on whether a standard round or the final
    public void roundTitle() {
        lineDivider();
        System.out.println("--------------------- R O U N D ---------------------");
        System.out.println("\t            " + currentRound++ + "            \n");
    }

    public void finalTitle() {

        lineDivider();
        System.out.println("-*-*-*-*-*-*-*-   F I N A L  R O U N D   -*-*-*-*-*-*-*-\n");
    }


    public void playTournament(List<Player> players) {

        List<Player> winners = new ArrayList<>();
        //When the tournament started

        start = System.currentTimeMillis();
        //System.out.println(date.dateString);
        //System.out.println(date);
        //Main loop for running rounds
        for (int i = 0; i < numberOfRounds; i++) {

            //Loop for titles, if not the last iteration then roundTitle
            if (i != numberOfRounds -1) {
                roundTitle();
            //else display finalTitle
            } else {
                finalTitle();
            }
            //winners List is passed as the players to run through the round
            winners = playRound(players);

            players = winners;

            //for each loop that calls on Player object method calculateScore
            for (Player player : players) {
                player.calculateScore();
            }

            //Output for the winner
            if (i == numberOfRounds - 1) {

                //get last remaining winner in the List output as Champion
                String champion = winners.get(0).printName() + " is the champion !!";
                String championUpper = champion.toUpperCase();
                System.out.println(championUpper);
                lineDivider();

            }


        }
        //Small method to calculate/sys out time taken to complete the tournament
        end = System.currentTimeMillis();
        timeTaken = start - end;
        System.out.println("\n\n This Tournament took" + timeTaken + " milliseconds to complete");

    }


    public List<Player> playRound(List<Player> arrayListPlayers) {
        //Pair up player object and

        List<Player> winners = new ArrayList<>();
       // List<Player> losers = new ArrayList<>();

        //Pairing up - each Player with the next Player
        for (int i = 0; i < arrayListPlayers.size(); i += 2) {

            System.out.println(arrayListPlayers.get(i).printName() + "  vs  " + arrayListPlayers.get((i + 1) % arrayListPlayers.size()).printName());

            arrayListPlayers.get(i).calculateScore();
            //Use score to decipher winner
            if (arrayListPlayers.get(i).score > arrayListPlayers.get(i + 1).score) {
                winners.add(arrayListPlayers.get(i));
            }
            //extra if statement to handle draws, if score is equal add player [0] to winners
            if (arrayListPlayers.get(i).score == arrayListPlayers.get(i + 1).score) {
                winners.add(arrayListPlayers.get(i));

            }
             else if (arrayListPlayers.get(i).score < arrayListPlayers.get(i + 1).score) {
                winners.add(arrayListPlayers.get(i + 1));
            }


            //Create List of losers (not output)
/*            if (arrayListPlayers.get(i).score < arrayListPlayers.get(i + 1).score) {
                winners.remove(arrayListPlayers.get(i));
                losers.add(arrayListPlayers.get(i));

            } else if (arrayListPlayers.get(i).score > arrayListPlayers.get(i + 1).score) {
                winners.remove(arrayListPlayers.get(i + 1));
                losers.add(arrayListPlayers.get(i + 1));
            }*/
        }



        System.out.println("\n-x-x-x-x-x-x-x     W I N N E R S     x-x-x-x-x-x-x-\n");
        //Loop through winners and attach names
        try {
            sleep(10);
            for (int i = 0; i < winners.size(); i++) {
                //SysOut to console the winners
                System.out.println(winners.get(i).printName());

            }
            }
            catch (InterruptedException e){
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
