package zadaca3.system;

import zadaca3.FuzzyConclusion;
import zadaca3.rules.AccelerationRules;

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

    public int conclude(int left, int right, int leftAngle, int rightAngle, int speed, int direction) {
        List<FuzzyConclusion> conclusions = base
                .getActivatedRules(left, right, leftAngle, rightAngle, speed, direction, (a, b) -> {
                    return Math.min(a, b);
                });
        return def.defuzzify(conclusions) - 80;
    }
}
