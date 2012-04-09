package me.bcap.number.operation;

import java.math.BigDecimal;
import java.math.RoundingMode;

import me.bcap.number.intf.Calculation;
import me.bcap.number.intf.VarDef;

public class Round implements Operation {

	private static final long serialVersionUID = 1L;

	Calculation<?> scale;
	RoundingMode rounding;

	public Round(Calculation<?> scale, RoundingMode rounding) {
		this.scale = scale;
		this.rounding = rounding;
	}

	public BigDecimal execute(BigDecimal acummulatedCalculation, VarDef... vars) {
		BigDecimal calculatedScale = scale.calculate(vars);
		if (calculatedScale.scale() > 0)
			calculatedScale = calculatedScale.setScale(0, RoundingMode.HALF_EVEN);
		return acummulatedCalculation.setScale(calculatedScale.intValue(), rounding);
	}

}
