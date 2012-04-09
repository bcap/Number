package me.bcap.number;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import me.bcap.number.intf.VarDef;

import org.junit.Test;

public class FormulaConstructorTest {

	@Test
	public void testEmptyConstructor() {
		int result = 0;
		
		Formula formula = new Formula();
		assertConstruct(formula, result, Formula.DEFAULT_SCALE, Formula.DEFAULT_DIVISION_ROUNDING);
	}
	
	@Test
	public void testScaleAndRoundingConstructor() {
		int result = 0;
		int scale = 500;
		RoundingMode rounding = RoundingMode.DOWN;
	
		Formula formula = new Formula(scale, rounding);
		assertConstruct(formula, result, scale, rounding);
	}
	
	@Test
	public void testValueConstructor() {
		Integer result = 5;
		
		Formula formula = new Formula(result);
		assertConstruct(formula, result, Formula.DEFAULT_SCALE, Formula.DEFAULT_DIVISION_ROUNDING);
	}
	
	@Test
	public void testValueAndScaleAndRoundingConstructor() {
		Integer result = 5;
		int scale = 500;
		RoundingMode rounding = RoundingMode.DOWN;

		Formula formula = new Formula(result, scale, rounding);
		assertConstruct(formula, result, scale, rounding);
	}
	
	@Test
	public void testVarConstructor() {
		String var = "x";
		Integer result = 5;
		
		Formula formula = new Formula(var);
		assertConstruct(formula, result, Formula.DEFAULT_SCALE, Formula.DEFAULT_DIVISION_ROUNDING, new Var(var, result));
	}
	
	@Test
	public void testVarAndScaleAndRoundingConstructor() {
		String var = "x";
		Integer result = 5;
		int scale = 500;
		RoundingMode rounding = RoundingMode.DOWN;

		Formula formula = new Formula(var, scale, rounding);
		assertConstruct(formula, result, scale, rounding, new Var(var, result));
	}
	
	@Test
	public void testCalculationConstructor() {
		Integer result = 5;
		Formula anotherFormula = new Formula(2).plus(3);
		
		Formula formula = new Formula(anotherFormula);
		assertConstruct(formula, result, Formula.DEFAULT_SCALE, Formula.DEFAULT_DIVISION_ROUNDING);
	}
	
	@Test
	public void testCalculationAndScaleAndRoundingConstructor() {
		Integer result = 5;
		Formula anotherFormula = new Formula(2).plus(3);
		int scale = 500;
		RoundingMode rounding = RoundingMode.DOWN;

		Formula formula = new Formula(anotherFormula, scale, rounding);
		assertConstruct(formula, result, scale, rounding );
	}
	
	private void assertConstruct(Formula formula, int result, int scale, RoundingMode divisionRounding, VarDef... vars) {
		assertThat(formula.getScale(), is(scale));
		assertThat(formula.getDivisionRounding(), is(divisionRounding));
		assertThat(formula.calculate(vars), is(new BigDecimal(result)));
	}
}
