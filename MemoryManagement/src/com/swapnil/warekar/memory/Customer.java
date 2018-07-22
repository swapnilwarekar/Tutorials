package com.swapnil.warekar.memory;

public class Customer implements CustomerReadOnly {
	
	private String name;
	
	public Customer() {
	}

	public Customer(String name) {
		super();
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see com.swapnil.warekar.memory.CustomerReadOnly#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see com.swapnil.warekar.memory.CustomerReadOnly#toString()
	 */
	@Override
	public String toString() {
		return "Customer [name=" + name + "]";
	}
}
