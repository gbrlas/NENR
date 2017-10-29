package zadaca1.zadatak2;

import zadaca1.zadatak1.DomainElement;
import zadaca1.zadatak1.IDomain;
import zadaca1.zadatak2.funkcije.IIntUnaryFunction;

/**
 *
 * @author goran
 * @version 1.0
 *
 * The implementation of the fuzzy set that is defined with its domain and membership function. Values of membership
 * functions are not stored internally but calculated on each call of <code>getValueAt(element)</code> method.
 */
public class CalculatedFuzzySet implements IFuzzySet {
	/**
	 * The domain of the fuzzy set.
	 */
	private IDomain domain;
	/**
	 * The membership function of this fuzzy set.
	 */
	private IIntUnaryFunction function;

	/**
	 * Constructor that initializes fuzzy sets domain and membership function.
	 *
	 * @param domain the domain of the fuzzy set.
	 * @param membershipFunction the membership function of the fuzzy set.
	 */
	public CalculatedFuzzySet(IDomain domain, IIntUnaryFunction membershipFunction) {
		this.domain = domain;
		this.function = membershipFunction;
	}

	@Override
	public IDomain getDomain() {
		return domain;
	}

	@Override
	public double getValueAt(DomainElement element) {
		return function.valueAt(domain.indexOfElement(element));
	}
}
