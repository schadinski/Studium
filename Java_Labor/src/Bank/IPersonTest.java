package Bank;


import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;





public class IPersonTest {

	Class<IPerson> testee;
	List<String> errors;
	
	@Before
	public void setUp() throws Exception {
		testee = IPerson.class;
		errors = new ArrayList<String>();
	}
	
	/****************************************************************
	 * UML2Code Tests. Is the given UML transformed to Java Code in 
	 * the right manner?
	 */

	@Test
	public void testGetName() {
		try {
			Method m = testee.getDeclaredMethod("getName");
			if(!m.getReturnType().getSimpleName().equalsIgnoreCase("String"))errors.add("Returntype is not String");
		}catch(NoSuchMethodException exc) {
			errors.add("Method getName does not exist");
		}
		finally{
			assertTrue(errors.toString(), errors.size()==0);
		}
	}
	
	@Test
	public void testSetName() {
		try {
			Method m = testee.getDeclaredMethod("setName", String.class);
			if(!m.getReturnType().getSimpleName().equalsIgnoreCase("void"))errors.add("Returntype is not void");
		}catch(NoSuchMethodException exc) {
			errors.add("Method setName with Parametertype String does not exist");
		}
		finally{
			assertTrue(errors.toString(), errors.size()==0);
		}
	}
	
	@Test
	public void testSetDateOfBirth() {
		try {
			Method m = testee.getDeclaredMethod("setDateOfBirth", Date.class);
			if(!m.getReturnType().getSimpleName().equalsIgnoreCase("void"))errors.add("Returntype is not void");
		}catch(NoSuchMethodException exc) {
			errors.add("Method setDateOfBirth with Parametertype Date does not exist");
		}
		finally{
			assertTrue(errors.toString(), errors.size()==0);
		}
	}
	
	@Test
	public void testGetDateOfBirth() {
		try {
			Method m = testee.getDeclaredMethod("getDateOfBirth");
			if(!m.getReturnType().getSimpleName().equalsIgnoreCase("Date"))errors.add("Returntype is not Date");
		}catch(NoSuchMethodException exc) {
			errors.add("Method getDateOfBirth does not exist");
		}
		finally{
			assertTrue(errors.toString(), errors.size()==0);
		}
	}
}