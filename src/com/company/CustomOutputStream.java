package com.company;


import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;

public class CustomOutputStream extends OutputStream {
    private JTextArea mainContents;

    public CustomOutputStream(JTextArea textArea) {
        this.mainContents = textArea;
    }

    @Override
    public void write(int b) throws IOException {
        // redirects data to the text area
        mainContents.append(String.valueOf((char)b));
        // scrolls the text area to the end of data
        mainContents.setCaretPosition(mainContents.getDocument().getLength());
    }
}



