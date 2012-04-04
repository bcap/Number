package me.bcap.number.operation;

import java.math.BigDecimal;

import me.bcap.number.intf.Calculation;
import me.bcap.number.intf.VarDef;

public class Multiplication extends SimpleOperation {

	public Multiplication(Calculation<?> calculation) {
		super(calculation);
	}

	public BigDecimal execute(BigDecimal acumulatedCalculation, VarDef... vars) {
		return acumulatedCalculation.multiply(calculation.calculate(vars));
	}

	
}
