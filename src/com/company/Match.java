package com.company;

import java.util.Random;
/**
 * Unused match class, this was intended to run games - winner of each game would be decided by random boolean
 * First to 11 games wins match, intended to also calculate for a 2 point advantage before decing winner of match
 * This is unfinished...

 */
public class Match {

    public boolean Match(boolean matchWinner) {
        Random random = new Random();
//For 50% chance of true
        boolean chance50oftrue = (random.nextInt(2) == 0) ? true : false;

        return chance50oftrue;


    }


}
