package zadaca1.zadatak2;

import zadaca1.zadatak1.DomainElement;
import zadaca1.zadatak1.IDomain;

/**
 * @author goran
 * @version 1.0
 *
 * This interface models a definition of fuzzy sets, which are sets whose elements have degrees
 * of membership.
 */
public interface IFuzzySet {
	/**
	 * Returns the domain on which the fuzzy set is defined.
	 *
	 * @return the domain on which the fuzzy set is defined.
	 */
	public IDomain getDomain();
	/**
	 * Returns the value of membership function for given element of fuzzy set.
	 *
	 * @param element the element of fuzzy set.
	 * @return the value of memebership function for given element of fuzzy set.
	 */
	public double getValueAt(DomainElement element);
}
