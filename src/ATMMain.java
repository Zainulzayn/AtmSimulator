import java.util.Scanner;

class Account {
    private String accountNumber;
    private double balance;

    public Account(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: " + amount);
        } else {
            System.out.println("Deposit amount must be positive!");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrew: " + amount);
            return true;
        } else {
            System.out.println("Insufficient funds or invalid amount!");
            return false;
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}

class ATMSimulator {
    private Account account;

    public ATMSimulator(Account account) {
        this.account = account;
    }

    public void checkBalance() {
        System.out.println("Current balance: " + account.getBalance());
    }

    public void withdrawCash(Scanner scanner) {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        account.withdraw(amount);
    }

    public void depositMoney(Scanner scanner) {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        account.deposit(amount);
    }

    public void transferFunds(Scanner scanner, Account targetAccount) {
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();
        if (account.withdraw(amount)) {
            targetAccount.deposit(amount);
            System.out.println("Successfully transferred: " + amount + " to account " + targetAccount.getAccountNumber());
        }
    }
}

public class ATMMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create two accounts for simulation
        Account account1 = new Account("123456789", 500.00);
        Account account2 = new Account("987654321", 300.00);

        ATMSimulator atmSimulator = new ATMSimulator(account1);

        while (true) {
            // Prompt for the next action
            System.out.print("Select an option (1: Check Balance, 2: Withdraw, 3: Deposit, 4: Transfer, 5: Exit): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    atmSimulator.checkBalance();
                    break;
                case 2:
                    atmSimulator.withdrawCash(scanner);
                    break;
                case 3:
                    atmSimulator.depositMoney(scanner);
                    break;
                case 4:
                    atmSimulator.transferFunds(scanner, account2);
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
}
