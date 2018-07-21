package com.swapnil.warekar.memory;

public class PassByValue {

	public static void main(String[] args) {
		int localValue = 5;
		calculate(localValue);
		// Prints 5 because Java always pass variable by value to method
		System.out.println(localValue);

	}

	// When we call this method value of localValue will be copied into value i.e pass by value.
	public static void calculate(int value) {
		value = value * 100;
	}
}
