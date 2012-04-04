package me.bcap.number;

import java.math.BigDecimal;

import me.bcap.number.intf.AritmethicsCalculation;
import me.bcap.number.intf.Calculation;
import me.bcap.number.intf.VarDef;

public class Formula extends AbstractCalculation<Formula> implements AritmethicsCalculation<Formula> {

	private static final long serialVersionUID = 1L;

	private Calculation<?> value;

	public Formula() {
		this.value = new Value(BigDecimal.ZERO);
	}

	public Formula(String var) {
		this.value = new VariableReplace(var);
	}
	
	public Formula(Number value) {
		this.value = new Value(value);
	}

	public Formula(Calculation<?> calculation) {
		this.value = calculation;
	}

	public BigDecimal calculate(VarDef... vars) {
		BigDecimal number = value.calculate(vars);

		for (Execution execution : executions) {
			Operation operation = execution.operation;
			BigDecimal value = execution.value.calculate(vars);

			if (operation == Operation.ADDITION)
				number = number.add(value);
			else if (operation == Operation.SUBSTRACTION)
				number = number.subtract(value);
			else if (operation == Operation.MULTIPLICATION)
				number = number.multiply(value);
			else if (operation == Operation.DIVISION)
				number = number.divide(value);
		}

		return number;
	}

	public Formula plus(Calculation<?> calculation) {
		executions.add(new Execution(Operation.ADDITION, calculation));
		return this;
	}

	public Formula plus(Number number) {
		executions.add(new Execution(Operation.ADDITION, new Value(number)));
		return this;
	}

	public Formula plus(String variable) {
		executions.add(new Execution(Operation.ADDITION, new VariableReplace(variable)));
		return this;
	}

	public Formula minus(Calculation<?> calculation) {
		executions.add(new Execution(Operation.SUBSTRACTION, calculation));
		return this;
	}

	public Formula minus(Number number) {
		executions.add(new Execution(Operation.SUBSTRACTION, new Value(number)));
		return this;
	}

	public Formula minus(String variable) {
		executions.add(new Execution(Operation.SUBSTRACTION, new VariableReplace(variable)));
		return this;
	}

	public Formula times(Calculation<?> calculation) {
		executions.add(new Execution(Operation.MULTIPLICATION, calculation));
		return this;
	}

	public Formula times(Number number) {
		executions.add(new Execution(Operation.MULTIPLICATION, new Value(number)));
		return this;
	}

	public Formula times(String variable) {
		executions.add(new Execution(Operation.MULTIPLICATION, new VariableReplace(variable)));
		return this;
	}

	public Formula dividedBy(Calculation<?> calculation) {
		executions.add(new Execution(Operation.DIVISION, calculation));
		return this;
	}

	public Formula dividedBy(Number number) {
		executions.add(new Execution(Operation.DIVISION, new Value(number)));
		return this;
	}

	public Formula dividedBy(String variable) {
		executions.add(new Execution(Operation.DIVISION, new VariableReplace(variable)));
		return this;
	}
}
