package zadaca3.rules;

import zadaca1.zadatak1.IDomain;
import zadaca1.zadatak1.SimpleDomain;
import zadaca1.zadatak2.CalculatedFuzzySet;
import zadaca1.zadatak2.StandardFuzzySets;
import zadaca3.utilities.Term;
import zadaca3.utilities.Variable;

public class RuleBase {
    private static Variable distance;
    private static Variable speed;
    private static Variable direction;
    private static Variable acceleration;
    private static Variable rudder;

    public static void setRuleBase() {
        IDomain distanceDomain = new SimpleDomain(0, 1221);
        IDomain speedDomain = new SimpleDomain(0, 101);
        IDomain accelerationDomain = new SimpleDomain(-50, 51);
        IDomain angleDomain = new SimpleDomain(-90, 91);

        distance = new Variable("distance");
        speed = new Variable("speed");
        acceleration = new Variable("acceleration");
        rudder = new Variable("rudder");

        Term distanceClose = new Term(distance, "close",
                new CalculatedFuzzySet(distanceDomain, StandardFuzzySets.lFunction(20, 40)));
        Term distanceMiddle = new Term(distance, "middle",
                new CalculatedFuzzySet(distanceDomain, StandardFuzzySets.lambdaFunction(35, 45, 55)));
        distance.addTerm(distanceClose);
        distance.addTerm(distanceMiddle);

        Term slow = new Term(speed, "slow",
                new CalculatedFuzzySet(speedDomain, StandardFuzzySets.lFunction(0, 40)));
        Term fast = new Term(speed, "fast",
                new CalculatedFuzzySet(speedDomain, StandardFuzzySets.gammaFunction(35, 70)));
        speed.addTerm(slow);
        speed.addTerm(fast);

        Term negative = new Term(acceleration, "negative",
                new CalculatedFuzzySet(accelerationDomain, StandardFuzzySets.lFunction(40, 50)));
        Term positive = new Term(acceleration, "positive",
                new CalculatedFuzzySet(accelerationDomain, StandardFuzzySets.lambdaFunction(52, 60, 68)));
        acceleration.addTerm(negative);
        acceleration.addTerm(positive);

        Term sharpRight = new Term(rudder, "sharpRight",
                new CalculatedFuzzySet(angleDomain, StandardFuzzySets.lFunction(-75, -45)));
        Term turnRight = new Term(rudder, "turnRight",
                new CalculatedFuzzySet(angleDomain, StandardFuzzySets.lFunction(-50, 0)));
        Term turnLeft = new Term(rudder, "turnLeft",
                new CalculatedFuzzySet(angleDomain, StandardFuzzySets.gammaFunction(0, 50)));
        Term sharpLeft = new Term(rudder, "sharpLeft",
                new CalculatedFuzzySet(angleDomain, StandardFuzzySets.gammaFunction(45, 75)));
        rudder.addTerm(turnLeft);
        rudder.addTerm(turnRight);
        rudder.addTerm(sharpLeft);
        rudder.addTerm(sharpRight);
    }

    public static Variable getDistance() {
        return distance;
    }

    public static Variable getSpeed() {
        return speed;
    }

    public static Variable getAcceleration() {
        return acceleration;
    }

    public static Variable getRudder() {
        return rudder;
    }
}
