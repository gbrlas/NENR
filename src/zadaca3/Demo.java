package zadaca3;

import zadaca3.system.*;

import javax.naming.InvalidNameException;

public class Demo {
    public static void main(String[] args) throws InvalidNameException, Exception {

        Defuzzifier def = new COADefuzzifier();
        FuzzySystem fsAccel = new AccelerationFuzzySystemMin(def);
        FuzzySystem fsRudder = new RudderFuzzySystemMin(def);

        int L = 200, D = 20, LK = 400, DK = 30, V = 20, S = 1, accel, rudder;

        accel = fsAccel.conclude(L, D, LK, DK, V, S);
        rudder = fsRudder.conclude(L, D, LK, DK, V, S);
        System.out.println(accel + " " + rudder);
        System.out.flush();

    }
}
