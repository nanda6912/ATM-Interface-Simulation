import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User class to manage user authentication and multiple bank accounts
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String cardNumber;
    private String pin;
    private String userName;
    private List<BankAccount> accounts;
    private int failedLoginAttempts;
    private boolean isCardLocked;
    
    public User(String cardNumber, String pin, String userName) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.userName = userName;
        this.accounts = new ArrayList<>();
        this.failedLoginAttempts = 0;
        this.isCardLocked = false;
    }
    
    // Getters
    public String getCardNumber() {
        return cardNumber;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public List<BankAccount> getAccounts() {
        return new ArrayList<>(accounts);
    }
    
    public boolean isCardLocked() {
        return isCardLocked;
    }
    
    public int getFailedLoginAttempts() {
        return failedLoginAttempts;
    }
    
    // Account Management
    public void addAccount(BankAccount account) {
        accounts.add(account);
    }
    
    public BankAccount getAccount(int index) {
        if (index >= 0 && index < accounts.size()) {
            return accounts.get(index);
        }
        return null;
    }
    
    public boolean validatePin(String enteredPin) {
        if (isCardLocked) {
            return false;
        }
        
        if (enteredPin.equals(this.pin)) {
            failedLoginAttempts = 0;
            return true;
        } else {
            failedLoginAttempts++;
            if (failedLoginAttempts >= 3) {
                isCardLocked = true;
                System.out.println("\n⚠️  Your card has been locked due to 3 failed attempts!");
            }
            return false;
        }
    }
    
    public boolean changePin(String oldPin, String newPin) {
        if (oldPin.equals(this.pin)) {
            this.pin = newPin;
            System.out.println("✓ PIN changed successfully!");
            return true;
        }
        return false;
    }
    
    public void unlockCard() {
        this.isCardLocked = false;
        this.failedLoginAttempts = 0;
    }
    
    @Override
    public String toString() {
        return "User: " + userName + " | Card: " + cardNumber + " | Accounts: " + accounts.size();
    }
}
