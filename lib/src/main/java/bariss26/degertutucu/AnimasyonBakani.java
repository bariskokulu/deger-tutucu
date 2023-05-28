package bariss26.degertutucu;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author BarisKOKULU
 *
 */
public class AnimasyonBakani {

	/**
	 * Tum animasyonlar.
	 */
	public static ArrayList<Animasyon> animeciler = new ArrayList<Animasyon>();
	public static ArrayList<AnimasyonBakaniDonguDinleyicisi> dinleyiciler = new ArrayList<AnimasyonBakaniDonguDinleyicisi>();
	static AnimasyonBakani kendi;
	static Logger log = Logger.getLogger("Anime Bakani");
	static boolean bitti;


	/**
	 * Sistemi baslatir.
	 * 
	 * @param yenilemeFrekansi Saniyedeki animasyon yenileme sayisi.
	 */
	public static void firla(long yenilemeFrekansi) {
		Timer t;
		(t = new Timer("anime")).schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					if(bitti) {
						animeciler.clear();
						dinleyiciler.clear();
						t.cancel();
					}
					for(Animasyon a : animeciler) {
						if(a.sonetkilesim+a.maxUzunluk>=System.currentTimeMillis()) {
							a.atamadanOnce();
							a.araDegeriAta(a.araDeger());
							a.atamadanSonra();
						}
					}
					for(AnimasyonBakaniDonguDinleyicisi d : dinleyiciler) d.dongu();
				} catch (Exception e) {
					log.log(Level.FINE, e.getMessage(), e);
				}
			}
		}, 0, 1000L/yenilemeFrekansi);
	}


	/**
	 * Yeni bi animasyon olusturur ve yenileme listesine ekler.
	 *
	 * @param animeci Animasyonun degistirecegi degerin sahibi.
	 * @param deger	Animasyonun degistirecegi deger.
	 * @param uzunluk Animasyon nisan aldiktan sonra yenilemeye devam edilecek sure.
	 * @return
	 */
	public static <T> Animasyon<T> yeniAnimasyon(Object animeci, DegerTutucu<T> deger, long uzunluk) {
		Animasyon<T> an = new Animasyon<T>(animeci, deger, uzunluk);
		animeciler.add(an);
		return an;
	}

	
	/**
	 *  Animasyon bakanini kapatip animasyonlari ve dinleyicileri siler.
	 */
	public void bitir() {
		bitti = true;
	}
	
	
	/*
	 * 
	 * 		Burdan asaginin henuz bi vasfi yok.
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
