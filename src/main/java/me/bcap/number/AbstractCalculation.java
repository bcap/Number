package me.bcap.number;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import me.bcap.number.intf.Calculation;
import me.bcap.number.intf.VarDef;
import me.bcap.number.operation.Operation;
import me.bcap.number.operation.Round;

public abstract class AbstractCalculation<T extends Calculation<?>> implements Calculation<T> {

	private static final long serialVersionUID = 1L;

	private static final VarDef[] EMPTY_VARS = new VarDef[0];

	private Set<String> variables = new TreeSet<String>();

	protected List<Operation> operations = new ArrayList<Operation>();

	protected void addVar(String variable) {
		variables.add(variable);
	}

	public Set<String> getVariables() {
		return new TreeSet<String>(variables);
	}

	@SuppressWarnings("unchecked")
	public T round(int scale, RoundingMode rounding) {
		operations.add(new Round(new Value(scale), rounding));
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	public T round(String variable, RoundingMode rounding) {
		operations.add(new Round(new VariableReplace(variable), rounding));
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	public T round(Calculation<?> calculation, RoundingMode rounding) {
		operations.add(new Round(calculation, rounding));
		return (T) this;
	}

	public BigDecimal calculate(Collection<VarDef> vars) {
		return calculate(vars != null ? (VarDef[]) vars.toArray() : EMPTY_VARS);
	}

	public BigDecimal calculate(int scale, RoundingMode roundingMode, Collection<VarDef> vars) {
		return calculate(scale, roundingMode, vars != null ? (VarDef[]) vars.toArray() : EMPTY_VARS);
	}

	public BigDecimal calculate(int scale, RoundingMode roundingMode, VarDef... vars) {
		return calculate(vars).setScale(scale, roundingMode);
	}

}
