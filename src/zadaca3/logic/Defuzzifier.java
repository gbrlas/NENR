package zadaca3.logic;

import zadaca3.utilities.FuzzyConclusion;

import java.util.List;

/**
 * Interface for creating defuzzifiers.
 *
 * @author goran
 * @version 1.0
 */
public interface Defuzzifier {
    /**
     * Returns the integer value representing the defuzzification of the conclusion.
     *
     * @param conclusions List of all the conclusions.
     * @return Integer value representing the defuzzification fo the conlcusion.
     * @throws Exception
     */
    int defuzzify(List<FuzzyConclusion> conclusions) throws Exception;
}
