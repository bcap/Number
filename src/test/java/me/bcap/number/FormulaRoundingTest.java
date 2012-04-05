package me.bcap.number;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

public class FormulaRoundingTest {

	@Test
	public void testRounding() {
		Formula formula = new Formula(10).dividedBy(3);
		assertThat(formula.calculate(2, RoundingMode.HALF_EVEN), is(new BigDecimal("3.33")));
		assertThat(formula.calculate(3, RoundingMode.FLOOR), is(new BigDecimal("3.333")));
		assertThat(formula.calculate(4, RoundingMode.CEILING), is(new BigDecimal("3.3334")));
	}
	
}
