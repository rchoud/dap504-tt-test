package com.company;

//This class was used to experiment with inheritance
//It is sub class of the Player class

public class TeamPlayer extends Player {
    //Constructor to map the attributes from super class Player
    public String teamName, teamCountry;

    public TeamPlayer(String firstName, String lastName, String teamName, String teamCountry) {
        super(firstName, lastName);
        this.teamName = teamName;
        this.teamCountry = teamCountry;
    }



}
