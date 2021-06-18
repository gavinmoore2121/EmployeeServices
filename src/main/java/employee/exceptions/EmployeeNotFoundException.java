package employee.exceptions;

public class EmployeeNotFoundException extends Exception {

	private static final long serialVersionUID = -6500471784757679975L;
	
	public EmployeeNotFoundException(int empNum) {
		super("Employee with ID#" + empNum + " not found.");
	}

}
