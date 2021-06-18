import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import employee.exceptions.EmployeeNotFoundException;
import employee.exceptions.EmployeeNumExistsException;
import employee.functions.Address;
import employee.functions.Employee;
import employee.functions.EmployeeServiceImpl;
import utilities.log4j.tools.LoggerTools;
import utilities.log4j.tools.UserInfoLog4JLevel;

public class EmployeeTests {
	
	private EmployeeServiceImpl empServTest;
	private final double EPSILON =  0.000001d;
	private static Logger log;
	
	@BeforeClass
	public static void initClass() {
		try {
			log = LoggerTools.getAndConfigureLogger(EmployeeTests.class.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Before
	public void initEach() {
		empServTest = EmployeeServiceImpl.getEmployeeServiceImpl(log);
		empServTest.clearAllEmployees();
		try {
			empServTest.addSampleData();
		} catch (EmployeeNumExistsException e) {
			log.log(UserInfoLog4JLevel.USERINFO, "Error adding sample data.");
			log.error("Initial sample data not properly uploaded: List may not have been properly cleared.", e);
		}
	}
	
	@Test
	public void testModifyingEmployeeName() {
		try {
			empServTest.findEmployeeByNo(1).setEmpName("Test");
			Assert.assertEquals(empServTest.findEmployeeByNo(1).getEmpName(), "Test");
	
		}
		catch (EmployeeNotFoundException e) {}
	}
	
	@Test
	public void testCalcAllEmployeeSalaries() {
		Assert.assertEquals(empServTest.calculateYearlySalary(), 180000.0, EPSILON);
	}
	
	@Test
	public void testDeletedEmployeeIsRemovedFromList() {
		try {
			empServTest.deleteEmployee(empServTest.findEmployeeByNo(1));
			Assert.assertNull(empServTest.findEmployeeByNo(1));
		}
		catch (EmployeeNotFoundException e) {}
	}
	
	@Test
	public void testSearchingNonExistingEmployeeThrowsException() {
		Assert.assertThrows(EmployeeNotFoundException.class, 
				() -> empServTest.findEmployeeByNo(-1));
	}
	
	@Test
	public void addedEmployeeCanBeFoundByEmpNo() {
		try {
			Employee e = new Employee(5, "TEST EMPLOYEE", 0.0, new Address("TEST", "ADDRESS"));
			empServTest.addNewEmployee(e);
			Assert.assertEquals(e, empServTest.findEmployeeByNo(5));
		} catch (EmployeeNotFoundException | EmployeeNumExistsException except) {}
	}
	
	@Test
	public void addingEmployeeWithExistingNumThrowsException() {
		Employee e = new Employee(1, "TEST EMPLOYEE", 0.0, new Address("TEST", "ADDRESS"));
		Assert.assertThrows(EmployeeNumExistsException.class, () -> empServTest.addNewEmployee(e));
	}

}
