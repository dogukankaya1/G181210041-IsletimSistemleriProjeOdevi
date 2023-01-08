/**
* @file G181210041
* @description 4 Seviyeli Oncelikli Gorevlendirici
* @course 2.Ogretim C
* @assignment Donem Sonu Proje Calismasi
* @date 02-01-2023  &  08-01-2023
* @author Doğukan Berk Kaya dogukan.kaya1@ogr.sakarya.edu.tr
*/

package pkt;

public class PCB {
	
	public static final String[] renkDizi = {"\u001B[31m","\u001B[32m",
			"\u001B[33m","\u001B[34m","\u001B[35m","\u001B[36m"}; //6 rengi ANSI olarak barındıran dizi
	
	public static final String ANSI_RESET = "\033[0m"; //Yazı rengini sıfırlamak için kullanılacak
	
	public int pid = 0; //process ismi/id'si
	public int oncelik = 0; //process onceligi 0-5 arasi olabilir
	public int totalZaman = 0; //processin tamamlanması için gerekli total süre
	public int kalanZaman = 0; //processin tamamlanması için gerekli kalan süre
	public int islemZamani = 0; //processin ne kadar süre islendiğini gösteren sayaç
	public int varisZamani = 0; //process varış zamanı
	public String yaziRengi = ""; //process bilgisi ekrana basılırken kullanılacak renk
	public String durum = "basladi"; //process işlendikçe kalanZaman kontrol edilip
	//durum "bitti" ya da "bitmedi" olarak değiştirilir
	
	public int atanmisQ = 0; //processe ayrılmıs (daha yuksek oncelik gelmedigi surece) isleyecek sure 
	
	
	PCB(int _pid,int _varisZamani,int _oncelik,int _totalZaman) 
	//yeni PCB oluşturur (dosyadan okuyarak oluşturmak için)
	{
		pid=_pid;
		varisZamani=_varisZamani;
		oncelik=_oncelik;
		totalZaman=_totalZaman;
		kalanZaman=_totalZaman;
		//islemZamani=0;
		yaziRengi = renkDizi[(pid%6)]; //0,1,2,3,4,5 olarak bir ANSI renk sececek
	}

	PCB(int _pid,int _varisZamani,int _oncelik,int _totalZaman,int _kalanZaman,int _islemZamani) 
	//zaten bir miktar işlenmiş PCB oluşturur (dosyadan okumadan oluşturmak için)
	{
		pid=_pid;
		varisZamani=_varisZamani;
		oncelik=_oncelik;
		totalZaman=_totalZaman;
		kalanZaman=_kalanZaman;
		islemZamani=_islemZamani;
		yaziRengi = renkDizi[(pid%6)];
	}
	
	
	boolean bittimi() { //Process'in daha işleme ihtiyacı var mı kontrol eder
		if(kalanZaman==0 || kalanZaman<0)
		{
			durum="sonlandi";
			return true;
		}
			
		else if(kalanZaman == totalZaman)
		{
			durum="basladi";
			return false;
		}
		
		else if (( this.islemZamani > 0 ) && (islemZamani<totalZaman))
		{
			durum = "yurutuluyor";
			return false;
		}
		else
			return false;
			
	}
	
	void durumKontrol() {
		
		if(kalanZaman == 0)
		{
			durum = "sonlandi.";
		}
		else if(kalanZaman == totalZaman)
		{
			durum = "basladi.";
		}
		else if (( this.islemZamani > 0 ) && (islemZamani<totalZaman))
		{
			durum = "yurutuluyor.";
		}
	}
	
	
}
