package zadaca3.rules;

import zadaca1.zadatak3.IBinaryFunction;
import zadaca3.FuzzyConclusion;
import zadaca3.Term;

import javax.naming.InvalidNameException;
import java.util.ArrayList;
import java.util.List;

public class AccelerationRules extends RuleBase {
    private static Rule rule1;
    private static Rule rule2;
    private static Rule rule3;
    private static Rule rule4;
    private static Rule rule5;

    public AccelerationRules() throws InvalidNameException {
        setRuleBase();

        rule1 = new Rule(new Term[]{getDistance().getTerm("close"), getSpeed().getTerm("fast")},
                getAcceleration().getTerm("negative"));

        rule2 = new Rule(new Term[]{getDistance().getTerm("middle"), getSpeed().getTerm("slow")},
                getAcceleration().getTerm("positive"));

        rule3 = new Rule(new Term[]{getDistance().getTerm("middle"), getDistance().getTerm("middle")},
                getAcceleration().getTerm("positive"));

        rule4 = new Rule(new Term[]{getDistance().getTerm("middle")}, getAcceleration().getTerm("positive"));

        rule5 = new Rule(new Term[]{getSpeed().getTerm("fast")}, getAcceleration().getTerm("negative"));
    }

    public List<FuzzyConclusion> getActivatedRules(int left, int right, int leftAngle, int rightAngle, int speed, int
            direction, IBinaryFunction function) {

        List<FuzzyConclusion> conclusions = new ArrayList<>();
        conclusions.add(rule1.getConclusion(function, left, speed));
        conclusions.add(rule1.getConclusion(function, right, speed));
        conclusions.add(rule1.getConclusion(function, leftAngle, speed));
        conclusions.add(rule1.getConclusion(function, rightAngle, speed));
        conclusions.add(rule2.getConclusion(function, left, speed));
        conclusions.add(rule2.getConclusion(function, right, speed));
        conclusions.add(rule3.getConclusion(function, left, right));
        conclusions.add(rule3.getConclusion(function, leftAngle, rightAngle));
        conclusions.add(rule4.getConclusion(function, left));
        conclusions.add(rule4.getConclusion(function, right));
        conclusions.add(rule5.getConclusion(function, speed));

        return conclusions;
    }
}
