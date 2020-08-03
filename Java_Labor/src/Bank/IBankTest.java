package Bank;


import static org.junit.Assert.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class IBankTest {

	Class<IBank> testee;
	List<String> errors;
	
	@Before
	public void setUp() throws Exception {
		testee = IBank.class;
		errors = new ArrayList<String>();
	}
	
	/****************************************************************
	 * UML2Code Tests. Is the given UML transformed to Java Code in 
	 * the right manner?
	 */

	@Test
	public void testAddAccount() {
		try {
			Method m = testee.getDeclaredMethod("addAccount", IAccount.class);
			if(!m.getReturnType().getSimpleName().equalsIgnoreCase("void"))errors.add("Returntype is not void");
		}catch(NoSuchMethodException exc) {
			errors.add("Method addAccount with Parametertype IAccount does not exist");
		}
		finally{
			assertTrue(errors.toString(), errors.size()==0);
		}
	}
	
	
	@Test
	public void testDeleteAccount() {
		try {
			Method m = testee.getDeclaredMethod("deleteAccount", IAccount.class);
			if(!m.getReturnType().getSimpleName().equalsIgnoreCase("void"))errors.add("Returntype is not void");
		}catch(NoSuchMethodException exc) {
			errors.add("Method deleteAccount with parameter type IAccount does not exist");
		}
		finally{
			assertTrue(errors.toString(), errors.size()==0);
		}
	}
	
	
	@Test
	public void testTransfer() {
		try {
			Method m = testee.getDeclaredMethod("transfer", long.class,long.class,float.class);
			if(!m.getReturnType().getSimpleName().equalsIgnoreCase("boolean"))errors.add("Returntype is not boolean");
		}catch(NoSuchMethodException exc) {
			errors.add("Method transfer with Parametertype long, long, float does not exist");
		}
		finally{
			assertTrue(errors.toString(), errors.size()==0);
		}
	}
}
