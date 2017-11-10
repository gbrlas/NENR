package zadaca3.rules;

import zadaca1.zadatak3.IBinaryFunction;
import zadaca3.utilities.FuzzyConclusion;
import zadaca3.utilities.Term;

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

        rule1 = new Rule(new Term[]{getSpeed().getTerm("fast")},
                getAcceleration().getTerm("negative"));

        rule2 = new Rule(new Term[]{getSpeed().getTerm("slow")},
                getAcceleration().getTerm("positive"));
    }

    public List<FuzzyConclusion> getActivatedRules(int left, int right, int leftAngle, int rightAngle, int speed, int
            direction, IBinaryFunction function) {

        List<FuzzyConclusion> conclusions = new ArrayList<>();
        conclusions.add(rule1.getConclusion(function, speed));
        conclusions.add(rule2.getConclusion(function, speed));

        return conclusions;
    }
}
