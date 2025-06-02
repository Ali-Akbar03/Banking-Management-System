// Abstract base class (Abstraction)
abstract class Bank {
    private String bankName;


    public Bank(String name) {
        this.bankName = name;
    }


    public String getBankName() {
        return bankName;
    }


    public abstract void displayBankInfo();
}


// Abstract Account class demonstrating Encapsulation & Inheritance
abstract class Account extends Bank {
    private String accHolder;
    private String accNumber;
    protected double balance;


    public Account(String bankName, String accHolder, String accNumber, double balance) {
        super(bankName);
        this.accHolder = accHolder;
        this.accNumber = accNumber;
        this.balance = balance;
    }


    public String getAccHolder() {
        return accHolder;
    }


    public String getAccNumber() {
        return accNumber;
    }


    public double getBalance() {
        return balance;
    }


    public abstract void withdraw(double amount); // Polymorphism (overridden in subclasses)


    public void displayAccount() {
        System.out.println("Bank: " + getBankName());
        System.out.println("Account Holder: " + accHolder);
        System.out.println("Account Number: " + accNumber);
        System.out.println("Balance: $" + balance);
    }


    @Override
    public void displayBankInfo() {
        System.out.println("Welcome to " + getBankName() + " Bank.");
    }
}


// SavingsAccount class demonstrating Polymorphism
class SavingsAccount extends Account {
    private double interestRate;


    public SavingsAccount(String bankName, String accHolder, String accNumber, double balance, double interestRate) {
        super(bankName, accHolder, accNumber, balance);
        this.interestRate = interestRate;
    }


    public void addInterest() {
        double interest = balance * interestRate / 100;
        balance += interest;
        System.out.println("Interest added: $" + interest);
    }


    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("$" + amount + " withdrawn from Savings. New balance: $" + balance);
        } else {
            System.out.println("Insufficient balance in Savings Account.");
        }
    }
}


// CurrentAccount class demonstrating Polymorphism
class CurrentAccount extends Account {
    private double overdraftLimit;


    public CurrentAccount(String bankName, String accHolder, String accNumber, double balance, double overdraftLimit) {
        super(bankName, accHolder, accNumber, balance);
        this.overdraftLimit = overdraftLimit;
    }


    @Override
    public void withdraw(double amount) {
        if (balance + overdraftLimit >= amount) {
            balance -= amount;
            System.out.println("$" + amount + " withdrawn from Current. New balance: $" + balance);
        } else {
            System.out.println("Overdraft limit exceeded in Current Account.");
        }
    }
}


// Main class
public class BankingSystem {
    public static void main(String[] args) {
        Account savings = new SavingsAccount("ABC Bank", "Alice", "SA123", 1000, 5);
        Account current = new CurrentAccount("ABC Bank", "Bob", "CA456", 500, 300);


        System.out.println("=== Savings Account ===");
        savings.displayBankInfo();
        savings.displayAccount();
        savings.withdraw(200);
        ((SavingsAccount) savings).addInterest(); // Downcasting to access subclass-specific method


        System.out.println("\n=== Current Account ===");
        current.displayBankInfo();
        current.displayAccount();
        current.withdraw(700);
    }
}
