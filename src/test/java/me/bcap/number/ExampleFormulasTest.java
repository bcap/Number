package me.bcap.number;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class ExampleFormulasTest {

	@Test
	public void calculatePythagoreanTheorem() {
		/* Classic pythagorean theorem where a right triangle formed by
		 * 
		 *   |\
		 * b | \ x
		 *   |  \ 
		 *   |___\
		 *     a
		 *     
		 * follows the following formula x^2 = a^2 + b^2
		 */

		Formula a2 = new Formula("a").poweredBy(2);
		Formula b2 = new Formula("b").poweredBy(2);
		Formula x = (new Formula(a2).plus(b2)).squareRoot();
		
		assertThat(x.calculate(new Var("a", 3), new Var("b", 4)), is(new BigDecimal(5)));
		assertThat(x.calculate(new Var("a", 5), new Var("b", 12)), is(new BigDecimal(13)));
		assertThat(x.calculate(new Var("a", 7), new Var("b", 24)), is(new BigDecimal(25)));
	}
}
