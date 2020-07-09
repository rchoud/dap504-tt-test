package com.company;

// Main class is the entry point to the application


public class Main {



    public static void main(String[] args) throws Exception {

        System.out.println("--------------------------------------------");
        System.out.println(" Welcome to the Table Tennis Java Simulator ");
        System.out.println("--------------------------------------------");
        System.out.println("----All output is displayed in the GUI------");

	// starting point of app, start Tournament class
        //Instantiated in Gui class
        //Tournament tournament = new Tournament(Tournament.numberOfPlayers);

        Gui Gui = new Gui();

    }
}
