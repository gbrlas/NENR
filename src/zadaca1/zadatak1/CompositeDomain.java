package zadaca1.zadatak1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * @author goran
 * @version 1.0
 *
 * Represents a Cartesian product of <code>SimpleDomain</code>s. Elements are ordered n-tuples (n > 1) of integers.
 */
public class CompositeDomain extends Domain {
	/**
	 * The components of <code>CompositeDomain</code>.
	 */
	private SimpleDomain[] components;

	/**
	 * Constructor which initializes the composite domain components to the provided ones.
	 *
	 * @param components the components of <code>CompositeDomain</code>.
	 */
	public CompositeDomain(IDomain ... components) {
		ArrayList<SimpleDomain> newComponents = new ArrayList<>();
		for (IDomain component : components) {
			newComponents.add((SimpleDomain) component);
		}
		this.components = newComponents.stream().toArray(SimpleDomain[]::new);
	}

	@Override
	public int getCardinality() {
		int num = 1;
		for (SimpleDomain component : components) {
			num *= component.getCardinality();
		}
		return num;
	}

	@Override
	public IDomain getComponent(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= this.components.length) {
			throw new IndexOutOfBoundsException("Invalid index " + index + " please try again");
		}

		return components[index];
	}

	@Override
	public int getNumberOfComponents() {
		return components.length;
	}

	@Override
	public Iterator<DomainElement> iterator() {
		return new CompositeDomainIterator();
	}

	private class CompositeDomainIterator implements Iterator<DomainElement> {

		private boolean done;

		private int numComponenets;
		private int[] cursorValue;

		public CompositeDomainIterator() {
			done = false;
			numComponenets = CompositeDomain.this.getNumberOfComponents();
			cursorValue = new int[numComponenets];
			for (int i = 0; i < numComponenets; ++i) {
				cursorValue[i] = ((SimpleDomain) CompositeDomain.this.getComponent(i)).getFirst();
			}
		}

		private boolean isFinished() {
			for (int i = 0; i < numComponenets; ++i) {
				if (cursorValue[i] < ((SimpleDomain) CompositeDomain.this.getComponent(i)).getLast() - 1) {
					return false;
				}
			}
			return true;
		}

		@Override
		public boolean hasNext() {
			return !this.done;
		}

		@Override
		public DomainElement next() throws NoSuchElementException {
			if (this.hasNext()) {
				int[] oldCursor = new int[numComponenets];
				oldCursor = Arrays.copyOf(cursorValue, numComponenets);
				DomainElement ret = DomainElement.of(oldCursor);

				this.done = isFinished();

				for (int i = numComponenets - 1; i >= 0; i--) {
					if (cursorValue[i] == ((SimpleDomain) CompositeDomain.this.getComponent(i)).getLast() - 1) {
						continue;
					}

					cursorValue[i]++;
					for (int j = i + 1; j < numComponenets; j++) {
						cursorValue[j] = ((SimpleDomain) CompositeDomain.this.getComponent(j)).getFirst();
					}
					break;
				}
				return ret;
			}
			throw new NoSuchElementException();
		}

		@Override
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

	}
}
