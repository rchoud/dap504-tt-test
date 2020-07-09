package com.company;

import java.util.Random;

public class Match {

    public boolean Match(boolean matchWinner) {
        Random random = new Random();
//For 50% chance of true
        boolean chance50oftrue = (random.nextInt(2) == 0) ? true : false;

        return chance50oftrue;


    }


}
