package com.company;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Tournament {

    public int numberOfPlayers;
    //static int currentRound = 1; // first round is always going to be one - Add this when .


    public List<Player> playersList = new ArrayList<Player>();



    public Tournament(int numberOfPlayers) throws IOException {

        this.numberOfPlayers = numberOfPlayers;


        createPlayerListArray(numberOfPlayers);  // todo - move into TheGUI class?

        //uncomment to view all the Player objects as name in arrayList
        //arrayListPlayers();

        playRound(playersList);

      //  this.numberOfRounds = calcNumbRounds();
       // System.out.println("rounds = " + numberOfRounds);

    } // end constructor




    public void createPlayerListArray(int numberOfPlayers) throws IOException {
        // create array list of player objects

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

            Random ran = new Random();
            int skill = ran.nextInt(80) + 20;

            this.playersList.add(new Player(firstName, lastName, skill));
            //shuffle the arrayList for randomness
            Collections.shuffle(playersList);



        }

    }

  /*  public void arrayListPlayers() {
        for (Player thePlayer : playersList) {
            //thePlayer: displays the address in memory to demonstrate that these are Objects
            System.out.println(thePlayer.firstName + " " + thePlayer.lastName + " Skill: " + thePlayer.skill);
        }
    }*/



    //Need to work out how to get this in the Gui
    public void playRound(List<Player> arrayListPlayers) throws IOException {
        //Pair up player object and
        List<Player> winners = new ArrayList<>();


            for(int i = 0; i < arrayListPlayers.size(); i+=2)
            {

                System.out.println(
                        arrayListPlayers.get(i).firstName + " " + arrayListPlayers.get(i).lastName + "(" + arrayListPlayers.get(i).skill + ") vs "
                + arrayListPlayers.get((i+1) % arrayListPlayers.size()).firstName + " " + arrayListPlayers.get((i+1) % arrayListPlayers.size()).lastName + "(" + arrayListPlayers.get((i+1) % arrayListPlayers.size()).skill + ")");

                if (arrayListPlayers.get(i).skill > arrayListPlayers.get(i+1).skill) {
                    winners.add(arrayListPlayers.get(i));
                    //System.out.println(winners.get(i).lastName);

            }
                else if(arrayListPlayers.get(i).skill < arrayListPlayers.get(i+1).skill) {
                    winners.add(arrayListPlayers.get(i+1));

                }

            }
//Loop through winners and attach names
        for(int i = 0; i < winners.size(); i++)
        {
            System.out.println("Winners:" + winners.get(i).firstName + " " +  winners.get(i).lastName);
        }


    }


/*




// I have no idea what I am doing here - trying to return list of winners
    private List<Player> winners() {
        System.out.println(playersList);
        return null;
    }*/


}
