package me.bcap.number.operation;

import java.io.Serializable;
import java.math.BigDecimal;

import me.bcap.number.intf.VarDef;

public interface Operation extends Serializable {

	public BigDecimal execute(BigDecimal acummulatedCalculation, VarDef... vars);
}
