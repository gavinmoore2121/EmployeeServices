package employee.functions;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.log4j.*;

import employee.exceptions.EmployeeNotFoundException;
import employee.exceptions.EmployeeNumExistsException;
import utilities.log4j.tools.UserInfoLog4JLevel;

/**
 * Class to maintain and perform operations on a list of Employees.
 * This is a singleton implementation: access the object through 
 * getEmployeeServiceImpl(Logger log).
 */
public class EmployeeServiceImpl implements EmployeeService {
	
	// Singleton class object
	private static EmployeeServiceImpl empServImpl = null; 
	
	// ArrayList to contain all Employees.
	private static ArrayList<Employee> employeeList; 
	static Logger log; 

	// Private constructor to instantiate singleton instance.
	private EmployeeServiceImpl(Logger log) {
		employeeList = new ArrayList<Employee>();
		EmployeeServiceImpl.log = log;
	}
	
	
	/**
	 * Instantiate if necessary and return the singleton instance of
	 * EmployeeServiceImpl. If one already exists, update the logging 
	 * system to the given parameter.
	 * 
	 * @param log: The Logger to use.
	 * @return: The instance of EmployeeServiceImpl.
	 */
	public static EmployeeServiceImpl getEmployeeServiceImpl(Logger log) {
		if (empServImpl == null) {
			log.trace("EmployeeServiceImpl instantiated.");
			empServImpl = new EmployeeServiceImpl(log);
		}
		// Slight modification of the standard Singleton constructor,
		// to facilitate changing the logging system during runtime if necessary.
		else {
			EmployeeServiceImpl.log = log;
		}
		
		log.trace("EmployeeServiceImpl object retrieved.");
		return empServImpl;
	}

	
	/**
	 * Find and return an Employee given their employee number.
	 */
	public Employee findEmployeeByNo(int empNo) throws EmployeeNotFoundException {
		Employee foundEmployee = null;
		for (Employee e: employeeList) if (e.getEmpNo() == empNo) foundEmployee = e;
		if (foundEmployee == null) {
			throw new EmployeeNotFoundException(empNo);
		}
		
		log.trace("Employee with ID #" + empNo + " requested and returned.");
		return foundEmployee;
	}
	
	
	public void createAndAddNewEmployee(BufferedReader reader) 
			throws NumberFormatException, EmployeeNumExistsException, IOException {
		Employee newEmp = new Employee();
		updateEmployeeNumber(newEmp, reader);
		updateEmployeeName(newEmp, reader);
		updateEmployeeAddress(newEmp, reader);
		updateEmployeeSalary(newEmp, reader);
		addNewEmployee(newEmp);
		log.trace("A new employee with ID #" + newEmp.getEmpNo() + " has been added.");
	}
	
	
	/**
	 * Check if a new employee has a unique employee number. If so, add them 
	 * to the employee list.
	 * @param e1: The employee to add.
	 * @throws EmployeeNumExistsException if the new employee's employee number
	 * is already in use.
	 */
	public void addNewEmployee (Employee e1) throws EmployeeNumExistsException {
		// Search through existing Employee numbers. Throw an error if the 
		// new one is taken.
		for (Employee e: employeeList) {
			if (e.getEmpNo() == e1.getEmpNo()) {
				log.error("User attempted to add employee with ID " + e1.getEmpNo() 
				+ ", but that employee number is already in use.");
				throw new EmployeeNumExistsException(e1.getEmpNo());
			}
		}
		// If check is passed, add the new Employee.
		employeeList.add(e1);
	}
	
	
	/**
	 * Remove the designated Employee from the employee list.
	 */
	public void deleteEmployee(Employee e1) {
		employeeList.remove(e1);
		log.trace("Employee " + e1 + " has been deleted.");
	}
	
	
	/**
	 * Sort all Employees, then output them in order.
	 */
	public void displayAllEmployees() {
		sortEmployees();
		employeeList.stream().forEach(e -> log.log(UserInfoLog4JLevel.USERINFO, e));
		log.log(UserInfoLog4JLevel.USERINFO, "");
		log.trace("Employee list requested and displayed");
	}
	
	
	/**
	 * Sort Employees based on their default order.
	 */
	public void sortEmployees() {
		Collections.sort(employeeList);
		log.trace("Employees sorted.");
	}

	
	/**
	 * Calculate and return the total yearly of all employees combined.
	 */
	public double calculateYearlySalary() {
		double sumOfSalaries = 0;
		for (Employee e: employeeList) sumOfSalaries += e.getSalary();
		log.trace("Office yearly salary calculated: " + sumOfSalaries);
		return sumOfSalaries;
	}
	
	
	/**
	 * Display a menu allowing the selection of a trait to modify. Once selected,
	 * accept the new value and alter the Employee appropriately.
	 * 
	 * @param e1: The employee to update.
	 * @param reader: A BufferedReader to read user input from the input stream.
	 * 
	 * @throws IOException if the BufferedReader passed is closed or otherwise invalid.
	 * @throws EmployeeNumExistsException 
	 */
	public void updateEmployee(Employee e1, BufferedReader reader) throws IOException, EmployeeNumExistsException{
		// Print update options.
		log.log(UserInfoLog4JLevel.USERINFO, "Select the trait to alter.");
		log.log(UserInfoLog4JLevel.USERINFO, "1: Employee number \n2: Employee name"
				+ "\n3: Employee address \n4: Employee address");
		
		try {
			// Read user selection.
			int userInput = Integer.parseInt(reader.readLine());
			
			// Call the appropriate update method based on user input.
			// The method will update the appropriate data or throw an exception if necessary.
			switch (userInput) {
			case 1: updateEmployeeNumber(e1, reader); break;
			case 2: updateEmployeeName(e1, reader); break;
			case 3: updateEmployeeAddress(e1, reader); break;
			case 4: updateEmployeeSalary(e1, reader); break;
			default: // Log user/input error.
				log.log(UserInfoLog4JLevel.USERINFO, "Invalid menu selection."); 
				log.error("Invalid menu selection in EmployeeServiceImpl.updateEmployee.");
				break;
			}
		}
		// Thrown if user inputs a non-integer in the menu.
		catch (NumberFormatException e) {
			log.error("Input error in EmployeeService.updateEmployee: " + e.getStackTrace().toString());
			log.log(UserInfoLog4JLevel.USERINFO, "Invalid input. Returning to main menu.");
		}

	}
	
