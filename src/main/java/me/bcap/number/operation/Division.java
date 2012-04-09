package me.bcap.number.operation;

import java.math.BigDecimal;
import java.math.RoundingMode;

import me.bcap.number.intf.Calculation;
import me.bcap.number.intf.VarDef;

public class Division extends SimpleOperation {

	private static final long serialVersionUID = 1L;

	private final int scale;
	private final RoundingMode rounding;

	public Division(Calculation<?> calculation, int scale, RoundingMode rounding) {
		super(calculation);
		this.scale = scale;
		this.rounding = rounding;
	}

	public BigDecimal execute(BigDecimal acumulatedCalculation, VarDef... vars) {
		return acumulatedCalculation.divide(calculation.calculate(vars), scale, rounding);
	}

}
