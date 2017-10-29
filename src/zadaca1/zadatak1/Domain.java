package zadaca1.zadatak1;

import java.util.NoSuchElementException;

/**
 * @author goran
 * @version 1.0
 *
 * Represents a domain of elements. Elements are ordered n-tuples of integers.
 */
public abstract class Domain implements IDomain {
	/**
	 * Returns the domain that contains an array of integer values in range [start, end>.
	 *
	 * @param start first element of the domain.
	 * @param end last element that is not included in domain.
	 * @return the domain that contains an array of integers in range [start, end>.
	 */
	public static IDomain intRange(int start, int end) {
		return new SimpleDomain(start, end);
	}

	/**
	 * Combines and returns the domain that is Cartesian product of given domains. Given domains must be instances of
	 * <code>SimpleDomain</code> class.
	 *
	 * @param first First domain to be combined. Must be instance of <code>SimpleDomain</code> class.
	 * @param second Second domain to be combined. Must be instance of <code>SimpleDomain</code> class.
	 * @return the domain that is Cartesian product of given domains.
	 */
	public static IDomain combine(IDomain first, IDomain second) {
		return new CompositeDomain(first, second);
	}

	@Override
	public int indexOfElement(DomainElement element) {
		int index = 0;
		try {
			for (DomainElement current : this) {
				if (current.equals(element)) {
					return index;
				}
				index++;
			}
		} catch (Exception e) {
			System.err.println("Element outside of domain!");
		}
		return 0;
	}

	@Override
	public DomainElement elementForIndex(int index) {
		int test = 0;
		for (DomainElement element : this) {
			if (index == test) {
				return element;
			}
			test++;
		}
		throw new NoSuchElementException();
	}
}
