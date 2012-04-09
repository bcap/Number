package me.bcap.number;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.math.BigDecimal;
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
	public void testSimplePower() {
		Formula formula = new Formula(5).poweredBy(3);
		assertThat(formula.calculate(), is(new BigDecimal("125")));
	}
	
	@Test
	public void testSimpleSquareRoot() {
		Formula formula = new Formula(25).squareRoot();
		assertThat(formula.calculate(), is(new BigDecimal("5")));
	}
	
	@Test
	public void testZeroSquareRoot() {
		Formula formula = new Formula(0).squareRoot();
		assertThat(formula.calculate(), is(new BigDecimal("0")));
	}
	
	@Test
	public void testLowPrecisionSquareRoot() {
		Formula formula = new Formula(2).withScale(10).squareRoot();
		assertThat(formula.calculate(), is(new BigDecimal("1.4142135624")));
	}
	
	@Test
	public void testHighPrecisionSquareRoot() {
		Formula formula = new Formula(2).withScale(80).squareRoot();
		assertThat(formula.calculate(), is(new BigDecimal("1.41421356237309504880168872420969807856967187537694807317667973799073247846210704")));
	}

	@Test
	public void testPowerFailOnNonInteger() {
		Formula formula = new Formula(5).poweredBy(3.1);
		try {
			formula.calculate();
			fail("Power should fail as a non integer was passed as argument");
		} catch (ArithmeticException e) {
			assertThat(e.getMessage(), is(notNullValue()));
			assertThat(e.getMessage().contains("3.1"), is(true));
		}
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
