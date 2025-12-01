import java.util.ArrayList;

class BankAccount {
    private String accountHolder;
    private double balance;
    private ArrayList<String> transactions;

    public BankAccount(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
        transactions.add("Account created with balance: Rs." + initialBalance);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add("Deposited: Rs." + amount);
            System.out.println("Deposit successful! New balance: Rs." + balance);
        } else {
            System.out.println("Invalid amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactions.add("Withdrew: Rs." + amount);
            System.out.println("Withdrawal successful! Remaining balance: Rs." + balance);
        } else {
            System.out.println("Insufficient balance or invalid amount!");
        }
    }

    public void showTransactions() {
        System.out.println("\nTransaction History:");
        for (String t : transactions) {
            System.out.println("- " + t);
        }
    }
}
