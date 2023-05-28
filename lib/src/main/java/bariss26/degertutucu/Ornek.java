package bariss26.degertutucu;

import java.util.function.Function;

public class Ornek {


	public static void main(String[] args) {
		// animasyonlar saniyede 60 kere yenilenecek şekilde yenileyiciyi baslatiyoruz
		AnimasyonBakani.firla(60L);


		// deger tutucusuna 0 atiyoruz
		DegerTutucu<Integer> deger = new DegerTutucu<Integer>(0);


		// son etkilesimden itibaren 1 saniye boyunca yenilenecek sekilde animasyon nesnemizi olusturuyoruz
		Animasyon<Integer> animasyon = AnimasyonBakani.yeniAnimasyon(null, deger, 1000L);
		
		// animasyonun su anki degerden hedef degere giderken izleyecegi yolu soyluyoruz
		animasyon.araDegerBulmaYontemi(new Function<Animasyon<Integer>, Integer>() {
			@Override
			public Integer apply(Animasyon<Integer> t) {
				// degeri onar onar degistir
				return t.deger.al()+(int) Math.signum(t.hedef.al()-t.deger.al())*10;
			}
		});
		
		// animasyona ilk hedef degerini belirtiyoruz
		animasyon.nisanAl(100);
		
		// sinira ulastiysa geri donmesi icin nisan aliyoruz
		animasyon.atamadanSonra(new Function<Animasyon<Integer>, Integer>() {
			@Override
			public Integer apply(Animasyon<Integer> t) {
				System.out.println(t.deger.al());
				if(t.deger.al()>=100) {
					t.nisanAl(0);
				} else if(t.deger.al()<=0) {
					t.nisanAl(100);
				}
				return 0;
			}
		});
		
	}

}
