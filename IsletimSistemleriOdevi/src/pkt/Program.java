/**
* @file G181210041
* @description 4 Seviyeli Oncelikli Gorevlendirici
* @course 2.Ogretim C
* @assignment Donem Sonu Proje Calismasi
* @date 02-01-2023  &  08-01-2023
* @author Doğukan Berk Kaya dogukan.kaya1@ogr.sakarya.edu.tr
*/


package pkt;

import java.util.ArrayList;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
    	DosyadanOku dosyadanInputOkuyucu = new DosyadanOku(); 
    	ArrayList<PCB> InputPCBlistesi = dosyadanInputOkuyucu.listeDondur(); 
    	
    	int q = 2; //(daha hızlı olması için q 'nün seçimi yüksek process sürelerinde
    			//formül ile secilmeli: optimizasyon için)
    	//(genellikle kuyruk ortalamasının yarısından biraz fazla secilir)
    	//ayrıca kısa süreli processlerin kuyruklarda ön sıralara cekilmesi de
    	//uygulamaların daha hızlı calıstıgı algısı olusturacaktır
    	Kuyruk kuyrukYuksekOncelikli = new Kuyruk();
    	Kuyruk kuyrukSeviye1 = new Kuyruk(q);
    	Kuyruk kuyrukSeviye2 = new Kuyruk(q);
    	Kuyruk kuyrukSeviye3 = new Kuyruk(q);
    	Kuyruk kuyrukRoundRobin = new Kuyruk(1); //q degeri 1 belirlendi
    	
	
		ProcessIsleyici pi = new ProcessIsleyici(kuyrukYuksekOncelikli,kuyrukSeviye1,
				kuyrukSeviye2,kuyrukSeviye3,kuyrukRoundRobin); 
		//processleri işletecek modülü olusturur
		
		double timer = 0; 
		boolean isItFinished = false;
		PCB islenecekPCB=null;
		int fcfsReminder =0;
		int whoSentThis=444;
		int sonAtanmisQ =0;
		
		do {
			
			isItFinished = false;
			
			ArrayList<PCB> yeniGelenPcbler = new ArrayList<PCB>(); 
			//her saniye varış zamanınca yeni gelen processler tutulacak dizi
			
			for(int i=0;i<InputPCBlistesi.size();i++) 
			{
				PCB eklenecek=InputPCBlistesi.get(i);
				
				if(eklenecek.varisZamani > timer) //üst varış zamanlarının boşa gezilmemesi için
					break;
				
				if(eklenecek.varisZamani == timer) 
				{
					yeniGelenPcbler.add(eklenecek);//timerın o saniyesinde gelen pcbler bir listede tutulur
					InputPCBlistesi.remove(eklenecek);
					//alt varış zamanları boşa gezilmemesi için cıkartmalar eklendi
					i--; 
				}
				
			}
			
			fcfsReminder =0;
			
			for(int i=0;i<yeniGelenPcbler.size();i++) 
				//timerın o saniyesinde gelen pcbler onceliklerine gore kuyruklara eklenir
			{	
					PCB eklenecek = yeniGelenPcbler.get(i);
					if(eklenecek.oncelik == 0) 
					{
						if(kuyrukYuksekOncelikli.KuyrukBoyu()<= 0) //boş kuyruğa mı eklendi
						{
							fcfsReminder = 1; //fcfsye ekleme yapıldı artık boş değil (askıya alma durumu için)
							
						}
						kuyrukYuksekOncelikli.eklemeYap(eklenecek);
						yeniGelenPcbler.remove(eklenecek);
						i--;
					}
					else if(eklenecek.oncelik == 1) {
						kuyrukSeviye1.eklemeYap(eklenecek);
						eklenecek.atanmisQ = kuyrukSeviye1.KuyrukQDegeri;
						yeniGelenPcbler.remove(eklenecek);
						i--;
					}
					else if(eklenecek.oncelik == 2) {
						kuyrukSeviye2.eklemeYap(eklenecek);
						eklenecek.atanmisQ = kuyrukSeviye2.KuyrukQDegeri;
						yeniGelenPcbler.remove(eklenecek);
						i--;
					}
					else if(eklenecek.oncelik == 3) {
						kuyrukSeviye3.eklemeYap(eklenecek);
						eklenecek.atanmisQ = kuyrukSeviye3.KuyrukQDegeri;
						yeniGelenPcbler.remove(eklenecek);
						i--;
					}
					else
				{
					
				}
			}
			
			OncelikliKuyrukIsleyici kuyrukIsleyici = new OncelikliKuyrukIsleyici(kuyrukSeviye1, 
					kuyrukSeviye2, kuyrukSeviye3, kuyrukRoundRobin);
			//Geri Beslemeli Sıralayıcı
			
			if(islenecekPCB!=null) 
			{	
				
				if(fcfsReminder==1 && whoSentThis!=0 && islenecekPCB.bittimi()==false)
				{
					islenecekPCB.durum="askida"; //yuksek oncelikli sıraya process geldiginde
					//dusuk oncelikli process askıya alınır

				}
				
				
				String pidStringi = String.format("%04d", islenecekPCB.pid);
				System.out.println(islenecekPCB.yaziRengi + (timer) + " sn process " + islenecekPCB.durum
						+ "(id:" + pidStringi + " oncelik:" + islenecekPCB.oncelik + " kalan sure:" + islenecekPCB.kalanZaman + " sn)" + islenecekPCB.ANSI_RESET);
				
				if(fcfsReminder==1 && whoSentThis!=0 && islenecekPCB.bittimi()==false)
				{
					islenecekPCB.durum="basladi";	//kesilen processin askida olarak baslamamasi icin
				}
				
			}
			
			
			
			if(kuyrukYuksekOncelikli.KuyrukBoyu()>0) 
				//Yuksek oncelikli sırada eleman olması durumunda FCFS calistirilir
			{
				
		    	FCFS fcfs = new FCFS(kuyrukYuksekOncelikli);
		    	
		    	whoSentThis = 0; //Process işletecine process onceligini gonderir
		    	islenecekPCB=fcfs.IslenecekSecimi(timer);
		    	
		    }
			
			else
				{ //yüksek öncelikli sıra boş ise normal oncelikli processlerden devam edilir
				//3 seviyeli isleme ve round robin olarak isleme yapılır
				
				if (sonAtanmisQ<=0 || islenecekPCB.bittimi()==true || islenecekPCB.durum=="zaman asimi") 
					//eger onceki processin atanmis suresi doldu veya process tumuyle bittiyse 
					//process isleyiciye yeni process gonderir 
				{
					if(islenecekPCB!=null && islenecekPCB.durum=="zaman asimi")
					{
						islenecekPCB.durum="basladi"; //zaman asimi olarak baslamamasi icin
					}
					
					if(kuyrukSeviye1.KuyrukBoyu()>0)
					{
				    	whoSentThis = 1;
				    	islenecekPCB=kuyrukIsleyici.IslenecekSecimi(kuyrukSeviye1,timer);
					}
					
					else if(kuyrukSeviye2.KuyrukBoyu()>0)
					{
						whoSentThis = 2;
				    	islenecekPCB=kuyrukIsleyici.IslenecekSecimi(kuyrukSeviye2,timer);
					}
					
					else if(kuyrukSeviye3.KuyrukBoyu()>0)
					{
						whoSentThis = 3;
				    	islenecekPCB=kuyrukIsleyici.IslenecekSecimi(kuyrukSeviye3,timer);
					}
					
					else if(kuyrukRoundRobin.KuyrukBoyu()>0) 
						//Son seviyedeki processleri RoundRobin yapılması
					{
						whoSentThis = 4;
				    	islenecekPCB=kuyrukIsleyici.IslenecekSecimi(kuyrukRoundRobin,timer);
					}
				}
				
			}

			if(islenecekPCB.atanmisQ-1==0)
			{
				sonAtanmisQ=0; 
			}
			
			pi.islet(islenecekPCB, timer, whoSentThis,fcfsReminder); 
			//PROCESSLERIN CALISTIRIMI
			
			
			if(		kuyrukYuksekOncelikli.doluluk()==0 
					&& kuyrukSeviye1.doluluk()==0 
					&& kuyrukSeviye2.doluluk()==0 
					&& kuyrukSeviye3.doluluk()==0
					&& kuyrukRoundRobin.doluluk()==0
					)
			{
				isItFinished = true; //kuyruklar boşsa döngüyü bitirecek şart sağlanır
				
			}
			
			timer++;
		
		}while(!(isItFinished==true) );
		
		String pidStringi = String.format("%04d", islenecekPCB.pid);
		System.out.println(islenecekPCB.yaziRengi + (timer) + " sn process " + islenecekPCB.durum
				+ "(id:" + pidStringi + " oncelik:" + islenecekPCB.oncelik + " kalan sure:" + islenecekPCB.kalanZaman + " sn)" + islenecekPCB.ANSI_RESET);
		
		
	}

	
	
}
