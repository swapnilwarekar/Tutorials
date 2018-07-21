# Memory Management in Java
This is a tutorial about memory management in Java.

#### Pass By Value
In Java, variable to the method is always pass by value. In following example, system will print value of localValue = 5. While calling calculate method copy of "localValue" will be created as "value" parameter to calculate method.

Passing by reference is not possible in Java.

```
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
