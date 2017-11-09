package zadaca3.system;

import zadaca3.rules.AccelerationRules;
import zadaca3.utilities.FuzzyConclusion;

import javax.naming.InvalidNameException;
import java.util.List;

public class AccelerationFuzzySystemProduct implements FuzzySystem {
    private Defuzzifier def;
    private AccelerationRules base;

    public AccelerationFuzzySystemProduct(Defuzzifier def) throws InvalidNameException {
        this.def = def;
        this.base = new AccelerationRules();
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
