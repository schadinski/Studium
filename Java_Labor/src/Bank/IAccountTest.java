package Bank;


import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class IAccountTest {

	Class<IAccount> testee;
	List<String> errors;
	
	@Before
	public void setUp() throws Exception {
		testee = IAccount.class;
		errors = new ArrayList<String>();
	}
	
	/****************************************************************
	 * UML2Code Tests. Is the given UML transformed to Java Code in 
	 * the right manner?
	 */

	@Test
	public void testDeposit() {
		try {
			Method m = testee.getDeclaredMethod("deposit", float.class);
			if(!m.getReturnType().getSimpleName().equalsIgnoreCase("boolean"))errors.add("Returntype is not boolean");
		}catch(NoSuchMethodException exc) {
			errors.add("Method deposit with Parametertype float does not exist");
		}
		finally{
			assertTrue(errors.toString(), errors.size()==0);
		}
	}
	
	@Test
	public void testWithdraw() {
		try {
			Method m = testee.getDeclaredMethod("withdraw", float.class);
			if(!m.getReturnType().getSimpleName().equalsIgnoreCase("boolean"))errors.add("Returntype is not boolean");
		}catch(NoSuchMethodException exc) {
			errors.add("Method withdraw with Parametertype float does not exist");
		}
		finally{
			assertTrue(errors.toString(), errors.size()==0);
		}
	}
	
	@Test
	public void testGetOwner() {
		try {
			Method m = testee.getDeclaredMethod("getOwner");
			if(!m.getReturnType().getSimpleName().equalsIgnoreCase("IPerson"))errors.add("Returntype is not IPerson");
		}catch(NoSuchMethodException exc) {
			errors.add("Method getOwner does not exist");
		}
		finally{
			assertTrue(errors.toString(), errors.size()==0);
		}
	}
	
	@Test
	public void testSetOwner() {
		try {
			Method m = testee.getDeclaredMethod("setOwner", IPerson.class);
			if(!m.getReturnType().getSimpleName().equalsIgnoreCase("void"))errors.add("Returntype is not void");
		}catch(NoSuchMethodException exc) {
			errors.add("Method setOwner with Parametertype IPerson does not exist");
		}
		finally{
			assertTrue(errors.toString(), errors.size()==0);
		}
	}
	
	@Test
	public void testSetAccountNo() {
		try {
			Method m = testee.getDeclaredMethod("setAccountNo", long.class);
			if(!m.getReturnType().getSimpleName().equalsIgnoreCase("void"))errors.add("Returntype is not void");
		}catch(NoSuchMethodException exc) {
			errors.add("Method setAccountNo with Parametertype long does not exist");
		}
		finally{
			assertTrue(errors.toString(), errors.size()==0);
		}
	}
	
	@Test
	public void testGetAccountNo() {
		try {
			Method m = testee.getDeclaredMethod("getAccountNo");
			if(!m.getReturnType().getSimpleName().equalsIgnoreCase("long"))errors.add("Returntype is not long");
		}catch(NoSuchMethodException exc) {
			errors.add("Method getAccountNo does not exist");
		}
		finally{
			assertTrue(errors.toString(), errors.size()==0);
		}
	}
	
	@Test
	public void testGetBalance() {
		try {
			Method m = testee.getDeclaredMethod("getBalance");
			if(!m.getReturnType().getSimpleName().equalsIgnoreCase("float"))errors.add("Returntype is not float");
		}catch(NoSuchMethodException exc) {
			errors.add("Method getBalance does not exist");
		}
		finally{
			assertTrue(errors.toString(), errors.size()==0);
		}
	}
}