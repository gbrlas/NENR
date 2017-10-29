package zadaca1.zadatak1;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author goran
 * @version 1.0
 *
 * Represents a domain that consists of a subset of integers.
 */
public class SimpleDomain extends Domain {
	/**
	 * The beginning of the domain interval (included).
	 */
	private int first;

	/**
	 * The end of the domain interval (excluded).
	 */
	private int last;

	/**
	 * Constructor which initializes the domain interval edges on given values.
	 *
	 * @param first the beginning of the domain interval (included)
	 * @param last  the end of the domain interval (excluded)
	 */
	public SimpleDomain(int first, int last) {
		if (first < last) {
			this.first = first;
			this.last = last;
		} else if (last < first) {
			this.first = last;
			this.last = first;
		}

	}

	/**
	 * Returns the beginning of the domain interval.
	 *
	 * @return beginning of the domain interval
	 */
	public int getFirst() {
		return first;
	}

	/**
	 * Returns the end of the domain interval.
	 *
	 * @return end of the domain interval
	 */
	public int getLast() {
		return last;
	}

	@Override
	public int getCardinality() {
		return Math.abs(this.last - this.first);
	}

	@Override
	public IDomain getComponent(int index) {
		return this;
	}

	@Override
	public int getNumberOfComponents() {
		return 1;
	}

	@Override
	public Iterator<DomainElement> iterator() {
		return new SimpleDomainIterator();
	}

	private class SimpleDomainIterator implements Iterator<DomainElement> {

		private int cursor;

		public SimpleDomainIterator() {
			this.cursor = SimpleDomain.this.first;
		}

		@Override
		public boolean hasNext() {
			return this.cursor < SimpleDomain.this.last;
		}

		@Override
		public DomainElement next() throws NoSuchElementException {
			if (this.hasNext()) {
				cursor++;
				return DomainElement.of(cursor - 1);
			}
			throw new NoSuchElementException();
		}

		@Override
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

	}
}
