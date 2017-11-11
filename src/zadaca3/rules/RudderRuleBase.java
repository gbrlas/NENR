package zadaca3.rules;

import zadaca1.zadatak3.IBinaryFunction;
import zadaca3.utilities.FuzzyConclusion;
import zadaca3.utilities.Term;

import javax.naming.InvalidNameException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing all the rules needed for the ship's rudder steering logic.
 *
 * @author goran
 * @version 1.0
 */
public class RudderRuleBase extends RuleBase {
    private static Rule rule1;
    private static Rule rule2;
    private static Rule rule3;
    private static Rule rule4;

    /**
     * Constructor which creates all the rules.
     *
     * @throws InvalidNameException If the term used for the rule doesn't exist in the rule base.
     */
    public RudderRuleBase() throws InvalidNameException {
        setRuleBase();

        rule1 = new Rule(new Term[]{getDistance().getTerm("tooClose")}, getRudder().getTerm("sharpRight"));
        rule2 = new Rule(new Term[]{getDistance().getTerm("tooClose")}, getRudder().getTerm("sharpLeft"));
        rule3 = new Rule(new Term[]{getDistance().getTerm("close")}, getRudder().getTerm("sharpRight"));
        rule4 = new Rule(new Term[]{getDistance().getTerm("close")}, getRudder().getTerm("sharpLeft"));

    }

    /**
     * Returns the list with all the rules needed for system logic.
     *
     * @param left       Distance from the left shore.
     * @param right      Distance from the right shore.
     * @param leftAngle  45 degree angle distance from the left shore.
     * @param rightAngle 45 degree angle distance from the right shore.
     * @param speed      Boat speed.
     * @param direction  Boat direction.
     * @param function   Function used for calculating the fuzzy set value.
     * @return List of all the rules needed for system logic.
     */
    public List<FuzzyConclusion> getActivatedRules(int left, int right, int leftAngle, int rightAngle, int speed, int
            direction, IBinaryFunction function) {
        List<FuzzyConclusion> conclusions = new ArrayList<>();

        conclusions.add(rule1.getConclusion(function, leftAngle));
        conclusions.add(rule1.getConclusion(function, left));

        conclusions.add(rule2.getConclusion(function, rightAngle));
        conclusions.add(rule2.getConclusion(function, right));

        conclusions.add(rule3.getConclusion(function, leftAngle));
        conclusions.add(rule3.getConclusion(function, left));

        conclusions.add(rule4.getConclusion(function, rightAngle));
        conclusions.add(rule4.getConclusion(function, right));

        return conclusions;
    }
}
