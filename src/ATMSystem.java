import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * ATMSystem - Backend system for managing users and data persistence
 */
public class ATMSystem {
    private Map<String, User> users;
    private User currentUser;
    private EnhancedBankAccount currentAccount;
    private static final String DATA_FILE = "atm_data.dat";
    private boolean isLoggedIn = false;
    
    public ATMSystem() {
        this.users = new HashMap<>();
        this.currentUser = null;
        this.currentAccount = null;
        loadData();
        initializeSampleData();
    }
    
    /**
     * Register new user
     */
    public boolean registerUser(String cardNumber, String pin, String userName) {
        if (users.containsKey(cardNumber)) {
            return false;
        }
        
        User newUser = new User(cardNumber, pin, userName);
        
        // Add default Savings account
        EnhancedBankAccount savingsAccount = new EnhancedBankAccount(
            cardNumber + "_SAV", userName, "Savings", 10000.0
        );
        newUser.addAccount(savingsAccount);
        
        // Add default Checking account
        EnhancedBankAccount checkingAccount = new EnhancedBankAccount(
            cardNumber + "_CHK", userName, "Checking", 5000.0
        );
        newUser.addAccount(checkingAccount);
        
        users.put(cardNumber, newUser);
        saveData();
        return true;
    }
    
    /**
     * Login user
     */
    public boolean loginUser(String cardNumber, String pin) {
        if (!users.containsKey(cardNumber)) {
            System.out.println("✗ Card number not found!");
            return false;
        }
        
        User user = users.get(cardNumber);
        
        if (user.isCardLocked()) {
            System.out.println("✗ Your card is locked! Please contact support.");
            return false;
        }
        
        if (user.validatePin(pin)) {
            this.currentUser = user;
            this.isLoggedIn = true;
            System.out.println("\n✓ Login successful! Welcome, " + user.getUserName());
            return true;
        } else {
            int attempts = user.getFailedLoginAttempts();
            System.out.println("✗ Invalid PIN! Attempts remaining: " + (3 - attempts));
            return false;
        }
    }
    
    /**
     * Logout current user
     */
    public void logoutUser() {
        if (isLoggedIn) {
            System.out.println("\n✓ Logged out successfully!");
            this.currentUser = null;
            this.currentAccount = null;
            this.isLoggedIn = false;
            saveData();
        }
    }
    
    /**
     * Set current account
     */
    public void setCurrentAccount(EnhancedBankAccount account) {
        this.currentAccount = account;
    }
    
    /**
     * Get current user
     */
    public User getCurrentUser() {
        return currentUser;
    }
    
    /**
     * Get current account
     */
    public EnhancedBankAccount getCurrentAccount() {
        return currentAccount;
    }
    
    /**
     * Check if user is logged in
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    
    /**
     * Initialize sample data
     */
    private void initializeSampleData() {
        if (users.isEmpty()) {
            // Create sample users
            User user1 = new User("4532123456789012", "1234", "Gajala");
            EnhancedBankAccount account1_1 = new EnhancedBankAccount(
                "4532123456789012_SAV", "Gajala", "Savings", 50000.0
            );
            EnhancedBankAccount account1_2 = new EnhancedBankAccount(
                "4532123456789012_CHK", "Gajala", "Checking", 25000.0
            );
            user1.addAccount(account1_1);
            user1.addAccount(account1_2);
            users.put("4532123456789012", user1);
            
            User user2 = new User("5412987654321098", "5678", "Pratik");
            EnhancedBankAccount account2_1 = new EnhancedBankAccount(
                "5412987654321098_SAV", "Pratik", "Savings", 75000.0
            );
            EnhancedBankAccount account2_2 = new EnhancedBankAccount(
                "5412987654321098_CHK", "Pratik", "Checking", 30000.0
            );
            user2.addAccount(account2_1);
            user2.addAccount(account2_2);
            users.put("5412987654321098", user2);
            
            User user3 = new User("6011111111111117", "9999", "Arjun");
            EnhancedBankAccount account3_1 = new EnhancedBankAccount(
                "6011111111111117_SAV", "Arjun", "Savings", 60000.0
            );
            EnhancedBankAccount account3_2 = new EnhancedBankAccount(
                "6011111111111117_CHK", "Arjun", "Checking", 35000.0
            );
            user3.addAccount(account3_1);
            user3.addAccount(account3_2);
            users.put("6011111111111117", user3);
            
            saveData();
        }
    }
    
    /**
     * Save data to file
     */
    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(users);
        } catch (Exception e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }
    
    /**
     * Load data from file
     */
    @SuppressWarnings("unchecked")
    private void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            users = (Map<String, User>) ois.readObject();
        } catch (Exception e) {
            users = new HashMap<>();
        }
    }
}
