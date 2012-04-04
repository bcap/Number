package me.bcap.number;

import me.bcap.number.intf.VarDef;

public class Var implements VarDef {

	private String name;
	private Number value;

	public Var(String name, Number value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}

	public Number getValue() {
		return value;
	}

}
