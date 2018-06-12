package spiral;

import static org.junit.Assert.*;

import org.junit.Test;

import spiral.NumberSpiral;

public class NumberSpiralTest {

	NumberSpiral numberSpiral = new NumberSpiral();

	@Test
	public void mainTest() {
		String[] args = { "90", "left" };
		NumberSpiral.main(args);
	}

	@Test
	public void validateInputTest() {
		String inputNo = "15";
		String inputDirection = "left";
		boolean inputCheck = numberSpiral.validateInput(inputNo, inputDirection);
		assertEquals("Input validation will be true", true, inputCheck);

		inputNo = "-15";
		inputDirection = "left";
		inputCheck = numberSpiral.validateInput(inputNo, inputDirection);
		assertEquals("Input validation will be false", false, inputCheck);

		inputNo = "15";
		inputDirection = "abc";
		inputCheck = numberSpiral.validateInput(inputNo, inputDirection);
		assertEquals("Input validation will be false", false, inputCheck);

		inputNo = "-15";
		inputDirection = "abc";
		inputCheck = numberSpiral.validateInput(inputNo, inputDirection);
		assertEquals("Input validation will be false", false, inputCheck);

		inputNo = "abc";
		inputDirection = "abc";
		inputCheck = numberSpiral.validateInput(inputNo, inputDirection);
		assertEquals("Input validation will be false", false, inputCheck);
	}

	@Test
	public void getMatrixDimensionTest() {
		int inputNo = 34;
		int matIndex = numberSpiral.getMatrixDimension(inputNo);
		assertEquals("Checked for Matrix dimension", 7, matIndex);
	}
}
