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


### How objects are passed to method in Java?
In Java, objects are passed to a method by creating copy of a reference variable of an object. Means, Copy of reference variable but pointing to same object. In following example customer is getting renamed and output is "Deva". Copy of reference variable "c" is created as "customer" while passing parameter to the method. "c" and "customer" variables on stack points to the same object on the heap. When we set name as "Deva" it get replaced for "c" because it is pointing to the same object.s

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