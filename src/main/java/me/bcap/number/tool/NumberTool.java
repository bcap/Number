package me.bcap.number.tool;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class NumberTool {
	
	public static BigDecimal toBigDecimal(Number number) {
		if (number instanceof Byte)
			return new BigDecimal((Byte) number);
		else if (number instanceof Short)
			return new BigDecimal((Short) number);
		else if (number instanceof Integer)
			return new BigDecimal((Integer) number);
		else if (number instanceof Long)
			return new BigDecimal((Long) number);
		else if (number instanceof Float)
			return BigDecimal.valueOf((Float)number);
		else if (number instanceof Double)
			return BigDecimal.valueOf((Double)number);
		else if(number instanceof BigDecimal)
			return (BigDecimal)number;
		else if (number instanceof BigInteger) 
			return new BigDecimal((BigInteger) number);
		else if (number instanceof AtomicInteger)
			return new BigDecimal(((AtomicInteger)number).get());
		else if (number instanceof AtomicLong)
			return new BigDecimal(((AtomicLong)number).get());
		else
			return new BigDecimal(number.toString());
	}
	
	public static BigInteger toClosestBigInteger(Number number) { 
		return toBigDecimal(number).setScale(0, RoundingMode.HALF_EVEN).toBigIntegerExact();
	}
	
	public static Integer toClosestInteger(Number number) {
		return toClosestBigInteger(number).intValue();
	}
	
	public static Long toClosestLong(Number number) {
		return toClosestBigInteger(number).longValue();
	}
	
}
