package Bank;


import static org.junit.Assert.*;
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


public class BankTest {

	private Bank testee;
	private Class<Bank> bc;
	private List<String> errors;
	
	@Before
	public void setUp() {
		testee = new Bank(66450050,"Sparkasse Offenburg/Ortenau");
		bc = Bank.class;
		errors = new ArrayList<String>();
	}
	
	/***********************************************************************************************
	 * UML Diagram Tests. Is the UML diagram transformed to code in a correct way?
	 ***********************************************************************************************/
	
	@Test
	public void testBankImplementsIBank() {
		List<String> errors = new ArrayList<String>();
		
		if(bc.getInterfaces().length==0 
				||!bc.getInterfaces()[0].getSimpleName().equalsIgnoreCase("IBank")) {
			errors.add("Klasse Bank implementiert nicht das Interface IBank");	
		}
		assertTrue(errors.toString(), errors.size()==0);		
	}
	
	@Test
	public void testAttributeAccounts() {
		try {
			Field f = bc.getDeclaredField("accounts");
			if((f.getModifiers() & Modifier.PROTECTED)==0)errors.add("Attribut accounts ist nicht protected");
			if(!f.getType().getSimpleName().equalsIgnoreCase("IAccount[]"))errors.add("Attribut accounts ist nicht vom Typ IAccount");
			if(!f.getType().isArray())errors.add("Attributtyp von accounts ist kein Array");
		}catch(NoSuchFieldException exc) {
			errors.add("Feld accounts existiert nicht");
		}
		finally{
			assertTrue(errors.toString(), errors.size()==0);
		}
	}
	
	@Test
	public void testAttributeBlz() {
		try {
			Field f = bc.getDeclaredField("blz");
			if((f.getModifiers() & Modifier.PROTECTED)==0)errors.add("Attribut blz ist nicht protected");
			if(!f.getType().getSimpleName().equalsIgnoreCase("int"))errors.add("Attribut blz ist nicht vom Typ int");
		}catch(NoSuchFieldException exc) {
			errors.add("Feld blz existiert nicht");
		}
		finally{
			assertTrue(errors.toString(), errors.size()==0);
		}
	}
	
	@Test
	public void testAttributeName() {
		try {
			Field f = bc.getDeclaredField("name");
			if((f.getModifiers() & Modifier.PROTECTED)==0)errors.add("Attribut name ist nicht protected");
			if(!f.getType().getSimpleName().equalsIgnoreCase("String"))errors.add("Attribut name ist nicht vom Typ String");
		}catch(NoSuchFieldException exc) {
			errors.add("Feld String existiert nicht");
		}
		finally{
			assertTrue(errors.toString(), errors.size()==0);
		}
	}
	
	@Test
	public void testAddAccount() {
		try {
			Method m = bc.getDeclaredMethod("addAccount",IAccount.class);
			if((m.getModifiers() & Modifier.PUBLIC)==0)errors.add("Methode addAccount ist nicht public");
			if(!m.getReturnType().getSimpleName().equalsIgnoreCase("void"))errors.add("Methode addAccount hat einen Rückgabewert ungleich void");
		}catch(NoSuchMethodException exc) {
			errors.add("Methode addAccount mit den Parametertypen IPerson, IAccount existiert nicht");
		}
		finally{
			assertTrue(errors.toString(), errors.size()==0);
		}
	}
	
	
	@Test
	public void testDeleteAccount() {
		try {
			Method m = bc.getDeclaredMethod("deleteAccount", IAccount.class);
			if((m.getModifiers() & Modifier.PUBLIC)==0)errors.add("Methode deleteAccount ist nicht public");
			if(!m.getReturnType().getSimpleName().equalsIgnoreCase("void"))errors.add("Methode deleteAccount hat einen Rückgabewert ungleich void");
		}catch(NoSuchMethodException exc) {
			errors.add("Methode deleteAccount mit den Parametertypen IAccount existiert nicht");
		}
		finally{
			assertTrue(errors.toString(), errors.size()==0);
		}
	}
	
	
	@Test
	public void testTransfer() {
		try {
			Method m = bc.getDeclaredMethod("transfer", long.class,long.class, float.class);
			if((m.getModifiers() & Modifier.PUBLIC)==0)errors.add("Methode transfer ist nicht public");
			if(!m.getReturnType().getSimpleName().equalsIgnoreCase("boolean"))errors.add("Methode transfer hat einen Rückgabewert ungleich boolean");
		}catch(NoSuchMethodException exc) {
			errors.add("Methode transfer mit den Parametertypen long, long, float existiert nicht");
		}
		finally{
			assertTrue(errors.toString(), errors.size()==0);
		}
	}
	
