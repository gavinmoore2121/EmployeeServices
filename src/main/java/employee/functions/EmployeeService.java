package employee.functions;

import java.io.BufferedReader;
import java.io.IOException;

import employee.exceptions.EmployeeNotFoundException;
import employee.exceptions.EmployeeNumExistsException;

public interface EmployeeService {
	void displayAllEmployees();
	double calculateYearlySalary();
	Employee findEmployeeByNo(int empNo) throws EmployeeNotFoundException;
	void addNewEmployee (Employee e1) throws EmployeeNumExistsException;
	void deleteEmployee(Employee e1);
	void updateEmployee(Employee e1, BufferedReader reader) throws IOException, EmployeeNumExistsException;
	
}
