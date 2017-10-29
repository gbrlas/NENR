package zadaca2;

import zadaca1.zadatak1.DomainElement;
import zadaca1.zadatak1.IDomain;
import zadaca1.zadatak2.IFuzzySet;

/**
 * Class used for representing the fuzzy set in a matrix notation.
 *
 * @author goran
 * @version 1.0
 *
 */
public class RelationMatrix {
	/**
	 * Fuzzy set represented by this matrix.
	 */
	private IFuzzySet set;
	/**
	 * 2D array used for storing the matrix values.
	 */
	private double[][] values;
	/**
	 * Number of rows in the matrix.
	 */
	private int rowNum;
	/**
	 * Number of columns in the matrix.
	 */
	private int colNum;

	/**
	 * Constructor which initializes the matrix from the provided fuzzy set.
	 * @param set Fuzzy set used to create the matrix.
	 */
	public RelationMatrix(IFuzzySet set) {
		this.set = set;

		rowNum = this.set.getDomain().getComponent(0).getCardinality();
		colNum = this.set.getDomain().getComponent(1).getCardinality();

		values = new double[rowNum][colNum];
		IDomain domain = set.getDomain();

		int i = 0;
		int j = 0;
		for (DomainElement element : domain) {
			values[i][j++] = this.set.getValueAt(element);

			if (j >= colNum) {
				j = 0;
				i++;
			}
		}
	}

	/**
	 * Returns the number of matrix rows.
	 * @return Number of matrix rows.
	 */
	public int getRowNum() {
		return rowNum;
	}

	/**
	 * Returns the number of matrix columns.
	 * @return Number of matrix columns.
	 */
	public int getColNum() {
		return colNum;
	}

	/**
	 * Returns the matrix value at [row][column].
	 * @param row Row index.
	 * @param col Column index.
	 * @return matrix value at [row][column].
	 */
	public double get(int row, int col) {
		return values[row][col];
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		for(int i = 0; i < rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				builder.append(values[i][j] + " ");
			}

			builder.append("\n");
		}

		return builder.toString();
	}

}