	@Test
	public void testToString() {
		try {
			Method m = bc.getDeclaredMethod("toString");
			if((m.getModifiers() & Modifier.PUBLIC)==0)errors.add("Methode toString ist nicht public");
			if(!m.getReturnType().getSimpleName().equalsIgnoreCase("String"))errors.add("Methode toString hat einen Rückgabewert ungleich String");
		}catch(NoSuchMethodException exc) {
			errors.add("Methode toString ohne Parametertypen existiert nicht");
		}
		finally{
			assertTrue(errors.toString(), errors.size()==0);
		}
	}
	
	@Test
	public void testCheckTransfer() {
		try {
			Method m = bc.getDeclaredMethod("checkTransfer", IAccount.class,IAccount.class, float.class);
			if((m.getModifiers() & Modifier.PRIVATE)==0)errors.add("Methode checkTransfer ist nicht private");
			if(!m.getReturnType().getSimpleName().equalsIgnoreCase("boolean"))errors.add("Methode checkTransfer hat einen Rückgabewert ungleich boolean");
		}catch(NoSuchMethodException exc) {
			errors.add("Methode checkTransfer mit den Parametertypen IAccount, IAccount, float existiert nicht");
		}
		finally{
			assertTrue(errors.toString(), errors.size()==0);
		}
	}
	
	@Test
	public void testConstructor() {
		try {
			Constructor<Bank> m = bc.getDeclaredConstructor(int.class,String.class);
			if((m.getModifiers() & Modifier.PUBLIC)==0)errors.add("Constructor ist nicht public");
		}catch(NoSuchMethodException exc) {
			errors.add("Constructor mit den Parametertypen int, String existiert nicht");
		}
		finally{
			assertTrue(errors.toString(), errors.size()==0);
		}
	}
	
	
	/****************************************************************************************************************
	 * Unit Tests. Testing the correct implementation of the functional requirements.
	 ****************************************************************************************************************/
	
	@Test
	public void testBankToString() {
		Date dateOfBirth = new Date(28, 5, 1970);
		IPerson customer = new Person("Hans Wurst", dateOfBirth);
		IAccount newAccount = new SavingsAccount(customer, new Date(30, 12, 2015));
		newAccount.setAccountNo(12345);
		testee.addAccount(newAccount);
		Date dateOfBirth1 = new Date(28, 5, 1970);
		IPerson customer1 = new Person("Hans Wurst", dateOfBirth1);
		IAccount newAccount1 = new CheckingAccount(customer1, new Date(30, 12, 2015) ,1500f);
		newAccount1.setAccountNo(67890);
		testee.addAccount(newAccount1);
		
		
	}
	
	
	
	@Test
	public void testBankAddAccountFirstAccount() {
		IAccount[] acs= null;
		Date dateOfBirth = new Date(28, 5, 1970);
		IPerson customer = new Person("Hans Wurst", dateOfBirth);
		IAccount newAccount = new SavingsAccount(customer, new Date(30, 12, 2015));
		newAccount.setAccountNo(12345);
		testee.addAccount(newAccount);
		try {
			Field accounts = testee.getClass().getDeclaredField("accounts");
			accounts.setAccessible(true);
			acs=(IAccount[]) accounts.get(testee);
		}catch(NoSuchFieldException exc) {
			exc.printStackTrace();
		}catch(IllegalAccessException exc) {
			exc.printStackTrace();
		}
		assertEquals(acs[0], newAccount);	
	}
	
