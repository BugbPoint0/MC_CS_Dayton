// David Dayton

public class SavingsAccount extends BankAccount {

	private double rate = 0.025;
	private int savingsNumber = 0;
	private String accountNumber;
	
	SavingsAccount(String name, double initalAmount)	{
		super(name, initalAmount);
		accountNumber = super.getAccountNumber() + "-" + savingsNumber;
	}
	
	SavingsAccount(SavingsAccount originalAccount, double initalAmount) {
		super(originalAccount, initalAmount);
		savingsNumber += 1;
		accountNumber = super.getAccountNumber() + "-" + savingsNumber;
	}
	
	public void postInterest() {
		double interest = super.getBalance();
		interest *= rate;
		super.deposit(interest);
	}
	
	public String getAccountNumber()
	{
		return accountNumber;
	}	
	
}
