package zadaca3;

import zadaca3.logic.*;

import javax.naming.InvalidNameException;

public class DemoTest {
    public static void main(String[] args) throws InvalidNameException, Exception {

        Defuzzifier def = new COADefuzzifier();
        Conclusion fsAccel = new AccelerationConclusionMin(def);
        Conclusion fsRudder = new RudderConclusionMin(def);

        int L = 200, D = 20, LK = 400, DK = 30, V = 20, S = 1, accel, rudder;

        accel = fsAccel.conclude(L, D, LK, DK, V, S);
        rudder = fsRudder.conclude(L, D, LK, DK, V, S);
        System.out.println(accel + " " + rudder);
        System.out.flush();

    }
}
