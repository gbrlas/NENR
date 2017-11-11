package zadaca3.logic;

import zadaca3.rules.RudderRuleBase;
import zadaca3.utilities.FuzzyConclusion;

import javax.naming.InvalidNameException;
import java.util.List;

/**
 * Class which is used for making the conclusion based on the input data using product operator.
 *
 * @author goran
 * @version 1.0
 */
public class RudderConclusionProduct implements Conclusion {
    /**
     * Defuzzifier used for defuzzification of the conclusion.
     */
    private Defuzzifier defuzzifier;
    /**
     * Rudder rule base used for the logic.
     */
    private RudderRuleBase base;

    /**
     * Constructor which sets the defuzzifier and instantiates the acceleration rule base.
     *
     * @param defuzzifier Defuzzifier used for defuzzification of the conclusion.
     * @throws InvalidNameException
     */
    public RudderConclusionProduct(Defuzzifier defuzzifier) throws InvalidNameException {
        this.defuzzifier = defuzzifier;
        this.base = new RudderRuleBase();
    }

    /**
     * Returns the defuzzifier.
     *
     * @return Defuzzifier used for defuzzification of the conclusion.
     */
    public Defuzzifier getDefuzzifier() {
        return defuzzifier;
    }

    /**
     * Returns the rudder rule base.
     *
     * @return Rudder rule base used for the logic.
     */
    public RudderRuleBase getBase() {
        return base;
    }

    public int conclude(int left, int right, int leftAngle, int rightAngle, int speed, int direction) throws Exception {
        List<FuzzyConclusion> conclusions = base.
                getActivatedRules(left, right, leftAngle, rightAngle, speed, direction, (a, b) -> a * b);

        return defuzzifier.defuzzify(conclusions) - 90;
    }
}
