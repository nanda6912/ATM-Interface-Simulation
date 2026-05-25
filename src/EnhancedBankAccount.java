import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * EnhancedBankAccount with comprehensive ATM features
 */
public class EnhancedBankAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String accountNumber;
    private String accountHolderName;
    private String accountType;
    private double balance;
    private double dailyWithdrawalLimit = 50000.0;
    private double dailyWithdrawn = 0.0;
    private List<Transaction> transactionHistory;
    
    private static final int[] DENOMINATIONS = {100, 200, 500, 2000};
    private static final int[] FAST_CASH = {500, 1000, 2000, 5000, 10000};
    
    public EnhancedBankAccount(String accountNumber, String accountHolderName, String accountType, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.accountType = accountType;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        this.transactionHistory.add(new Transaction("ACCOUNT_CREATED", 0, initialBalance));
    }
    
    // Getters
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getAccountHolderName() {
        return accountHolderName;
    }
    
    public String getAccountType() {
        return accountType;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public double getDailyWithdrawn() {
        return dailyWithdrawn;
    }
    
    public double getDailyWithdrawalLimit() {
        return dailyWithdrawalLimit;
    }
    
    public List<Transaction> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }
    
    // Balance operations
    public void displayBalance() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("BALANCE INQUIRY");
        System.out.println("=".repeat(50));
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Account Type: " + accountType);
        System.out.println("Current Balance: Rs. " + String.format("%.2f", balance));
        System.out.println("Available for Withdrawal: Rs. " + String.format("%.2f", (dailyWithdrawalLimit - dailyWithdrawn)));
        System.out.println("=".repeat(50));
    }
    
    // Withdrawal
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("✗ Amount must be greater than 0!");
            return false;
        }
        
        if (amount % 100 != 0) {
            System.out.println("✗ Amount must be in multiples of 100!");
            return false;
        }
        
        if (amount > balance) {
            System.out.println("✗ Insufficient balance! Available: Rs. " + balance);
            return false;
        }
        
        double remainingDailyLimit = dailyWithdrawalLimit - dailyWithdrawn;
        if (amount > remainingDailyLimit) {
            System.out.println("✗ Daily withdrawal limit exceeded!");
            System.out.println("Daily limit: Rs. " + dailyWithdrawalLimit);
            System.out.println("Already withdrawn: Rs. " + dailyWithdrawn);
            System.out.println("Remaining: Rs. " + remainingDailyLimit);
            return false;
        }
        
        balance -= amount;
        dailyWithdrawn += amount;
        transactionHistory.add(new Transaction("WITHDRAWAL", amount, balance));
        
        System.out.println("\n✓ Withdrawal successful!");
        System.out.println("Amount withdrawn: Rs. " + String.format("%.2f", amount));
        System.out.println("Remaining balance: Rs. " + String.format("%.2f", balance));
        printDenominations(amount);
        
        return true;
    }
    
    // Deposit
    public boolean deposit(double amount) {
        if (amount <= 0) {
            System.out.println("✗ Amount must be greater than 0!");
            return false;
        }
        
        if (amount % 100 != 0) {
            System.out.println("✗ Amount must be in multiples of 100!");
            return false;
        }
        
        balance += amount;
        transactionHistory.add(new Transaction("DEPOSIT", amount, balance));
        
        System.out.println("\n✓ Deposit successful!");
        System.out.println("Amount deposited: Rs. " + String.format("%.2f", amount));
        System.out.println("New balance: Rs. " + String.format("%.2f", balance));
        
        return true;
    }
    
    // Transfer
    public boolean transferTo(EnhancedBankAccount recipient, double amount) {
        if (amount <= 0) {
            System.out.println("✗ Amount must be greater than 0!");
            return false;
        }
        
        if (amount % 100 != 0) {
            System.out.println("✗ Amount must be in multiples of 100!");
            return false;
        }
        
        if (amount > balance) {
            System.out.println("✗ Insufficient balance!");
            return false;
        }
        
        balance -= amount;
        recipient.balance += amount;
        
        this.transactionHistory.add(new Transaction("TRANSFER_OUT", amount, balance, recipient.accountNumber));
        recipient.transactionHistory.add(new Transaction("TRANSFER_IN", amount, recipient.balance, this.accountNumber));
        
        System.out.println("\n✓ Transfer successful!");
        System.out.println("Amount transferred: Rs. " + String.format("%.2f", amount));
        System.out.println("Recipient: " + recipient.accountNumber);
        System.out.println("Your new balance: Rs. " + String.format("%.2f", balance));
        
        return true;
    }
    
    // Mini Statement
    public void displayMiniStatement() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("MINI STATEMENT (Last 5 Transactions)");
        System.out.println("=".repeat(60));
        System.out.println("Account: " + accountNumber);
        System.out.println("-".repeat(60));
        
        int start = Math.max(0, transactionHistory.size() - 5);
        for (int i = start; i < transactionHistory.size(); i++) {
            System.out.println((i - start + 1) + ". " + transactionHistory.get(i).getFormattedTransaction());
        }
        
        System.out.println("-".repeat(60));
        System.out.println("Current Balance: Rs. " + String.format("%.2f", balance));
        System.out.println("=".repeat(60));
    }
    
    // Transaction History
    public void displayTransactionHistory() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("TRANSACTION HISTORY");
        System.out.println("=".repeat(80));
        System.out.println("Account: " + accountNumber + " | Holder: " + accountHolderName);
        System.out.println("-".repeat(80));
        
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found!");
        } else {
            for (int i = 0; i < transactionHistory.size(); i++) {
                System.out.println((i + 1) + ". " + transactionHistory.get(i).getFormattedTransaction());
            }
        }
        
        System.out.println("-".repeat(80));
        System.out.println("Current Balance: Rs. " + String.format("%.2f", balance));
        System.out.println("=".repeat(80));
    }
    
    // Reset daily withdrawal
    public void resetDailyWithdrawal() {
        this.dailyWithdrawn = 0.0;
    }
    
    // Print denominations for withdrawal
    private void printDenominations(double amount) {
        System.out.println("\nDenominations dispensed:");
        int[] remaining = new int[DENOMINATIONS.length];
        double temp = amount;
        
        for (int i = DENOMINATIONS.length - 1; i >= 0; i--) {
            remaining[i] = (int) (temp / DENOMINATIONS[i]);
            temp -= remaining[i] * DENOMINATIONS[i];
        }
        
        for (int i = 0; i < DENOMINATIONS.length; i++) {
            if (remaining[i] > 0) {
                System.out.println("  " + DENOMINATIONS[i] + " Rs. X " + remaining[i] + " = " + (DENOMINATIONS[i] * remaining[i]));
            }
        }
    }
    
    @Override
    public String toString() {
        return String.format("Account: %s | Holder: %s | Type: %s | Balance: Rs. %.2f",
                accountNumber, accountHolderName, accountType, balance);
    }
}
