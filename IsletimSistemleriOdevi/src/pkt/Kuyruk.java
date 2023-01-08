package pkt;

import java.util.ArrayList;

public class Kuyruk {
	
		public ArrayList<PCB> ProcessKuyrugu;
		public int KuyrukOnceligi=0;
		public int KuyrukQDegeri=1; //default q
		
		
		Kuyruk(){
			ProcessKuyrugu=KuyrukOlustur();
		}
		Kuyruk(int q){
			ProcessKuyrugu=KuyrukOlustur();
			KuyrukQDegeri = q;
		}
		
		ArrayList<PCB> KuyrukOlustur(){
			ArrayList<PCB> Kuyruk = new ArrayList<PCB>();
			return Kuyruk;
		}
		
		int KuyrukBoyu() {
			return ProcessKuyrugu.size();
		}
		
		void kuyruktanCikar(int cikarilacakSira)
		{
			PCB cikacakPCB = ProcessKuyrugu.get(cikarilacakSira);
			ProcessKuyrugu.remove(cikacakPCB);
		}
		
		void kuyruktanCikar(PCB cikacakPCB)
		{	
			ProcessKuyrugu.remove(cikacakPCB);
		}
		
		int doluluk() {
			return ProcessKuyrugu.size();
		}
		
		void eklemeYap(PCB pcb) {
			ProcessKuyrugu.add(pcb);
		}
		
}
