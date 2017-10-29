package zadaca1.zadatak3;

import zadaca1.zadatak1.Debug;
import zadaca1.zadatak1.Domain;
import zadaca1.zadatak1.DomainElement;
import zadaca1.zadatak1.IDomain;
import zadaca1.zadatak2.IFuzzySet;
import zadaca1.zadatak2.MutableFuzzySet;

/**
 * @author goran
 * @version 1.0
 *
 * Class used to demo the third assignment.
 *
 */
public class Demo {
	public static void main(String[] args) throws Exception {
		IDomain d = Domain.intRange(0, 11);
		IFuzzySet set1 = new MutableFuzzySet(d)
				.set(DomainElement.of(0), 1.0)
				.set(DomainElement.of(1), 0.8)
				.set(DomainElement.of(2), 0.6)
				.set(DomainElement.of(3), 0.4)
				.set(DomainElement.of(4), 0.2);
		Debug.print(set1, "Set1:");

		IFuzzySet notSet1 = Operations.unaryOperation( set1, Operations.zadehNot());
		Debug.print(notSet1, "notSet1:");

		IFuzzySet union = Operations.binaryOperation(set1, notSet1, Operations.zadehOr());
		Debug.print(union, "Set1 union notSet1:");

		IFuzzySet hinters = Operations.binaryOperation(set1, notSet1, Operations.hamacherTNorm(1.0));
		Debug.print(hinters, "Set1 intersection with notSet1 using parameterised Hamacher T norm with parameter 1.0:");
	}
}
