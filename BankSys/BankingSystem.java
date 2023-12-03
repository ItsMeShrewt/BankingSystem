import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Account {

    private int acc_Number;
    private String accountHolderName;
    private String accType;
    private double balance;

    // Constructor
    public Account(int acc_Number, String accountHolderName, String accType) {
        this.acc_Number = acc_Number;
        this.accountHolderName = accountHolderName;
        this.accType = accType;
        this.balance = 0.0; // Initial balance is set to 0
    }

    // Getter methods
    public int getAccountNumber() {
        return acc_Number;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public String getaccountType() {
        return accType;
    }

    // Other methods
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit successful. Updated balance: ₱" + balance);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. Updated balance: ₱" + balance);
        } else {
            System.out.println("Insufficient funds. Withdrawal failed.");
        }
    }

    public void transfer(Account recipient, double amount) {
        if (amount <= balance) {
            balance -= amount;
            recipient.deposit(amount);
            System.out.println("Transfer successful. Updated balance: ₱" + balance);
        } else {
            System.out.println("Insufficient funds. Transfer failed.");
        }
    }
}
//Main Class
public class BankingSystem {
    private static Map<Integer, Account> accounts = new HashMap<>();
    private static Scanner bs = new Scanner(System.in);

    public static void main(String[] args) {
        int option = -1;

        while (option != 5) {
            System.out.println("_________________________________________");
            System.out.println("||     Welcome to the Bank of CC3D     ||");
            System.out.println("||_____________________________________||");
            System.out.println("|| [0] Create an Account               ||");
            System.out.println("|| [1] Check Account Details           ||");
            System.out.println("|| [2] Deposit                         ||");
            System.out.println("|| [3] Withdraw                        ||");
            System.out.println("|| [4] Transfer                        ||");
            System.out.println("|| [5] Exit                            ||");
            System.out.println("||_____________________________________||");
            System.out.println("Please enter your option: ");
            option = bs.nextInt();
//User Interaction
            switch (option) {
                case 0:
                    createAccount();
                    break;

                case 1:
                    checkAccountDetails();
                    break;

                case 2:
                    performDeposit();
                    break;

                case 3:
                    performWithdrawal();
                    break;

                case 4:
                    performTransfer();
                    break;

                case 5:
                    System.out.println("Thank you for using CC3D Bank.");
                    break;

                default:
                    System.out.println("Invalid option...");
                    break;
            }
        }
    }

    private static void createAccount() {
        System.out.println("Enter account number:");
        int acc_Number = bs.nextInt();
        System.out.println("Enter account holder name:");
        String accountHolderName = bs.next();
        System.out.println("Choose account type: (Savings)(Current)");
        String accType = bs.next();

        Account newAccount = new Account(acc_Number, accountHolderName, accType);
        accounts.put(acc_Number, newAccount);

        System.out.println("Account created successfully.");
    }

    private static void checkAccountDetails() {
        System.out.println("Enter your Account Number:");
        int acc_Number = bs.nextInt();

        if (accounts.containsKey(acc_Number)) {
            Account account = accounts.get(acc_Number);
            System.out.println("Account Details:");
            System.out.println("Account Holder: " + account.getAccountHolderName());
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Account Type: " + account.getaccountType());
            System.out.println("Balance: ₱" + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }
    // Update the performDeposit() method
    private static void performDeposit() {
        System.out.println("Enter your Account Number:");
        int acc_Number = bs.nextInt();

        if (accounts.containsKey(acc_Number)) {
            Account account = accounts.get(acc_Number);
            System.out.println("Enter deposit amount (₱):");
            String amountString = "₱" + bs.next().trim(); // Add the peso sign (₱) before reading the amount
            double dAmount = Double.parseDouble(amountString.replace("₱", "").replace(",", ""));
            account.deposit(dAmount);
        } else {
            System.out.println("Account not found.");
        }
    }

    // Update the performWithdrawal() method
    private static void performWithdrawal() {
        System.out.println("Enter your Account Number:");
        int acc_Number = bs.nextInt();

        if (accounts.containsKey(acc_Number)) {
            Account account = accounts.get(acc_Number);
            System.out.println("Enter withdrawal amount (₱):");
            String amountString = "₱" + bs.next().trim(); // Add the peso sign (₱) before reading the amount
            double wAmount = Double.parseDouble(amountString.replace("₱", "").replace(",", ""));
            account.withdraw(wAmount);
        } else {
            System.out.println("Account not found.");
        }
    }

    // Update the performTransfer() method
    private static void performTransfer() {
        System.out.println("Enter your Account Number:");
        int acc_Number = bs.nextInt();

        if (accounts.containsKey(acc_Number)) {
            Account senderAccount = accounts.get(acc_Number);
            System.out.println("Enter the account number of the recipient:");
            int acc_Number2 = bs.nextInt();

            if (accounts.containsKey(acc_Number2)) {
                Account recipientAccount = accounts.get(acc_Number2);
                System.out.println("Enter transfer amount (₱):");
                String amountString = "₱" + bs.next().trim(); // Add the peso sign (₱) before reading the amount
                double tAmount = Double.parseDouble(amountString.replace("\u20B1", "").replace(",", ""));
                senderAccount.transfer(recipientAccount, tAmount);
            } else {
                System.out.println("Recipient account not found.");
            }
        } else {
            System.out.println("Sender account not found.");
        }
    }
}
