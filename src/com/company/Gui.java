package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import static java.lang.Thread.sleep;


public class Gui extends JFrame {
    //Gui components

    private JPanel mainPanel;
    private JLabel mainTitle;
    private JButton clearButton;
    private JLabel firstLabel;
    private JTextField userInput;
    private JButton displayPlayersButton;

    private JTextArea leftTextArea;

    private JButton makeMatches;

    private JComboBox comboBox1;
    private JTextArea mainTextArea;
    private JButton showPlayers;



    public Gui() throws IOException {

        JFrame frame = new JFrame("Table Tennis App");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPanel = new JScrollPane(textArea);

        frame.pack();
        frame.setSize(800, 400);

        JTextArea textArea1 = new JTextArea(3, 16);
        mainTextArea.setEditable(false);




        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                leftTextArea.setText("");

            }


        });



        displayPlayersButton.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent evt) {

                try {
                    String labelText = userInput.getText();
                    // JOptionPane.showMessageDialog(null, "This is really difficult");
                    int playerAsInt = Integer.parseInt(labelText);

                    firstLabel.setText(labelText);
                    Tournament tournament = new Tournament(playerAsInt);

                    //System.out.println(playerAsInt);
                    //tournament.numberOfPlayers = playerAsInt;

                    System.out.println(tournament.numberOfPlayers);

                    for (int i = 0; i < tournament.playersList.size(); i++) {

                        leftTextArea.append(tournament.playersList.get(i).firstName + " " + tournament.playersList.get(i).lastName + "\n");


                    }

                   /* for (int i = 0; i < tournament.winnersList.size(); i++) {

                        mainTextArea.append(tournament.winnersList.get(i).firstName + " " + tournament.winnersList.get(i).lastName + "\n");


                    }*/


                } catch (NumberFormatException | IOException e) {
                    JOptionPane.showMessageDialog(null,
                            "Please enter number of players\nFor example 128, 64, 32, 16");

                }


            }


        });


        makeMatches.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });

        frame.setVisible(true);
    }


}

