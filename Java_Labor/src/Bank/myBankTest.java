package Bank;

public class myBankTest {

	public static void main(String[] args) {
		Date myDate = new Date(23,3,1984);
		Date today = new Date(16,4,2016);
		Person myPerson = new Person("Katharina Schwab", myDate);
		Account myChecking = new CheckingAccount(myPerson, today, 0);	//constructor CheckingAccount
//		System.out.println(myChecking);					//toString CheckingAccount
		
		Bank myBank = new Bank(68052328, "Sparkasse");		//constructor Bank
		
//		myBank.addAccount(myChecking);						//addAccount Bank
//		System.out.println(myBank);							//toString Bank
		
		Date testDate = new Date(23,3,1984);
		Person testPerson = new Person("Manuel Mustermann", testDate);
		Account MustermannSaving = new SavingsAccount(testPerson, today);	//constructor SavingsAccount
		myBank.addAccount(MustermannSaving);
		
//		MustermannSaving.deposit(1000);
		
//		System.out.println(myChecking);						//toString SavingsAccount
//		
//		System.out.println(myBank);
		
//		myBank.deleteAccount(MustermannSaving);					//delete Account Bank
		
//		System.out.println(myBank);

//		testPerson.setName("Marion Mustermann");			//setName Person
//		myBank.addAccount(MustermannSaving);	
//		System.out.println(MustermannSaving);
//		
//		Date testDate2 = new Date(12,6,2005);				//setDateOfBirth Person
//		testPerson.setDateOfBirth(testDate2);
//		System.out.println(testPerson.getDateOfBirth());	//getDateOfBirth Person
//		
//		MustermannSaving.deposit(500);							//deposit SavingsAccount
//		//System.out.println(testAccount);
//		
//		Date testDate3 = new Date(6,7,2004);
//		Account mustermannChecking = new CheckingAccount(testPerson,testDate3,0);
//		myBank.addAccount(mustermannChecking);
//		System.out.println(myBank);
		
		Date testDate3 = new Date(6,7,2004);
		Account mySaving = new SavingsAccount(testPerson,today);
		myBank.addAccount(mySaving);
		
		
		myBank.transfer(MustermannSaving.getAccountNo(), mySaving.getAccountNo(), 100);
		System.out.println(myBank+"\n");
//		System.out.println(myBank.numbersOfAccounts+"\n");
		myBank.deleteAccount(MustermannSaving);
		System.out.println(myBank+"\n");
//		System.out.println(myBank.numbersOfAccounts+"\n");
//		System.out.println(myBank.accounts.length);
//		myChecking.deposit(200);
//		
//		myBank.transfer(myChecking.getAccountNo(), mustermannChecking.getAccountNo(),100);
//		System.out.println(myBank);		//transfer saving to checking
//		
//		System.out.println(myBank);
//		
//		myBank.transfer(mustermannChecking.getAccountNo(), MustermannSaving.getAccountNo(),200);
//		System.out.println("soll -200 "+mustermannChecking);		//transfer checking to saving
//		System.out.println("soll 500 "+MustermannSaving);
//		
//		myChecking.deposit(5000);
//		System.out.println(myChecking);
//		
//		myBank.transfer(myChecking.getAccountNo(),mustermannChecking.getAccountNo() , 250);
//		
//		boolean temp = testDate.isValidDate(-1,3,2008);
//		System.out.println(temp);
	}

}
