package me.bcap.number;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import me.bcap.number.intf.VarDef;

import org.junit.Before;
import org.junit.Test;

public class FormulaScalingAndRoundingTest {

	private Formula divisionFormula;
	
	@Before
	public void setup() {
		divisionFormula = new Formula("x").dividedBy("y");
	}
	
	@Test
	public void testDefaultRoundingAndScale() {
		StringBuilder result = new StringBuilder("6.");
		for(int i = 0; i < 34; i++)
			result.append("6");
		result.replace(result.length() - 1, result.length(), "7");
		
		// 20 divided by 3 should result in 6.6666...6667 (34 fractional digits)
 		assertThat(new Formula(20).dividedBy(3).calculate(), is(new BigDecimal(result.toString())));
	}
	
	@Test
	public void testDecimal128RoundingAndScale() {
		StringBuilder result = new StringBuilder("6.");
		for(int i = 0; i < 34; i++)
			result.append("6");
		result.replace(result.length() - 1, result.length(), "7");
		
		// 20 divided by 3 should result in 6.6666...6667 (34 fractional digits)
 		assertThat(new Formula(20, Formula.DECIMAL128_SCALE, Formula.DEFAULT_DIVISION_ROUNDING).dividedBy(3).calculate(), is(new BigDecimal(result.toString())));
	}
	
	@Test
	public void testDecimal64RoundingAndScale() {
		StringBuilder result = new StringBuilder("6.");
		for(int i = 0; i < 16; i++)
			result.append("6");
		result.replace(result.length() - 1, result.length(), "7");
		
		// 20 divided by 3 should result in 6.6666...6667 (34 fractional digits)
 		assertThat(new Formula(20, Formula.DECIMAL64_SCALE, Formula.DEFAULT_DIVISION_ROUNDING).dividedBy(3).calculate(), is(new BigDecimal(result.toString())));
	}
	
	@Test
	public void testDecimal32RoundingAndScale() {
		StringBuilder result = new StringBuilder("6.");
		for(int i = 0; i < 7; i++)
			result.append("6");
		result.replace(result.length() - 1, result.length(), "7");
		
		// 20 divided by 3 should result in 6.6666...6667 (34 fractional digits)
 		assertThat(new Formula(20, Formula.DECIMAL32_SCALE, Formula.DEFAULT_DIVISION_ROUNDING).dividedBy(3).calculate(), is(new BigDecimal(result.toString())));
	}
	
	@Test
	public void testResultRoundingHalfEven() {
		RoundingMode rounding = RoundingMode.HALF_EVEN;
		//positives
		assertDivisionRounding("5.00", rounding, new Var("x", 10), new Var("y", 2));
		assertDivisionRounding("3.33", rounding, new Var("x", 10), new Var("y", 3));
		assertDivisionRounding("6.67", rounding, new Var("x", 20), new Var("y", 3));
		assertDivisionRounding("0.00", rounding, new Var("x", 0.01), new Var("y", 2));
		assertDivisionRounding("0.02", rounding, new Var("x", 0.03), new Var("y", 2));
		//negatives
		assertDivisionRounding("-5.00", rounding, new Var("x", -10), new Var("y", 2));
		assertDivisionRounding("-3.33", rounding, new Var("x", -10), new Var("y", 3));
		assertDivisionRounding("-6.67", rounding, new Var("x", -20), new Var("y", 3));
		assertDivisionRounding("0.00",  rounding, new Var("x", -0.01), new Var("y", 2));
		assertDivisionRounding("-0.02", rounding, new Var("x", -0.03), new Var("y", 2));
	}
	
	@Test
	public void testResultRoundingHalfDown() {
		RoundingMode rounding = RoundingMode.HALF_DOWN;
		//positives
		assertDivisionRounding("5.00", rounding, new Var("x", 10), new Var("y", 2));
		assertDivisionRounding("3.33", rounding, new Var("x", 10), new Var("y", 3));
		assertDivisionRounding("6.67", rounding, new Var("x", 20), new Var("y", 3));
		assertDivisionRounding("0.00", rounding, new Var("x", 0.01), new Var("y", 2));
		assertDivisionRounding("0.01", rounding, new Var("x", 0.03), new Var("y", 2));
		//negatives
		assertDivisionRounding("-5.00", rounding, new Var("x", -10), new Var("y", 2));
		assertDivisionRounding("-3.33", rounding, new Var("x", -10), new Var("y", 3));
		assertDivisionRounding("-6.67", rounding, new Var("x", -20), new Var("y", 3));
		assertDivisionRounding("0.00",  rounding, new Var("x", -0.01), new Var("y", 2));
		assertDivisionRounding("-0.01", rounding, new Var("x", -0.03), new Var("y", 2));
	}
	
	@Test
	public void testResultRoundingHalfUp() {
		RoundingMode rounding = RoundingMode.HALF_UP;
		//positives
		assertDivisionRounding("5.00", rounding, new Var("x", 10), new Var("y", 2));
		assertDivisionRounding("3.33", rounding, new Var("x", 10), new Var("y", 3));
		assertDivisionRounding("6.67", rounding, new Var("x", 20), new Var("y", 3));
		assertDivisionRounding("0.01", rounding, new Var("x", 0.01), new Var("y", 2));
		assertDivisionRounding("0.02", rounding, new Var("x", 0.03), new Var("y", 2));
		//negatives
		assertDivisionRounding("-5.00", rounding, new Var("x", -10), new Var("y", 2));
		assertDivisionRounding("-3.33", rounding, new Var("x", -10), new Var("y", 3));
		assertDivisionRounding("-6.67", rounding, new Var("x", -20), new Var("y", 3));
		assertDivisionRounding("-0.01", rounding, new Var("x", -0.01), new Var("y", 2));
		assertDivisionRounding("-0.02", rounding, new Var("x", -0.03), new Var("y", 2));
	}
	
