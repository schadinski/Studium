package Bank;

public class SavingsAccount extends Account {
	private Date creationDate;
	private Date fixDate;
	private boolean isFixDateSet;
	private Date notBefore = new Date(1, 1, 2010);
	/*	protected IPerson owner;
		protected long accountNumber;
		protected float balance;
	 */
	//TODO wie verhält sich notBefore, da jedes mal neues date erstellt wird
	// sollte das vielleicht in den konstruktor?

	//Constructor
	public SavingsAccount(IPerson owner, Date creationDate) {
		this.owner = owner;
		this.creationDate = creationDate;
		this.isFixDateSet = false;
		this.balance = 0;
		this.accountNumber = createAccountnumber();
	}
	
	public void fixAccount(Date fixDate) {
		this.fixDate = fixDate;
		this.isFixDateSet = true;
	}

	@Override
	public synchronized boolean deposit(float amount) {
		//einzahlen
//		TODO zusammenhang mit aufruf von bank, transfer
//		if(this.owner != owner){
//			return false;
//		}
		if( isPossibleToDeposit(amount)){
			synchronized (lock) {
			this.balance += amount;
			}
			return true;
		}
		else {
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

	public boolean isPossibleToDeposit(float amount) {
		if( amount < 0){
			return false;
		}
		else if(isFixDateSet){
			return false;
		}
		else if((this.creationDate.isBefore(notBefore))){
			return false;
		}
		
		else {
			return true;
		}
	}

	public synchronized boolean isPossibleToWithDraw(float amount) {
		if(isFixDateSet || this.balance < amount){
			return false;
		}
		else{
			return true;
		}
	}
	
	@Override
	public String toString(){
		String temp;
		if( this.fixDate == null){
			temp = this.accountNumber+ ", Saving Account ,"+this.owner.getName()+", "+this.balance +", not fixed\n";
		}
		else{
			temp = this.accountNumber+ ", Saving Account ,"+this.owner.getName()+", "+this.balance +", "+ this.fixDate+"\n";
		}
		return temp;
	}

	/*
	 * Overload
	 * @see Bank.IAccount#isPossibleToDeposit(Bank.IAccount, float)
	 */
	public boolean isPossibleToDeposit(IAccount fromAccount, float amount) {
		if( amount < 0){
			return false;
		}
		else if(isFixDateSet){
			return false;
		}
		else if(this.creationDate.isBefore(notBefore)){
			return false;
		}
		else if(this.owner.equals(fromAccount.getOwner())== false){
			return false;
		}
		else if(!this.equals(fromAccount)){
			return false;
		}
		else {
			return true;
		}
	}

	/*
	 * Overload
	 * @see Bank.IAccount#isPossibleToWithDraw(Bank.IAccount, float)
	 */
	public synchronized boolean isPossibleToWithDraw(IAccount toAccount, float amount) {
		if(isFixDateSet || this.balance < amount){
			return false;
		}
		else if(!this.equals(toAccount)){
			return false;
		}
		else{
			return true;
		}
	}

}
