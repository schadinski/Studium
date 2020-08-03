package Bank;
public interface IAccount {
	public boolean deposit(float amount);
	public boolean withdraw(float amount);
	public IPerson getOwner();
	public void setOwner(IPerson newOwner);
	public void setAccountNo(long newAccountNumber);
	public long getAccountNo();
	public float getBalance();
	public boolean isPossibleToDeposit(IAccount fromAccount, float amount);
	public boolean isPossibleToWithDraw(IAccount toAccount, float amount);
	public boolean isPossibleToDeposit(float amount);
	public boolean isPossibleToWithDraw(float amount);
	
	
}
