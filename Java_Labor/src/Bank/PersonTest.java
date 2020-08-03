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





public class PersonTest {

	Class<Person> testee;
	List<String> errors;
	
	@Before
	public void setUp() throws Exception {
		testee = Person.class;
		errors = new ArrayList<String>();
	}
	
	/****************************************************************
	 * UML2Code Tests. Is the given UML transformed to Java Code in 
	 * the right manner?
	 */

	@Test
	public void testPersonImplementsIPerson() {
		List<String> errors = new ArrayList<String>();
		
		if(testee.getInterfaces().length==0 
				||!testee.getInterfaces()[0].getSimpleName().equalsIgnoreCase("IPerson")) {
			errors.add("Klasse Person implementiert nicht das Interface IPerson");	
		}
		assertTrue(errors.toString(), errors.size()==0);		
	}
	
	@Test
	public void testGetName() {
		try {
			Method m = testee.getDeclaredMethod("getName");
			if(!m.getReturnType().getSimpleName().equalsIgnoreCase("String"))errors.add("Returntype is not String");
			if((m.getModifiers() & Modifier.PUBLIC)==0)errors.add("Methode getName ist nicht public.");
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
			if((m.getModifiers() & Modifier.PUBLIC)==0)errors.add("Methode setName ist nicht public.");
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
			if((m.getModifiers() & Modifier.PUBLIC)==0)errors.add("Methode setDateOfBirth ist nicht public.");
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
			if((m.getModifiers() & Modifier.PUBLIC)==0)errors.add("Methode setDateOfBirth ist nicht public.");
		}catch(NoSuchMethodException exc) {
			errors.add("Method getDateOfBirth does not exist");
		}
		finally{
			assertTrue(errors.toString(), errors.size()==0);
		}
	}
	
	@Test
	public void testConstructor() {
		try {
			Constructor<Person> m = testee.getDeclaredConstructor(String.class, Date.class);
			if((m.getModifiers() & Modifier.PUBLIC)==0)errors.add("Constructor ist nicht public");
		}catch(NoSuchMethodException exc) {
			errors.add("Constructor mit den Parametertypen String, Date existiert nicht");
		}
		finally{
			assertTrue(errors.toString(), errors.size()==0);
		}
	}
}