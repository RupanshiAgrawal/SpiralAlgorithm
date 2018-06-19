import java.util.Arrays;

/**
 * 
 * @author rupanshi_agrawal
 *
 */
public class NumberSpiral {
	/** rowIndex */
	private static int rowIndex;
	
	/** colIndex */
	private static int colIndex;
	
	/** moveDir */
	private static SpiralMovement moveDir = SpiralMovement.NORTH;
	
	/** spiral */
	private static int[][] spiral;
	
	/** enum for SpiralMovement */
	enum SpiralMovement {
		NORTH, SOUTH, WEST, EAST;
	}
	
	/** enum for Direction */
	enum Direction {
		LEFT, RIGHT;
	}

	/**
	 * Method main
	 * 
	 * @param args
	 */
	public static void main(final String[] args) {
		final String inputNo = args[0];
		final String inputDirection = args[1];
		System.out.println("************************");
		System.out.println("Integer Spiral Demo");
		System.out.println("************************\n");
		System.out.println("Command line arguments received:\n");
		System.out.println("Integer Value : " + inputNo);
		System.out.println("Direction     : " + inputDirection);
		final NumberSpiral spiral = new NumberSpiral();
		final boolean correctInput = spiral.validateInput(inputNo, inputDirection);
		if (correctInput) {
			final int inputInteger = Integer.parseInt(inputNo);
			spiral.processAndPrintSpiral(inputInteger, inputDirection);
		}
	}

	/**
	 * Method validateInput
	 * 
	 * @param inputNo
	 * @param inputDirection
	 * @return boolean
	 */
	protected boolean validateInput(final String inputNo, final String inputDirection) {
		boolean inputCheck = false;
		int inputInteger = 0;
		try {
			inputInteger = Integer.parseInt(inputNo);
		} catch (NumberFormatException e) {
			System.out.println("\nInteger Value is invalid.It should be number.");
			return inputCheck;
		}
		if (inputInteger >= 0 && (inputDirection.equalsIgnoreCase(Direction.LEFT.toString())
				|| inputDirection.equalsIgnoreCase(Direction.RIGHT.toString()))) {
			inputCheck = true;
		} else {
			inputCheck = false;
			if (inputInteger < 0) {
				System.out.println("\nYou need to enter a positive value for integer.");
			}
			if (!inputDirection.equalsIgnoreCase(Direction.LEFT.toString())
					&& !inputDirection.equalsIgnoreCase(Direction.RIGHT.toString())) {
				System.out.println("\nPlease enter direction of spiral either LEFT or RIGHT (case insensitive).");
			}
		}
		return inputCheck;
	}

	/**
	 * Method processAndPrintSpiral
	 * 
	 * @param inputNo
	 * @param inputDirection
	 */
	private void processAndPrintSpiral(final int inputNo, final String inputDirection) {
		// determining the matrix size
		final int matrixSize = getMatrixDimension(inputNo);
		spiral = new int[matrixSize][matrixSize];

		// assigning -1 for empty array values.
		for (final int[] row : spiral) {
			Arrays.fill(row, -1);
		}

		// initial position
		rowIndex = colIndex = matrixSize / 2;
		spiral[rowIndex][colIndex] = 0;

		processSpiral(inputNo, inputDirection);
		// Printing matrix
		printSpiral(spiral);
	}

	/**
	 * Method getMatrixDimension
	 * 
	 * @param inputNo
	 * @return Integer
	 */
	protected int getMatrixDimension(final int inputNo) {
		final int input = inputNo;
		final double sqrt = Math.sqrt(input);
		return (int) Math.ceil(sqrt) + 1;
	}

	/**
	 * Method processSpiral
	 * 
	 * @param inputNo
	 * @param inputDirection
	 */
	private void processSpiral(final int inputNo, final String inputDirection) {
		int digit = 0;
		while (digit++ < inputNo) {
			switch (moveDir) {
			case NORTH:
				if (inputDirection.equalsIgnoreCase(Direction.LEFT.toString())) {
					navigateWest(true);
				} else {
					navigateEast(false);
				}
				break;
			case SOUTH:
				if (inputDirection.equalsIgnoreCase(Direction.LEFT.toString())) {
					navigateEast(true);
				} else {
					navigateWest(false);
				}
				break;
			case WEST:
				if (inputDirection.equalsIgnoreCase(Direction.LEFT.toString())) {
					navigateSouth(true);
				} else {
					navigateNorth(false);
				}
				break;
			case EAST:
				if (inputDirection.equalsIgnoreCase(Direction.LEFT.toString())) {
					navigateNorth(true);
				} else {
					navigateSouth(false);
				}
				break;
			default:
				System.out.println("Not a valid direction for spiral movement!!!");
			}
			spiral[rowIndex][colIndex] = digit;
		}
	}

	/**
	 * Method navigateNorth
	 * 
	 * @param leftDir
	 */
	private void navigateNorth(final boolean leftDir) {
		rowIndex--;
		if (spiral[rowIndex][colIndex] == -1) {
			moveDir = SpiralMovement.NORTH;
		} else {
			rowIndex++;
			colIndex = leftDir ? colIndex + 1 : colIndex - 1;
		}
	}

	/**
	 * Method navigateWest
	 * 
	 * @param leftDir
	 */
	private void navigateWest(final boolean leftDir) {
		colIndex--;
		if (spiral[rowIndex][colIndex] == -1) {
			moveDir = SpiralMovement.WEST;
		} else {
			colIndex++;
			rowIndex = leftDir ? rowIndex - 1 : rowIndex + 1;
		}
	}

	/**
	 * Method navigateSouth
	 * 
	 * @param leftDir
	 */
	private void navigateSouth(final boolean leftDir) {
		rowIndex++;
		if (spiral[rowIndex][colIndex] == -1) {
			moveDir = SpiralMovement.SOUTH;
		} else {
			rowIndex--;
			colIndex = leftDir ? colIndex - 1 : colIndex + 1;
		}
	}

	/**
	 * Method navigateEast
	 * 
	 * @param leftDir
	 */
	private void navigateEast(final boolean leftDir) {
		colIndex++;
		if (spiral[rowIndex][colIndex] == -1) {
			moveDir = SpiralMovement.EAST;
		} else {
			colIndex--;
			rowIndex = leftDir ? rowIndex + 1 : rowIndex - 1;
		}
	}

	/**
	 * Method printSpiral
	 * 
	 * @param spiral
	 */
	private void printSpiral(final int[][] spiral) {

		for (final int[] row : spiral) {
			for (final int val : row) {
				String valStr = Integer.toString(val);
				if ("-1".equals(valStr)) {
					valStr = " ";
				}
				if (valStr.length() == 1) {
					System.out.print(valStr + "   ");
				} else if (valStr.length() == 2) {
					System.out.print(valStr + "  ");
				} else {
					System.out.print(valStr + " ");
				}
			}
			System.out.println();
		}
	}

}
