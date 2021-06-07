package employee.functions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.*;

public class EmployeeServiceImpl implements EmployeeService {
	
	// ArrayList to contain all Employees.
	public static ArrayList<Employee> employeeList = new ArrayList<Employee>();
	private Logger log; 
	
	public EmployeeServiceImpl() {
		// Initialize and configure logger.
		log = Logger.getLogger(EmployeeServiceImpl.class.getName());
		log.setLevel(Level.ALL);
		BasicConfigurator.configure();
		employeeList.add(new Employee(1, "John Doe", 50000.0, new Address("100 New York Ave", "New York")));
		employeeList.add(new Employee(2, "Jane Smith", 60000.0, new Address("101 New York Ave", "New York")));
		employeeList.add(new Employee(3, "Ed Johns", 70000.0, new Address("102 New York Ave", "New York")));
	}

	/**
	 * Sort all Employees by their default order, then output them in order.
	 */
	public void displayAllEmployees() {
		sortEmployees();
		for (Employee e: employeeList) System.out.println(e);
		log.info("Employee list displayed");
	}

	/**
	 * Calculate and return the total yearly of all employees combined.
	 */
	public double calculateYearlySalary() {
		double sumOfSalaries = 0;
		for (Employee e: employeeList) sumOfSalaries += e.getSalary();
		log.info("Yearly salary calculated.");
		return sumOfSalaries;
	}

	/**
	 * Find and return an Employee given their employee number.
	 */
	public Employee findByEmployeeNo(int empNo) {
		Employee foundEmployee = null;
		for (Employee e: employeeList) if (e.getEmpNo() == empNo) foundEmployee = e;
		log.info("Employee with ID #" + empNo + "returned.");
		return foundEmployee;
	}
	
	/**
	 * Sort Employees based on their default order.
	 */
	public void sortEmployees() {
		Collections.sort(employeeList);
		log.info("Employees sorted.");
	}

	/**
	 * Display a menu allowing the selection of a trait to modify. Once selected,
	 * accept the new value and alter the Employee appropriately.
	 */
	public void updateEmployee(Employee e1, Scanner scnr) {
		//TODO Consider separating this into separate methods.
		System.out.println("Select the trait to alter.");
		System.out.println("1: Employee number\n 2: Employee name"
				+ "\n3: Employee address \n4: Employee address");
		try {
			int userInput = scnr.nextInt();
		
			switch (userInput) {
			case 1: System.out.println("Enter new employee number.");
			e1.setEmpNo(scnr.nextInt());
			System.out.println("Employee number updated.");
			log.info("Employee " + e1 + " has had their ID modified.");
			break;
			case 2: System.out.println("Enter new employee name.");
			e1.setEmpName(scnr.nextLine());
			System.out.println("Employee name updated.");
			log.info("Employee " + e1 + " has had their name modified.");
			break;
			case 3: scnr.nextLine();
			System.out.println("Enter new employee city.");
			String newCity = scnr.nextLine();
			System.out.println("Enter new employee state.");
			String newState = scnr.nextLine();
			e1.setAddress(new Address(newCity, newState));
			System.out.println("Employee address updated.");
			log.info("Employee " + e1 + " has had their address modified.");
			break;
			case 4: System.out.println("Enter new employee salary.");
			e1.setSalary(scnr.nextDouble());
			System.out.println("Employee salary updated.");
			log.info("Employee " + e1 + " has had their salary modified.");
			break;
			default: // Place error in log.
				System.out.println("Invalid menu selection."); 
				log.trace("Invalid menu selection in EmployeeServiceImpl.updateEmployee.");
				break;
			}
		}
		catch (InputMismatchException e) {
			log.error("Error in EmployeeService.updateEmployee: /n" + e);
			System.out.println("Invalid input.");
		}

	}

	/**
	 * Remove the designated Employee from the employee list.
	 */
	public void deleteEmployee(Employee e1) {
		log.info("Employee " + e1 + " has been deleted.");
		employeeList.remove(e1);
	}

}
