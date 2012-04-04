package me.bcap.number;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class FormulaTest {

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
	public void testVarAddition() {
		Formula formula = new Formula("x").plus("y");
		Var[] vars = new Var[] { new Var("x", 5.5), new Var("y", 10) };
		assertThat(formula.calculate(vars), is(new BigDecimal("15.5")));
	}

	@Test
	public void testVarSubstraction() {
		Formula formula = new Formula("x").minus("y");
		Var[] vars = new Var[] { new Var("x", 10), new Var("y", 15.5) };
		assertThat(formula.calculate(vars), is(new BigDecimal("-5.5")));
	}

	@Test
	public void testVarMultiplication() {
		Formula formula = new Formula("x").times("y");
		Var[] vars = new Var[] { new Var("x", 5), new Var("y", 10.1) };
		assertThat(formula.calculate(vars), is(new BigDecimal("50.5")));
	}

	@Test
	public void testVarDivision() {
		Formula formula = new Formula("x").dividedBy("y");
		Var[] vars = new Var[] { new Var("x", 5), new Var("y", 2) };
		assertThat(formula.calculate(vars), is(new BigDecimal("2.5")));
	}

	@Test
	public void testVarChainedCalculation() {
		Formula formula = ((new Formula("a").plus("b")).times(new Formula("c").minus("d"))).dividedBy("e");
		Var[] vars = new Var[] { 
			new Var("a", 5), 
			new Var("b", 2),
			new Var("c", 12),
			new Var("d", 2),
			new Var("e", 10)
		};
		assertThat(formula.calculate(vars), is(new BigDecimal("7")));
		
		vars = new Var[] { 
			new Var("a", 1), 
			new Var("b", 2),
			new Var("c", 15),
			new Var("d", 12),
			new Var("e", 3)
		};
		assertThat(formula.calculate(vars), is(new BigDecimal("3")));
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
	
	@Test
	public void testRounding() {
		Formula formula = new Formula(10).dividedBy(3);
		assertThat(formula.calculate(2, RoundingMode.HALF_EVEN), is(new BigDecimal("3.33")));
		assertThat(formula.calculate(3, RoundingMode.FLOOR), is(new BigDecimal("3.333")));
		assertThat(formula.calculate(4, RoundingMode.CEILING), is(new BigDecimal("3.3334")));
	}

}
