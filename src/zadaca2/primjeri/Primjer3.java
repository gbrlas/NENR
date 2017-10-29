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
 * Class used to demo the third assignment.
 * @author goran
 * @version 1.0
 *
 */
public class Primjer3 {
	public static void main(String[] args) {
		IDomain u = Domain.intRange(1, 5); // {1,2,3,4}
		IFuzzySet r = new MutableFuzzySet(Domain.combine(u, u))
				.set(DomainElement.of(1, 1), 1)
				.set(DomainElement.of(2, 2), 1)
				.set(DomainElement.of(3, 3), 1)
				.set(DomainElement.of(4, 4), 1)
				.set(DomainElement.of(1, 2), 0.3)
				.set(DomainElement.of(2, 1), 0.3)
				.set(DomainElement.of(2, 3), 0.5)
				.set(DomainElement.of(3, 2), 0.5)
				.set(DomainElement.of(3, 4), 0.2)
				.set(DomainElement.of(4, 3), 0.2);

		IFuzzySet r2 = r;

		System.out.println("Početna relacija je neizrazita relacija ekvivalencije? "
				+ Relations.isFuzzyEquivalence(r2));
		System.out.println();

		RelationMatrix matrix;
		for (int i = 1; i <= 3; i++) {
			r2 = Relations.compositionOfBinaryRelations(r2, r);

			System.out.println("Broj odrađenih kompozicija: " + i + ". Relacija je:");

			for (DomainElement e : r2.getDomain()) {
				System.out.println("mu(" + e + ")=" + r2.getValueAt(e));
			}

			matrix = new RelationMatrix(r2);

			System.out.println();
			System.out.println(matrix);

			System.out.println("Ova relacija je neizrazita relacija ekvivalencije? "
					+ Relations.isFuzzyEquivalence(r2));

			System.out.println();
		}
	}
}
