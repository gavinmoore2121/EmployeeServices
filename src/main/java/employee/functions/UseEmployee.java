package employee.functions;

import java.util.Scanner;

public class UseEmployee {

	//TODO Correct Logger to enable saving to a file. 
	//TODO Complete testing Class.
	public static void main(String[] args) {
		// Convert to Stream
		EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
		Scanner scnr = new Scanner(System.in);
		
		
		// Main loop: Output menu, read input, call appropriate method. Repeat until
		// user chooses to exit program.
		int menuSelection = 0;
		do {
			printMenu();
			menuSelection = scnr.nextInt();
			
			switch (menuSelection) {
			case 1: employeeService.displayAllEmployees(); break;
			case 2: System.out.println("Yearly combined salary: " + employeeService.calculateYearlySalary()); break;
			case 3: System.out.println(employeeService.findByEmployeeNo(requestAndReadEmployeeNum(scnr))); break;
			case 4: employeeService.updateEmployee(employeeService.findByEmployeeNo(requestAndReadEmployeeNum(scnr)), scnr); break;
			case 5: employeeService.deleteEmployee(employeeService.findByEmployeeNo(requestAndReadEmployeeNum(scnr)));
			System.out.println("Employee deleted."); break;
			case 6: break; //Ignore entry to exit program outside of switch.
			default: System.out.println("Invalid entry.");
			}
		} while (menuSelection != 6);
	}
	
	public static void printMenu() {
		System.out.println("Select an option.");
		System.out.println("1: List all employees.");
		System.out.println("2: Display yearly salary.");
		System.out.println("3: Display an employee's details.");
		System.out.println("4: Modify an employee's details.");
		System.out.println("5: Remove an employee from the system.");
		System.out.println("6: Exit program.");
	}
	
	public static int requestAndReadEmployeeNum(Scanner scnr) {
		System.out.println("Enter employee number.");
		return scnr.nextInt();
	}

}
