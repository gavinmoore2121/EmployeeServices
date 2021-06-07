package employee.functions;

import java.util.Scanner;

public interface EmployeeService {
	void displayAllEmployees();
	double calculateYearlySalary();
	Employee findByEmployeeNo(int empNo);
	void updateEmployee(Employee e1, Scanner scnr);
	void deleteEmployee(Employee e1);
}
