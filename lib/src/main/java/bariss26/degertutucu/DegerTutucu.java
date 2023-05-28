package bariss26.degertutucu;

import java.util.ArrayList;


/**
 * @author BarisKOKULU
 *
 *	Pointer lazim.
 *
 * @param <T> Tutulan deger turu.
 */
public class DegerTutucu<T> {

	private T deger;
	private ArrayList<DegerDegistiDinleyicisi<T>> dinleyiciler = new ArrayList<>(0);

	private DegerTutucu() {

	}

	public DegerTutucu(T deger) {
		ata(deger);
	}

	public T ata(T deger) {
		for(DegerDegistiDinleyicisi<T> d : dinleyiciler) d.degerDegisti(this.deger, deger);
		return this.deger = deger;
	}

	public T al() {
		return deger;
	}

	public String stringAl() {
		return (String)deger;
	}

	public int intAl() {
		return (int)deger;
	}

	public double doubleAl() {
		return (double)deger;
	}

	public float floatAl() {
		return (float)deger;
	}

	public long longAl() {
		return (long)deger;
	}

	public short shortAl() {
		return (short)deger;
	}

	public byte byteAl() {
		return (byte)deger;
	}

	public boolean booleanAl() {
		return (boolean)deger;
	}


	/**
	 *	Deger degisince bildirilecek yeni bi dinleyici kaydeder.
	 * 
	 * @param degerDegistiDinleyicisi Dinleyici.
	 */
	public void degerDegistiDinleyicisi(DegerDegistiDinleyicisi<T> degerDegistiDinleyicisi) {
		dinleyiciler.add(degerDegistiDinleyicisi);
	}

}
