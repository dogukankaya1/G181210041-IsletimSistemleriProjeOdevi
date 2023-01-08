/**
* @file G181210041
* @description 4 Seviyeli Oncelikli Gorevlendirici
* @course 2.Ogretim C
* @assignment Donem Sonu Proje Calismasi
* @date 02-01-2023  &  08-01-2023
* @author Doğukan Berk Kaya dogukan.kaya1@ogr.sakarya.edu.tr
*/

package pkt;

public class FCFS {
	
	Kuyruk islenecekKuyruk;
	
	FCFS(Kuyruk yuksekOncelikliKuyruk ){
		islenecekKuyruk= yuksekOncelikliKuyruk;
	}
	
	
	PCB IslenecekSecimi(double timer) {
		
		PCB pcb=null;
		
		if(islenecekKuyruk.KuyrukBoyu()==0) //Kuyrukta eleman var mı kontrol eder
		{
			System.out.println("FCFS Kuyrugu bos.");
			
		}
		else //eleman varsa
		{
			pcb = islenecekKuyruk.ProcessKuyrugu.get(0);
			pcb.atanmisQ = pcb.totalZaman; //total zamanı bitirene kadar işlenecek
		}
		
		return pcb;
		
	}
	
}
