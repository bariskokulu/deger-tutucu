package bariss26.valueholder;

import java.util.ArrayList;


/**
 * @author BarisKOKULU
 *
 *	Need pointers.
 *
 * @param <T> Type of the value which is held.
 */
public class ValueHolder<T> {

	private T value;
	private ArrayList<ValueChangedListener<T>> listeners = new ArrayList<>(0);

	private ValueHolder() {

	}

	public ValueHolder(T value) {
		assign(value);
	}

	public T assign(T value) {
		for(ValueChangedListener<T> d : listeners) d.valueChanged(this.value, value);
		return this.value = value;
	}

	public T get() {
		return value;
	}

	public String getString() {
		return (String)value;
	}

	public Integer getInt() {
		return (Integer)value;
	}

	public Double getDouble() {
		return (Double)value;
	}

	public Float getFloat() {
		return (Float)value;
	}

	public Long getLong() {
		return (Long)value;
	}

	public Short getShort() {
		return (Short)value;
	}

	public Byte getByte() {
		return (Byte)value;
	}

	public Boolean getBoolean() {
		return (Boolean)value;
	}


	/**
	 *	Registers a listener that will be notified when the value this holder is holding changes.
	 * 
	 * @param valueChangedListener The listener.
	 */
	public void addValueChangedListener(ValueChangedListener<T> valueChangedListener) {
		listeners.add(valueChangedListener);
	}

}
