package zadaca1.zadatak1;

import java.util.Arrays;

/**
 * @author goran
 * @version 1.0
 *
 * Represents one element of the domain. Instances of this class represent concrete ordered n-tuples of integer.
 */
public class DomainElement {
	/**
	 * Values for this domain element.
	 */
	private int[] values;

	/**
	 * Constructor which initializes <code>DomainElement</code> tuple to the provided values.
	 *
	 * @param values <code>DomainElement</code> values.
	 */
	public DomainElement(int... values) {
		if (values.length > 0) {
			this.values = Arrays.copyOf(values, values.length);
		}
	}

	/**
	 * Returns the number of <code>DomainElement</code> components.
	 *
	 * @return the number of <code>DomainElement</code> components.
	 */
	public int getNumberOfComponents() {
		return this.values.length;
	}

	/**
	 * Returns the <code>DomainElement</code> value with the given index.
	 *
	 * @param index the index of needed <code>DomainElement</code> value.
	 * @return the <code>DomainElement</code> value with the given index.
	 */
	public int getComponentValue(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= this.values.length) {
			throw new IndexOutOfBoundsException("Invalid index " + index + " please try again");
		}

		return values[index];
	}

	/**
	 * Returns the values of this <code>DomainElement</code>.
	 * @return Values of this <code>DomainElement</code>.
	 */
	public int[] getValues() {
		return values;
	}

	public static DomainElement of(int... values) {
		return new DomainElement(values);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(values);

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (getClass() != obj.getClass()) {
			return false;
		}

		DomainElement other = (DomainElement) obj;
		if (!Arrays.equals(values, other.getValues())) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		if (this.getNumberOfComponents() == 1) {
			return Integer.valueOf(this.getComponentValue(0)).toString();
		}

		StringBuilder builder = new StringBuilder();
		builder.append('(');

		for (int i = 0; i < values.length; ++i) {
			builder.append(values[i]);
			builder.append(", ");
		}

		builder.setLength(builder.length() - 2);
		builder.append(')');

		return builder.toString();
	}

}
