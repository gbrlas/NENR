package zadaca1.zadatak2.funkcije;

/**
 *
 * @author goran
 * @version 1.0
 *
 * Represents a gamma function used in fuzzy sets.
 *
 */
public class GammaFunction implements IIntUnaryFunction {

	/**
	 * Function alpha value.
	 */
	private int alpha;
	/**
	 * Function beta value.
	 */
	private int beta;

	/**
	 * Constructor used for creating the gamma function.
	 * @param alpha alpha value
	 * @param beta beta value
	 */
	public GammaFunction(int alpha, int beta) {
		this.alpha = alpha;
		this.beta = beta;
	}

	@Override
	public double valueAt(int x) {
		if (x < this.alpha) {
			return 0;
		}
		if (x >= this.beta) {
			return 1;
		}
		return (double) (x - this.alpha) / (this.beta - this.alpha);
	}

}
