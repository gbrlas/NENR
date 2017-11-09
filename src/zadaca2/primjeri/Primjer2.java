/**
 *
 */
package zadaca2.primjeri;

import zadaca1.zadatak1.Domain;
import zadaca1.zadatak1.DomainElement;
import zadaca1.zadatak1.IDomain;
import zadaca1.zadatak2.IFuzzySet;
import zadaca1.zadatak2.MutableFuzzySet;
import zadaca2.RelationMatrix;
import zadaca2.Relations;

/**
 * Class used to demo the second assignment.
 *
 * @author goran
 * @version 1.0
 *
 */
public class Primjer2 {
	public static void main(String[] args) {

		IDomain u1 = Domain.intRange(1, 5); // {1,2,3,4}

		IDomain u2 = Domain.intRange(1, 4); // {1,2,3}

		IDomain u3 = Domain.intRange(1, 5); // {1,2,3,4}

		IFuzzySet r1 = new MutableFuzzySet(Domain.combine(u1, u2))
				.set(DomainElement.of(1, 1), 0.3)
				.set(DomainElement.of(1, 2), 1)
				.set(DomainElement.of(3, 3), 0.5)
				.set(DomainElement.of(4, 3), 0.5);

		RelationMatrix r1Matrix = new RelationMatrix(r1);
		System.out.println("R1:");
		System.out.println(r1Matrix);

		IFuzzySet r2 = new MutableFuzzySet(Domain.combine(u2, u3))
				.set(DomainElement.of(1, 1), 1)
				.set(DomainElement.of(2, 1), 0.5)
				.set(DomainElement.of(2, 2), 0.7)
				.set(DomainElement.of(3, 3), 1)
				.set(DomainElement.of(3, 4), 0.4);

		RelationMatrix r2Matrix = new RelationMatrix(r2);
		System.out.println("R2:");
		System.out.println(r2Matrix);

		IFuzzySet r1r2 = Relations.compositionOfBinaryRelations(r1, r2);

		for (DomainElement e : r1r2.getDomain()) {
			System.out.println("mu(" + e + ")=" + r1r2.getValueAt(e));
		}

		RelationMatrix matrix = new RelationMatrix(r1r2);
		System.out.println();
		System.out.println("R1R2:");
		System.out.println(matrix);

	}
}
