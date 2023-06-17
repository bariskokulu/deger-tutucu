package bariss26.valueholder;

public interface ValueChangedListener<T> {
	T valueChanged(T oldValue, T newValue);
}