	@Test
	public void testBankAddAccountTwoTimesSameAccount() {
		IAccount[] acs= null;
		Date dateOfBirth = new Date(28, 5, 1970);
		IPerson customer = new Person("Hans Wurst", dateOfBirth);
		IAccount newAccount = new SavingsAccount(customer, new Date(30, 12, 2015));
		newAccount.setAccountNo(12345);
		testee.addAccount(newAccount);
		testee.addAccount(newAccount);
		try {
			Field accounts = testee.getClass().getDeclaredField("accounts");
			accounts.setAccessible(true);
			acs=(IAccount[]) accounts.get(testee);
		}catch(NoSuchFieldException exc) {
			exc.printStackTrace();
		}catch(IllegalAccessException exc) {
			exc.printStackTrace();
		}
		double noOfAccounts = 0;
		for (int i=0; i<acs.length; i++) {
			if (acs[i]!= null)
				noOfAccounts++;
		}
		assertEquals(noOfAccounts,1,0);
	}
	
	
	@Test
	public void testBankDeleteOneAccount() {
		IAccount[] acs= null;
		Date dateOfBirth = new Date(28, 5, 1970);
		IPerson customer = new Person("Hans Wurst", dateOfBirth);
		IAccount newAccount = new SavingsAccount(customer, new Date(30, 12, 2015));
		newAccount.setAccountNo(12345);
		testee.addAccount(newAccount);
		testee.deleteAccount(newAccount);
		try {
			Field accounts = testee.getClass().getDeclaredField("accounts");
			accounts.setAccessible(true);
			acs=(IAccount[]) accounts.get(testee);
		}catch(NoSuchFieldException exc) {
			exc.printStackTrace();
		}catch(IllegalAccessException exc) {
			exc.printStackTrace();
		}
		double noOfAccounts = 0;
		for (int i=0; i<acs.length; i++) {
			if (acs[i]!= null)
				noOfAccounts++;
		}
		assertEquals(noOfAccounts, 0, 0);
		
		//assertEquals(0, acs.length, 0);
	}
	
	
	@Test
	public void testBankDeleteTwoAccounts() {
		IAccount[] acs= null;
		Date dateOfBirth = new Date(28, 5, 1970);
		IPerson customer = new Person("Hans Wurst", dateOfBirth);
		IAccount newAccount = new SavingsAccount(customer, new Date(30, 12, 2015));
		newAccount.setAccountNo(12345);
		testee.addAccount(newAccount);
		IAccount newAccount2 = new SavingsAccount(customer, new Date(30, 12, 2015));
		newAccount2.setAccountNo(112233);
		testee.addAccount(newAccount2);
		testee.deleteAccount(newAccount);
		try {
			Field accounts = testee.getClass().getDeclaredField("accounts");
			accounts.setAccessible(true);
			acs=(IAccount[]) accounts.get(testee);
		}catch(NoSuchFieldException exc) {
			exc.printStackTrace();
		}catch(IllegalAccessException exc) {
			exc.printStackTrace();
		}
		double noOfAccounts = 0;
		for (int i=0; i<acs.length; i++) {
			if (acs[i]!= null)
				noOfAccounts++;
		}
		assertEquals(1, noOfAccounts, 0);
		
		//assertEquals(1, acs.length, 0);
	}
	
	@Test
	public void testBankDeleteMiddleThreeAccounts() {
		IAccount[] acs= null;
		Date dateOfBirth = new Date(28, 5, 1970);
		IPerson customer = new Person("Hans Wurst", dateOfBirth);
		IAccount newAccount = new SavingsAccount(customer, new Date(30, 12, 2015));
		newAccount.setAccountNo(12345);
		testee.addAccount(newAccount);
		IAccount newAccount2 = new SavingsAccount(customer, new Date(30, 12, 2015));
		newAccount2.setAccountNo(112233);
		testee.addAccount(newAccount2);
		IAccount newAccount3 = new SavingsAccount(customer, new Date(30, 12, 2015));
		newAccount3.setAccountNo(1144433);
		testee.addAccount(newAccount3);
		testee.deleteAccount(newAccount2);
		try {
			Field accounts = testee.getClass().getDeclaredField("accounts");
			accounts.setAccessible(true);
			acs=(IAccount[]) accounts.get(testee);
		}catch(NoSuchFieldException exc) {
			exc.printStackTrace();
		}catch(IllegalAccessException exc) {
			exc.printStackTrace();
		}
		double noOfAccounts = 0;
		for (int i=0; i<acs.length; i++) {
			if (acs[i]!= null)
				noOfAccounts++;
		}
		assertEquals(2, noOfAccounts, 0);
		// assertEquals(2, acs.length, 0);
	}
	
