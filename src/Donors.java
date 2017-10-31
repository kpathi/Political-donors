/*Kavya Pathi
 * Java version-1.8
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;


public class Donors {
	public static void main(String[] args) {
		HashSet<String> s1 = new HashSet<>();
		for (int m = 1960; m <= 2017; m++) {
			for (int n = 1; n <= 12; n++) {
				int days = days(m, n);
				for (int t = 1; t <= days; t++) {
					StringBuilder p1 = new StringBuilder();
					p1.append(String.format("%02d", n));
					p1.append(String.format("%02d", t));
					p1.append(String.format("%04d", m));
					s1.add(p1.toString());
				}
			}
		}
		HashMap<area1, countad> h1 = new HashMap<>();
		HashMap<day1, countad> h2 = new HashMap<>();
		
		try {
			File z = new File(args[0]);
			FileReader rr = new FileReader(z);
			BufferedReader br = new BufferedReader(rr);
			String line;
			PrintWriter pr = new PrintWriter(args[1], "UTF-8");
			
			
	}
}
