package me.bcap.number;

import java.math.BigDecimal;

import me.bcap.number.intf.VarDef;

class VariableReplace extends AbstractCalculation {

	private static final long serialVersionUID = 1L;

	private String varName;
	
	public VariableReplace(String varName) {
		this.varName = varName;
	}
	
	public BigDecimal calculate(VarDef... vars) {
		return new BigDecimal(findVarValue(vars).toString());
	}

	private Number findVarValue(VarDef... vars) {
		for (VarDef varDef : vars) {
			String iterableName = varDef.getName();
			if(iterableName != null && iterableName.equals(varName))
				return varDef.getValue();
		}
		throw new ArithmeticException("Variable " + varName + " not defined");
	}

}