	@Test
	public void testTransferTwoCheckingAccounts() {
		Date dateOfBirth = new Date(28, 5, 1970);
		IPerson customer = new Person("Christine Luchner", dateOfBirth);
		IPerson customer1 = new Person("Hans Wurst", dateOfBirth);
		IAccount newAccount = new CheckingAccount(customer, new Date(30, 12, 2015), 0f);
		newAccount.setAccountNo(12345);
		newAccount.deposit(200);
		testee.addAccount(newAccount);
		IAccount newAccount2 = new CheckingAccount(customer1, new Date(30, 12, 2015), 0f);
		newAccount2.setAccountNo(112233);
		testee.addAccount(newAccount2);
		assertTrue(testee.transfer(12345, 112233, 100));
		//System.out.println(newAccount.getBalance() +" "+ newAccount2.getBalance());
		assertTrue(newAccount.getBalance() == 100 && newAccount2.getBalance() == 100);
	}

//	@Test
//	public void testTransferTwoCheckingAccountsPositiveLimits() {
//		Date dateOfBirth = new Date(28, 5, 1970);
//		IPerson customer = new Person("Christine Luchner", dateOfBirth);
//		IPerson customer1 = new Person("Hans Wurst", dateOfBirth);
//		IAccount newAccount = new CheckingAccount(customer, new Date(30, 12, 2015), 500f);
//		newAccount.setAccountNo(12345);
//		testee.addAccount(newAccount);
//		IAccount newAccount2 = new CheckingAccount(customer1, new Date(30, 12, 2015), 1000f);
//		newAccount2.setAccountNo(112233);
//		testee.addAccount(newAccount2);
//		assertTrue(testee.transfer(12345, 112233, 100));
//		//System.out.println(newAccount.getBalance() +" "+ newAccount2.getBalance());
//		assertTrue(newAccount.getBalance() == -100 && newAccount2.getBalance() == +100);
//	}

	
	@Test
	public void testTransferTwoSavingsAccounts() {
		Date dateOfBirth = new Date(28, 5, 1970);
		IPerson customer = new Person("Christine Luchner", dateOfBirth);
		//IPerson customer1 = new Person("Hans Wurst", dateOfBirth);
		IAccount newAccount = new SavingsAccount(customer, new Date(30, 12, 2015));
		newAccount.setAccountNo(12345);
		newAccount.deposit(1000);
		testee.addAccount(newAccount);
		IAccount newAccount2 = new SavingsAccount(customer, new Date(30, 12, 2015));
		newAccount2.setAccountNo(112233);
		testee.addAccount(newAccount2);
		assertTrue(testee.transfer(12345, 112233, 100));
		//System.out.println(newAccount.getBalance() +" "+ newAccount2.getBalance());
		assertTrue(newAccount.getBalance() == 900 && newAccount2.getBalance() == +100);
	}
	
	@Test
	public void testTransferCheckingtoSavingsAccount() {
		Date dateOfBirth = new Date(28, 5, 1970);
		IPerson customer = new Person("Christine Luchner", dateOfBirth);
		//IPerson customer1 = new Person("Hans Wurst", dateOfBirth);
		IAccount newAccount = new CheckingAccount(customer, new Date(30, 12, 2015), 0f);
		newAccount.setAccountNo(12345);
		newAccount.deposit(200);
		testee.addAccount(newAccount);
		IAccount newAccount2 = new SavingsAccount(customer, new Date(30, 12, 2015));
		newAccount2.setAccountNo(112233);
		testee.addAccount(newAccount2);
		assertTrue(testee.transfer(12345, 112233, 100));
		//System.out.println(newAccount.getBalance() +" "+ newAccount2.getBalance());
		assertTrue(newAccount.getBalance() == 100 && newAccount2.getBalance() == 100);
	}
	
	@Test
	public void testTransferSavingstoCheckingAccount() {
		Date dateOfBirth = new Date(28, 5, 1970);
		IPerson customer = new Person("Christine Luchner", dateOfBirth);
		//IPerson customer1 = new Person("Hans Wurst", dateOfBirth);
		IAccount newAccount = new CheckingAccount(customer, new Date(30, 12, 2015), -500f);
		newAccount.setAccountNo(12345);
		testee.addAccount(newAccount);
		IAccount newAccount2 = new SavingsAccount(customer, new Date(30, 12, 2015));
		newAccount2.setAccountNo(112233);
		testee.addAccount(newAccount2);
		newAccount2.deposit(1000);
		assertTrue(testee.transfer(112233, 12345, 100));
		//System.out.println(newAccount.getBalance() +" "+ newAccount2.getBalance());
		assertTrue(newAccount.getBalance() == 100 && newAccount2.getBalance() == 900);
	}
	
