/**
* @file G181210041
* @description 4 Seviyeli Oncelikli Gorevlendirici
* @course 2.Ogretim C
* @assignment Donem Sonu Proje Calismasi
* @date 02-01-2023  &  08-01-2023
* @author Doğukan Berk Kaya dogukan.kaya1@ogr.sakarya.edu.tr
*/

package pkt;

public class OncelikliKuyrukIsleyici {
	
	Kuyruk seviye1;
	Kuyruk seviye2;
	Kuyruk seviye3;
	Kuyruk seviyeRR;
	
	
	OncelikliKuyrukIsleyici(Kuyruk _seviye1, Kuyruk _seviye2, Kuyruk _seviye3, Kuyruk _seviyeRR){
		
		seviye1 = _seviye1;
		seviye2 = _seviye2;
		seviye3 = _seviye3;
		seviyeRR = _seviyeRR;
		
	}

	
	PCB IslenecekSecimi(Kuyruk islenecekKuyruk,double timer) {
		
		PCB pcb=null;
		
		if(islenecekKuyruk.KuyrukBoyu()==0) //Kuyrukta eleman var mı kontrol eder
		{
			System.out.println("Gonderilen ara seviye kuyruk bos.");
		}
		else //eleman varsa
		{
		
				pcb = islenecekKuyruk.ProcessKuyrugu.get(0); //kuyruktaki ilk eleman secilir
				//optimizasyon icin dusuk process zamanli processler kuyruk baslarina alinarak
				//daha hizli calisiyor algisi yaratilabilir
				
		}
		
		return pcb;
		
	}
	
	
}
