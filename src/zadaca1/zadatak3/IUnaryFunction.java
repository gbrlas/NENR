package zadaca1.zadatak3;

/**
 * @author goran
 * @version 1.0
 *
 * Represents a unart operation on fuzzy sets. E.g. fuzzy complement.
 */
public interface IUnaryFunction {
	/**
	 * Returns a value of membership function of a fuzzy set that is a result of this unary operation.
	 *
	 * @param a the value of membership function of starting set.
	 * @return a value of membership function of a fuzzy set that is a result of this unary operation.
	 */
	public double valueAt(double x);
}
