package com.company;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Tournament {


    static int numberOfPlayers;
    //   static int currentRound = 1; // first round is always going to be one - Add this when .

    public List<Player> playersList = new ArrayList<Player>();


    public Tournament() throws IOException {

        Tournament.numberOfPlayers = 8;

        createPlayerListArray(numberOfPlayers);  // todo - move into TheGUI class?

        //this.numberOfRounds = calcNumbRounds();
        //System.out.println("rounds = " + numberOfRounds);

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

            this.playersList.add( new Player(firstName, lastName));


        }

        for (Player thePlayer: playersList ) {
            //thePlayer: displays the address in memory to demonstrate that these are Objects
            System.out.println( thePlayer + " : " + thePlayer.firstName + " " + thePlayer.lastName);
        }
    }



}
