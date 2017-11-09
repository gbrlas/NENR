package zadaca1.zadatak3;

import zadaca1.zadatak1.DomainElement;
import zadaca1.zadatak2.IFuzzySet;
import zadaca1.zadatak2.MutableFuzzySet;

/**
 * @author goran
 * @version 1.0
 *
 * Represents a concrete fuzzy set operations.
 */
public class Operations {

	/**
	 * Returns a resulting fuzzy set after given unary function operates on given set.
	 *
	 * @param set the set to operate on.
	 * @param operation the function to operate with.
	 * @return a resulting fuzzy set after given unary function operates on given set.
	 */
	public static IFuzzySet unaryOperation(IFuzzySet set, IUnaryFunction operation) throws Exception {
		MutableFuzzySet mutableSet = new MutableFuzzySet(set.getDomain());
		for (DomainElement element : set.getDomain()) {
			mutableSet.set(element, operation.valueAt(set.getValueAt(element)));
		}

		return mutableSet;
	}

	/**
	 * Returns a resulting fuzzy set after given binary operation operates between two given sets.
	 *
	 * @param set1 one of two sets to operate on.
	 * @param set2 one of two sets to operate on.
	 * @param operation the function to operate with.
	 * @return a resulting fuzzy set after given binary operation operates between two given sets.
	 */
	public static IFuzzySet binaryOperation(IFuzzySet set1, IFuzzySet set2, IBinaryFunction operation) throws Exception {
		MutableFuzzySet mutableSet = new MutableFuzzySet(set1.getDomain());
		for (DomainElement element : set1.getDomain()) {
			mutableSet.set(element, operation.valueAt(set1.getValueAt(element), set2.getValueAt(element)));
		}

		return mutableSet;
	}

	/**
	 * Returns the function that represents a Zadeh complement.
	 *
	 * @return the function that represents a Zadeh complement.
	 */
	public static IUnaryFunction zadehNot() {
		return a -> {
			return 1 - a;
		};
	}

	/**
	 * Returns the function that represents a Zadeh minimum.
	 *
	 * @return the function that represents a Zadeh minimum.
	 */
	public static IBinaryFunction zadehAnd() {
		return (a, b) -> {
			return Math.min(a, b);
		};
	}

	/**
	 * Returns the function that represents a Zadeh maximum.
	 *
	 * @return the function that represents a Zadeh maximum.
	 */
	public static IBinaryFunction zadehOr() {
		return (a, b) -> {
			return Math.max(a, b);
		};
	}

	/**
	 * Returns the function that represents a Hamacher t-norm.
	 *
	 * @param param the parameter of Hamacher t-norm.
	 * @return the function that represents a Hamacher t-norm.
	 */
	public static IBinaryFunction hamacherTNorm(double param) throws IllegalArgumentException {
		if (param < 0) {
			throw new IllegalArgumentException("Parameter must be zero or positive.");
		}
		return (a, b) -> {
			return (a * b) / (param + (1 - param) * (a + b - a * b));
		};
	}

	/**
	 * Returns the function that represents a Hamacher s-norm.
	 *
	 * @param param the parameter of Hamacher s-norm.
	 * @return the function that represents a Hamacher s-norm.
	 */
	public static IBinaryFunction hamacherSNorm(double param) throws IllegalArgumentException {
		if (param < 0) {
			throw new IllegalArgumentException("Parameter must be zero or positive.");
		}
		return (a, b) -> {
			return (a + b - (2 - param) * a * b) / (1 - (1 - param) * a * b);
		};
	}

}
