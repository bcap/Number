package me.bcap.number;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import me.bcap.number.intf.AritmethicsCalculation;
import me.bcap.number.intf.Calculation;
import me.bcap.number.intf.VarDef;
import me.bcap.number.operation.Addition;
import me.bcap.number.operation.Division;
import me.bcap.number.operation.Multiplication;
import me.bcap.number.operation.Operation;
import me.bcap.number.operation.Power;
import me.bcap.number.operation.Substraction;

public class Formula extends AbstractCalculation<Formula> implements AritmethicsCalculation<Formula> {

	// statics 
	private static final long serialVersionUID = 1L;
	
	public static final int DECIMAL128_SCALE = MathContext.DECIMAL128.getPrecision();
	
	public static final int DECIMAL64_SCALE = MathContext.DECIMAL64.getPrecision();
	
	public static final int DECIMAL32_SCALE = MathContext.DECIMAL32.getPrecision();
	
	public static int DEFAULT_SCALE = DECIMAL128_SCALE;

	public static RoundingMode DEFAULT_DIVISION_ROUNDING = RoundingMode.HALF_EVEN;

	public static int getDefaultScaleForNewFormulas() {
		return DEFAULT_SCALE;
	}
	
	public static void setDefaultScaleForNewFormulas(int scale) {
		DEFAULT_SCALE = scale;
	}
	
	public static RoundingMode getDefaultDivisionRoundingForNewFormulas() {
		return DEFAULT_DIVISION_ROUNDING;
	}
	
	public static void gsetDefaultDivisionRoundingForNewFormulas(RoundingMode roundingMode) {
		DEFAULT_DIVISION_ROUNDING = roundingMode;
	}
	
	// object attributes 
	private Calculation<?> calculation;

	private int scale = DEFAULT_SCALE;

	private RoundingMode divisionRounding = DEFAULT_DIVISION_ROUNDING;
	
	// constructors
	public Formula() {
		this(new Value(BigDecimal.ZERO), DEFAULT_SCALE, DEFAULT_DIVISION_ROUNDING);
	}
	
	public Formula(int scale, RoundingMode divisionRounding) {
		this(new Value(BigDecimal.ZERO), scale, divisionRounding);
	}

	public Formula(String var) {
		this(var, DEFAULT_SCALE, DEFAULT_DIVISION_ROUNDING);
	}

	public Formula(Number value) {
		this(value, DEFAULT_SCALE, DEFAULT_DIVISION_ROUNDING);
	}

	public Formula(Calculation<?> calculation) {
		this(calculation, DEFAULT_SCALE, DEFAULT_DIVISION_ROUNDING);
	}
	
	public Formula(String var, int scale, RoundingMode divisionRounding) {
		this.calculation = new VariableReplace(var);
		initScaleAndRounding(scale, divisionRounding);
	}

	public Formula(Number value, int scale, RoundingMode divisionRounding) {
		this.calculation = new Value(value);
		initScaleAndRounding(scale, divisionRounding);
	}

	public Formula(Calculation<?> calculation, int scale, RoundingMode divisionRounding) {
		this.calculation = calculation;
		initScaleAndRounding(scale, divisionRounding);
	}
	
	// private methods
	private void initScaleAndRounding(int scale, RoundingMode divisionRounding) { 
		this.scale = scale;
		this.divisionRounding = divisionRounding;
	}
	
	//public methods
	public Formula withDivisionRounding(RoundingMode rounding) {
		this.divisionRounding = rounding;
		return this;
	}
	
	public Formula withScale(int scale) {
		this.scale = scale;
		return this;
	}
	
	public RoundingMode getDivisionRounding() {
		return divisionRounding;
	}

	public int getScale() {
		return scale;
	}
	
	public BigDecimal calculate(VarDef... vars) {
		BigDecimal number = calculation.calculate(vars);

		for (Operation operation : operations)
			number = operation.execute(number, vars);

		return cleanScale(number);
	}

	private BigDecimal cleanScale(BigDecimal number) {
		number = number.stripTrailingZeros();
		if(number.scale() < 0)
			number = number.setScale(0);
		return number;
	}

	public Formula plus(Calculation<?> calculation) {
		operations.add(new Addition(calculation));
		return this;
	}

	public Formula plus(Number number) {
		operations.add(new Addition(new Value(number)));
		return this;
	}

	public Formula plus(String variable) {
		operations.add(new Addition(new VariableReplace(variable)));
		return this;
	}

	public Formula minus(Calculation<?> calculation) {
		operations.add(new Substraction(calculation));
		return this;
	}

	public Formula minus(Number number) {
		operations.add(new Substraction(new Value(number)));
		return this;
	}

	public Formula minus(String variable) {
		operations.add(new Substraction(new VariableReplace(variable)));
		return this;
	}

	public Formula times(Calculation<?> calculation) {
		operations.add(new Multiplication(calculation));
		return this;
	}

	public Formula times(Number number) {
		operations.add(new Multiplication(new Value(number)));
		return this;
	}

	public Formula times(String variable) {
		operations.add(new Multiplication(new VariableReplace(variable)));
		return this;
	}

	public Formula dividedBy(Calculation<?> calculation) {
		return dividedBy(calculation, scale, divisionRounding);
	}

	public Formula dividedBy(Number number) {
		return dividedBy(number, scale, divisionRounding);
	}

	public Formula dividedBy(String variable) {
		return dividedBy(variable, scale, divisionRounding);
	}

	public Formula dividedBy(Calculation<?> calculation, int scale, RoundingMode rounding) {
		operations.add(new Division(calculation, scale, divisionRounding));
		return this;
	}

	public Formula dividedBy(Number number, int scale, RoundingMode rounding) {
		operations.add(new Division(new Value(number), scale, divisionRounding));
		return this;
	}

	public Formula dividedBy(String variable, int scale, RoundingMode rounding) {
		operations.add(new Division(new VariableReplace(variable), scale, divisionRounding));
		return this;
	}

	public Formula poweredBy(Calculation<?> calculation) {
		operations.add(new Power(calculation));
		return this;
	}

	public Formula poweredBy(Number number) {
		operations.add(new Power(new Value(number)));
		return this;
	}

	public Formula poweredBy(String variable) {
		operations.add(new Power(new VariableReplace(variable)));
		return this;
	}
}
