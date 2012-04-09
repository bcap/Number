package me.bcap.number.operation;

import java.math.BigDecimal;

import me.bcap.number.intf.Calculation;
import me.bcap.number.intf.VarDef;

public class Power extends SimpleOperation {

	private static final long serialVersionUID = 1L;

	public Power(Calculation<?> calculation) {
		super(calculation);
	}

	public BigDecimal execute(BigDecimal acumulatedCalculation, VarDef... vars) {
		BigDecimal powerAsBigDecimal = calculation.calculate(vars);
		Integer power;
		try {
			power = powerAsBigDecimal.toBigIntegerExact().intValue();
		} catch (ArithmeticException e) {
			throw new ArithmeticException("Can only power to an integer value. Actual value: " + powerAsBigDecimal);
		}
		return acumulatedCalculation.pow(power);
	}

}
