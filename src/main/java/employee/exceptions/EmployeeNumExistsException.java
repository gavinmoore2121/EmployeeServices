package employee.exceptions;

public class EmployeeNumExistsException extends Exception {

	private static final long serialVersionUID = -730436253002753183L;
	
	public EmployeeNumExistsException(int empNum) {
		super("Employee with ID#" + empNum + " already exists.");
	}

}
