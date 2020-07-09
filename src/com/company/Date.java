package com.company;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//Date object, just trying out a simple Class and how to instantiate
public class Date {

    public String dateString;

    Date(String dateString) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy/ HH:mm");
        LocalDateTime now = LocalDateTime.now();
        //System.out.println(dtf.format(now));
        this.dateString = dtf.format(now);

    }

}
