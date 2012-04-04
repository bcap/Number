package me.bcap.number.operation;

import java.math.BigDecimal;

import me.bcap.number.intf.VarDef;

public interface Operation {

	public BigDecimal execute(BigDecimal acummulatedCalculation, VarDef... vars);
}
