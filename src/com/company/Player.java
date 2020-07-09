package com.company;

import java.util.Random;

public class Player {

    private String firstName, lastName;
    public int score = 0;
//    todo - make private

    public Player(String firstName, String lastName) {
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
