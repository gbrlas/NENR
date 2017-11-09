package zadaca3;

import zadaca1.zadatak2.IFuzzySet;

/**
 * Class which represents a fuzzy conclusion.
 *
 * @author goran
 * @version 1.0
 */
public class FuzzyConclusion {
    /**
     * Fuzzy set for this conclusion.
     */
    private IFuzzySet set;

    /**
     * Constructor which sets the conclusion parameters to the provided ones.
     *
     * @param set Fuzzy set.
     */
    public FuzzyConclusion(IFuzzySet set) {
        this.set = set;
    }

    /**
     * Returns the conclusion fuzzy set.
     *
     * @return Conclusion set.
     */
    public IFuzzySet getSet() {
        return set;
    }

    /**
     * Sets the conlcusion fuzzy set.
     *
     * @param set Conclusion set.
     */
    public void setSet(IFuzzySet set) {
        this.set = set;
    }
}
