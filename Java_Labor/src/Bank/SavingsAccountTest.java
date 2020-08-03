package Bank;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;




public class SavingsAccountTest {
	private Class<SavingsAccount> bc;
	private List<String> errors;
	
	@Before
	public void setUp() {
		
		bc = SavingsAccount.class;
		errors = new ArrayList<String>();
	}
	
	@Test
	public void testSavingsAccountExtendsAccount() {
		List<String> errors = new ArrayList<String>();
		
		if(!bc.getSuperclass().getSimpleName().equalsIgnoreCase("Account")) {
			errors.add("Klasse SavingsAccount erweitert nicht die Klasse Account");	
		}
		assertTrue(errors.toString(), errors.size()==0);		
	}
	
	@Test
	public void testDeposit() {
		try {
			Method m = bc.getDeclaredMethod("deposit", float.class);
			if(!m.getReturnType().getSimpleName().equalsIgnoreCase("boolean"))errors.add("Returntype is not boolean");
			if((m.getModifiers() & Modifier.PUBLIC)==0)errors.add("Methode deposit ist nicht public.");
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
			Method m = bc.getDeclaredMethod("withdraw", float.class);
			if(!m.getReturnType().getSimpleName().equalsIgnoreCase("boolean"))errors.add("Returntype is not boolean");
			if((m.getModifiers() & Modifier.PUBLIC)==0)errors.add("Methode withdraw ist nicht public.");
		}catch(NoSuchMethodException exc) {
			errors.add("Method withdraw with Parametertype float does not exist");
		}
		finally{
			assertTrue(errors.toString(), errors.size()==0);
		}
	}
	
	@Test
	public void testfixAccount() {
		try {
			Method m = bc.getDeclaredMethod("fixAccount", Date.class);
			if(!m.getReturnType().getSimpleName().equalsIgnoreCase("void"))errors.add("Returntype is not void");
			if((m.getModifiers() & Modifier.PUBLIC)==0)errors.add("Methode fixAccount ist nicht public.");
		}catch(NoSuchMethodException exc) {
			errors.add("Method fixAccount with Parametertype Date does not exist");
		}
		finally{
			assertTrue(errors.toString(), errors.size()==0);
		}
	}
	
	@Test
	public void testConstructor() {
		try {
			Constructor<SavingsAccount> m = bc.getDeclaredConstructor(IPerson.class, Date.class);
			if((m.getModifiers() & Modifier.PUBLIC)==0)errors.add("Constructor ist nicht public");
		}catch(NoSuchMethodException exc) {
			errors.add("Constructor mit den Parametertypen IPerson, Date existiert nicht");
		}
		finally{
			assertTrue(errors.toString(), errors.size()==0);
		}
	}
}
