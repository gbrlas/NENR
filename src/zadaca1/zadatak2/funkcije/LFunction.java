package zadaca1.zadatak2.funkcije;

/**
 *
 * @author goran
 * @version 1.0
 *
 * Represents a L function used in fuzzy sets.
 *
 */
public class LFunction implements IIntUnaryFunction {

	/**
	 * Function alpha value.
	 */
	private int alpha;
	/**
	 * Function beta value.
	 */
	private int beta;

	/**
	 * Constructor used for creating the L function.
	 * @param alpha alpha value
	 * @param beta beta value
	 */
	public LFunction(int alpha, int beta) {
		this.alpha = alpha;
		this.beta = beta;
	}

	@Override
	public double valueAt(int x) {
		if (x < this.alpha) {
			return 1;
		}
		if (x >= this.beta) {
			return 0;
		}
		return (double) (this.beta - x) / (this.beta - this.alpha);
	}

}