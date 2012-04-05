package me.bcap.number;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class VariablesFormulaTest {
	
	@Test
	public void testAdditionWithVarsAndConstants() {
		Formula formula = new Formula("x").plus(10);
		assertThat(formula.calculate(new Var("x", 5.5)), is(new BigDecimal("15.5")));
	}

	@Test
	public void testSubstractionWithVarsAndConstants() {
		Formula formula = new Formula("x").minus(15.5);
		assertThat(formula.calculate(new Var("x", 10)), is(new BigDecimal("-5.5")));
	}

	@Test
	public void testMultiplicationWithVarsAndConstants() {
		Formula formula = new Formula("x").times(10.1);
		assertThat(formula.calculate(new Var("x", 5)), is(new BigDecimal("50.5")));
	}

	@Test
	public void testDivisionWithVarsAndConstants() {
		Formula formula = new Formula("x").dividedBy(2);
		assertThat(formula.calculate(new Var("x", 5)), is(new BigDecimal("2.5")));
	}
	
	@Test
	public void testAdditionWithVarsOnly() {
		Formula formula = new Formula("x").plus("y");
		Var[] vars = new Var[] { new Var("x", 5.5), new Var("y", 10) };
		assertThat(formula.calculate(vars), is(new BigDecimal("15.5")));
	}

	@Test
	public void testSubstractionWithVarsOnly() {
		Formula formula = new Formula("x").minus("y");
		Var[] vars = new Var[] { new Var("x", 10), new Var("y", 15.5) };
		assertThat(formula.calculate(vars), is(new BigDecimal("-5.5")));
	}

	@Test
	public void testMultiplicationWithVarsOnly() {
		Formula formula = new Formula("x").times("y");
		Var[] vars = new Var[] { new Var("x", 5), new Var("y", 10.1) };
		assertThat(formula.calculate(vars), is(new BigDecimal("50.5")));
	}

	@Test
	public void testDivisionWithVarsOnly() {
		Formula formula = new Formula("x").dividedBy("y");
		Var[] vars = new Var[] { new Var("x", 5), new Var("y", 2) };
		assertThat(formula.calculate(vars), is(new BigDecimal("2.5")));
	}

	@Test
	public void testChainedCalculation() {
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
	public void testVarNotDefined() {
		Formula formula = new Formula("a").plus(5);

	}
}
