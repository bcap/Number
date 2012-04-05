package me.bcap.number;

import java.math.BigDecimal;
import java.math.RoundingMode;

import me.bcap.number.intf.AritmethicsCalculation;
import me.bcap.number.intf.Calculation;
import me.bcap.number.intf.VarDef;
import me.bcap.number.operation.Addition;
import me.bcap.number.operation.Division;
import me.bcap.number.operation.Multiplication;
import me.bcap.number.operation.Operation;
import me.bcap.number.operation.Substraction;

public class Formula extends AbstractCalculation<Formula> implements AritmethicsCalculation<Formula> {

	private static final long serialVersionUID = 1L;

	private static final int DEFAULT_SCALE = 100;

	private static final RoundingMode DEFAULT_DIVISION_ROUNDING = RoundingMode.HALF_EVEN;

	private Calculation<?> calculation;

	private int scale = DEFAULT_SCALE;

	private RoundingMode divisionRounding = DEFAULT_DIVISION_ROUNDING;

	public Formula() {
		this.calculation = new Value(BigDecimal.ZERO);
	}

	public Formula(String var) {
		this.calculation = new VariableReplace(var);
	}

	public Formula(Number value) {
		this.calculation = new Value(value);
	}

	public Formula(Calculation<?> calculation) {
		this.calculation = calculation;
	}

	public Formula withDivisionRounding(RoundingMode rounding) {
		this.divisionRounding = rounding;
		return this;
	}
	
	public Formula withScale(int scale) {
		this.scale = scale;
		return this;
	}

	public BigDecimal calculate(VarDef... vars) {
		BigDecimal number = calculation.calculate(vars);

		for (Operation operation : operations)
			number = operation.execute(number, vars);

		return cleanScale(number);
	}

	private BigDecimal cleanScale(BigDecimal number) {
		number = number.stripTrailingZeros();
		if(number.scale() < 0)
			number = number.setScale(0);
		return number;
	}

	public Formula plus(Calculation<?> calculation) {
		operations.add(new Addition(calculation));
		return this;
	}

	public Formula plus(Number number) {
		operations.add(new Addition(new Value(number)));
		return this;
	}

	public Formula plus(String variable) {
		operations.add(new Addition(new VariableReplace(variable)));
		return this;
	}

	public Formula minus(Calculation<?> calculation) {
		operations.add(new Substraction(calculation));
		return this;
	}

	public Formula minus(Number number) {
		operations.add(new Substraction(new Value(number)));
		return this;
	}

	public Formula minus(String variable) {
		operations.add(new Substraction(new VariableReplace(variable)));
		return this;
	}

	public Formula times(Calculation<?> calculation) {
		operations.add(new Multiplication(calculation));
		return this;
	}

	public Formula times(Number number) {
		operations.add(new Multiplication(new Value(number)));
		return this;
	}

	public Formula times(String variable) {
		operations.add(new Multiplication(new VariableReplace(variable)));
		return this;
	}

	public Formula dividedBy(Calculation<?> calculation) {
		return dividedBy(calculation, scale, divisionRounding);
	}

	public Formula dividedBy(Number number) {
		return dividedBy(number, scale, divisionRounding);
	}

	public Formula dividedBy(String variable) {
		return dividedBy(variable, scale, divisionRounding);
	}

	public Formula dividedBy(Calculation<?> calculation, int scale, RoundingMode rounding) {
		operations.add(new Division(calculation, scale, divisionRounding));
		return this;
	}

	public Formula dividedBy(Number number, int scale, RoundingMode rounding) {
		operations.add(new Division(new Value(number), scale, divisionRounding));
		return this;
	}

	public Formula dividedBy(String variable, int scale, RoundingMode rounding) {
		operations.add(new Division(new VariableReplace(variable), scale, divisionRounding));
		return this;
	}

}
