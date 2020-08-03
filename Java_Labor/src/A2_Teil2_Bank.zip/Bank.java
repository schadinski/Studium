package Bank;

public class Bank implements IBank {
	protected IAccount[] accounts = new IAccount[100];
	protected int blz;
	protected String name;
	private int numbersOfAccounts;

	// Constructor
	public Bank(int blz, String name) {
		this.name = name;
		this.blz = blz;
	}

	// methods
	public void addAccount(IAccount newAccount) {
		
		if (numbersOfAccounts == accounts.length) {
			IAccount[] temp = new IAccount[numbersOfAccounts + 100];
			System.arraycopy(accounts, 0, temp, 0, accounts.length);
			accounts = temp;
		}
		
		for (int i = 0; i < numbersOfAccounts; i++) {
			if (newAccount.getAccountNo() == accounts[i].getAccountNo()) {
				return;
			}
		}
		accounts[numbersOfAccounts] = newAccount;
		numbersOfAccounts++;
	}

	public void deleteAccount(IAccount account) {
			
		for (int i = 0; i < numbersOfAccounts; i++) {
			if (account.getAccountNo() == accounts[i].getAccountNo()) {
				for (int j = i; j <= numbersOfAccounts; j++) {
					accounts[j] = null;
					accounts[j] = accounts[i + 1];
					accounts[numbersOfAccounts-1]= null;
				}
				break;
			}
		}
		numbersOfAccounts--;
		return;
	}

	public  boolean transfer(long fromAccountNumber, long toAccountNumber, float amount) {
		
		if(fromAccountNumber == toAccountNumber){
			return false;
		}
		
		int accountWithDraw;
		int accountDeposit;
		for (accountWithDraw = 0; accountWithDraw < numbersOfAccounts; accountWithDraw++){
			if(accounts[accountWithDraw].getAccountNo()== fromAccountNumber){
				break;
			}
		}
		for (accountDeposit = 0; accountDeposit < numbersOfAccounts; accountDeposit++){
			if(accounts[accountDeposit].getAccountNo()== toAccountNumber){
				break;
			}
		}
		
		boolean isPossibleToTransfer = checkTransfer(accounts[accountWithDraw], accounts[accountDeposit], amount);
		
		if(isPossibleToTransfer){				
			accounts[accountWithDraw].withdraw(amount);
			accounts[accountDeposit].deposit(amount);
			
			return true;
		}
		else{
			return false;
		}
	}
	
	protected void finalize(){
		numbersOfAccounts--;
	}

	@Override
	public String toString(){
		String result = this.name+","+this.blz+"\n";
		for (int i = 0; i < numbersOfAccounts; i++) {
			result = result.concat(accounts[i].toString());
		}
		return result;
		
		//result = result.concat(accounts[i].getAccountNo()+","+accounts[i].toString()+"," +accounts[i].getOwner()+","+ accounts[i].getBalance() +"\n");
	}

	private boolean checkTransfer(IAccount fromAccount, IAccount toAccount, float amount) {
		
		
		boolean isPossibleToWithDraw = fromAccount.isPossibleToWithDraw(toAccount, amount);
		boolean isPossibleToDeposit = toAccount.isPossibleToDeposit(fromAccount, amount);
		
		if(isPossibleToDeposit && isPossibleToWithDraw){				//true && true
			return true;
		}
		
		else{
			return false;
		}
	}

}
