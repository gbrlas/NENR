package zadaca1.zadatak2;

import zadaca1.zadatak1.DomainElement;
import zadaca1.zadatak1.IDomain;

/**
 * @author goran
 * @version 1.0
 *
 * The implementation of fuzzy set that internally keeps the values of membership function. It is possible to set the
 * membership function value.
 */
public class MutableFuzzySet implements IFuzzySet {
	/**
	 * The domain of the fuzzy set.
	 */
	private IDomain domain;
	/**
	 * Values of membership function of the fuzzy set. Allowed values are between zero and one.
	 */
	private double[] memberships;

	/**
	 * Constructor that initialized fuzzy sets domain and membership array.
	 *
	 * @param domain the domain of the fuzzy set.
	 */
	public MutableFuzzySet(IDomain domain) {
		this.domain = domain;
		memberships = new double[domain.getCardinality()];
	}

	/**
	 * Sets the value of given elements membership function to given value.
	 *
	 * @param element the element of the fuzzy set.
	 * @param mu      the value of memebership function.
	 * @return this instance of <code>MutableFuzzySet</code> class.
	 */
	public MutableFuzzySet set(DomainElement element, double val) throws IllegalArgumentException {
		if (val < 0 || val > 1) {
			throw new IllegalArgumentException("The memebership function value must be between zero and one.");
		}

		this.memberships[domain.indexOfElement(element)] = val;
		return this;
	}

	@Override
	public double getValueAt(DomainElement element) {
		return memberships[this.domain.indexOfElement(element)];
	}

	@Override
	public IDomain getDomain() {
		return this.domain;
	}

}
