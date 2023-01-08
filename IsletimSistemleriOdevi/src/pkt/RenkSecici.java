/**
* @file G181210041
* @description 4 Seviyeli Oncelikli Gorevlendirici
* @course 2.Ogretim C
* @assignment Donem Sonu Proje Calismasi
* @date 02-01-2023  &  08-01-2023
* @author Doğukan Berk Kaya dogukan.kaya1@ogr.sakarya.edu.tr
*/

package pkt;

public class RenkSecici {

	int renkSecici() {
		
		int renk=0;
		
		return renk;
	}
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	
	
	
	String[] renkDizi = {"\033[0m","\u001B[31m","\u001B[32m",
			"\u001B[33m","\u001B[34m","\u001B[35m","\u001B[36m"};
	
	
	
	// Reset
    public static final String RESET = "\033[0m";  // Text Reset

    // Regular Colors
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;37m";   // WHITE

	
	
	
}