	/**
	 * Read a user-input integer and updates the given Employee's employeeNumber to the 
	 * new value.
	 * 
	 * @param e1: The Employee to modify.
	 * @param reader: A Reader to read user input from the input stream.
	 * @throws NumberFormatException: Thrown if user enters a non-integer.
	 * @throws IOException: Thrown if the BufferedReader is closed before input is read.
	 */
	public void updateEmployeeNumber(Employee e1, BufferedReader reader) 
			throws EmployeeNumExistsException, NumberFormatException, IOException {
		
		log.log(UserInfoLog4JLevel.USERINFO, "Enter new employee number.");
		int newEmpNo = Integer.parseInt(reader.readLine());
		if (e1.getEmpNo() == newEmpNo) {
			log.log(UserInfoLog4JLevel.USERINFO, "Identical number, employee not changed.");
		}
		
		// Check if new employee number is already in use.
		for (Employee e: employeeList) {
			if (e.getEmpNo() == newEmpNo) {
				log.error("User attempted to change employee with ID#" + e1.getEmpNo() 
				+ " to ID#" + newEmpNo + ", but that number is already in use.");
				throw new EmployeeNumExistsException(newEmpNo);
			}
		}
		
		e1.setEmpNo(newEmpNo);
		log.log(UserInfoLog4JLevel.USERINFO, "Employee number updated.");
		log.trace("Employee " + e1 + " has had their ID modified.");
	}
	
	
	/**
	 * Read a user-input string and updates the given Employee's name to the 
	 * new value.
	 * 
	 * @param e1: The Employee to modify.
	 * @param reader: A Reader to read user input from the input stream.
	 * @throws IOException: Thrown if the BufferedReader is closed before input is read.
	 */
	public void updateEmployeeName(Employee e1, BufferedReader reader) throws IOException {
		log.log(UserInfoLog4JLevel.USERINFO, "Enter new employee name.");
		String newName = reader.readLine();
		e1.setEmpName(newName);
		log.log(UserInfoLog4JLevel.USERINFO, "Employee name updated.");
		log.trace("Employee " + e1 + " has had their name changed to " + newName);
	}
	
	
	/**
	 * Read a user-input city and state as Strings separated by newline characters, 
	 * then creates a new Address with those parameters and sets it as the given employee's
	 * new address.
	 * 
	 * @param e1: The Employee to modify.
	 * @param reader: A Reader to read user input from the input stream.
	 * @throws IOException: Thrown if the BufferedReader is closed before input is read.
	 */
	public void updateEmployeeAddress(Employee e1, BufferedReader reader) throws IOException {
		
		log.log(UserInfoLog4JLevel.USERINFO, "Enter new employee city.");
		String newCity = reader.readLine();
		log.log(UserInfoLog4JLevel.USERINFO, "Enter new employee state.");
		String newState = reader.readLine();
		e1.setAddress(new Address(newCity, newState));
		log.log(UserInfoLog4JLevel.USERINFO, "Employee address updated.");
		log.trace("Employee " + e1 + " has had their address updated to: "
				+ e1.getAddress().toString());
	}
	
	
	/**
	 * Read a user-input double and updates the given Employee's salary to the 
	 * new value.
	 * 
	 * @param e1: The Employee to modify.
	 * @param reader: A Reader to read user input from the input stream.
	 * @throws NumberFormatException: Thrown if user enters a non-integer character.
	 * @throws IOException: Thrown if the BufferedReader is closed before input is read.
	 */
	public void updateEmployeeSalary(Employee e1, BufferedReader reader) throws NumberFormatException, IOException {
		log.log(UserInfoLog4JLevel.USERINFO, "Enter new employee salary.");
		e1.setSalary(Double.parseDouble(reader.readLine()));
		log.log(UserInfoLog4JLevel.USERINFO, "Employee salary updated.");
		log.trace("Employee " + e1 + " has had their salary modified.");
	}
	
	
	/**
	 * Add several sample Employees to the registry for testing/demo purposes.
	 * @throws EmployeeNumExistsException if an employee with employeeNum 1, 2, or
	 * 		3 is already in the list.
	 */
	public void addSampleData() throws EmployeeNumExistsException {
		// SAMPLE DATA.
		addNewEmployee(new Employee(1, "John Doe", 50000.0, 
				new Address("100 New York Ave", "New York")));
		addNewEmployee(new Employee(2, "Jane Smith", 60000.0, 
				new Address("101 New York Ave", "New York")));
		addNewEmployee(new Employee(3, "Ed Johns", 70000.0, 
				new Address("102 New York Ave", "New York")));
		log.trace("Sample data added to list.");
	}
	
	
	/**
	 * Delete all employees in the registry.
	 */
	public void clearAllEmployees() {
		employeeList.clear();
		log.trace("All employees deleted.");
	}

}
