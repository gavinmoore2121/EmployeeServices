package employee.functions;

public class Employee implements Comparable<Employee>{
	int empNo;
	String empName;
	double salary;
	Address address;
	
	
	
	/**
	 * Standard parameterized constructor.
	 * 
	 * @param empNo: Employee's ID number.
	 * @param empName: Employee's first and last name.
	 * @param salary: Employee's yearly salary.
	 * @param address: Employee's permanent address.
	 */
	public Employee(int empNo, String empName, double salary, Address address) {
		super();
		this.empNo = empNo;
		this.empName = empName;
		this.salary = salary;
		this.address = address;
	}
	
	/**
	 * Override of toString method, to return the employee's details formatted for output.
	 */
	@Override
	public String toString() {
		return empName + ", employee #" + empNo + ", home address = " + 
				address + ", yearly salary of " + salary;
	}

	/**
	 * Defines default comparison behavior, based on the employee's ID number (empNo).
	 */
	public int compareTo(Employee e) {
		if (this.empNo > e.empNo) return 1;
		else if (this.empNo < e.empNo) return -1;
		return 0;
	}

	/**
	 * Standard getter/setter methods for Employee.empNo, Employee.empName, Employee.salary,
	 * and Employee.address.
	 */
	public int getEmpNo() {
		return empNo;
	}
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
}
