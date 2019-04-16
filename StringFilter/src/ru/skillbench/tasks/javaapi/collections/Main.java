package ru.skillbench.tasks.javaapi.collections;

import java.util.Iterator;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StringFilter sf = new StringFilterImpl();
		sf.add("pattern1:stryes");
		sf.add("pattern2:yesstr");
		sf.add("pattern3:stryesstr");

		sf.add("pattern4:yesstryes");
		sf.add("pattern5:stryesstryes");
		sf.add("pattern6:yesstryesstr");
		
		sf.add("(916)7330893");
		sf.add("1488");
		sf.add("22.24");
		
		sf.add(null);
		//sf.remove("pattern1:stryes");
		//sf.removeAll();
		for(String s : sf.getCollection()){
			System.out.println(s);
		}
		System.out.println("//////////////////");
		Iterator<String> i1 = sf.getStringsStartingWith("pattern2");
		while(i1.hasNext()){
			System.out.println(i1.next());
		}
		System.out.println("//////////////////");
		Iterator<String> i2 = sf.getStringsContaining("3");
		while(i2.hasNext()){
			System.out.println(i2.next());
		}
		System.out.println("//////////////////");
		/*Iterator<String> i3 = sf.getStringsByPattern("*str*");
		while(i3.hasNext()){
			System.out.println(i3.next());
		}*/
		System.out.println("//////////////////");
		/*Iterator<String> i4 = sf.getStringsByNumberFormat("##.##");
		while(i4.hasNext()){
			System.out.println(i4.next());
		}*/
	}

}
