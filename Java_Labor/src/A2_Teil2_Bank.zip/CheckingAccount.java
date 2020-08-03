package Bank;

public class CheckingAccount extends Account {
	private Date creationDate;
	private float creditLimit;		//Dispolimit
	/*	protected IPerson owner;
		protected long accountNumber;
		protected float balance;
	 */

	//Constructor
	public CheckingAccount(IPerson owner, Date creationDate, float creditLimit){
		this.owner = owner;
		this.creationDate = creationDate;
		this.creditLimit = creditLimit;	
		this.balance = 0;
		this.accountNumber = createAccountnumber();
		
	}
	
	@Override
	public synchronized boolean deposit(float amount) {
		//einzahlen
				
		if(isPossibleToDeposit(amount)){
			synchronized (lock) {
			this.balance += amount;
			}
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public synchronized boolean withdraw(float amount) {
		// abheben
		if(isPossibleToWithDraw(amount)){
			synchronized (lock) {
			this.balance -= amount;
			}
			return true;
		}
		
		else{
			return false;
		}
		
	}
	
	@Override
	public String toString(){
		String temp = this.accountNumber+ ", Checking Account ,"+this.owner.getName()+", "+this.balance +", "+ this.creditLimit+"\n";
		return temp;
	}

	@Override
	public boolean isPossibleToDeposit(IAccount fromAccount, float amount) {
		if( amount < 0){
			return false;
		}
		else{
			return true;
		}
	}

	@Override
	public boolean isPossibleToWithDraw(IAccount toAccount, float amount) {
		float tempBalance;
		synchronized (lock) {
		tempBalance = this.balance;
		}
		if((tempBalance -= amount) >= this.creditLimit){
			return true;
		}
		else{
			return false;
		}
	}

	/*
	 * Overload
	 * @see Bank.IAccount#isPossibleToDeposit(float)
	 */
	public boolean isPossibleToDeposit(float amount) {
		return true;
	}

	/*
	 * Overload
	 * @see Bank.IAccount#isPossibleToWithDraw(float)
	 */
	public boolean isPossibleToWithDraw(float amount) {
		float tempBalance;
		synchronized (lock) {
		tempBalance = this.balance;
		}
		if((tempBalance -= amount) >= this.creditLimit){
			return true;
		}
		else{
			return false;
		}
	}

	

	

}
