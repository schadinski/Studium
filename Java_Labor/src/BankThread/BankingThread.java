package BankThread;
import Bank.Account;
import Bank.Bank;
import Bank.CheckingAccount;
import Bank.Date;
import Bank.Person;;


public class BankingThread extends Thread {
	private static final Bank bank = new Bank(12345678, "Bankhaus Raff & Gier");
	private long accountNo1, accountNo2;
	
	public BankingThread(long accNo1, long accNo2) {
		accountNo1 = accNo1;
		accountNo2 = accNo2;
	}
	
	@Override
	public void run() {
		for (int i = 0; i<100; i++){
		bank.transfer(accountNo1, accountNo2, 1);
		bank.transfer(accountNo2, accountNo1, 1);
		}
	}
	
	public static void main(String args[])throws InterruptedException {
		Date today = new Date(27,4,2016);
		
		Date dateK1 = new Date(23,3,1984);
		Person personK1 = new Person("Marion MeierK1", dateK1);
		Account k1 = new CheckingAccount(personK1, today, 0);
		
		Date dateK2 = new Date(24,8,2013);
		Person personK2 = new Person("Ines IrgendwerK2", dateK2);
		Account k2 = new CheckingAccount(personK2, today, 0);
		
		bank.addAccount(k1);
		bank.addAccount(k2);
		
		k1.deposit(100);
		k2.deposit(200);
		
		//test
//		System.out.println(bank);
//		
//		bank.transfer(k1.getAccountNo(), k2.getAccountNo(), 10);
//		System.out.println(bank);
//		bank.transfer(k2.getAccountNo(), k1.getAccountNo(), 10);
//		System.out.println(bank);
		
		Thread t1 = new BankingThread(k1.getAccountNo(), k2.getAccountNo());
		Thread t2 = new BankingThread(k1.getAccountNo(), k2.getAccountNo());
		
		t1.start();
		t2.start();
		
		
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException joinException) {
			throw new InterruptedException ("Cannot Join");
		}
			System.out.println(bank);
		/*
		 * Aufgabe 2 c
		 * Es wird deutlich, dass sich das tatsächlich vorhandene Geld vermehrt,
		 * aber das darf natürlich nicht passieren.Der Grund ist das sog. Race Condition, das auftritt, wenn
		 * mehrere Threads auf Variablen zugreifen. Die Variablen müssen durch das Anlegen kritischer 
		 * Abschnitte geschützt werden.
		 */
			
	}
}
