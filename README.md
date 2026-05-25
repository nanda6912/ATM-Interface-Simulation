# ATM Interface Simulation

A comprehensive Java-based ATM Simulation System with full authentication, multiple account management, and transaction tracking capabilities.

## 📋 Features

### Core Features
✅ **User Authentication**
- Card number and PIN validation
- Card locking after 3 failed attempts
- PIN change functionality

✅ **Account Management**
- Multiple accounts per user (Checking & Savings)
- Balance inquiry
- Daily withdrawal limits (Rs. 50,000)

✅ **Transactions**
- Withdraw cash (with denomination breakdown)
- Deposit money
- Transfer between accounts
- Mini statement (last 5 transactions)
- Complete transaction history

✅ **Security**
- User authentication with PIN
- Card locking mechanism
- Failed attempt tracking
- Secure data persistence

✅ **Advanced Features**
- Fast Cash with preset amounts
- Amount validation (multiples of 100)
- Denomination calculation
- Session management
- Data persistence

## 📁 Project Structure

```
src/
├── ATM.java                    # Original simple ATM implementation
├── BankAccount.java            # Basic bank account class
├── EnhancedBankAccount.java    # Enhanced account with full features
├── Transaction.java            # Transaction tracking and history
├── User.java                   # User authentication & management
├── ATMSystem.java              # Backend system & data persistence
└── ATMInterface.java           # Main CLI interface
```

## 🚀 Getting Started

### Prerequisites
- Java 8 or higher
- Terminal/Command Prompt

### Installation & Compilation

```bash
# Navigate to src directory
cd src

# Compile all Java files
javac *.java

# Run the ATM Interface
java ATMInterface
```

## 🧪 Test Credentials

Use these credentials to test the system:

| Card Number | PIN | Name | Balance (CHK) | Balance (SAV) |
|-------------|-----|------|---------------|---------------|
| 4532123456789012 | 1234 | Gajala | Rs. 50,000 | Rs. 25,000 |
| 5412987654321098 | 5678 | Pratik | Rs. 75,000 | Rs. 40,000 |
| 6011111111111117 | 9999 | Arjun | Rs. 100,000 | Rs. 60,000 |

## 📖 Usage Guide

### Main Menu Options

1. **Check Balance** - View current account balance and available withdrawal limit
2. **Withdraw Money** - Withdraw cash (amounts must be multiples of 100)
3. **Deposit Money** - Deposit cash (amounts must be multiples of 100)
4. **Fast Cash** - Quick withdrawal with preset amounts (500, 1000, 2000, 5000, 10000)
5. **Transfer Funds** - Transfer money between your accounts
6. **Mini Statement** - View last 5 transactions
7. **Transaction History** - View complete transaction history
8. **Change PIN** - Update your PIN for security
9. **Select Account** - Switch between multiple accounts
10. **Logout** - End current session

## 🔒 Security Features

- **PIN Validation**: 4-digit PIN verification
- **Card Locking**: Automatic lock after 3 failed attempts
- **Daily Limits**: Rs. 50,000 daily withdrawal limit
- **Data Persistence**: User and transaction data saved to file
- **Session Management**: Automatic logout capability

## 💳 Transaction Types

- **WITHDRAWAL**: Withdraw cash from account
- **DEPOSIT**: Add money to account
- **TRANSFER_OUT**: Send money to another account
- **TRANSFER_IN**: Receive money from another account
- **ACCOUNT_CREATED**: Initial account creation

## 📊 Sample Output

```
============================================================
   WELCOME TO SECURE ATM SYSTEM
============================================================

1. Login
2. Register
3. Exit

--------------------------------------------------

Account: 4532123456789012-CHK | Holder: Gajala | Type: Checking | Balance: Rs. 50000.00

==================================================
BALANCE INQUIRY
==================================================
Account Number: 4532123456789012-CHK
Account Holder: Gajala
Account Type: Checking
Current Balance: Rs. 50000.00
Available for Withdrawal: Rs. 50000.00
==================================================

✓ Withdrawal successful!
Amount withdrawn: Rs. 5000.00
Remaining balance: Rs. 45000.00

Denominations dispensed:
  2000 Rs. X 2 = 4000
  500 Rs. X 2 = 1000
```

## 🔄 Transaction History Example

```
============================================================
TRANSACTION HISTORY
============================================================
Account: 4532123456789012-CHK | Holder: Gajala

1. [25/05/2026 12:15:30] ACCOUNT CREATED: Initial Balance: Rs. 50000.00
2. [25/05/2026 12:15:45] WITHDRAWAL: Rs. 5000.00 | Balance: Rs. 45000.00
3. [25/05/2026 12:16:20] DEPOSIT: Rs. 10000.00 | Balance: Rs. 55000.00
4. [25/05/2026 12:17:00] TRANSFER OUT: Rs. 2000.00 to 4532123456789012-SAV | Balance: Rs. 53000.00

Current Balance: Rs. 53000.00
============================================================
```

## 📝 Class Descriptions

### User.java
- Manages user authentication
- Stores multiple bank accounts
- Tracks failed login attempts
- Handles PIN changes
- Card locking mechanism

### EnhancedBankAccount.java
- Full account functionality
- Balance operations (deposit/withdraw)
- Transfer between accounts
- Transaction history tracking
- Daily withdrawal limits
- Denomination calculation

### Transaction.java
- Records transaction details
- Timestamps for all transactions
- Supports multiple transaction types
- Formatted output for history

### ATMSystem.java
- Backend user management
- Authentication system
- Data persistence
- Sample data initialization
- File-based storage

### ATMInterface.java
- Complete CLI menu system
- User login/registration
- Account selection
- All transaction operations
- Formatted output

## 🔧 Customization

You can customize:
- Daily withdrawal limit (default: Rs. 50,000)
- Denomination types
- Fast cash preset amounts
- Account initial balances
- PIN length requirements

## 📦 Data Persistence

User data is automatically saved to `atm_data.dat` file in the same directory:
- User credentials
- Account information
- Transaction history
- All balance updates

## ⚙️ Technical Details

- **Language**: Java
- **Architecture**: Object-Oriented
- **Data Storage**: Serialized file-based storage
- **UI**: Command-line interface
- **Persistence**: Automatic save/load on operations

## 🎯 Future Enhancements

- GUI implementation (Swing/JavaFX)
- Database integration (MySQL/PostgreSQL)
- Advanced security (encryption, hashing)
- Email/SMS notifications
- ATM network features
- Advanced reporting
- Admin dashboard

## 📄 License

This project is open source and available for educational purposes.

## 👤 Author

Created by: Nanda Kumar

## 📞 Support

For issues or questions, please create an issue in the repository.

---

**Enjoy using the ATM Interface Simulation!** 🎉
