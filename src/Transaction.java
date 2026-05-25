import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Transaction class to track all ATM transactions
 */
public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String transactionType; // WITHDRAWAL, DEPOSIT, TRANSFER_OUT, TRANSFER_IN, ACCOUNT_CREATED
    private double amount;
    private double balanceAfter;
    private LocalDateTime timestamp;
    private String relatedAccount; // For transfers
    
    public Transaction(String transactionType, double amount, double balanceAfter) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.timestamp = LocalDateTime.now();
        this.relatedAccount = "";
    }
    
    public Transaction(String transactionType, double amount, double balanceAfter, String relatedAccount) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.timestamp = LocalDateTime.now();
        this.relatedAccount = relatedAccount;
    }
    
    // Getters
    public String getTransactionType() {
        return transactionType;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public double getBalanceAfter() {
        return balanceAfter;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public String getRelatedAccount() {
        return relatedAccount;
    }
    
    /**
     * Get formatted transaction details
     */
    public String getFormattedTransaction() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String timeStr = timestamp.format(formatter);
        
        String details = "";
        switch (transactionType) {
            case "WITHDRAWAL":
                details = String.format("[%s] WITHDRAWAL: Rs. %.2f | Balance: Rs. %.2f", 
                    timeStr, amount, balanceAfter);
                break;
            case "DEPOSIT":
                details = String.format("[%s] DEPOSIT: Rs. %.2f | Balance: Rs. %.2f", 
                    timeStr, amount, balanceAfter);
                break;
            case "TRANSFER_OUT":
                details = String.format("[%s] TRANSFER OUT: Rs. %.2f to %s | Balance: Rs. %.2f", 
                    timeStr, amount, relatedAccount, balanceAfter);
                break;
            case "TRANSFER_IN":
                details = String.format("[%s] TRANSFER IN: Rs. %.2f from %s | Balance: Rs. %.2f", 
                    timeStr, amount, relatedAccount, balanceAfter);
                break;
            case "ACCOUNT_CREATED":
                details = String.format("[%s] ACCOUNT CREATED: Initial Balance: Rs. %.2f", 
                    timeStr, balanceAfter);
                break;
            default:
                details = String.format("[%s] %s: Rs. %.2f | Balance: Rs. %.2f", 
                    timeStr, transactionType, amount, balanceAfter);
        }
        
        return details;
    }
    
    @Override
    public String toString() {
        return getFormattedTransaction();
    }
}
