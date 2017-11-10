package zadaca3.rules;

import zadaca1.zadatak1.DomainElement;
import zadaca1.zadatak2.MutableFuzzySet;
import zadaca1.zadatak3.IBinaryFunction;
import zadaca3.utilities.FuzzyConclusion;
import zadaca3.utilities.Term;

/**
 * Class which represents a single if-then rule with one or more antecedents and a single consequent.
 *
 * @author goran
 * @version 1.0
 */
public class Rule {
    /**
     * Rule antecedents.
     */
    private Term[] antecedents;
    /**
     * Rule consequent.
     */
    private Term consequent;

    /**
     * Constructor which sets the rule parameters to the provided ones.
     *
     * @param antecedents Rule antecedents.
     * @param consequent  Rule consequent.
     */
    public Rule(Term[] antecedents, Term consequent) {
        this.antecedents = antecedents;
        this.consequent = consequent;
    }

    /**
     * Returns the rule antecedents.
     *
     * @return Rule antecedents.
     */
    public Term[] getAntecedents() {
        return antecedents;
    }

    /**
     * Returns the rule consequent.
     *
     * @return Rule consequent.
     */
    public Term getConsequent() {
        return consequent;
    }

    /**
     * Calculates the fuzzy conclusion based on the antecedents and the provided function and values.
     *
     * @param function Fuzzy function.
     * @param x        Values of the antecedents.
     * @return New fuzzy conclusion.
     */
    public FuzzyConclusion getConclusion(IBinaryFunction function, int... x) {
        double mi = 1;
        int n = antecedents.length;

        for (int i = 0; i < n; i++) {
            mi = function.valueAt(mi, antecedents[i].getFuzzySet().getValueAt(DomainElement.of(x[i])));
        }

        MutableFuzzySet conclusion = new MutableFuzzySet(consequent.getFuzzySet().getDomain());
        for (DomainElement element : consequent.getFuzzySet().getDomain()) {
            conclusion.set(element, function.valueAt(mi, consequent.getFuzzySet().getValueAt(element)));
        }

        return new FuzzyConclusion(conclusion);
    }
}
