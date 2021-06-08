package employee.functions;

import java.io.BufferedReader;

import employee.exceptions.EmployeeNotFoundException;

public interface EmployeeService {
	void displayAllEmployees();
	double calculateYearlySalary();
	Employee findByEmployeeNo(int empNo) throws EmployeeNotFoundException;
	void updateEmployee(Employee e1, BufferedReader reader);
	void deleteEmployee(Employee e1);
}
