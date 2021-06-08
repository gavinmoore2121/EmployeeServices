import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import employee.exceptions.EmployeeNotFoundException;
import employee.functions.Address;
import employee.functions.Employee;
import employee.functions.EmployeeServiceImpl;

public class EmployeeTests {
	
	private EmployeeServiceImpl empServTest;
	private final double EPSILON =  0.000001d;
	
	@Before
	public void initEach() {
		empServTest = new EmployeeServiceImpl();
	}
	
	@Test
	public void testModifyingEmployeeName() {
		try {
			empServTest.findByEmployeeNo(1).setEmpName("Test");
			Assert.assertEquals(empServTest.findByEmployeeNo(1).getEmpName(), "Test");
	
		}
		catch (EmployeeNotFoundException e) {}
	}
	
	@Test
	public void testCalcAllEmployeeSalaries() {
		empServTest.displayAllEmployees();
		System.out.println(empServTest.calculateYearlySalary());
		Assert.assertEquals(empServTest.calculateYearlySalary(), 180000.0, EPSILON);
	}
	
	@Test
	public void testDeletedEmployeeIsRemovedFromList() {
		try {
			empServTest.deleteEmployee(empServTest.findByEmployeeNo(1));
			Assert.assertNull(empServTest.findByEmployeeNo(1));
		}
		catch (EmployeeNotFoundException e) {}
	}
	
	@Test
	public void testSearchingNonExistingEmployeeThrowsException() {
		Assert.assertThrows(EmployeeNotFoundException.class, 
				() -> empServTest.findByEmployeeNo(-1));
	}
	
	@Test
	public void addedEmployeeCanBeFoundByEmpNo() {
		Employee e = new Employee(5, "TEST EMPLOYEE", 0.0, new Address("TEST", "ADDRESS"));
		empServTest.addNewEmployee(e);
		try {
			Assert.assertEquals(e, empServTest.findByEmployeeNo(5));
		} catch (EmployeeNotFoundException e1) {}
	}

}
