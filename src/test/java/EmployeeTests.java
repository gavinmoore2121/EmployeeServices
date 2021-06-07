import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
		empServTest.findByEmployeeNo(1).setEmpName("Test");
		Assert.assertEquals(empServTest.findByEmployeeNo(1).getEmpName(), "Test");
	}
	
	@Test
	public void testCalcAllEmployeeSalaries() {
		empServTest.displayAllEmployees();
		System.out.println(empServTest.calculateYearlySalary());
		Assert.assertEquals(empServTest.calculateYearlySalary(), 180000.0, EPSILON);
	}
	
	@Test
	public void testDeletedEmployeeIsRemovedFromList() {
		empServTest.deleteEmployee(empServTest.findByEmployeeNo(1));
		Assert.assertNull(empServTest.findByEmployeeNo(1));
		
	}

}
