package com.swapnil.warekar.memory;

public class FinalKeyWordTest {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		final CustomerReadOnly customer1 = new Customer("Swapnil");
		final Customer customer2;
		customer2 = new Customer("Deva");

		// Following assignment will show the syntax error
		// The final local variable customer2 may already have been assigned
		
		// customer2 = new Customer("Prashant");
		
		// It means when reference variable is marked as final then it's pointer cannot be changed to another object
		// However we can change objects property value as follows.
		customer2.setName("Prashant");
		
		// Java does have reserved keyword "const" but it is not implemented therefore there will not be const correctness implemented in Java
		// Hence object's properties can be altered even though we made reference variable final
	}

}
