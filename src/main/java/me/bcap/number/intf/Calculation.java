package me.bcap.number.intf;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Set;

public interface Calculation<T extends Calculation<?>> extends Serializable {

	public Set<String> getVariables();
	
	public BigDecimal calculate(VarDef... vars);

	public BigDecimal calculate(int scale, RoundingMode roundingMode, VarDef... vars);
	
	public BigDecimal calculate(Collection<VarDef> vars);

	public BigDecimal calculate(int scale, RoundingMode roundingMode, Collection<VarDef> vars);

}
