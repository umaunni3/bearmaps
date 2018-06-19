/**
 * This class represents a bank account whose current balance is a nonnegative
 * amount in US dollars.
 */
public class Account {

    public int balance;
    public Account parentAccount;

    /** Initialize an account with the given BALANCE. */
    public Account(int balance) {
        this.balance = balance;
        this.parentAccount = null;
    }

    public Account(int balance, Account parent) {
        this.balance = balance;
        this.parentAccount = parent;
    }

    /** Deposits AMOUNT into the current account. */
    public void deposit(int amount) {
        if (amount < 0) {
            System.out.println("Cannot deposit negative amount.");
        } else {
            balance += amount;
        }
    }

    /**
     * Subtract AMOUNT from the account if possible. If subtracting AMOUNT
     * would leave a negative balance, print an error message and leave the
     * balance unchanged.
     */
    public boolean withdraw(int amount) {
        // TODO
        if (amount < 0) {  
            if (this.parentAccount != null) {
                return this.parentAccount.withdraw(amount);
            }
            System.out.println("Cannot withdraw negative amount.");
            return false;
            
        } else if (balance < amount) {
            if (this.parentAccount != null) {
                int overdraft = amount - this.balance;
                if (this.parentAccount.withdraw(overdraft)) {
                    this.balance = 0;
                }
            }
            System.out.println("Insufficient funds");
            return false;
        } else {
            balance -= amount;
        }
        return true;
    }

    /**
     * Merge account OTHER into this account by removing all money from OTHER
     * and depositing it into this account.
     */
    public void merge(Account other) {
        this.balance += other.balance;
        other.balance = 0;
    }
}
