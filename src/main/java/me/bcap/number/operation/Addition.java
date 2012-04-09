package me.bcap.number.operation;

import java.math.BigDecimal;

import me.bcap.number.intf.Calculation;
import me.bcap.number.intf.VarDef;

public class Addition extends SimpleOperation {

	private static final long serialVersionUID = 1L;

	public Addition(Calculation<?> calculation) {
		super(calculation);
	}

	public BigDecimal execute(BigDecimal acumulatedCalculation, VarDef... vars) {
		return acumulatedCalculation.add(calculation.calculate(vars));
	}

}
