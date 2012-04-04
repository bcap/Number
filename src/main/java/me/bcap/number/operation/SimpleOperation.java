package me.bcap.number.operation;

import me.bcap.number.intf.Calculation;

public abstract class SimpleOperation implements Operation {

	protected final Calculation<?> calculation;

	public SimpleOperation(Calculation<?> calculation) {
		this.calculation = calculation;
	}

	public Calculation<?> getCalculation() {
		return this.calculation;
	}
}
