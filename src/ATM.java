import java.util.*;
public class ATM {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankAccount userAccount = new BankAccount("GAJALA", 100000.00);
        final int USER_PIN = 1234;
        System.out.print("Enter your 4-digit PIN: ");
        int pin = sc.nextInt();
        if (pin >= 1000 && pin <= 9999) {
            if (pin == USER_PIN) {
                int choice;
                do {
                    System.out.println("\n===== ATM Menu =====");
                    System.out.println("1. Check Balance");
                    System.out.println("2. Deposit Money");
                    System.out.println("3. Withdraw Money");
                    System.out.println("4. Transaction History");
                    System.out.println("5. Exit");
                    System.out.print("Enter your choice: ");
                    choice = sc.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.println("Current Balance: Rs." + userAccount.getBalance());
                            break;
                        case 2:
                            System.out.print("Enter amount to deposit: Rs.");
                            double depositAmount = sc.nextDouble();
                            System.out.print("Enter the count of 100's notes:");
			    double Hu= sc.nextDouble();
			    System.out.print("Enter the count of 200's notes:");
			    double Tw= sc.nextDouble();
                            System.out.print("Enter the count of 500's notes:");
		            double Fi= sc.nextDouble();
			    double checkAmount=Hu*100+Tw*200+500*Fi;
			     if(checkAmount==depositAmount){
			     userAccount.deposit(depositAmount);
			      }
			     else{
				System.out.println("Amount is matched- Please Verify ");
			      }
                            break;
                        case 3:
                            System.out.print("Enter amount to withdraw: Rs.");
                            double withdrawAmount = sc.nextDouble();
                            userAccount.withdraw(withdrawAmount);
                            break;
                        case 4:
                            userAccount.showTransactions();
                            break;
                        case 5:
                            System.out.println("Thank you for using our ATM!");
                            break;
                        default:
                            System.out.println("Invalid choice! Try again.");
                    }
                } while (choice != 5);
            } else {
                System.out.println("Incorrect PIN! Access Denied.");
            }

        } else {
            System.out.println("ENTER 4 DIGITS PIN ONLY!");
        }
        sc.close();
    }
}
