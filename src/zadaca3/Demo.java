package zadaca3;

import zadaca3.system.*;

import javax.naming.InvalidNameException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Demo {

    public static void main(String[] args) throws IOException, InvalidNameException, Exception {

        Defuzzifier def = new COADefuzzifier();
        FuzzySystem fsAccel = new AccelerationFuzzySystemMin(def);
        FuzzySystem fsRudder = new RudderFuzzySystemMin(def);

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int L = 0, D = 0, LK = 0, DK = 0, V = 0, S = 0, accel, rudder;
        String line = null;
        while (true) {
            if ((line = input.readLine()) != null) {
                if (line.charAt(0) == 'K') {
                    break;
                }
                Scanner s = new Scanner(line);
                L = s.nextInt();
                D = s.nextInt();
                LK = s.nextInt();
                DK = s.nextInt();
                V = s.nextInt();
                S = s.nextInt();
            }

            // fuzzy magic ...
            accel = fsAccel.conclude(L, D, LK, DK, V, S);
            rudder = fsRudder.conclude(L, D, LK, DK, V, S);
            System.out.println(accel + " " + rudder);
            System.out.flush();
        }

    }

}