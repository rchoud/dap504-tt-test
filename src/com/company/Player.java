package com.company;

import java.util.Random;

/**
 * <p><b>Player class is used to create Player objects</b></p>
 * <p>Player objects are the main object in the simulator, they are used to create Lists and are passed to other Objects and classes
 * The attributes of Player object are, firstName, lastName.</p>
 * The calculateScore method runs a Randomising function (random number between 20-100) to give the Player a score -
 * this is called during each round so that a new score is given each round
 *
 * printName method is just to concatenate the first name and last name
 */

public class Player {

    private String firstName, lastName;
    public int score = 0;

    Player(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        calculateScore();
    }

    //Generate a random number between 20-100 for score
    public void calculateScore() {
        Random ran = new Random();
        //Generate arbitrary value to decide a winner
        score = ran.nextInt(80) + 20;


    }

    public String printName() {
        String name = this.firstName + " " + this.lastName;
        return name;
    }


}
