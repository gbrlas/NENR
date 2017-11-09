package zadaca3.utilities;

import javax.naming.InvalidNameException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class used for representing a single variable.
 *
 * @author goran
 * @version 1.0
 */
public class Variable {
    /**
     * Variable name.
     */
    private String name;
    /**
     * List of terms that this variable can become.
     */
    private List<Term> terms;

    /**
     * Constructor which sets the variable parameters.
     *
     * @param name Variable name.
     */
    public Variable(String name) {
        this.name = name;
        this.terms = new ArrayList<>();
    }

    /**
     * Returns the variable name.
     *
     * @return Variable name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the variable term located at the provided list index.
     *
     * @param index Index of the variable term.
     * @return Variable term located at the provided index.
     * @throws IndexOutOfBoundsException If the index is lower than 0 or larger than the list size.
     */
    public Term getTerm(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= terms.size()) {
            throw new IndexOutOfBoundsException("Term index out of bounds!");
        }

        return terms.get(index);
    }

    /**
     * Returns the variable term with the provided name.
     *
     * @param name Term name.
     * @return Variable term with the provided name.
     * @throws InvalidNameException If the term with the provided name doesn't exist in the list.
     */
    public Term getTerm(String name) throws InvalidNameException {
        for (Term term : terms) {
            if (term.getName().equals(name)) {
                return term;
            }
        }

        throw new InvalidNameException("Invalid term name provided.");
    }

    /**
     * Adds the provided term to the variable term list.
     *
     * @param term Term to be added to the variable term list.
     */
    public void addTerm(Term term) {
        this.terms.add(term);
    }
}
