package zadaca1.zadatak1;

import zadaca1.zadatak2.IFuzzySet;

/**
 * @author goran
 *
 * Class used for debugging and testing if the domains and sets are behaving correctly.
 *
 */
public class Debug {
	public static void print(IDomain domain, String headingText) {
		if (headingText!=null) {
			System.out.println(headingText);
		}

		for (DomainElement e : domain) {
			System.out.println("Element domene: " + e);
		}

		System.out.println("Kardinalitet domene je: " + domain.getCardinality());
		System.out.println();
	}

	public static void print(IFuzzySet set, String headingText) {
		if (headingText != null) {
			System.out.println(headingText);
		}

		for (DomainElement e : set.getDomain()) {
			System.out.println("d(" + e.getComponentValue(0) + ")="+set.getValueAt(e));
		}

		System.out.println();
	}
}
