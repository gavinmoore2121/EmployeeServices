package employee.functions;

import java.io.BufferedReader;

public interface EmployeeService {
	void displayAllEmployees();
	double calculateYearlySalary();
	Employee findByEmployeeNo(int empNo);
	void updateEmployee(Employee e1, BufferedReader reader);
	void deleteEmployee(Employee e1);
}
