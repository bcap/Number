package me.bcap.number;

import java.math.BigDecimal;

import me.bcap.number.intf.VarDef;
import me.bcap.number.tool.NumberTool;

class Value extends AbstractCalculation<Value> {

	private static final long serialVersionUID = 1L;

	protected Number value;

	public Value(Number value) {
		this.value = value;
	}

	public BigDecimal calculate(VarDef... vars) {
		return NumberTool.toBigDecimal(value);
	}

}
