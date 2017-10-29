package zadaca1.zadatak3;

/**
 * @author goran
 * @version 1.0
 *
 * Represents a binary operation on fuzzy sets. E.g. fuzzy union and fuzzy intersection.
 */
public interface IBinaryFunction {
	/**
	 * Returns a value of membership function of a fuzzy set that is a result of this binary operation.
	 *
	 * @param y the value of membership function of first set.
	 * @param y the value of membership function of second set.
	 * @return a value of membership function of a fuzzy set that is a result of this binary operation.
	 */
	public double valueAt(double x, double y);
}
