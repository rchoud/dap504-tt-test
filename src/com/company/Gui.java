package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

/**
 * <p><b>Gui class</b> is where all swing Gui functionality is carried out</p>
 * The main JFrame frame contains all the layout elements consisting of JPanels, JScrollPanes, JTextAreas, and JButtons
 * <p>Buttons contain the action listeners to trigger events from user interaction.</p>
 */

public class Gui {
    //Gui components

    private JPanel mainPanel;
    private JLabel mainTitle;
    private JButton clearButton;
    private JLabel picLabel;
    private JButton displayPlayersButton;
    private JTextArea leftTextArea;
    private JScrollPane centrePane;
    private JTextArea centreTextArea;
    private JButton jButton;
    private JRadioButton b1;
    private JRadioButton b2;
    private JRadioButton b3;
    private JLabel roundsLabel;
    private JLabel playersLabel;
    private JLabel dateLabel;
    private JProgressBar progressBar1;
    private JScrollPane leftPane;
    private JButton exitButton;
    private JRadioButton b4;


    public Gui() {

        //Gui constructors
        JFrame frame = new JFrame("Table Tennis App");

        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea textArea = new JTextArea();
        JScrollPane scrollPanel = new JScrollPane(textArea);

        PrintStream printStream = new PrintStream(new CustomOutputStream(centreTextArea));
        System.setOut(printStream);
        System.setErr(printStream);


        frame.pack();
        frame.setSize(950, 750);

        //Set the main JTextAreas to be uneditable
        this.leftTextArea.setEditable(false);
        this.centreTextArea.setEditable(false);

        /**
         * <p><b>Start button Listener</b>
         * <p>This button listens for the values supplied by the user selecting from jRadioButtons, these values are passed as String variables to playerAsInt to be converted into ints</p>
         * <p>Tournament object is instantiated here with the playersAsInt var for numberOfPlayers</p>
         * <p>List of Player Objects instantiated and runTournament method is called - the rest is handled in Tournament class</p>
         * <p>Superficial output of all Players in the tournament with for loop, displays all names in leftTextArea with numbers</p>
         *
         */
        // Adding Listener to JButton.
        jButton.addActionListener(new ActionListener() {
            // Anonymous class.
            public void actionPerformed(ActionEvent e) {
                System.out.println("------------------------------------------------------");
                System.out.println(" +++++   WELCOME TO THE TOURNAMENT  +++++");

                //Unfinished progress bar...Doesn't actually run alongside output - needed threading, which was explored but unsuccessful
                final Timer t = new Timer(10, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        progressBar1.setValue(progressBar1.getValue() + 1);
                        if (progressBar1.getValue() == 100) {
                            ((Timer) e.getSource()).stop();
                        }
                    }
                });

                try {
                    String radioText = "";
                    String dialog = " ";
                    //Radio buttons for tournament size
                    if (b1.isSelected()) {
                        radioText = "16";
                    } else if (b2.isSelected()) {
                        radioText = "32";
                    } else if (b3.isSelected()) {
                        radioText = "64"; //64 is selected as default which is set in the Gui designer
                    } else if (b4.isSelected()) {
                        radioText = "128";
                    } else {
                        dialog = "No Button selected";
                    }

                    // Convert user input to Integer before passing to Tournament
                    int playerAsInt = Integer.parseInt(radioText);
                    int numberOfRounds;

                    //Instantiation of Tournament object
                    Tournament tournament = new Tournament(playerAsInt);

                    //Date class object to display date in Gui
                    Date date = new Date(null);

                    tournament.calculateRounds(playerAsInt);
                    // Use playersAsInt
                    numberOfRounds = tournament.calculateRounds(playerAsInt);

                    //System.out.println(playerAsInt);
                    tournament.numberOfPlayers = playerAsInt;

                    //JLabels across the top, display the number of players / rounds
                    playersLabel.setText(radioText + " Players");
                    roundsLabel.setText(numberOfRounds + " Rounds");
                    dateLabel.setText("Date of Tournament:  " + date.dateString);

                    //Initial creation of List of <Player> objects
                    List<Player> arrayListPlayers = tournament.playersList;

                    //run playRound method from Tournament
                    tournament.playRound(arrayListPlayers);

                    //For loop, to output the initial list of Players to the Gui leftTextArea
                    //This panel does not change through the tournament, serves as a log of all the players in any given tournament
                    for (int i = 0; i < tournament.playersList.size(); i++) {
                        //Display all players that enter the tournament in the left Panel
                        //i+1 Iterator for numbers next to each Player
                        leftTextArea.append(i + 1 + "  " + tournament.playersList.get(i).printName() + "\n");
                    }


                    //Start the progress bar
                    t.start();

                    //invokeLater used do move the scroll position back to the top
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            leftPane.getViewport().setViewPosition(new Point(0, 0));
                            centrePane.getViewport().setViewPosition(new Point(0, 0));
                        }
                    });

                    //If no radio button is selected and user presses to Start
                } catch (NumberFormatException | IOException er) {
                    JOptionPane.showMessageDialog(null,
                            "Please select the number of players with the radio buttons");
                }
            }
        });

        /**
         * <p><b>Clear Button</b> empties the JText Areas (leftTextArea and centreTextArea), resets the progress bar back to 0
         * Reset currentRound = 0</p>
         * <p>User can select new radio button for player numbers and Start a new tournament</p>
         */
        //Only clears the text, does not restart
        clearButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                leftTextArea.setText("");
                centreTextArea.setText("");
                progressBar1.setValue(0);
                //Reset to current round 1 to run a new tournament
                Tournament.currentRound = 1;
            }
        });

        /**
         * <p><b>Exit Button</b> uses quite a crude System.exit function to terminate the program</p>
         */
        //Exit Button event listener
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //A bit crude but seems to work effectively
                System.exit(0);
            }
        });

        frame.setVisible(true);

    }

}

