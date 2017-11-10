package zadaca3.rules;

import zadaca1.zadatak3.IBinaryFunction;
import zadaca3.utilities.FuzzyConclusion;
import zadaca3.utilities.Term;

import javax.naming.InvalidNameException;
import java.util.ArrayList;
import java.util.List;

public class RudderRules extends RuleBase {
    private static Rule rule1;
    private static Rule rule2;
    private static Rule rule3;
    private static Rule rule4;
    private static Rule rule5;
    private static Rule rule6;

    public RudderRules() throws InvalidNameException {
        setRuleBase();

        rule1 = new Rule(new Term[]{getDistance().getTerm("tooClose")}, getRudder().getTerm("sharpRight"));
        rule2 = new Rule(new Term[]{getDistance().getTerm("tooClose")}, getRudder().getTerm("sharpLeft"));
        rule3 = new Rule(new Term[]{getDistance().getTerm("close")}, getRudder().getTerm("sharpRight"));
        rule4 = new Rule(new Term[]{getDistance().getTerm("close")}, getRudder().getTerm("sharpLeft"));

    }

    public List<FuzzyConclusion> getActivatedRules(int left, int right, int leftAngle, int rightAngle, int speed, int
            direction, IBinaryFunction function) {
        List<FuzzyConclusion> conclusions = new ArrayList<>();

        conclusions.add(rule1.getConclusion(function, leftAngle));
        conclusions.add(rule2.getConclusion(function, rightAngle));
        conclusions.add(rule1.getConclusion(function, left));
        conclusions.add(rule2.getConclusion(function, right));

        //conclusions.add(rule3.getConclusion(function, leftAngle));
        conclusions.add(rule4.getConclusion(function, rightAngle));
        //conclusions.add(rule3.getConclusion(function, left));
        conclusions.add(rule4.getConclusion(function, right));

        return conclusions;
    }
}
