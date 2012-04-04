package me.bcap.number.tool;

import java.math.BigDecimal;
import java.math.BigInteger;

public class NumberTool {
	
	public static BigDecimal toBigDecimal(Number number) {
		if(number instanceof BigDecimal)
			return (BigDecimal)number;
		else if (number instanceof BigInteger) 
			return new BigDecimal((BigInteger) number);
		else
			return new BigDecimal(number.toString());
	}
}
