/**
* @file G181210041
* @description 4 Seviyeli Oncelikli Gorevlendirici
* @course 2.Ogretim C
* @assignment Donem Sonu Proje Calismasi
* @date 02-01-2023  &  08-01-2023
* @author Doğukan Berk Kaya dogukan.kaya1@ogr.sakarya.edu.tr
*/

package pkt;

public class ProcessIsleyici {
	
	Kuyruk s0; //FCFS First come first served mantigiyla calisan yuksek oncelikli kuyruk
	Kuyruk s1;
	Kuyruk s2;
	Kuyruk s3;
	Kuyruk s4; //RoundRobin
	
	
	ProcessIsleyici(Kuyruk _s0,Kuyruk _s1,Kuyruk _s2,Kuyruk _s3,Kuyruk _s4 )
	{
			s0 = _s0;
			s1 = _s1;
			s2 = _s2;
			s3 = _s3;
			s4 = _s4;
	}
	
	void islet(PCB pcb,double timer,int whoSentMeThis, int fcfsReminder) {
		//Processi isleme koyar
		
		if(whoSentMeThis==444)
		{
			System.out.println("Gonderen bilinmiyor.");
			return;
		}
		if(pcb==null)
		{
			System.out.println(whoSentMeThis+" pcb gondermedi");
			return;
		}
		
			String pidStringi = String.format("%04d", pcb.pid);
			double time=timer;
			System.out.println(pcb.yaziRengi + (time) + " sn process " + pcb.durum
					+ "(id:" + pidStringi + " oncelik:" + pcb.oncelik + " kalan sure:" + pcb.kalanZaman + " sn)" + pcb.ANSI_RESET);
				
			calistir(pcb,whoSentMeThis);
		
	}
	
	void calistir(PCB pcb,int whoSentMeThis) {
		//Process islenir
		int who=whoSentMeThis;
		pcb.kalanZaman = pcb.kalanZaman-1; // pcb'nin kalan zamanı q kadar azaltıldı
		pcb.islemZamani = pcb.islemZamani+1; //işlem zamanı ise q kadar artırıldı
		pcb.atanmisQ --; //her islemde ayrilan vakti de 1 azaliyor
		boolean bitti = pcb.bittimi(); //islem sonunda process bitti mi kontrol edip durumu günceller
		if(bitti==true)
		{
			switch (who) {
			  case 0:
				  s0.kuyruktanCikar(pcb);
				  pcb.durum="sonlandi";
			    break;
			  case 1:
				  s1.ProcessKuyrugu.remove(pcb);
				  pcb.durum="sonlandi";
			    break;
			  case 2:
				  s2.kuyruktanCikar(pcb);
				  pcb.durum="sonlandi";
			    break;
			  case 3:
				  s3.kuyruktanCikar(pcb);
				  pcb.durum="sonlandi";
			    break;
			  case 4:
				  s4.kuyruktanCikar(pcb);
				  pcb.durum="sonlandi";
			    break;
			  default:
				  System.out.println("Err PI switch def");
			}
		}
		if(bitti==false && pcb.atanmisQ<=0)
		{
			pcb.durum="zaman asimi"; //eger bitmeden ayrılan q suresi bittiyse zaman asimi olmustur
			switch (who) {
			  case 0:
				  System.out.println("This shouldnt happen in FCFS");
				  s0.kuyruktanCikar(pcb);
				  pcb.oncelik++;
				  s1.eklemeYap(pcb);
				  pcb.atanmisQ=pcb.atanmisQ+s1.KuyrukQDegeri;
				  pcb.durum="zaman asimi";
			    break;
			  case 1:
				  s1.ProcessKuyrugu.remove(pcb);
				  pcb.oncelik++;
				  s2.eklemeYap(pcb);
				  pcb.atanmisQ=pcb.atanmisQ+s2.KuyrukQDegeri;
				  pcb.durum="zaman asimi";
			    break;
			  case 2:
				  s2.kuyruktanCikar(pcb);
				  pcb.oncelik++;
				  s3.eklemeYap(pcb);
				  pcb.atanmisQ=pcb.atanmisQ+s3.KuyrukQDegeri;
				  pcb.durum="zaman asimi";
			    break;
			  case 3:
				  s3.kuyruktanCikar(pcb);
				  pcb.oncelik++;
				  s4.eklemeYap(pcb);
				  pcb.atanmisQ=pcb.atanmisQ+s4.KuyrukQDegeri;
				  pcb.durum="zaman asimi";
			    break;
			  case 4:
				  s4.kuyruktanCikar(pcb);
				  s4.eklemeYap(pcb);
				  pcb.atanmisQ=pcb.atanmisQ+s4.KuyrukQDegeri;
				  pcb.durum="zaman asimi";
			    break;
			  default:
				  System.out.println("Err PI switch def");
			}
			
		}
	}
	

}
