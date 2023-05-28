package bariss26.degertutucu;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Consumer;

/**
 * @author BarisKOKULU
 *
 * Belirli deger degistiginde degeri hedefe gonderen yardimci.
 */
public abstract class DegerBaglayici<T> implements DegerDegistiDinleyicisi<T> {

	DegerTutucu<T> dan;
	
	public static class DegerdenConsumeraBaglayici<T> extends DegerBaglayici<T> {

		Consumer<DegerTutucu<T>> a;

		public DegerdenConsumeraBaglayici(DegerTutucu<T> dan, Consumer<DegerTutucu<T>> a) {
			this.dan = dan;
			this.a = a;
			dan.degerDegistiDinleyicisi(this);
			a.accept(dan);
		}
		
		@Override
		public T degerDegisti(T eskideger, T yenideger) {
			a.accept(dan);
			return yenideger;
		}
		
	}
	
	public static class DegerdenMetodaBaglayici<T> extends DegerBaglayici<T> {
		
		Object n;
		Method m;
		
		/**
		 * Deger turu T olmak uzere, belirtilen nesnenin <belirtilen metod adi>(T) metodunu deger parametresiyle cagirir.
		 */
		public DegerdenMetodaBaglayici(DegerTutucu<T> dan, Object nesne, String metod) {
			this.dan = dan;
			this.n = nesne;
			try {
				this.m = nesne.getClass().getMethod(metod, dan.getClass());
				dan.degerDegistiDinleyicisi(this);
				m.invoke(n, dan.al());
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public T degerDegisti(T eskideger, T yenideger) {
			try {
				m.invoke(n, yenideger);
				return yenideger;
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
			return yenideger;
		}

	}
	
	public static class DegerdenDegereBaglayici<T> extends DegerBaglayici<T> {

		DegerTutucu<T> a;

		public DegerdenDegereBaglayici(DegerTutucu<T> dan, DegerTutucu<T> a) {
			this.dan = dan;
			this.a = a;
			dan.degerDegistiDinleyicisi(this);
			a.ata(dan.al());
		}
		
		@Override
		public T degerDegisti(T eskideger, T yenideger) {
			a.ata(yenideger);
			return yenideger;
		}
		
	}
	
	
	
}
