package com.swapnil.warekar.memory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AvoidingEscapingReferences {

	public static void main(String[] args) {
		
		// Programmer can add customer without knowing the implementation of addCustomer method.
		CustomerRecords2 customerRecords = new CustomerRecords2();
		customerRecords.addCustomer(new Customer("Swapnil"));
		customerRecords.addCustomer(new Customer("Deva"));
		customerRecords.addCustomer(new Customer("Prashant"));
		
	
		// We have returned unmodifiable map therefore programmer cannot clear the map
		// Following statement will throw an UnsupportedOperationException
		// customerRecords.getCustomers().clear();
		// Making map unmodifiable we avoided escaping reference to a customer records map.s
		System.out.println("Customer Records " + customerRecords.getCustomers());
		
		// We will try to get single customer and try to modify the name
		CustomerReadOnly customer = customerRecords.getCustomers().get("Swapnil");
		// But we have created read only interface for customer which doesn't have setName method exposed.
		// Following statement will give syntax error as CustomerReadOnly interface doesn't have setName method 
		// customer.setName("Shiva");
		// Creating readonly interface for customer, we avoided escaping reference to a customer object.
	}

}

class CustomerRecords2 {
	private Map<String, Customer> records;
	
	public CustomerRecords2() {
		this.records = new HashMap<String, Customer>();
	}
	
	public void addCustomer(Customer c) {
		this.records.put(c.getName(), c);
	}
	
	public Map<String, CustomerReadOnly> getCustomers() {
		return Collections.unmodifiableMap(this.records);
	}
	
	public CustomerReadOnly getCustomer(String name) {
		return records.get(name);
	}
}