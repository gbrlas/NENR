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
    private static Variable acceleration;
    private static Variable rudder;

    public static void setRuleBase() {
        IDomain distanceDomain = new SimpleDomain(0, 1301);
        IDomain speedDomain = new SimpleDomain(0, 201);
        IDomain accelerationDomain = new SimpleDomain(0, 100);
        IDomain angleDomain = new SimpleDomain(0, 181);

        distance = new Variable("distance");
        speed = new Variable("speed");
        acceleration = new Variable("acceleration");
        rudder = new Variable("rudder");

        Term distanceTooClose = new Term(distance, "tooClose",
                new CalculatedFuzzySet(distanceDomain, StandardFuzzySets.lFunction(25, 40)));
        Term distanceClose = new Term(distance, "close",
                new CalculatedFuzzySet(distanceDomain, StandardFuzzySets.lFunction(30, 60)));
        Term distanceMiddle = new Term(distance, "middle",
                new CalculatedFuzzySet(distanceDomain, StandardFuzzySets.gammaFunction(40, 70)));
        distance.addTerm(distanceTooClose);
        distance.addTerm(distanceClose);
        distance.addTerm(distanceMiddle);


        Term slow = new Term(speed, "slow",
                new CalculatedFuzzySet(speedDomain, StandardFuzzySets.lFunction(10, 30)));
        Term fast = new Term(speed, "fast",
                new CalculatedFuzzySet(speedDomain, StandardFuzzySets.gammaFunction(25, 45)));
        speed.addTerm(slow);
        speed.addTerm(fast);


        Term negative = new Term(acceleration, "negative",
                new CalculatedFuzzySet(accelerationDomain, StandardFuzzySets.lFunction(0, 65)));
        Term positive = new Term(acceleration, "positive",
                new CalculatedFuzzySet(accelerationDomain, StandardFuzzySets.gammaFunction(55, 120)));
        acceleration.addTerm(negative);
        acceleration.addTerm(positive);


        Term sharpRight = new Term(rudder, "sharpRight",
                new CalculatedFuzzySet(angleDomain, StandardFuzzySets.lFunction(0, 30)));
        Term turnRight = new Term(rudder, "turnRight",
                new CalculatedFuzzySet(angleDomain, StandardFuzzySets.lFunction(20, 50)));
        Term r = new Term(rudder, "r",
                new CalculatedFuzzySet(angleDomain, StandardFuzzySets.lFunction(0, 70)));
        Term l = new Term(rudder, "l",
                new CalculatedFuzzySet(angleDomain, StandardFuzzySets.gammaFunction(110, 180)));
        Term turnLeft = new Term(rudder, "turnLeft",
                new CalculatedFuzzySet(angleDomain, StandardFuzzySets.lFunction(130, 160)));
        Term sharpLeft = new Term(rudder, "sharpLeft",
                new CalculatedFuzzySet(angleDomain, StandardFuzzySets.gammaFunction(150, 180)));
        rudder.addTerm(turnLeft);
        rudder.addTerm(turnRight);
        rudder.addTerm(sharpLeft);
        rudder.addTerm(sharpRight);
        rudder.addTerm(r);
        rudder.addTerm(l);
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
