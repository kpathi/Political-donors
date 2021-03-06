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
			rr.close();
			pr.close();
			PrintWriter pr1 = new PrintWriter(args[2], "UTF-8");
			ArrayList<day1> arr = new ArrayList<>();
			arr.addAll(h2.keySet());
			Collections.sort(arr, new Comparator<day1>(){
				public int compare(day1 rd1, day1 rd2) {
					if (!rd1.s4.equals(rd2.s4)) {
						return rd1.s4.compareTo(rd2.s4);
					} else {
						return vb(rd1.day, rd2.day);
					}	}
			});
			for (day1 rd : arr) {
				countad y2 = h2.get(rd);
				pr1.println(buildDateString(rd, y2));
			}
			pr1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static int vb(String s1, String s2) {
		String y1 = s1.substring(4);
		String y2 = s2.substring(4);
		if (!y1.equals(y2)) {
			return y1.compareTo(y2);
		}
		String sg = s1.substring(0, 2);
		String sg1 = s2.substring(0, 2);
		if (!sg.equals(sg1)) {
			return sg.compareTo(sg1);
		}
		return s1.substring(2, 4).compareTo(s2.substring(2, 4));
	}
	public static String buildZipString(area1 rz, countad fs) {
		StringBuilder bd1 = new StringBuilder();
		bd1.append(rz.s6);
		bd1.append("|");
		bd1.append(rz.area);
		bd1.append("|");
		long q = Math.round(fs.median());
		bd1.append(q);
		bd1.append("|");
		bd1.append(fs.occurance);
		bd1.append("|");
		bd1.append(Math.round(fs.total));
		return bd1.toString();
	}
	public static String buildDateString(day1 rz, countad fs) {
		StringBuilder bd2 = new StringBuilder();
		bd2.append(rz.s4);
		bd2.append("|");
		bd2.append(rz.day);
		bd2.append("|");
		long q = Math.round(fs.median());
		bd2.append(q);
		bd2.append("|");
		bd2.append(fs.occurance);
		bd2.append("|");
		bd2.append(Math.round(fs.total));
		return bd2.toString();
	}
	public static int days(int year, int month) {
		Calendar dr = new GregorianCalendar(year, month - 1, 1);
		return dr.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
}

class day1 {
	String s4;
	String day;
	public day1(String s4, String day) {
		this.s4 = s4;
		this.day = day;
	}
	@Override
	public int hashCode() {
		return (s4 + day).hashCode();
	}
	@Override
	public boolean equals(Object r) {
		return s4.equals(((day1)r).s4) && day.equals(((day1)r).day);
	}
}

class area1 {
	String s6, area;
	public area1(String s6, String area) {
		this.s6 = s6;
		this.area = area;
	}
	@Override
	public int hashCode() {
		return (s6 + area).hashCode();
	}
	@Override
	public boolean equals(Object r) {
		return s6.equals(((area1)r).s6) && area.equals(((area1)r).area);
	}
}

class countad {
	double total;
	int occurance;
	PriorityQueue<Double> minHeap;
	PriorityQueue<Double> maxHeap;
	public countad(int occurance, double total) {
		this.occurance = occurance;
		this.total = total;
		this.minHeap = new PriorityQueue<>();
		this.maxHeap = new PriorityQueue<Double>(10, new Comparator<Double>(){
			public int compare(Double a, Double b) {
				return b.compareTo(a);
			}
		});
	}
	public double median() {
		double fnl = 0;
		if (minHeap.size() == maxHeap.size()) {
			fnl = (minHeap.peek() + maxHeap.peek()) * 0.5;
		} else if (minHeap.size() > maxHeap.size()) {
			fnl = minHeap.peek();
		} else {
			fnl = maxHeap.peek();
		}
		return fnl;
	}
	public void add(double trsct1) {
		if (minHeap.isEmpty()) {
			minHeap.add(trsct1);
			return;
		}
		if (minHeap.size() == maxHeap.size()) {
			double min1 = minHeap.peek();
			if (trsct1 >= min1) {
				minHeap.add(trsct1);
			} else {
				maxHeap.add(trsct1);
			}
		} else if (minHeap.size() > maxHeap.size()) {
			if (trsct1 <= minHeap.peek()) {
				maxHeap.add(trsct1);
			} else {
				maxHeap.add(minHeap.poll());
				minHeap.add(trsct1);
			}
		} else {
			if (trsct1 >= maxHeap.peek()) {
				minHeap.add(trsct1);
			} else {
				minHeap.add(maxHeap.poll());
				maxHeap.add(trsct1);
			}
		}
			
			
	}
}
