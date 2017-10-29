package zadaca1.zadatak2.funkcije;

/**
 *
 * @author goran
 * @version 1.0
 *
 * Represents a lambda function used in fuzzy sets.
 *
 */
public class LambdaFunction implements IIntUnaryFunction {

	/**
	 * Function alpha value.
	 */
	private int alpha;
	/**
	 * Function beta value.
	 */
	private int beta;
	/**
	 * Function gamma value.
	 */
	private int gamma;

	/**
	 * Constructor used for creating the lambda function.
	 * @param alpha alpha value
	 * @param beta beta value
	 * @param gamma gamma value
	 */
	public LambdaFunction(int alpha, int beta, int gamma) {
		this.alpha = alpha;
		this.beta = beta;
		this.gamma = gamma;
	}

	@Override
	public double valueAt(int x) {
		if (x < this.alpha) {
			return 0;
		}
		if (this.alpha <= x && x < this.beta) {
			return (double) (x - this.alpha) / (this.beta - this.alpha);
		}
		if (this.beta <= x && x < this.gamma) {
			return (double) (this.gamma - x) / (this.gamma - this.beta);
		}
		return 0;
	}

}