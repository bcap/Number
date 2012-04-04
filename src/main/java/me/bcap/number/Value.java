package me.bcap.number;

import java.math.BigDecimal;

import me.bcap.number.intf.VarDef;

class Value extends AbstractCalculation {

	private static final long serialVersionUID = 1L;

	protected Number value;

	public Value(Number value) {
		this.value = value;
	}

	public BigDecimal calculate(VarDef... vars) {
		return NumberTool.toBigDecimal(value);
	}

}
