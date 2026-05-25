import java.util.Scanner;
import java.util.List;

/**
 * ATMInterface - User interface for ATM operations
 */
public class ATMInterface {
    private ATMSystem atmSystem;
    private Scanner scanner;
    private static final int[] FAST_CASH_OPTIONS = {500, 1000, 2000, 5000, 10000};
    
    public ATMInterface() {
        this.atmSystem = new ATMSystem();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Main entry point
     */
    public void start() {
        displayWelcome();
        
        while (true) {
            if (!atmSystem.isLoggedIn()) {
                displayLoginMenu();
            } else {
                displayMainMenu();
            }
        }
    }
    
    /**
     * Display welcome message
     */
    private void displayWelcome() {
        clearScreen();
        System.out.println("\n" + "=".repeat(60));
        System.out.println("   WELCOME TO SECURE ATM SYSTEM");
        System.out.println("=".repeat(60) + "\n");
    }
    
    /**
     * Display login menu
     */
    private void displayLoginMenu() {
        System.out.println("\n" + "-".repeat(50));
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.println("-".repeat(50));
        System.out.print("Enter your choice: ");
        
        int choice = getIntInput();
        
        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                register();
                break;
            case 3:
                System.out.println("\n✓ Thank you for using ATM. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("✗ Invalid choice!");
        }
    }
    
    /**
     * Login user
     */
    private void login() {
        System.out.print("\nEnter Card Number: ");
        String cardNumber = scanner.nextLine().trim();
        
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine().trim();
        
        if (atmSystem.loginUser(cardNumber, pin)) {
            selectAccount();
        }
    }
    
    /**
     * Register new user
     */
    private void register() {
        System.out.print("\nEnter Card Number: ");
        String cardNumber = scanner.nextLine().trim();
        
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine().trim();
        
        System.out.print("Enter Your Name: ");
        String name = scanner.nextLine().trim();
        
        if (atmSystem.registerUser(cardNumber, pin, name)) {
            System.out.println("\n✓ Registration successful! You can now login.");
        } else {
            System.out.println("\n✗ Card number already exists!");
        }
    }
    
    /**
     * Select account to operate on
     */
    private void selectAccount() {
        User user = atmSystem.getCurrentUser();
        List<BankAccount> accounts = user.getAccounts();
        
        if (accounts.size() == 1) {
            atmSystem.setCurrentAccount(accounts.get(0));
            return;
        }
        
        while (true) {
            System.out.println("\n" + "-".repeat(50));
            System.out.println("SELECT ACCOUNT");
            System.out.println("-".repeat(50));
            
            for (int i = 0; i < accounts.size(); i++) {
                System.out.println((i + 1) + ". " + accounts.get(i).toString());
            }
            System.out.println((accounts.size() + 1) + ". Back");
            System.out.print("Enter your choice: ");
            
            int choice = getIntInput();
            
            if (choice >= 1 && choice <= accounts.size()) {
                atmSystem.setCurrentAccount(accounts.get(choice - 1));
                System.out.println("\n✓ Account selected: " + accounts.get(choice - 1).getAccountNumber());
                break;
            } else if (choice == accounts.size() + 1) {
                atmSystem.logoutUser();
                break;
            } else {
                System.out.println("✗ Invalid choice!");
            }
        }
    }
    
    /**
     * Display main menu
     */
    private void displayMainMenu() {
        BankAccount account = atmSystem.getCurrentAccount();
        User user = atmSystem.getCurrentUser();
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("MAIN MENU - " + user.getUserName());
        System.out.println("=".repeat(50));
        System.out.println("1. Check Balance");
        System.out.println("2. Withdraw Money");
        System.out.println("3. Deposit Money");
        System.out.println("4. Fast Cash");
        System.out.println("5. Transfer Funds");
        System.out.println("6. Mini Statement");
        System.out.println("7. Transaction History");
        System.out.println("8. Change PIN");
        System.out.println("9. Select Account");
        System.out.println("10. Logout");
        System.out.println("=".repeat(50));
        System.out.print("Enter your choice: ");
        
        int choice = getIntInput();
        
        switch (choice) {
            case 1:
                checkBalance();
                break;
            case 2:
                withdrawMoney();
                break;
            case 3:
                depositMoney();
                break;
            case 4:
                fastCash();
                break;
            case 5:
                transferFunds();
                break;
            case 6:
                miniStatement();
                break;
            case 7:
                transactionHistory();
                break;
            case 8:
                changePIN();
                break;
            case 9:
                selectAccount();
                break;
            case 10:
                atmSystem.logoutUser();
                break;
            default:
                System.out.println("✗ Invalid choice!");
        }
    }
    
    /**
     * Check balance
     */
    private void checkBalance() {
        BankAccount account = atmSystem.getCurrentAccount();
        account.displayBalance();
    }
    
    /**
     * Withdraw money
     */
    private void withdrawMoney() {
        BankAccount account = atmSystem.getCurrentAccount();
        System.out.print("\nEnter amount to withdraw (multiple of 100): Rs. ");
        double amount = getDoubleInput();
        account.withdraw(amount);
    }
    
    /**
     * Deposit money
     */
    private void depositMoney() {
        BankAccount account = atmSystem.getCurrentAccount();
        System.out.print("\nEnter amount to deposit (multiple of 100): Rs. ");
        double amount = getDoubleInput();
        account.deposit(amount);
    }
    
    /**
     * Fast cash withdrawal
     */
    private void fastCash() {
        System.out.println("\n" + "-".repeat(40));
        System.out.println("FAST CASH OPTIONS");
        System.out.println("-".repeat(40));
        
        for (int i = 0; i < FAST_CASH_OPTIONS.length; i++) {
            System.out.println((i + 1) + ". Rs. " + FAST_CASH_OPTIONS[i]);
        }
        System.out.println((FAST_CASH_OPTIONS.length + 1) + ". Custom Amount");
        System.out.println((FAST_CASH_OPTIONS.length + 2) + ". Cancel");
        System.out.print("Select option: ");
        
        int choice = getIntInput();
        
        if (choice >= 1 && choice <= FAST_CASH_OPTIONS.length) {
            atmSystem.getCurrentAccount().withdraw(FAST_CASH_OPTIONS[choice - 1]);
        } else if (choice == FAST_CASH_OPTIONS.length + 1) {
            System.out.print("Enter amount: Rs. ");
            double amount = getDoubleInput();
            atmSystem.getCurrentAccount().withdraw(amount);
        } else if (choice == FAST_CASH_OPTIONS.length + 2) {
            System.out.println("✓ Operation cancelled.");
        } else {
            System.out.println("✗ Invalid choice!");
        }
    }
    
    /**
     * Transfer funds
     */
    private void transferFunds() {
        BankAccount sender = atmSystem.getCurrentAccount();
        User currentUser = atmSystem.getCurrentUser();
        List<BankAccount> accounts = currentUser.getAccounts();
        
        if (accounts.size() < 2) {
            System.out.println("✗ You need at least 2 accounts for transfer!");
            return;
        }
        
        System.out.println("\n" + "-".repeat(40));
        System.out.println("SELECT RECIPIENT ACCOUNT");
        System.out.println("-".repeat(40));
        
        int recipientIndex = -1;
        for (int i = 0; i < accounts.size(); i++) {
            if (!accounts.get(i).equals(sender)) {
                System.out.println((i + 1) + ". " + accounts.get(i).toString());
            }
        }
        System.out.print("Select recipient account: ");
        int choice = getIntInput();
        
        if (choice >= 1 && choice <= accounts.size() && choice - 1 != accounts.indexOf(sender)) {
            BankAccount recipient = accounts.get(choice - 1);
            System.out.print("Enter amount to transfer (multiple of 100): Rs. ");
            double amount = getDoubleInput();
            sender.transferTo(recipient, amount);
        } else {
            System.out.println("✗ Invalid selection!");
        }
    }
    
    /**
     * Display mini statement
     */
    private void miniStatement() {
        atmSystem.getCurrentAccount().displayMiniStatement();
    }
    
    /**
     * Display transaction history
     */
    private void transactionHistory() {
        atmSystem.getCurrentAccount().displayTransactionHistory();
    }
    
    /**
     * Change PIN
     */
    private void changePIN() {
        User user = atmSystem.getCurrentUser();
        System.out.print("\nEnter current PIN: ");
        String oldPin = scanner.nextLine().trim();
        
        System.out.print("Enter new PIN: ");
        String newPin = scanner.nextLine().trim();
        
        System.out.print("Confirm new PIN: ");
        String confirmPin = scanner.nextLine().trim();
        
        if (newPin.equals(confirmPin)) {
            if (user.changePin(oldPin, newPin)) {
                System.out.println("✓ PIN changed successfully!");
            } else {
                System.out.println("✗ Incorrect current PIN!");
            }
        } else {
            System.out.println("✗ PINs do not match!");
        }
    }
    
    /**
     * Get integer input safely
     */
    private int getIntInput() {
        try {
            int value = Integer.parseInt(scanner.nextLine().trim());
            return value;
        } catch (NumberFormatException e) {
            System.out.println("✗ Invalid input!");
            return -1;
        }
    }
    
    /**
     * Get double input safely
     */
    private double getDoubleInput() {
        try {
            double value = Double.parseDouble(scanner.nextLine().trim());
            return value;
        } catch (NumberFormatException e) {
            System.out.println("✗ Invalid input!");
            return -1;
        }
    }
    
    /**
     * Clear screen
     */
    private void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n");
        }
    }
    
    /**
     * Main method
     */
    public static void main(String[] args) {
        ATMInterface atm = new ATMInterface();
        atm.start();
    }
}
