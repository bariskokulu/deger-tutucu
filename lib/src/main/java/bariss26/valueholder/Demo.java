package bariss26.valueholder;

import java.util.function.Function;

public class Demo {


	public static void main(String[] args) {
		// starting the system in a way that it will refresh animations 60 times every second
		AnimationHandler.launch(60L);


		// ve assign 0 to value
		ValueHolder<Integer> value = new ValueHolder<Integer>(0);


		// creating our animation object which will be kept alive for a second after its last interaction
		Animation<Integer> animasyon = AnimationHandler.newAnimation(null, value, 1000L);

		// setting the interpolator which will calculate the new value
		animasyon.setInterpolator(new Function<Animation<Integer>, Integer>() {
			@Override
			public Integer apply(Animation<Integer> t) {
				
				// increase the value ten by ten
				return t.value.get()+(int) Math.signum(t.destination.get()-t.value.get())*10;
			}
		});

		// we set aim at the destination
		animasyon.aimAt(100);

		// turn back if you reached the bound
		animasyon.afterAssignment(new Function<Animation<Integer>, Integer>() {
			@Override
			public Integer apply(Animation<Integer> t) {
				System.out.println(t.value.get());
				if(t.value.get()>=100) {
					t.aimAt(0);
				} else if(t.value.get()<=0) {
					t.aimAt(100);
				}
				return 0;
			}
		});

	}

}
