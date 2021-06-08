package employee.functions;

public class Person {
	String name;
	Address address;
	
	/**
	 * @param name: First and last name of the person, as a single string.
	 * @param address: Address containing the street and city of the person.
	 */
	public Person(String name, Address address) {
		this.name = name;
		this.address = address;
	}
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
