package BankThread;
public class CannotJoinException extends Exception {

	public CannotJoinException (String massage){
		super(massage);
	}
	
	public CannotJoinException (){
		super("method join for one of the threads is failed");
	}
}
