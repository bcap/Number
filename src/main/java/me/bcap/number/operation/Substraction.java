package me.bcap.number.operation;

import java.math.BigDecimal;

import me.bcap.number.intf.Calculation;
import me.bcap.number.intf.VarDef;

public class Substraction extends SimpleOperation {

	private static final long serialVersionUID = 1L;

	public Substraction(Calculation<?> value) {
		super(value);
	}

	public BigDecimal execute(BigDecimal acumulatedCalculation, VarDef... vars) {
		return acumulatedCalculation.subtract(calculation.calculate(vars));
	}

}
