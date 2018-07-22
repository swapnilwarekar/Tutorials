# Memory Management in Java
This is a tutorial about memory management in Java.

### Pass By Value
In Java, variable to the method is always pass by value. In following example, system will print value of localValue = 5. While calling calculate method copy of "localValue" will be created as "value" parameter to calculate method.

Passing by reference is not possible in Java.

```java
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
```


### How objects are passed to a method in Java?
In Java, objects are passed to a method by creating copy of a reference variable of an object. Means, Copy of reference variable but pointing to same object. In following example customer is getting renamed and output is "Deva". Copy of reference variable "c" is created as "customer" while passing parameter to the method. "c" and "customer" variables on stack points to the same object on the heap. When we set name as "Deva" it get replaced for "c" because it is pointing to the same objects.

```java
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
```

### Final Keyword

Please go through following example and don't forget to read comments written in between code.

```java
public class FinalKeyWordTest {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		final Customer customer1 = new Customer("Swapnil");
		final Customer customer2;
		customer2 = new Customer("Deva");

		// Following assignment will show the syntax error
		// The final local variable customer2 may already have been assigned
		
		customer2 = new Customer("Prashant");
		
		// It means when reference variable is marked as final then it's pointer cannot be changed to another object
		// However we can change objects property value as follows.
		customer2.setName("Prashant");
		
		// Java does have reserved keyword "const" but it is not implemented therefore there will not be const correctness implemented in Java
		// Hence object's properties can be altered even though we made reference variable final
	}

}
```

### Escaping references
In following example "getCustomers" method of "CustomerRecords" class returns reference to the map containing customer records.
Here, programmer gets full access to map of customer records and can manipulate / delete records accidently. This reference to the map of Customer records is an escaping reference here.
Escaping reference may lead system to inconsistency  

```java
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
```

### Avoiding escaping references

In following Java example we have avoided escaping references by making "customer records" map unmodifiable
also, creating readonly interface which doesn't having "setName" method to the "customer" we have avoided escaping reference to the customer object.

```java
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
```

### String pool

Java has string pool, If any string is already present in pool then JVM just creates reference variable
pointing to same string object. However it is not true with calculated strings. To put calculated string into the pool
we need to call "intern" method. To achieve this string pool mechanism Strings are immutable objects in Java.
Go through following example for more details.

```java 
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

```  