import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * ATMSystem - Backend system for managing users and persistence
 */
public class ATMSystem {
    private Map<String, User> users;
    private User currentUser;
    private EnhancedBankAccount currentAccount;
    private static final String DATA_FILE = "atm_data.dat";
    
    public ATMSystem() {
        this.users = new HashMap<>();
        this.currentUser = null;
        this.currentAccount = null;
        loadData();
        initializeSampleData();
    }
    
    /**
     * Register a new user
     */
    public boolean registerUser(String cardNumber, String pin, String userName) {
        if (users.containsKey(cardNumber)) {
            return false;
        }
        
        User newUser = new User(cardNumber, pin, userName);
        
        // Create default Checking account
        EnhancedBankAccount checkingAccount = new EnhancedBankAccount(
            cardNumber + "-CHK", 
            userName, 
            "Checking", 
            10000.0
        );
        newUser.addAccount(checkingAccount);
        
        // Create Savings account
        EnhancedBankAccount savingsAccount = new EnhancedBankAccount(
            cardNumber + "-SAV", 
            userName, 
            "Savings", 
            5000.0
        );
        newUser.addAccount(savingsAccount);
        
        users.put(cardNumber, newUser);
        saveData();
        return true;
    }
    
    /**
     * Login user
     */
    public boolean loginUser(String cardNumber, String pin) {
        if (!users.containsKey(cardNumber)) {
            System.out.println("✗ Card not found!");
            return false;
        }
        
        User user = users.get(cardNumber);
        
        if (user.isCardLocked()) {
            System.out.println("✗ Your card has been locked! Please contact customer service.");
            return false;
        }
        
        if (user.validatePin(pin)) {
            this.currentUser = user;
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
        if (currentUser != null) {
            System.out.println("\n✓ Logged out successfully. Thank you for using ATM!");
            currentUser = null;
            currentAccount = null;
            saveData();
        }
    }
    
    /**
     * Check if user is logged in
     */
    public boolean isLoggedIn() {
        return currentUser != null;
    }
    
    /**
     * Get current logged-in user
     */
    public User getCurrentUser() {
        return currentUser;
    }
    
    /**
     * Set current account
     */
    public void setCurrentAccount(EnhancedBankAccount account) {
        this.currentAccount = account;
    }
    
    /**
     * Get current account
     */
    public EnhancedBankAccount getCurrentAccount() {
        return currentAccount;
    }
    
    /**
     * Save data to file
     */
    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(users);
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }
    
    /**
     * Load data from file
     */
    @SuppressWarnings("unchecked")
    public void loadData() {
        File file = new File(DATA_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
                users = (Map<String, User>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error loading data: " + e.getMessage());
                users = new HashMap<>();
            }
        }
    }
    
    /**
     * Initialize sample data for testing
     */
    private void initializeSampleData() {
        if (users.isEmpty()) {
            // User 1
            User user1 = new User("4532123456789012", "1234", "Gajala");
            EnhancedBankAccount acc1 = new EnhancedBankAccount("4532123456789012-CHK", "Gajala", "Checking", 50000);
            EnhancedBankAccount acc2 = new EnhancedBankAccount("4532123456789012-SAV", "Gajala", "Savings", 25000);
            user1.addAccount(acc1);
            user1.addAccount(acc2);
            users.put("4532123456789012", user1);
            
            // User 2
            User user2 = new User("5412987654321098", "5678", "Pratik");
            EnhancedBankAccount acc3 = new EnhancedBankAccount("5412987654321098-CHK", "Pratik", "Checking", 75000);
            EnhancedBankAccount acc4 = new EnhancedBankAccount("5412987654321098-SAV", "Pratik", "Savings", 40000);
            user2.addAccount(acc3);
            user2.addAccount(acc4);
            users.put("5412987654321098", user2);
            
            // User 3
            User user3 = new User("6011111111111117", "9999", "Arjun");
            EnhancedBankAccount acc5 = new EnhancedBankAccount("6011111111111117-CHK", "Arjun", "Checking", 100000);
            EnhancedBankAccount acc6 = new EnhancedBankAccount("6011111111111117-SAV", "Arjun", "Savings", 60000);
            user3.addAccount(acc5);
            user3.addAccount(acc6);
            users.put("6011111111111117", user3);
            
            saveData();
            System.out.println("✓ Sample data initialized");
        }
    }
}
