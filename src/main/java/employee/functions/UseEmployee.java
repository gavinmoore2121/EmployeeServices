package employee.functions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import employee.exceptions.EmployeeNotFoundException;

public class UseEmployee {

	public static void main(String[] args) {
		// Convert to Stream
		EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		
		// Main loop: Output menu, read input, call appropriate method. Repeat until
		// user chooses to exit program.
		int menuSelection = 0;
		do {
			printMenu();
			
			try {
				menuSelection = Integer.parseInt(reader.readLine());
			} catch (NumberFormatException e) {
				System.out.println("Invalid menu selection.");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Invalid menu selection.");
				e.printStackTrace();
			}
			
			try {
				switch (menuSelection) {
				case 1: employeeService.displayAllEmployees(); break;
				case 2: System.out.println("Yearly combined salary: " + employeeService.calculateYearlySalary()); break;
				case 3: System.out.println(employeeService.findByEmployeeNo(requestAndReadEmployeeNum(reader))); break;
				case 4: employeeService.updateEmployee(employeeService.findByEmployeeNo(requestAndReadEmployeeNum(reader)), reader); break;
				case 5: employeeService.deleteEmployee(employeeService.findByEmployeeNo(requestAndReadEmployeeNum(reader)));
				System.out.println("Employee deleted."); break;
				case 6: break; //Ignore entry to exit program outside of switch.
				default: System.out.println("Invalid entry.");
				}
			}
			catch (EmployeeNotFoundException e) {
				System.out.println(e);
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
	
	public static int requestAndReadEmployeeNum(BufferedReader reader) {
		System.out.println("Enter employee number.");
		try {
			return Integer.parseInt(reader.readLine());
		}
		catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("Invalid integer.");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Invalid integer.");
		}
		return -1;
	}

}
