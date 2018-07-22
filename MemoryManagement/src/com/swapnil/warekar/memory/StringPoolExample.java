package com.swapnil.warekar.memory;

public class StringPoolExample {

	public static void main(String[] args) {

		String one = "hello";
		String two = "hello";
		
		// Java has string pool, if any string is already in pool then it just creates 
		// reference variable pointing to same object.
		// Hence following statement prints output as "true"
		
		System.out.println(one == two);
		// Equal equal checks reference equality
		
		
		// However this is not true with calculated strings
		String three = new Integer(100).toString();
		String four = "100";
		
		// Following statement prints output as "false"
		System.out.println(three == four);
		// Hence references for three and four are not equal
		
		// If we use "intern" method calculated string will be created on the pool
		String five = new Integer(100).toString().intern();
		String six = "100";
		
		// Following statement prints output as "true"
		System.out.println(five == six);
		// Hence references for three and four are equal again
	}

}
