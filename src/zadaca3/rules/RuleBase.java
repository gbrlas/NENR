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
        IDomain domain1 = new SimpleDomain(0, 1301);
        IDomain domain2 = new SimpleDomain(0, 500);
        IDomain domain3 = new SimpleDomain(0, 180);

        distance = new Variable("distance");
        speed = new Variable("speed");
        direction = new Variable("direction");
        acceleration = new Variable("acceleration");
        rudder = new Variable("rudder");

        Term distanceClose = new Term(distance, "close",
                new CalculatedFuzzySet(domain1, StandardFuzzySets.lFunction(60, 180)));
        Term distanceMiddle = new Term(distance, "middle",
                new CalculatedFuzzySet(domain1, StandardFuzzySets.gammaFunction(60, 220)));
        distance.addTerm(distanceClose);
        distance.addTerm(distanceMiddle);

        Term slow = new Term(speed, "slow",
                new CalculatedFuzzySet(domain2, StandardFuzzySets.lFunction(10, 40)));
        Term fast = new Term(speed, "fast",
                new CalculatedFuzzySet(domain2, StandardFuzzySets.gammaFunction(30, 50)));
        speed.addTerm(slow);
        speed.addTerm(fast);

        Term negative = new Term(acceleration, "negative",
                new CalculatedFuzzySet(domain2, StandardFuzzySets.lFunction(0, 85)));
        Term positive = new Term(acceleration, "positive",
                new CalculatedFuzzySet(domain2, StandardFuzzySets.gammaFunction(75, 160)));
        acceleration.addTerm(negative);
        acceleration.addTerm(positive);

        Term nb = new Term(rudder, "nb",
                new CalculatedFuzzySet(domain3, StandardFuzzySets.lFunction(0, 40)));
        Term ns = new Term(rudder, "ns",
                new CalculatedFuzzySet(domain3, StandardFuzzySets.lambdaFunction(30, 60, 95)));
        Term ps = new Term(rudder, "ps",
                new CalculatedFuzzySet(domain3, StandardFuzzySets.lambdaFunction(85, 120, 150)));
        Term pb = new Term(rudder, "pb",
                new CalculatedFuzzySet(domain3, StandardFuzzySets.gammaFunction(140, 180)));
        rudder.addTerm(nb);
        rudder.addTerm(ns);
        rudder.addTerm(ps);
        rudder.addTerm(pb);
    }

    public static Variable getDistance() {
        return distance;
    }

    public static Variable getSpeed() {
        return speed;
    }

    public static Variable getDirection() {
        return direction;
    }

    public static Variable getAcceleration() {
        return acceleration;
    }

    public static Variable getRudder() {
        return rudder;
    }
}
