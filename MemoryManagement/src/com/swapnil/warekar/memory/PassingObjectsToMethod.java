package com.swapnil.warekar.memory;

public class PassingObjectsToMethod {

	public static void main(String[] args) {
		// Reference variable c will be created on stack and Customer object with name "Swapnil" will be created on heap.
		Customer c = new Customer("Swapnil");
		renameCustomer(c);
		System.out.println(c.getName());

	}
	
	// Copy or reference variable "c" will be created on the stack as "customer" and will point to the same object created on the heap earlier.
	// Therefore program will rename Customer from "Swapnil" to "Deva".
	public static void renameCustomer(Customer customer) {
		customer.setName("Deva");
	}

}
