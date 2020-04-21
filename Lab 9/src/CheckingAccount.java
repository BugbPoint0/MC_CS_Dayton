// David Dayton

public class CheckingAccount extends BankAccount {

	private static final double FEE = .15;
	
	CheckingAccount(String name, double initalAmount)	{
		super(name, initalAmount);
		super.setAccountNumber(getAccountNumber() + "-10");
	}
	
	
	public boolean withdraw(double amount) {
		amount += FEE;
		boolean completed = super.withdraw(amount);
		return completed;
	}
	

}


	