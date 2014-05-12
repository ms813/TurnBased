package Generic;

import java.util.Random;

/**
 * Created by Matthew on 27/04/2014.
 */
public class Dice {

    public static int roll(int n, int sides) {
        Random rnd = new Random();

        int total = 0;
        for(int i = 0; i < n; i++){
            total += (rnd.nextInt(sides) +1);
        }
        return total;
    }

}
