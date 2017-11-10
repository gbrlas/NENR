package zadaca3.utilities;

import zadaca1.zadatak2.IFuzzySet;

/**
 * Class which represents a single term used in fuzzy logic.
 *
 * @author goran
 * @version 1.0
 */
public class Term {
    /**
     * Term variable.
     */
    private Variable variable;
    /**
     * Term name.
     */
    private String name;
    /**
     * Term fuzzy set.
     */
    private IFuzzySet fuzzySet;

    /**
     * Constructor which sets the term parameters to the provided ones.
     *
     * @param variable Term variable.
     * @param name     Term name.
     * @param fuzzySet Term fuzzy set.
     */
    public Term(Variable variable, String name, IFuzzySet fuzzySet) {
        this.variable = variable;
        this.name = name;
        this.fuzzySet = fuzzySet;
    }

    /**
     * Returns the term variable.
     *
     * @return Term variable.
     */
    public Variable getVariable() {
        return variable;
    }

    /**
     * Returns the term name.
     *
     * @return Term name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the term fuzzy set.
     *
     * @return Term fuzzy set.
     */
    public IFuzzySet getFuzzySet() {
        return fuzzySet;
    }
}
