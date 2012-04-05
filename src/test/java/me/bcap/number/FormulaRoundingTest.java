package me.bcap.number;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.math.RoundingMode;

import me.bcap.number.intf.VarDef;

import org.junit.Before;
import org.junit.Test;

public class FormulaRoundingTest {

	private Formula divisionFormula;
	
	@Before
	public void setup() {
		divisionFormula = new Formula("x").dividedBy("y");
	}
	
	@Test
	public void testResultRoundingHalfEven() {
		RoundingMode rounding = RoundingMode.HALF_EVEN;
		assertDivisionRounding("5.00", rounding, new Var("x", 10), new Var("y", 2));
		assertDivisionRounding("3.33", rounding, new Var("x", 10), new Var("y", 3));
		assertDivisionRounding("6.67", rounding, new Var("x", 20), new Var("y", 3));
		assertDivisionRounding("0.00", rounding, new Var("x", 0.005), new Var("y", 1));
		assertDivisionRounding("0.02", rounding, new Var("x", 0.015), new Var("y", 1));
	}
	
	private void assertDivisionRounding(String result, RoundingMode rounding, VarDef... vars) {
		assertThat(divisionFormula.calculate(2, rounding, vars), is(new BigDecimal(result)));
	}
	
}
