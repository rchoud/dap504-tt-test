package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;


public class Gui {
    //Gui components

    private JPanel mainPanel;
    private JLabel mainTitle;
    private JButton clearButton;
    private JLabel picLabel;
    private JButton displayPlayersButton;

    private JTextArea leftTextArea;

    private JButton makeMatches;

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

    private JButton showPlayers;


    public Gui() {


        JFrame frame = new JFrame("Table Tennis App");

        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea textArea = new JTextArea();
        JScrollPane scrollPanel = new JScrollPane(textArea);

        PrintStream printStream = new PrintStream(new CustomOutputStream(centreTextArea));
        System.setOut(printStream);
        System.setErr(printStream);


        frame.pack();
        frame.setSize(1200, 650);


        this.leftTextArea.setEditable(false);
        this.centreTextArea.setEditable(false);


        // Adding Listener to JButton.
        jButton.addActionListener(new ActionListener() {
            // Anonymous class.
            public void actionPerformed(ActionEvent e) {

                //Unfinished progress bar...
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
                        radioText = "64";
                    } else {
                        dialog = "No Button selected";
                    }

                    // Convert user input to Int before passing to tournament
                    int playerAsInt = Integer.parseInt(radioText);

                    //picLabel.setText(labelText + " Players");

                    int numberOfRounds;

                    Tournament tournament = new Tournament(playerAsInt);
                    Date date = new Date(null);


                    tournament.calcNumbRounds(playerAsInt);
                    numberOfRounds = tournament.calcNumbRounds(playerAsInt);

                    //System.out.println(playerAsInt);
                    tournament.numberOfPlayers = playerAsInt;
                    playersLabel.setText(radioText + " Players");
                    roundsLabel.setText(numberOfRounds + " Rounds");

                    dateLabel.setText("Date of Tournament:  " + date.dateString);

                    for (int i = 0; i < tournament.playersList.size(); i++) {
                        //Display all players that enter the tournament in the left Panel
                        //i+1 Iterator for numbers next to each Player
                        leftTextArea.append(i+1 + "\t" + tournament.playersList.get(i).printName() + "\n");
                    }

                    t.start();


                    //Create List
                    List<Player> arrayListPlayers = tournament.playersList;

                    tournament.playTournament(arrayListPlayers);

                } catch (NumberFormatException | IOException er) {
                    JOptionPane.showMessageDialog(null,
                            "Please select the number of players with the radio buttons");

                }
            }
        });

        //Only clears the text, does not restart
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leftTextArea.setText("");
                centreTextArea.setText("");
                progressBar1.setValue(0);
                //Reset to current round 1 to run a new tournament
                Tournament.currentRound = 1;

            }

        });


        frame.setVisible(true);


    }

}

