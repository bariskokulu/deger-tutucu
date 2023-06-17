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

	public int getInt() {
		return (int)value;
	}

	public double getDouble() {
		return (double)value;
	}

	public float getFloat() {
		return (float)value;
	}

	public long getLong() {
		return (long)value;
	}

	public short getShort() {
		return (short)value;
	}

	public byte getByte() {
		return (byte)value;
	}

	public boolean getBoolean() {
		return (boolean)value;
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
