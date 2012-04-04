package me.bcap.number.intf;

public interface AritmethicsCalculation<T extends Calculation<?>> extends Calculation<T> {

	public T plus(Calculation<?> calculation);

	public T plus(Number number);

	public T plus(String variable);

	public T minus(Calculation<?> calculation);

	public T minus(Number number);

	public T minus(String variable);

	public T times(Calculation<?> calculation);

	public T times(Number number);

	public T times(String variable);

	public T dividedBy(Calculation<?> calculation);

	public T dividedBy(Number number);

	public T dividedBy(String variable);
	
//	public T poweredBy(Calculation<?> calculation);
//
//	public T poweredBy(Number number);
//
//	public T poweredBy(String variable);
//	
//	public T root(Calculation<?> calculation);
//
//	public T root(Number number);
//
//	public T root(String variable);

}
