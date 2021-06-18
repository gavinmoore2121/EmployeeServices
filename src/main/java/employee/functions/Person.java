package employee.functions;

public class Person {
	private String name;
	private Address address;
	
	/**
	 * Standard non-parameterized constructor and default values.
	 */
	public Person() {
		this.name = "NULL";
		this.address = null;
	}
	
	/**
	 * Standard parameterized constructor.
	 * @param name: First and last name of the person, as a single string.
	 * @param address: Address containing the street and city of the person.
	 */
	public Person(String name, Address address) {
		this.name = name;
		this.address = address;
	}
	
	// Standard setters and getters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	
	
}
