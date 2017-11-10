package zadaca3.logic;

import zadaca3.rules.AccelerationRules;
import zadaca3.utilities.FuzzyConclusion;

import javax.naming.InvalidNameException;
import java.util.List;

public class AccelerationFuzzySystemMin implements FuzzySystem {
    private Defuzzifier def;
    private AccelerationRules base;

    public AccelerationFuzzySystemMin(Defuzzifier def) throws InvalidNameException {
        this.def = def;
        this.base = new AccelerationRules();
    }

    public Defuzzifier getDef() {
        return def;
    }

    public AccelerationRules getBase() {
        return base;
    }

    public int conclude(int left, int right, int leftAngle, int rightAngle, int speed, int direction) throws Exception {
        List<FuzzyConclusion> conclusions = base
                .getActivatedRules(left, right, leftAngle, rightAngle, speed, direction, (a, b) -> Math.min(a, b));

        return def.defuzzify(conclusions) - 50;
    }
}
