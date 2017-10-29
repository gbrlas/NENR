package zadaca1.zadatak2.funkcije;

/**
 * @author goran
 * @version 1.0
 *
 * Represent a general membership function defined on integers. The values of membership function are distributed
 * over indexes of domain of fuzzy set.
 */
public interface IIntUnaryFunction {
	/**
	 * Returns a value of membership function for the element on given index in fuzzy sets domain.
	 *
	 * @param index the index of fuzzy set element for which the value of membership function is returned.
	 * @return a value of membership function for the element on given index in fuzzy sets domain.
	 */
	public double valueAt(int index);
}
