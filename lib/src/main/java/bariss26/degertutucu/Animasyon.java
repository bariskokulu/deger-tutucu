package bariss26.degertutucu;

import java.util.function.Function;

public class Animasyon<T> {

	Object animeci;
	DegerTutucu<T> deger;
	DegerTutucu<T> hedef;
	Function<Animasyon<T>, T> araDegerBulmaYontemi, atamadanOnce, atamadanSonra;
	long sonetkilesim, maxUzunluk;

	Animasyon(Object animeci, DegerTutucu<T> deger, long maxUzunluk) {
		this.animeci = animeci;
		this.hedef = new DegerTutucu<T>((this.deger = deger).al());
		this.maxUzunluk = maxUzunluk;
		this.sonetkilesim = System.currentTimeMillis();
	}
	
	/**
	 * Ara degeri atama oncesi hesaplayan fonksiyonu belirler.
	 * 
	 * @param araDegerBulmaYontemi Ara degeri hesaplayan fonksiyon.
	 * @return Animasyon.
	 */
	public Animasyon<T> araDegerBulmaYontemi(Function<Animasyon<T>, T> araDegerBulmaYontemi) {
		this.araDegerBulmaYontemi = araDegerBulmaYontemi;
		return this;
	}

	/**
	 * Ara deger atamadan once cagrilacak fonksiyonu belirler.
	 * 
	 * @param araDegerBulmaYontemi Ara deger atamadan once cagrilacak fonksiyon.
	 * @return Animasyon.
	 */
	public Animasyon<T> atamadanOnce(Function<Animasyon<T>, T> atamadanOnce) {
		this.atamadanOnce = atamadanOnce;
		return this;
	}

	/**
	 * Ara deger atamadan sonra cagrilacak fonksiyonu belirler.
	 * 
	 * @param araDegerBulmaYontemi Ara deger atamadan sonra cagrilacak fonksiyon.
	 * @return Animasyon.
	 */
	public Animasyon<T> atamadanSonra(Function<Animasyon<T>, T> atamadanSonra) {
		this.atamadanSonra = atamadanSonra;
		return this;
	}

	/**
	 * Animasyonun degeri goturecegi hedef degeri belirler.
	 * 
	 * @param <T> Hedef deger.
	 */
	public void nisanAl(T o) {
		sonetkilesim = System.currentTimeMillis();
		hedef.ata(o);
	}

	
	/*
	 * 
	 * Getter setterlar.
	 * 
	 */
	
	
	public Object animeciyiAl() {
		return animeci;
	}

	public void animeciyiAta(Object animeci) {
		this.animeci = animeci;
	}

	public DegerTutucu<T> degerTutucuyuAl() {
		return deger;
	}

	public void degerTutucuyuAta(DegerTutucu<T> deger) {
		this.deger = deger;
	}

	public DegerTutucu<T> hedefDegerTutucuyuAl() {
		return hedef;
	}

	public void hedefDegerTutucuyuAta(DegerTutucu<T> hedef) {
		this.hedef = hedef;
	}

	public long maxUzunluguAl() {
		return maxUzunluk;
	}

	public void maxUzunluguAta(long maxUzunluk) {
		this.maxUzunluk = maxUzunluk;
	}
	
	
	/*
	 * 
	 * Animasyon bakaninin kullandigi fonksiyonlar.
	 * 
	 */

	T atamadanOnce() {
		return atamadanOnce!=null?atamadanOnce.apply(this):null;
	}
	
	T araDegeriAta(T araDeger) {
		return deger.ata(araDeger);
	}
	
	T araDeger() {
		return araDegerBulmaYontemi.apply(this);
	}
	
	T atamadanSonra() {
		return atamadanSonra!=null?atamadanSonra.apply(this):null;
	}
	
}
