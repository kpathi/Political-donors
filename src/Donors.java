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
			while ((line = br.readLine()) != null) {
				String[] strs = line.split("\\|");
				if (strs.length < 15 || (15 < strs.length && strs[15].length() != 0) || strs[14].length() == 0 || strs[0].length() == 0) {
					continue;	}
				String u1 = strs[0];
				String trnsct = strs[14];
				double tr = Double.parseDouble(trnsct);	
				try {
					String st = strs[10].substring(0, 5);
					area1 a1 = new area1(u1, st);
					countad y = null;
					if (!h1.containsKey(a1)) {
						y = new countad(1, tr);
						y.add(tr);
						h1.put(a1, y);
					} else {
						y = h1.get(a1);
						y.occurance += 1;
						y.total += tr;
						y.add(tr);
					}
					pr.println(buildZipString(a1, y));	} 
				catch (Exception e) {	
				}
				
				String day = strs[13];
				if (!s1.contains(day)) {
					continue;
				} //medianvals_by_date
				day1 dd = new day1(u1, day);
				countad y1 = null;
				if (!h2.containsKey(dd)) {
					y1 = new countad(1, tr);
					y1.add(tr);
					h2.put(dd, y1);
				} else {
					y1 = h2.get(dd);
					y1.occurance += 1;
					y1.total += tr;
					y1.add(tr);
				}
			}
			
			
			
	}
}
