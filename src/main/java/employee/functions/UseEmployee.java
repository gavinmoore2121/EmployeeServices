package employee.functions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import employee.exceptions.EmployeeNotFoundException;
import employee.exceptions.EmployeeNumExistsException;
import utilities.log4j.tools.LoggerTools;
import utilities.log4j.tools.UserInfoLog4JLevel;

public class UseEmployee {
	
	private static Logger log;
	

	public static void main(String[] args) throws IOException {
		// Create logger.
		log = LoggerTools.getAndConfigureLogger(UseEmployee.class.getName());

		// Instantiate employee service and input reader.
		EmployeeServiceImpl employeeService = EmployeeServiceImpl.getEmployeeServiceImpl(log);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		// DEMO CODE ONLY. Add demo/test data.
		try {
			employeeService.addSampleData();
		} catch (EmployeeNumExistsException e1) {
			log.log(UserInfoLog4JLevel.USERINFO, "Duplicate employee numbers exist; Sample data may not have been added.");
			log.error("Initial sample data not properly uploaded.", e1);
		}
		
		// Main loop: Output menu, read input, call appropriate method. Repeat until
		// user chooses to exit program.
		int menuSelection = -1;
		do {
			printMenu();
			
			try {
				menuSelection = Integer.parseInt(reader.readLine());
		
				switch (menuSelection) {
				case 1: // Display all Employees.
					employeeService.displayAllEmployees(); 
					break;
				case 2: // Calculate the yearly salary of all Employees combined.
					log.log(UserInfoLog4JLevel.USERINFO, 
							"Yearly office salary: " + employeeService.calculateYearlySalary() +"\n"); 
					break;
				case 3: // Print a specific Employee's record.
					log.log(UserInfoLog4JLevel.USERINFO, employeeService.findEmployeeByNo(requestAndReadEmployeeNum(reader)) + "\n");
					break;
				case 4: // Update an Employee.
					employeeService.updateEmployee(employeeService.findEmployeeByNo(requestAndReadEmployeeNum(reader)), reader);
					break;
				case 5: // Add a new Employee.
					employeeService.createAndAddNewEmployee(reader); 
					break; 
				case 6: // Delete an Employee.
					employeeService.deleteEmployee(employeeService.findEmployeeByNo(requestAndReadEmployeeNum(reader)));
					log.log(UserInfoLog4JLevel.USERINFO, "Employee deleted.\n"); 
					break;
				
				case 7: break; //Ignore entry, program will exit outside of switch statement.
				default: 
					log.log(UserInfoLog4JLevel.USERINFO, "Invalid entry.\n");
					log.debug("User selected " + menuSelection + " in the main menu.");
				}
			}
			
			catch (EmployeeNumExistsException | EmployeeNotFoundException e) {
				// Indicates an Employee number requested does not exist,
				// or the user attempted to set two to the same value.
				log.error("Employee number conflict.", e);
				log.log(UserInfoLog4JLevel.USERINFO, e.getMessage() + "\n");
			}
			catch (NumberFormatException e) {
				// Indicates user input a non-integer when an integer was requested.
				log.error("Non-integer read in main menu.", e);
				log.log(UserInfoLog4JLevel.USERINFO, "Invalid integer input.\n");
				menuSelection = -1;
			}
			catch (IOException e) {
				// Indicates input stream failure, exit program.
				log.error("Major input error, exiting program.", e);
				log.log(UserInfoLog4JLevel.USERINFO, "Major input error, exiting program. Stack trace:", e);
				menuSelection = 7;
			}
			
		} while (menuSelection != 7); // End main loop.
	}
	
	
	/**
	 * Print user menu selections.
	 */
	public static void printMenu() {
		log.log(UserInfoLog4JLevel.USERINFO, "Select an option.");
		log.log(UserInfoLog4JLevel.USERINFO, "1: List all employees.");
		log.log(UserInfoLog4JLevel.USERINFO, "2: Display office yearly salary.");
		log.log(UserInfoLog4JLevel.USERINFO, "3: Display an employee's details.");
		log.log(UserInfoLog4JLevel.USERINFO, "4: Modify an employee's details.");
		log.log(UserInfoLog4JLevel.USERINFO, "5: Add an employee to the system.");
		log.log(UserInfoLog4JLevel.USERINFO, "6: Remove an employee from the system.");
		log.log(UserInfoLog4JLevel.USERINFO, "7: Exit program.");
	}
	
	
	/**
	 * Request, read, and parse a user-input employee number.
	 * @param reader: A Reader to read user input from the input stream.
	 * @return: The employee number as a user-input integer.
	 * 
	 * @throws NumberFormatException if user inputs a non-integer.
	 * @throws IOException if reader is closed, or otherwise fails.
	 */
	public static int requestAndReadEmployeeNum(BufferedReader reader) 
			throws NumberFormatException, IOException {
		
		log.log(UserInfoLog4JLevel.USERINFO, "Enter employee number to access.");
		return Integer.parseInt(reader.readLine());
	}

}
