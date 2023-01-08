/**
* @file G181210041
* @description 4 Seviyeli Oncelikli Gorevlendirici
* @course 2.Ogretim C
* @assignment Donem Sonu Proje Calismasi
* @date 02-01-2023  &  08-01-2023
* @author Doğukan Berk Kaya dogukan.kaya1@ogr.sakarya.edu.tr
*/

package pkt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class DosyadanOku {
	
	ArrayList<PCB> InputPCBler; 

	DosyadanOku(){
		
		InputPCBler = new ArrayList<PCB>(); 
		//Input pcbleri tutmak için bir liste oluşturdum

		try (BufferedReader reader = new BufferedReader(new FileReader("giris.txt"))) 
		//giris.txt'den input pcbleri okuyup listeye ekler
	{
	    String satir = "";
	    int yazilacakPid = 0;
	    int yazilacakVarisZamani = 0;
	    int yazilacakOncelik = 0;
	    int yazilacakTotalZaman = 0;
	    
	    while ((satir = reader.readLine()) != null) { //input satır satır okunuyor
	    	
	        String[] girdiler = satir.split(","); //girdi istenen formata sokuluyor
	        girdiler[0] = girdiler[0].trim();
	        girdiler[1] = girdiler[1].trim();
	        girdiler[2] = girdiler[2].trim();
	        
	        // parse the values and create a new PCB object
	        yazilacakVarisZamani = Integer.parseInt(girdiler[0]);
	        yazilacakOncelik = Integer.parseInt(girdiler[1]);
	        yazilacakTotalZaman = Integer.parseInt(girdiler[2]);
	        PCB pcb = new PCB(yazilacakPid, yazilacakVarisZamani, yazilacakOncelik, yazilacakTotalZaman);
	        //alinan degerlerle pcb olusturuldu
	        
	        
	        InputPCBler.add(pcb);//olusturulan pcb listeye eklendi

	        
	        yazilacakPid++; //pid bir sonraki process için artırıldı
	    }
	} catch (Exception e) { 
	    e.printStackTrace();
	}
		
	}
	
	ArrayList<PCB> listeDondur(){
		return InputPCBler;
	}
	
}
