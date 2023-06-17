package bariss26.valueholder;

public class ValueAndDestination<T> {

	public ValueHolder<T> value;
	public ValueHolder<T> destination;

	public ValueAndDestination(ValueHolder<T> value, ValueHolder<T> destination) {
		this.value = value;
		this.destination = destination;
	}
	
}
