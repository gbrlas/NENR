package zadaca3.system;

import zadaca3.rules.RudderRules;
import zadaca3.utilities.FuzzyConclusion;

import javax.naming.InvalidNameException;
import java.util.List;

public class RudderFuzzySystemProduct implements FuzzySystem {
    private Defuzzifier def;
    private RudderRules base;

    public RudderFuzzySystemProduct(Defuzzifier def) throws InvalidNameException {
        this.def = def;
        this.base = new RudderRules();
    }

    public Defuzzifier getDef() {
        return def;
    }

    public int conclude(int left, int right, int leftAngle, int rightAngle, int speed, int direction) throws Exception {
        List<FuzzyConclusion> conclusions = base.
                getActivatedRules(left, right, leftAngle, rightAngle, speed, direction, (a, b) -> {
                    return a * b;
                });
        return def.defuzzify(conclusions);
    }
}
