package Bank;

public interface IBank {

	public void addAccount(IAccount newAccount);
	
	public void deleteAccount(IAccount newAccount);
	
	public boolean transfer(long fromAccountNumber, long toAccountNUmber, float amount);
	
}
