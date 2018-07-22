package com.swapnil.warekar.memory;

import java.util.HashMap;
import java.util.Map;

public class EscapingReferenceExample {

	public static void main(String[] args) {
		
		// Programmer can add customer without knowing the implementation of addCustomer method.
		CustomerRecords customerRecords = new CustomerRecords();
		customerRecords.addCustomer(new Customer("Swapnil"));
		customerRecords.addCustomer(new Customer("Deva"));
		customerRecords.addCustomer(new Customer("Prashant"));
		
		
		
		
		// Now we need list of all customers to be printed out.
		Map<String, Customer> records = customerRecords.getCustomers();
		System.out.println("Customer Records " + records);
		
		// But we have escaping reference of map having customer records.
		// Programmer can clear all the records accidently with escaped reference of customer records map.
		// Escaping references may lead system to inconsistency.
		records.clear();
		System.out.println("Escaped customer records reference test " + customerRecords.getCustomers());
		
	}

}

class CustomerRecords {
	private Map<String, Customer> records;
	
	public CustomerRecords() {
		this.records = new HashMap<String, Customer>();
	}
	
	public void addCustomer(Customer c) {
		this.records.put(c.getName(), c);
	}
	
	public Map<String, Customer> getCustomers() {
		return this.records;
	}
}