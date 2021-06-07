package employee.functions;

public class Address {
	private String city;
	private String state;
	
	/**
	 * Standard parameterized constructor.
	 * @param city: The city the address is in.
	 * @param state: The state the address is in.
	 */
	public Address(String city, String state) {
		super();
		this.city = city;
		this.state = state;
	}
	
	
	/**
	 * Override of toString method, to return the city and state as a String.
	 */
	@Override
	public String toString() {
		return city + ", " + state;
	}



	/**
	 * Standard getter/setter methods for Address.city and Address.state.
	 */
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
	
	
}
