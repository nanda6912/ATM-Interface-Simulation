# Setup Instructions

## Prerequisites
- Java 8 or higher
- Any terminal or command prompt

## Installation Steps

### 1. Clone the Repository
```bash
git clone https://github.com/nanda6912/ATM-Interface-Simulation.git
cd ATM-Interface-Simulation
```

### 2. Navigate to Source Directory
```bash
cd src
```

### 3. Compile All Java Files
```bash
javac *.java
```

### 4. Run the Application
```bash
java ATMInterface
```

## Troubleshooting

### Issue: "javac command not found"
**Solution**: Ensure Java is properly installed and added to PATH

### Issue: "FileNotFound" error
**Solution**: Make sure you're in the `src` directory when running the application

### Issue: "Class not found"
**Solution**: Recompile all files using `javac *.java`

## Test the Application

### Sample Credentials
```
Card 1: 4532123456789012 | PIN: 1234 | Name: Gajala
Card 2: 5412987654321098 | PIN: 5678 | Name: Pratik
Card 3: 6011111111111117 | PIN: 9999 | Name: Arjun
```

### Testing Features
1. **Login**: Use any of the above credentials
2. **Check Balance**: View current balance
3. **Withdraw**: Try withdrawing Rs. 5000 or Rs. 10000
4. **Deposit**: Add funds to account
5. **Transfer**: Transfer between your accounts
6. **View History**: Check transaction history

## File Structure
```
ATM-Interface-Simulation/
├── src/
│   ├── ATM.java
│   ├── ATMInterface.java
│   ├── ATMSystem.java
│   ├── BankAccount.java
│   ├── EnhancedBankAccount.java
│   ├── Transaction.java
│   └── User.java
├── README.md
├── SETUP.md
└── .gitignore
```

## Data Persistence
- User data is automatically saved to `atm_data.dat`
- Data is loaded on startup
- All transactions are persisted

## Advanced Setup (Optional)

### Creating Custom Users
Edit `ATMSystem.java` in the `initializeSampleData()` method to add your own users:
```java
User customUser = new User("cardNumber", "PIN", "Name");
EnhancedBankAccount account = new EnhancedBankAccount(
    "accountID", "Name", "Savings", 50000.0
);
customUser.addAccount(account);
users.put("cardNumber", customUser);
```

### Modifying Withdrawal Limits
Edit `EnhancedBankAccount.java` line ~16:
```java
private double dailyWithdrawalLimit = 50000.0; // Change this value
```

## Need Help?
Refer to README.md for detailed feature documentation