	@Test
	public void testResultRoundingDown() {
		RoundingMode rounding = RoundingMode.DOWN;
		//positives
		assertDivisionRounding("5.00", rounding, new Var("x", 10), new Var("y", 2));
		assertDivisionRounding("3.33", rounding, new Var("x", 10), new Var("y", 3));
		assertDivisionRounding("6.66", rounding, new Var("x", 20), new Var("y", 3));
		assertDivisionRounding("0.00", rounding, new Var("x", 0.01), new Var("y", 2));
		assertDivisionRounding("0.01", rounding, new Var("x", 0.03), new Var("y", 2));
		//negatives
		assertDivisionRounding("-5.00", rounding, new Var("x", -10), new Var("y", 2));
		assertDivisionRounding("-3.33", rounding, new Var("x", -10), new Var("y", 3));
		assertDivisionRounding("-6.66", rounding, new Var("x", -20), new Var("y", 3));
		assertDivisionRounding("0.00",  rounding, new Var("x", -0.01), new Var("y", 2));
		assertDivisionRounding("-0.01", rounding, new Var("x", -0.03), new Var("y", 2));
	}
	
	@Test
	public void testResultRoundingUp() {
		RoundingMode rounding = RoundingMode.UP;
		//positives
		assertDivisionRounding("5.00", rounding, new Var("x", 10), new Var("y", 2));
		assertDivisionRounding("3.34", rounding, new Var("x", 10), new Var("y", 3));
		assertDivisionRounding("6.67", rounding, new Var("x", 20), new Var("y", 3));
		assertDivisionRounding("0.01", rounding, new Var("x", 0.01), new Var("y", 2));
		assertDivisionRounding("0.02", rounding, new Var("x", 0.03), new Var("y", 2));
		//negatives
		assertDivisionRounding("-5.00", rounding, new Var("x", -10), new Var("y", 2));
		assertDivisionRounding("-3.34", rounding, new Var("x", -10), new Var("y", 3));
		assertDivisionRounding("-6.67", rounding, new Var("x", -20), new Var("y", 3));
		assertDivisionRounding("-0.01", rounding, new Var("x", -0.01), new Var("y", 2));
		assertDivisionRounding("-0.02", rounding, new Var("x", -0.03), new Var("y", 2));
	}
	
	@Test
	public void testResultRoundingFloor() {
		RoundingMode rounding = RoundingMode.FLOOR;
		//positives
		assertDivisionRounding("5.00", rounding, new Var("x", 10), new Var("y", 2));
		assertDivisionRounding("3.33", rounding, new Var("x", 10), new Var("y", 3));
		assertDivisionRounding("6.66", rounding, new Var("x", 20), new Var("y", 3));
		assertDivisionRounding("0.00", rounding, new Var("x", 0.01), new Var("y", 2));
		assertDivisionRounding("0.01", rounding, new Var("x", 0.03), new Var("y", 2));
		//negatives
		assertDivisionRounding("-5.00", rounding, new Var("x", -10), new Var("y", 2));
		assertDivisionRounding("-3.34", rounding, new Var("x", -10), new Var("y", 3));
		assertDivisionRounding("-6.67", rounding, new Var("x", -20), new Var("y", 3));
		assertDivisionRounding("-0.01", rounding, new Var("x", -0.01), new Var("y", 2));
		assertDivisionRounding("-0.02", rounding, new Var("x", -0.03), new Var("y", 2));
	}
	
	@Test
	public void testResultRoundingCeiling() {
		RoundingMode rounding = RoundingMode.CEILING;
		//positives
		assertDivisionRounding("5.00", rounding, new Var("x", 10), new Var("y", 2));
		assertDivisionRounding("3.34", rounding, new Var("x", 10), new Var("y", 3));
		assertDivisionRounding("6.67", rounding, new Var("x", 20), new Var("y", 3));
		assertDivisionRounding("0.01", rounding, new Var("x", 0.01), new Var("y", 2));
		assertDivisionRounding("0.02", rounding, new Var("x", 0.03), new Var("y", 2));
		//negatives
		assertDivisionRounding("-5.00", rounding, new Var("x", -10), new Var("y", 2));
		assertDivisionRounding("-3.33", rounding, new Var("x", -10), new Var("y", 3));
		assertDivisionRounding("-6.66", rounding, new Var("x", -20), new Var("y", 3));
		assertDivisionRounding("0.00", rounding, new Var("x", -0.01), new Var("y", 2));
		assertDivisionRounding("-0.01", rounding, new Var("x", -0.03), new Var("y", 2));
	}
	
	private void assertDivisionRounding(String result, RoundingMode rounding, VarDef... vars) {
		assertThat(divisionFormula.calculate(2, rounding, vars), is(new BigDecimal(result)));
	}
	
}
