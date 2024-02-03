import java.util.Scanner;

// Account class representing a bank account
class Account {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    // Constructor
    public Account(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    // Getter methods
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: $" + balance);
        } else {
            System.out.println("Invalid amount for deposit.");
        }
    }

    // Withdrawal method
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: $" + balance);
        } else {
            System.out.println("Invalid amount for withdrawal or insufficient funds.");
        }
    }

    // Check balance method
    public void checkBalance() {
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Current Balance: $" + balance);
    }
}

// ATM class representing the ATM machine
class ATM {
    private Account currentAccount;

    // Method to authenticate the user
    public boolean authenticate(String enteredAccountNumber, String enteredPIN) {
        // Dummy authentication logic, replace with actual authentication mechanism
        return enteredAccountNumber.equals("123456") && enteredPIN.equals("1234");
    }

    // Method to display ATM menu
    public void displayMenu() {
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    // Method to execute ATM operations
    public void executeOperation(int choice) {
        Scanner scanner = new Scanner(System.in);

        switch (choice) {
            case 1:
                currentAccount.checkBalance();
                break;
            case 2:
                System.out.print("Enter deposit amount: $");
                double depositAmount = scanner.nextDouble();
                currentAccount.deposit(depositAmount);
                break;
            case 3:
                System.out.print("Enter withdrawal amount: $");
                double withdrawalAmount = scanner.nextDouble();
                currentAccount.withdraw(withdrawalAmount);
                break;
            case 4:
                System.out.println("Exiting ATM. Thank you!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        scanner.close();
    }

    // Method to start ATM operations
    public void startATM() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.next();

        System.out.print("Enter PIN: ");
        String pin = scanner.next();

        if (authenticate(accountNumber, pin)) {
            System.out.println("Authentication successful. Welcome!");

            // Create an account object (replace with database retrieval)
            currentAccount = new Account(accountNumber, "Account Holder Name", 1000.0);

            while (true) {
                displayMenu();
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                executeOperation(choice);
            }
        } else {
            System.out.println("Authentication failed. Exiting ATM.");
        }
        scanner.close();
    }
}

// Main class to run the ATM application
public class ATMApplication {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.startATM();
    }
}
