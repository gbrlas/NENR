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
    private static Rule rule7;
    private static Rule rule8;
    private static Rule rule9;
    private static Rule rule10;

    public RudderRules() throws InvalidNameException {
        setRuleBase();

        rule1 = new Rule(new Term[]{getDistance().getTerm("close")}, getRudder().getTerm("nb"));

        rule2 = new Rule(new Term[]{getDistance().getTerm("close")}, getRudder().getTerm("pb"));

        rule3 = new Rule(new Term[]{getDistance().getTerm("close"),}, getRudder().getTerm("nb"));

        rule4 = new Rule(new Term[]{getDistance().getTerm("close"),}, getRudder().getTerm("pb"));

        rule5 = new Rule(new Term[]{getDistance().getTerm("close"), getDistance().getTerm("middle")},
                getRudder().getTerm("ns"));

        rule6 = new Rule(new Term[]{getDistance().getTerm("close"), getDistance().getTerm("middle")},
                getRudder().getTerm("ps"));

        rule7 = new Rule(new Term[]{getDistance().getTerm("middle"),}, getRudder().getTerm("ns"));

        rule8 = new Rule(new Term[]{getDistance().getTerm("middle"),}, getRudder().getTerm("ps"));

        rule9 = new Rule(new Term[]{getDistance().getTerm("close"), getDistance().getTerm("middle")}, getRudder().getTerm("nb"));

        rule10 = new Rule(new Term[]{getDistance().getTerm("close"), getDistance().getTerm("middle")}, getRudder().getTerm("pb"));


    }

    public List<FuzzyConclusion> getActivatedRules(int left, int right, int leftAngle, int rightAngle, int speed, int
            direction, IBinaryFunction function) {
        List<FuzzyConclusion> conclusions = new ArrayList<>();
        conclusions.add(rule1.getConclusion(function, left));
        conclusions.add(rule2.getConclusion(function, right));
        conclusions.add(rule3.getConclusion(function, leftAngle));
        conclusions.add(rule4.getConclusion(function, rightAngle));
        conclusions.add(rule5.getConclusion(function, left, right));
        conclusions.add(rule6.getConclusion(function, right, left));
        conclusions.add(rule7.getConclusion(function, leftAngle));
        conclusions.add(rule8.getConclusion(function, rightAngle));
        conclusions.add(rule9.getConclusion(function, leftAngle, rightAngle));
        conclusions.add(rule10.getConclusion(function, rightAngle, leftAngle));

        return conclusions;
    }
}
