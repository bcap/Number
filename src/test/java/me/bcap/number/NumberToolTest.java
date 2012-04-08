package me.bcap.number;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import me.bcap.number.tool.NumberTool;

import org.junit.Test;

public class NumberToolTest {

	@Test
	public void testToBigDecimal() {
		assertThat(NumberTool.toBigDecimal((byte)123), is(new BigDecimal(123)));
		assertThat(NumberTool.toBigDecimal((short)123), is(new BigDecimal(123)));
		assertThat(NumberTool.toBigDecimal((int)123), is(new BigDecimal(123)));
		assertThat(NumberTool.toBigDecimal((long)123), is(new BigDecimal(123)));
		assertThat(NumberTool.toBigDecimal((float)123.4).setScale(1, RoundingMode.HALF_EVEN), is(new BigDecimal("123.4")));
		assertThat(NumberTool.toBigDecimal((double)123.4).setScale(1, RoundingMode.HALF_EVEN), is(new BigDecimal("123.4")));
		assertThat(NumberTool.toBigDecimal(new BigInteger("123")), is(new BigDecimal(123)));
		assertThat(NumberTool.toBigDecimal(new BigDecimal("123")), is(new BigDecimal(123)));
		assertThat(NumberTool.toBigDecimal(new AtomicInteger(123)), is(new BigDecimal(123)));
		assertThat(NumberTool.toBigDecimal(new AtomicLong(123)), is(new BigDecimal(123)));
	}
}
