package employee.functions;

public class Employee extends Person implements Comparable<Employee>{
	private int empNo;
	private double salary;
	
	/**
	 * Non-parameterized constructor and default values.
	 */
	public Employee() {
		super();
		this.empNo = -1;
		this.salary = -1.0;
	}
	
	/**
	 * Standard parameterized constructor.
	 * 
	 * @param empNo: Employee's ID number.
	 * @param empName: Employee's first and last name.
	 * @param salary: Employee's yearly salary.
	 * @param address: Employee's permanent address.
	 */
	public Employee(int empNo, String empName, double salary, Address address) {
		super(empName, address);
		this.empNo = empNo;
		this.salary = salary;
	}
	
	/**
	 * Override of toString method, to return the employee's details formatted for output.
	 */
	@Override
	public String toString() {
		return this.getName() + ", employee #" + empNo + ", home address = " + 
				this.getAddress() + ", yearly salary of " + salary;
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
		return this.getName();
	}
	public void setEmpName(String empName) {
		this.setName(empName);
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
}
