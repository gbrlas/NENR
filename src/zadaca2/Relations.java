package zadaca2;

import zadaca1.zadatak1.Domain;
import zadaca1.zadatak1.IDomain;
import zadaca1.zadatak2.IFuzzySet;
import zadaca1.zadatak2.MutableFuzzySet;

/**
 * Class which represents a definition of fuzzy relation. Provides common relation operations such as checking
 * whether the relation is symmetric, reflexive, UxU, min-max transitive and fuzzy equivalence.
 * It also also provided implementation for creating a composition of two fuzzy relations.
 *
 * @author goran
 * @version 1.0
 *
 */
public class Relations {
	/**
	 * Returns true if the domain on which given fuzzy set is defined is equal to Cartesian product U x U,
	 * false otherwise.
	 *
	 * @param relation the fuzzy relation.
	 * @return true if the domain on which given fuzzy set is defined is equal to Cartesian product U x U,
	 * false otherwise.
	 */
	public static boolean isUtimesURelation(IFuzzySet relation) {
		IDomain domain = relation.getDomain();

		if (domain.getNumberOfComponents() != 2) {
			return false;
		}

		return domain.getComponent(0).equals(domain.getComponent(1));
	}

	/**
	 * Returns true if the given relation defined on UxU domain is symmetric, false otherwise.
	 *
	 * @param relation the fuzzy relation.
	 * @return true if the given relation defined on UxU domain  is symmetric, false
	 * otherwise.
	 */
	public static boolean isSymmetric(IFuzzySet relation) {
		if (!isUtimesURelation(relation)) {
			return false;
		}

		RelationMatrix matrix = new RelationMatrix(relation);

		int rows = matrix.getRowNum();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < rows; j++) {
				if (matrix.get(i, j) != matrix.get(j, i)) {
					return false;
				}
			}
		}

		return true;
	}


	/**
	 * Returns true if the given relation defined on UxU domain is reflexive, false otherwise.
	 *
	 * @param relation the fuzzy relation.
	 * @return true if the given relation defined on UxU domain is reflexive, false otherwise.
	 */
	public static boolean isReflexive(IFuzzySet relation) {
		if (!isUtimesURelation(relation)) {
			return false;
		}

		RelationMatrix matrix = new RelationMatrix(relation);

		int rows = matrix.getRowNum();
		for (int i = 0; i < rows; i++) {
			if (matrix.get(i, i) != 1) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Returns true if the given relation defined on UxU domain is max-min transitive,
	 * false otherwise.
	 *
	 * @param relation the fuzzy relation.
	 * @return true if the given relation defined on UxU domain is max-min transitive,
	 * false otherwise.
	 */
	public static boolean isMaxMinTransitive(IFuzzySet relation) {
		if (!isUtimesURelation(relation)) {
			return false;
		}

		RelationMatrix matrix = new RelationMatrix(relation);

		int rows = matrix.getRowNum();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < rows; j++) {
				double max = Double.NEGATIVE_INFINITY;

				for (int k = 0; k < rows; k++) {
					double min = Math.min(matrix.get(i, k), matrix.get(k, j));
					if (min > max) {
						max = min;
					}
				}

				if (matrix.get(i, j) < max) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Returns the composite relation of given relations defined on UxW domain.
	 *
	 * @param relation1 relation defined on UxV domain.
	 * @param relation2 relation defined on VxW domain.
	 * @return the composite relation of given relations defined on UxW domain.
	 */
	public static IFuzzySet compositionOfBinaryRelations(IFuzzySet relation1, IFuzzySet relation2) throws IllegalArgumentException {

		IDomain U = relation1.getDomain().getComponent(0);
		IDomain V1 = relation1.getDomain().getComponent(1);
		IDomain V2 = relation2.getDomain().getComponent(0);
		IDomain W = relation2.getDomain().getComponent(1);

		if (!V1.equals(V2)) {
			throw new IllegalArgumentException("Incompatible domains, cannot create the compositions of binary relations.");
		}

		MutableFuzzySet composition = new MutableFuzzySet(Domain.combine(U, W));

		RelationMatrix m1 = new RelationMatrix(relation1);
		RelationMatrix m2 = new RelationMatrix(relation2);

		int cardinalityU = U.getCardinality();
		int cardinalityV = V1.getCardinality();
		int cardinalityW = W.getCardinality();
		double[][] compositionMatrix = new double[cardinalityU][cardinalityW];

		for (int i = 0; i < cardinalityU; i++) {
			for (int j = 0; j < cardinalityW; j++) {
				compositionMatrix[i][j] = 0;
				for (int k = 0; k < cardinalityV; k++) {
					double min = Math.min(m1.get(i, k), m2.get(k, j));
					if (k == 0) {
						compositionMatrix[i][j] = min;
					}
					else {
						compositionMatrix[i][j] = Math.max(compositionMatrix[i][j], min);
					}
				}

				composition.set(composition.getDomain().elementForIndex(i * cardinalityU + j), compositionMatrix[i][j]);
			}
		}

		return composition;
	}

	/**
	 * Returns true< if given relation is fuzzy equivalence relation, false otherwise. Fuzzy
	 * equivalence relation is fuzzy relation that is reflexive, symmetric and max-min transitive.
	 *
	 * @param relation the fuzzy relation.
	 * @return true if given relation is fuzzy equivalence relation, false otherwise.
	 */
	public static boolean isFuzzyEquivalence(IFuzzySet relation) {
		return isReflexive(relation) && isSymmetric(relation) && isMaxMinTransitive(relation);
	}

}
