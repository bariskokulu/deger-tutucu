package bariss26.valueholder;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author BarisKOKULU
 *
 */
public class AnimationHandler {

	/**
	 * All animations.
	 */
	public static ArrayList<Animation> animations = new ArrayList<Animation>();
	public static ArrayList<AnimationHandlerLoopListener> listeners = new ArrayList<AnimationHandlerLoopListener>();
	static AnimationHandler INSTANCE;
	static Logger log = Logger.getLogger("Animation Handler");
	static boolean ended;


	/**
	 * Starts up the system.
	 * 
	 * @param refreshRate Count of refreshes per second.
	 */
	public static void launch(long refreshRate) {
		Timer t;
		(t = new Timer("anime")).schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					if(ended) {
						animations.clear();
						listeners.clear();
						t.cancel();
					}
					for(Animation a : animations) {
						if(a.lastinteraction+a.maxDuration>=System.currentTimeMillis()) {
							a.beforeAssignment();
							a.assignInterpolated(a.interpolated());
							a.afterAssignment();
						}
					}
					for(AnimationHandlerLoopListener d : listeners) d.loop();
				} catch (Exception e) {
					log.log(Level.FINE, e.getMessage(), e);
				}
			}
		}, 0, 1000L/refreshRate);
	}


	/**
	 * Constructs a new animation and registers it, making it ready to start.
	 *
	 * @param weeb The object that owns the value.
	 * @param value	The value that will be played around by the animation.
	 * @param duration Amount of milliseconds the animation will be kept alive after the last interaction/aim.
	 * @return
	 */
	public static <T> Animation<T> newAnimation(Object weeb, ValueHolder<T> value, long duration) {
		Animation<T> an = new Animation<T>(weeb, value, duration);
		animations.add(an);
		return an;
	}

	
	/**
	 *  Stops the handler.
	 */
	public void end() {
		ended = true;
	}
	
	
	/*
	 * 
	 * 		Currently useless from down here.
	 * 
	 */
	
	
	public static double cos90(double d) {
		return Math.signum(d)*(1-Math.cos(d*Math.PI*0.5D));
	}

	public static double cos(double d, double a1, double a2) {
		return Math.cos((a1+(a2-a1)*d)*Math.PI*0.5D);
	}

	public static double sin90(double d) {
		return Math.sin(d*Math.PI*0.5D);
	}

	public static double cos180(double d) {
		return Math.signum(d)*(1-(Math.cos(d*Math.PI)+1)/2.0D);
	}

}
