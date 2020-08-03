package Bank;


import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class AccountTest {

	//private Account testee;
	private Class<Account> bc;
	private List<String> errors;
	
	@Before
	public void setUp() {
		/*Calendar date = Calendar.getInstance();
		date.set(1972, 3, 18);
		IPerson owner = new Person("Patrick Luchner", date);
		testee = new CheckingAccount(owner,Calendar.getInstance(),1000f);*/
		bc = Account.class;
		errors = new ArrayList<String>();
	}
	
	/***********************************************************************************************
	 * UML Diagram Tests. Is the UML diagram transformed to code in a correct way?
	 ***********************************************************************************************/
	
	@Test
	public void testAccountImplementsIAccount() {
		List<String> errors = new ArrayList<String>();
		
		if(bc.getInterfaces().length==0 
				||!bc.getInterfaces()[0].getSimpleName().equalsIgnoreCase("IAccount")) {
			errors.add("Klasse Account implementiert nicht das Interface IAccount");	
		}
		assertTrue(errors.toString(), errors.size()==0);		
	}
	
	@Test
	public void testAttributeOwner() {
		try {
			Field f = bc.getDeclaredField("owner");
			if((f.getModifiers() & Modifier.PROTECTED)==0)errors.add("Attribut owner ist nicht protected");
			if(!f.getType().getSimpleName().equalsIgnoreCase("IPerson"))errors.add("Attribut owner ist nicht vom Typ IPerson");
		}catch(NoSuchFieldException exc) {
			errors.add("Feld owner existiert nicht");
		}
		finally{
			assertTrue(errors.toString(), errors.size()==0);
		}
	}
	
	@Test
	public void testAttributeAccountNumber() {
		try {
			Field f = bc.getDeclaredField("accountNumber");
			if((f.getModifiers() & Modifier.PROTECTED)==0)errors.add("Attribut accountNumber ist nicht protected");
			if(!f.getType().getSimpleName().equalsIgnoreCase("long"))errors.add("Attribut accountNumber ist nicht vom Typ long");
		}catch(NoSuchFieldException exc) {
			errors.add("Feld accountNumber existiert nicht");
		}
		finally{
			assertTrue(errors.toString(), errors.size()==0);
		}
	}
	
	@Test
	public void testAttributeBalance() {
		try {
			Field f = bc.getDeclaredField("balance");
			if((f.getModifiers() & Modifier.PROTECTED)==0)errors.add("Attribut balance ist nicht protected");
			if(!f.getType().getSimpleName().equalsIgnoreCase("float"))errors.add("Attribut balance ist nicht vom Typ float");
		}catch(NoSuchFieldException exc) {
			errors.add("Feld balance existiert nicht");
		}
		finally{
			assertTrue(errors.toString(), errors.size()==0);
		}
	}
	
	
	
	@Test
	public void testDeposit() {
		try {
			Method m = bc.getDeclaredMethod("deposit", float.class);
			if(!m.getReturnType().getSimpleName().equalsIgnoreCase("boolean"))errors.add("Returntype is not boolean");
			if((m.getModifiers() & Modifier.ABSTRACT)==0)errors.add("Methode deposit ist nicht abstract.");
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
			if((m.getModifiers() & Modifier.ABSTRACT)==0)errors.add("Methode withdraw ist nicht abstract.");
			if((m.getModifiers() & Modifier.PUBLIC)==0)errors.add("Methode withdraw ist nicht public.");
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
			Method m = bc.getDeclaredMethod("getOwner");
			if(!m.getReturnType().getSimpleName().equalsIgnoreCase("IPerson"))errors.add("Returntype is not IPerson");
			if((m.getModifiers() & Modifier.PUBLIC)==0)errors.add("Methode getOwner ist nicht public.");
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
			Method m = bc.getDeclaredMethod("setOwner", IPerson.class);
			if(!m.getReturnType().getSimpleName().equalsIgnoreCase("void"))errors.add("Returntype is not void");
			if((m.getModifiers() & Modifier.PUBLIC)==0)errors.add("Methode setOwner ist nicht public.");
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
			Method m = bc.getDeclaredMethod("setAccountNo", long.class);
			if(!m.getReturnType().getSimpleName().equalsIgnoreCase("void"))errors.add("Returntype is not void");
			if((m.getModifiers() & Modifier.PUBLIC)==0)errors.add("Methode setAccountNo ist nicht public.");
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
			Method m = bc.getDeclaredMethod("getAccountNo");
			if(!m.getReturnType().getSimpleName().equalsIgnoreCase("long"))errors.add("Returntype is not long");
			if((m.getModifiers() & Modifier.PUBLIC)==0)errors.add("Methode getAccountNo ist nicht public.");
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
			Method m = bc.getDeclaredMethod("getBalance");
			if(!m.getReturnType().getSimpleName().equalsIgnoreCase("float"))errors.add("Returntype is not float");
			if((m.getModifiers() & Modifier.PUBLIC)==0)errors.add("Methode getBalance ist nicht public.");
		}catch(NoSuchMethodException exc) {
			errors.add("Method getBalance does not exist");
		}
		finally{
			assertTrue(errors.toString(), errors.size()==0);
		}
	}
	
	
}
