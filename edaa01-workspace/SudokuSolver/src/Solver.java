import java.util.Arrays;

public class Solver {
	private int[][] matrix;

	public Solver() {
		matrix = new int[9][9];
	}

	/**
	 * Method to set the value for a square
	 * 
	 * @parameter int row the row the square we want to set the value for is in.
	 * @parameter int column the column the square we want to set the value for
	 *            is in.
	 */
	public void setValue(int row, int column, int value) {
		if (row > -1 && 9 > row && column > -1 && 9 > column) {
			matrix[row][column] = value;
		}
	}

	/**
	 * Method to get the value assigned to a square
	 * 
	 * @parameter int row the row the square we want the value for is in.
	 * @parameter int column the column the square we want the value for is in.
	 * @return int value the value.
	 */
	public int getValue(int row, int column) {
		int value = 0;
		if (row > -1 && 9 > row && column > -1 && 9 > column) {
			value = matrix[row][column];
		}
		return value;
	}

	public void print() {
		for (int[] row : matrix) {
			System.out.println(Arrays.toString(row));
		}
	}

	public void clear() {
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				matrix[row][column] = 0;
			}
		}
	}

	/**
	 * Method which quickly checks for obvious cases with no solutions and calls
	 * the method SolveFrom() if the Sudoku isn't obviously unsolvable.
	 */
	public boolean solve() {
		// starts with a test to find obvious cases with no solutions quickly
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int setvalue = matrix[i][j];
				if (setvalue != 0 && testValue(i, j, setvalue) == false) {
					return false;
				}
			}
		}
		// end test for finding obvious cases with no solutions quickly
		return solveFrom(0, 0);
	}

	/**
	 * Method to solve the sudoku, if possible.
	 * 
	 * 
	 @parameter int row should be 0 because we start solving on the first row.
	 * @parameter int column should be 0 because we start solving in the first
	 *            column.
	 * @return true if there is a solution, otherwise false.
	 */
	private boolean solveFrom(int row, int column) {
		int value = matrix[row][column];
		// tries different values for the square which has not been assigned a
		// value by the user.
		if (value == 0) {
			for (int testValue = 1; testValue < 10; testValue++) {
				if (testValue(row, column, testValue)) {
					matrix[row][column] = testValue;
					if (column < 8) {
						if (solveFrom(row, column + 1)) {
							return true;
						}
					} else {
						if (row < 8) {
							if (solveFrom(row + 1, column - 8)) {
								return true;
							}
						} else {
							return true;
						}
					}
				}
			}
			matrix[row][column] = 0;
		}
		// passes on to the next square to be solved for if user has assigned a
		// value for the square
		if (value != 0) {
			if (column < 8) {
				if (solveFrom(row, column + 1)) {
					return true;
				}
			} else {
				if (row < 8) {
					if (solveFrom(row + 1, column - 8)) {
						return true;
					}
				} else {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Method to check if there are duplicate values in the 3x3 box, the column,
	 * or the row a specific square is in.
	 * 
	 * @parameter int row the row the square we are checking for is in.
	 * @parameter int column the column the square we are checking for us in.
	 * @parameter int testValue the value we assign the square while running the
	 *            test.
	 * @return true if there are no duplicate values, otherwise false.
	 */
	private boolean testValue(int row, int column, int testValue) {
		// check if there is already a square with testValue in the same
		// row
		boolean keepgoing = checkRow(row, column, testValue);
		if (keepgoing == false) {
			return false;
		}
		// check if there is already a square with testValue in the same
		// column
		keepgoing = checkColumn(row, column, testValue);
		if (keepgoing == false) {
			return false;
		}
		// check if there is already a square with testValue in the same
		// 3x3 box
		keepgoing = checkBox(row, column, testValue);
		if (keepgoing == false) {
			return false;
		}
		return true;
	}

	private boolean checkRow(int row, int column, int testValue) {
		for (int i = 0; i < 9; i++) {
			if (column != i) { // don't want to check for the square we are
								// working on
				int currentValue = matrix[row][i];
				if (testValue == currentValue) {
					return false;
				}

			}
		}
		return true;
	}

	/**
	 * Method to check if there are duplicate values in the column a specific
	 * square is in.
	 * 
	 * @parameter int row the row the square we are checking for is in.
	 * @parameter int column the column the square we are checking for is in.
	 * @parameter int testValue the value we assign the square while running the
	 *            test.
	 * @return true if there are no duplicate values, otherwise false.
	 */
	private boolean checkColumn(int row, int column, int testValue) {
		for (int i = 0; i < 9; i++) {
			if (row != i) { // don't want to check for the square we are
							// working on
				int currentValue = matrix[i][column];
				if (testValue == currentValue) {
					return false;
				}

			}
		}
		return true;
	}

	/**
	 * Method to check if there are duplicate values in the 3x3 box a specific
	 * square is in.
	 * 
	 * @parameter int row the row the square we are checking for is in.
	 * @parameter int column the column the square we are checking for is in.
	 * @parameter int testValue the value we assign the square while running the
	 *            test.
	 * @return true if there are no duplicate values, otherwise false.
	 */
	private boolean checkBox(int row, int column, int testValue) {
		int startRow = row - row % 3; // 3*(row/3)
		int startColumn = column - column % 3; // 3*(column/3)
		for (int j = startColumn; j <= startColumn + 2; j++) {
			for (int k = startRow; k <= startRow + 2; k++) {
				if ((j != column || k != row) && matrix[k][j] == testValue) {
					return false;
				}
			}
		}
		return true;
	}
}
