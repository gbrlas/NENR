package zadaca1.zadatak1;


/**
 * @author goran
 * @version 1.0
 *
 * Represents a domain model and defines domain functionality.
 */
public interface IDomain extends Iterable<DomainElement> {

	/**
	 * Returns the cardinality of a domain.
	 *
	 * @return the cardinality of a domain.
	 */
	public int getCardinality();

	/**
	 * Returns the component of the Cartesian product with index <code>index</code>.
	 * <code>SimpleDomain</code> returns itself.
	 *
	 * @param index the index of component of the Cartesian product.
	 * @return the component of the Cartesian product with index <code>index</code>.
	 */
	public IDomain getComponent(int index);

	/**
	 * Returns a number of <code>SimpleDomain</code>s for Cartesian product operation.
	 * <code>SimpleDomain</code> returns 1.
	 *
	 * @return the number of <code>SimpleDomain</code>s for Cartesian product operation.
	 */
	public int getNumberOfComponents();

	/**
	 * Returns the index of given domain element.
	 *
	 * @param element the domain element for which the index is needed.
	 * @return the index of given domain element.
	 */
	public int indexOfElement(DomainElement element);

	/**
	 * Returns the domain element for given index.
	 *
	 * @param index the index for needed domain element.
	 * @return the domain element for given index.
	 */
	public DomainElement elementForIndex(int index);
}
