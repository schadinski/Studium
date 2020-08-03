package Bank;

public abstract class Account implements IAccount{
	protected IPerson owner;
	protected long accountNumber;
	protected float balance;
	protected long[] allAccountNumbers = new long[100];
	protected int numbersOfAccountnumbers = 0;
	protected final Object lock = new Object();

	public IPerson getOwner(){
		return this.owner;
	}
	public void setOwner(IPerson newOwner){
		this.owner = newOwner;
		
	}
	public void setAccountNo(long newAccountNumber){
		this.accountNumber = newAccountNumber;
	}
	public long getAccountNo(){
		return this.accountNumber;
		
	}
	public float getBalance(){
		return this.balance;
	}
	
	@Override 
	public boolean equals(Object o){
		if(o == null){
			return false;
		}
		if(this== o){
			return true;
		}
		
		IAccount other = (IAccount) o;
		
		IPerson one = this.getOwner();
		IPerson two = other.getOwner();
		
		Date oneDate = one.getDateOfBirth();
		Date twoDate = two.getDateOfBirth();
		boolean result = false;
		if(one.getName().compareTo(two.getName()) == 0){
			result = true;
		}
		
		return 		result
				&& 	oneDate.equals(twoDate);
		}
	
	/*
	 * method to create new account number and save all account numbers.
	 * check it is not existing until yet
	 * return long the new account number or -1
	 */
	long createAccountnumber(){
		long tempAccountNumber = (long) (Math.random() * 10000000 + 10000);
		
		if (numbersOfAccountnumbers == allAccountNumbers.length) {
			long[] temp = new long[numbersOfAccountnumbers + 100];
			System.arraycopy(allAccountNumbers, 0, temp, 0, allAccountNumbers.length);
			allAccountNumbers = temp;
		}
		
			for(int i = 0; i< numbersOfAccountnumbers; i++){
				if(allAccountNumbers[i] == tempAccountNumber){
					return -1;
				}
			}
			allAccountNumbers[numbersOfAccountnumbers] = tempAccountNumber;	
		
			
		
		numbersOfAccountnumbers++;
		return tempAccountNumber;
		
	}
	
	public abstract boolean deposit(float amount);
	public abstract boolean withdraw(float amount);
	public abstract String toString();
}
