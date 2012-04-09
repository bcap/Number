package me.bcap.number.operation;

import me.bcap.number.intf.Calculation;

public abstract class SimpleOperation implements Operation {

	private static final long serialVersionUID = 1L;

	protected final Calculation<?> calculation;

	public SimpleOperation(Calculation<?> calculation) {
		this.calculation = calculation;
	}

	public Calculation<?> getCalculation() {
		return this.calculation;
	}
}