	@Test
	public void testTransferSameAccount() {
		Date dateOfBirth = new Date(28, 5, 1970);
		IPerson customer = new Person("Hans Wurst", dateOfBirth);
		IAccount newAccount = new SavingsAccount(customer, new Date(30, 12, 2015));
		newAccount.setAccountNo(12345);
		testee.addAccount(newAccount);
		assertFalse(testee.transfer(newAccount.getAccountNo(), newAccount.getAccountNo(), 1000));
	}
	
	
	@Test 
	public void testTransferOverseedLimit() {
		Date dateOfBirth = new Date(28, 5, 1970);
		IPerson customer = new Person("Hans Wurst", dateOfBirth);
		IAccount newAccount = new CheckingAccount(customer, new Date(30, 12, 2015),500f);
		newAccount.setAccountNo(12345);
		testee.addAccount(newAccount);
		IAccount newAccount2 = new CheckingAccount(customer, new Date(30, 12, 2015),500);
		newAccount2.setAccountNo(112233);
		testee.addAccount(newAccount2);
		assertFalse(testee.transfer(12345, 112233, 1000));
	}
	
	@Test
	public void testTransferDifferentOwner() {
		Date dateOfBirth = new Date(28, 5, 1970);
		IPerson customer = new Person("Christine Luchner", dateOfBirth);
		IPerson customer1 = new Person("Hans Wurst", dateOfBirth);
		IAccount newAccount = new SavingsAccount(customer,new Date(30, 12, 2015));
		newAccount.setAccountNo(12345);
		testee.addAccount(newAccount);
		IAccount newAccount2 = new SavingsAccount(customer1, new Date(30, 12, 2015));
		newAccount2.setAccountNo(112233);
		testee.addAccount(newAccount2);
		assertFalse(testee.transfer(12345, 112233, 10));
	}
	
	@Test
	public void testTransferNegativeBalance() {
		Date dateOfBirth = new Date(28, 5, 1970);
		IPerson customer = new Person("Hans Wurst", dateOfBirth);
		IAccount newAccount = new SavingsAccount(customer, new Date(30, 12, 2015));
		newAccount.setAccountNo(12345);
		testee.addAccount(newAccount);
		IAccount newAccount2 = new SavingsAccount(customer, new Date(30, 12, 2015));
		newAccount2.setAccountNo(112233);
		testee.addAccount(newAccount2);
		assertFalse(testee.transfer(12345, 112233, 100));
	}
	
	@Test
	public void testTransferFixed() {
		Date dateOfBirth = new Date(28, 5, 1970);
		IPerson customer = new Person("Hans Wurst", dateOfBirth);
		IAccount newAccount = new SavingsAccount(customer, new Date(30, 12, 2015));
		newAccount.setAccountNo(12345);
		SavingsAccount sa = ((SavingsAccount)newAccount);
		sa.fixAccount(new Date(30, 12, 2015));
		newAccount = sa;
		testee.addAccount(newAccount);
		IAccount newAccount2 = new SavingsAccount(customer, new Date(30, 12, 2015));
		newAccount2.setAccountNo(112233);
		testee.addAccount(newAccount2);
		assertFalse(testee.transfer(12345, 112233, 100));
	}
	
	@Test 
	public void testTransferCreationDate() {
		Date creationDate = new Date(12, 3, 1999);
		Date dateOfBirth = new Date(28, 5, 1970);
		IPerson customer = new Person("Hans Wurst", dateOfBirth);
		IAccount newAccount = new SavingsAccount(customer, new Date(30, 12, 2015));
		newAccount.setAccountNo(12345);
		newAccount.deposit(1000f);
		testee.addAccount(newAccount);
		IAccount newAccount2 = new SavingsAccount(customer, creationDate);
		newAccount2.setAccountNo(112233);
		testee.addAccount(newAccount2);
		assertFalse(testee.transfer(12345, 112233, 100));
	}
}
