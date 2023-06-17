package bariss26.valueholder;

import java.util.function.Function;

public class Animation<T> {

	Object weeb;
	ValueHolder<T> value;
	ValueHolder<T> destination;
	Function<Animation<T>, T> interpolator, beforeAssignment, afterAssignment;
	long lastinteraction, maxDuration;

	Animation(Object weeb, ValueHolder<T> value, long maxDuration) {
		this.weeb = weeb;
		this.destination = new ValueHolder<T>((this.value = value).get());
		this.maxDuration = maxDuration;
		this.lastinteraction = System.currentTimeMillis();
	}
	
	/**
	 * Assigns the function which is used for interpolating between the current value and the destination value.
	 * 
	 * @param interpolator The function.
	 * @return The animation itself.
	 */
	public Animation<T> setInterpolator(Function<Animation<T>, T> interpolator) {
		this.interpolator = interpolator;
		return this;
	}

	/**
	 * Registers a new listener which will be called before the interpolated value is assigned.
	 * 
	 * @param beforeAssignment The function.
	 * @return The animation itself.
	 */
	public Animation<T> assignmadanOnce(Function<Animation<T>, T> beforeAssignment) {
		this.beforeAssignment = beforeAssignment;
		return this;
	}

	/**
	 * Registers a new listener which will be called after the interpolated value is assigned.
	 * 
	 * @param araDegerBulmaYontemi Ara deger assignmadan sonra cagrilacak fonksiyon.
	 * @return The animation itself.
	 */
	public Animation<T> afterAssignment(Function<Animation<T>, T> afterAssignment) {
		this.afterAssignment = afterAssignment;
		return this;
	}

	/**
	 * Specifies the destination value.
	 * 
	 * @param <T> destination value.
	 */
	public void aimAt(T o) {
		lastinteraction = System.currentTimeMillis();
		destination.assign(o);
	}

	
	/*
	 * 
	 * Getters setters.
	 * 
	 */
	


	public Object getWeeb() {
		return weeb;
	}

	public void setWeeb(Object weeb) {
		this.weeb = weeb;
	}

	public ValueHolder<T> getValue() {
		return value;
	}

	public void setValue(ValueHolder<T> value) {
		this.value = value;
	}

	public ValueHolder<T> getDestination() {
		return destination;
	}

	public void setDestination(ValueHolder<T> destination) {
		this.destination = destination;
	}

	public Function<Animation<T>, T> getBeforeAssignment() {
		return beforeAssignment;
	}

	public void setBeforeAssignment(Function<Animation<T>, T> beforeAssignment) {
		this.beforeAssignment = beforeAssignment;
	}

	public Function<Animation<T>, T> getAfterAssignment() {
		return afterAssignment;
	}

	public void setAfterAssignment(Function<Animation<T>, T> afterAssignment) {
		this.afterAssignment = afterAssignment;
	}

	public long getLastinteraction() {
		return lastinteraction;
	}

	public void setLastinteraction(long lastinteraction) {
		this.lastinteraction = lastinteraction;
	}

	public long getMaxDuration() {
		return maxDuration;
	}

	public void setMaxDuration(long maxDuration) {
		this.maxDuration = maxDuration;
	}

	public Function<Animation<T>, T> getInterpolator() {
		return interpolator;
	}
	
	
	
	/*
	 * 
	 * Used by Animation Handler.
	 * 
	 */

	T beforeAssignment() {
		return beforeAssignment!=null?beforeAssignment.apply(this):null;
	}
	
	T assignInterpolated(T araDeger) {
		return value.assign(araDeger);
	}
	
	T interpolated() {
		return interpolator.apply(this);
	}
	
	T afterAssignment() {
		return afterAssignment!=null?afterAssignment.apply(this):null;
	}
	
}
