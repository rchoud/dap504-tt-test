package com.company;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Date class is used to create a date stamp in the format of dd/MM/yyyy (10/07/2020)
 * Date object is instantiated in Gui to display when the Tournament took place
 */


//Date object, just trying out a simple Class and how to instantiate
public class Date {

    public String dateString;

    Date(String dateString) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy/ HH:mm");
        LocalDateTime now = LocalDateTime.now();
        //System.out.println(dtf.format(now));
        this.dateString = dtf.format(now);

    }

    public void timeFunction() {

        final long delay = 60;
        long start, end;
        long timeTaken = 0;

    }
}
