package zadaca1.zadatak2;

import zadaca1.zadatak2.funkcije.GammaFunction;
import zadaca1.zadatak2.funkcije.IIntUnaryFunction;
import zadaca1.zadatak2.funkcije.LFunction;
import zadaca1.zadatak2.funkcije.LambdaFunction;

/**
 * @author goran
 * @version 1.0
 *
 * A class that offers implementations of membership functions such as l-function, gamma-function, lambda-function etc.
 */
public class StandardFuzzySets {
	/**
	 * Returns a concrete l-function with given parameters.
	 *
	 * @param alpha l-function parameter.
	 * @param beta  l-function parameter.
	 * @return a concrete l-function with given parameters.
	 */
	public static IIntUnaryFunction lFunction(int alpha, int beta) {
		return new LFunction(alpha, beta);
	}

	/**
	 * Returns a concrete gamma-function with given parameters.
	 *
	 * @param alpha gamma-function parameter.
	 * @param beta  gamma-function parameter.
	 * @return a concrete gamma-function with given parameters.
	 */
	public static IIntUnaryFunction gammaFunction(int alpha, int beta) {
		return new GammaFunction(alpha, beta);
	}

	/**
	 * Returns a concrete lambda-function with given parameters.
	 *
	 * @param alpha lambda-function parameter.
	 * @param beta  lambda-function parameter.
	 * @param gamma lambda-function parameter.
	 * @return a concrete lambda-function with given parameters.
	 */
	public static IIntUnaryFunction lambdaFunction(int alpha, int beta, int gamma) {
		return new LambdaFunction(alpha, beta, gamma);
	}

}
