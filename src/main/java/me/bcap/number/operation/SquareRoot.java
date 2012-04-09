package me.bcap.number.operation;

import java.math.BigDecimal;

import me.bcap.number.Formula;
import me.bcap.number.Var;
import me.bcap.number.intf.VarDef;

public class SquareRoot implements Operation {

	private static final long serialVersionUID = 1L;

	private BigDecimal precisionComparator;
	private Formula formula;
	
	public SquareRoot(int precision) {
		this.formula = new Formula("y").withScale(precision).plus(new Formula("x").withScale(precision).dividedBy("y")).dividedBy(2);
		this.precisionComparator = constructPrecisonComparator(precision);
	}

	private BigDecimal constructPrecisonComparator(int precision) {
		Formula formula = new Formula("x").withScale(precision).dividedBy(10);
		BigDecimal result = BigDecimal.ONE;
		for(int i = 0; i < precision; i++)
			result = formula.calculate(new Var("x", result));
		return result;
	}

	public BigDecimal execute(BigDecimal acummulatedCalculation, VarDef... vars) {
		
		BigDecimal result = acummulatedCalculation.divide(new BigDecimal(2));
		BigDecimal lastCalculation = BigDecimal.ZERO;
		
		while(result.subtract(lastCalculation).abs().compareTo(precisionComparator) > 0) {
			lastCalculation = result;
			result = formula.calculate(new Var("x", acummulatedCalculation), new Var("y", lastCalculation));
		}
		
		return result;
	}

}
