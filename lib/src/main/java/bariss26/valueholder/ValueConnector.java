package bariss26.valueholder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Consumer;

/**
 * @author BarisKOKULU
 *
 *  Helper that assigns the value to connected destination as the value changes.
 */
public abstract class ValueConnector<T> implements ValueChangedListener<T> {

	ValueHolder<T> from;
	
	public static class ValueToConsumerConnector<T> extends ValueConnector<T> {

		Consumer<ValueHolder<T>> to;

		public ValueToConsumerConnector(ValueHolder<T> from, Consumer<ValueHolder<T>> to) {
			this.from = from;
			this.to = to;
			from.addValueChangedListener(this);
			to.accept(from);
		}
		
		@Override
		public T valueChanged(T oldValue, T newValue) {
			to.accept(from);
			return newValue;
		}
		
	}
	
	public static class ValueToMethodConnector<T> extends ValueConnector<T> {
		
		Object n;
		Method m;
		
		/**
		 * Deger turu T olmak uzere, belirtilen nesnenin <belirtilen metod adi>(T) metodunu deger parametresiyle cagirir.
		 */
		public ValueToMethodConnector(ValueHolder<T> from, Object object, String method) {
			this.from = from;
			this.n = object;
			try {
				this.m = object.getClass().getMethod(method, from.getClass());
				from.addValueChangedListener(this);
				m.invoke(n, from.get());
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public T valueChanged(T oldValue, T newValue) {
			try {
				m.invoke(n, newValue);
				return newValue;
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
			return newValue;
		}

	}
	
	public static class ValueToValueConnector<T> extends ValueConnector<T> {

		ValueHolder<T> to;

		public ValueToValueConnector(ValueHolder<T> from, ValueHolder<T> to) {
			this.from = from;
			this.to = to;
			from.addValueChangedListener(this);
			to.assign(from.get());
		}
		
		@Override
		public T valueChanged(T oldValue, T newValue) {
			to.assign(newValue);
			return newValue;
		}
		
	}
	
	
	
}
