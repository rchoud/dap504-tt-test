package com.company;

public class Player {

    public String firstName, lastName;
    public int skill = 0;
//    todo - make private


    public Player(String firstName, String lastName, int skill) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.skill = skill;

    }

    public void printName() {

        System.out.println(firstName + " " + lastName);
    }


}
