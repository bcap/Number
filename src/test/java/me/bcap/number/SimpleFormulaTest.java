package me.bcap.number;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SimpleFormulaTest {

	@Test
	public void testSimpleAddition() {
		Formula formula = new Formula(5.5).plus(10);
		assertThat(formula.calculate(), is(new BigDecimal("15.5")));
	}

	@Test
	public void testSimpleSubstraction() {
		Formula formula = new Formula(10).minus(15.5);
		assertThat(formula.calculate(), is(new BigDecimal("-5.5")));
	}

	@Test
	public void testSimpleMultiplication() {
		Formula formula = new Formula(5).times(10.1);
		assertThat(formula.calculate(), is(new BigDecimal("50.5")));
	}

	@Test
	public void testSimpleDivision() {
		Formula formula = new Formula(5).dividedBy(2);
		assertThat(formula.calculate(), is(new BigDecimal("2.5")));
	}

	@Test
	public void testSimpleChainedCalculation() {
		Formula formula = ((new Formula(5).plus(2)).times(new Formula(12).minus(2))).dividedBy(10);
		assertThat(formula.calculate(), is(new BigDecimal("7")));
	}

	@Test
	public void testOrderOfChainedCalculation() {
		List<Formula> formulas = new ArrayList<Formula>();
		formulas.add(((new Formula(5).plus(2)).times(new Formula(12).minus(2))).dividedBy(10));
		formulas.add((new Formula(5).plus(2).times(new Formula(12).minus(2))).dividedBy(10));
		formulas.add(new Formula(5).plus(2).times(new Formula(12).minus(2)).dividedBy(10));
		
		for (Formula formula : formulas)
			assertThat(formula.calculate(), is(new BigDecimal("7")));
		
	}
}
