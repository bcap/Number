package me.bcap.number;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import me.bcap.number.intf.VarDef;

import org.junit.Test;

public class VariablesFormulaTest {
	
	@Test
	public void testWithVarsAndConstants() {
		Formula formula = (new Formula("x").plus(10).minus("y")).times(5).dividedBy("z");
		VarDef[] vars = new VarDef[]{
			new Var("x", 8),
			new Var("y", 3),
			new Var("z", 10)
		};
		assertThat(formula.calculate(vars), is(new BigDecimal("7.5")));
		assertFormulaContainVars(formula, "x", "y", "z");
	}

	@Test
	public void testWithVarsOnly() {
		Formula formula = (new Formula("x").plus("a").minus("y")).times("b").dividedBy("z");
		VarDef[] vars = new VarDef[]{
			new Var("x", 8),
			new Var("y", 3),
			new Var("z", 10),
			new Var("a", 10),
			new Var("b", 5)
		};
		assertThat(formula.calculate(vars), is(new BigDecimal("7.5")));
		assertFormulaContainVars(formula, "x", "y", "z", "a", "b");
	}
	

	@Test
	public void testVarUsedMoreThanOnce() {
		Formula formula = (new Formula("x").plus("x")).times("x");
		assertThat(formula.calculate(new Var("x", 3)), is(new BigDecimal("18")));
		assertFormulaContainVars(formula, "x");
	}

	@Test
	public void testValuesExercise() {
		Formula formula = (new Formula("a").plus("b")).times("c");

		class Expectation {
			VarDef[] vars;
			String result;
		}
		
		Expectation[] expectations = new Expectation[] {
			new Expectation() {{
				vars = new VarDef[]{
					new Var("a", 1),
					new Var("b", 2),
					new Var("c", 3)
				};
				result = "9";
			}},
			
			new Expectation() {{
				vars = new VarDef[]{
					new Var("a", 2),
					new Var("b", 3),
					new Var("c", 4)
				};
				result = "20";
			}},
			
			new Expectation() {{
				vars = new VarDef[]{
					new Var("a", 3),
					new Var("b", 4),
					new Var("c", 5)
				};
				result = "35";
			}},
			
			new Expectation() {{
				vars = new VarDef[]{
					new Var("a", -1),
					new Var("b", -2),
					new Var("c", -1)
				};
				result = "3";
			}},
			
			new Expectation() {{
				vars = new VarDef[]{
					new Var("a", 100),
					new Var("b", -100),
					new Var("c", 5)
				};
				result = "0";
			}},
		};
		
		for (Expectation expectation : expectations) {
			assertThat(formula.calculate(expectation.vars), is(equalTo(new BigDecimal(expectation.result))));
			assertFormulaContainVars(formula, "a", "b", "c");
		}
	}
	
	@Test
	public void testVarNotDefined() {
		Formula formula = new Formula("someVariableName").plus(5);
		try { 
			formula.calculate();
			fail("should launch an ArithmeticException as the variable 'a' is not defined");
		} catch (ArithmeticException e) {
			assertThat(e.getMessage(), is(notNullValue()));
			assertThat("Exception message should contains an indication to the undefined variable", e.getMessage().contains(" someVariableName "), is(true));
		}
	}
	
	@Test
	public void testUnusedVarDefined() {
		Formula formula = new Formula("someVariableName").plus(5);
		assertThat(formula.calculate(new Var("someVariableName", 3), new Var("anUnusedVariable", 7)), is(new BigDecimal(8)));
	}
	
	private void assertFormulaContainVars(Formula formula, String... vars) {
		assertThat("formula contains a different amount of variables", formula.getVariables().size(), is(vars.length));
		for (String var : vars)
			assertThat("var " + var + " is not present in the formula", formula.getVariables().contains(var), is(true));
	}
}
