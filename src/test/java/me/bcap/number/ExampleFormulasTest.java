package me.bcap.number;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class ExampleFormulasTest {

	@Test
	public void calculateSquareRoot() {
		Formula formula = new Formula("y").withScale(500).plus(new Formula("x").withScale(500).dividedBy("y")).dividedBy(2);
		//calculating the sqrt of 9 by using 9/2 as sample
		
		int rootOf = 2;
		
		BigDecimal result = new Formula(rootOf).dividedBy(2).calculate();
		BigDecimal lastCalculation = BigDecimal.ZERO;
		while(result.subtract(lastCalculation).abs().compareTo(new BigDecimal("0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001")) > 0) {
			lastCalculation = result;
			result = formula.calculate(new Var("x", rootOf), new Var("y", lastCalculation));
			System.out.println(result);
		}
	}
}
