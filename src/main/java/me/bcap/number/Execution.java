package me.bcap.number;

import me.bcap.number.intf.Calculation;

class Execution {
	protected Operation operation;
	protected Calculation value;

	public Execution(Operation operation, Calculation value) {
		this.operation = operation;
		this.value = value;
	}
}
