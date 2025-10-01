import java.util.Scanner;

class Transaction {
    private double amount;
    private String type;

    public Transaction(double amount, String type) {
        this.amount = amount;
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public void print() {
        System.out.println(type + ": " + amount);
    }
}

class BankAccount {
    private double balance;
    private Transaction[] transactions;
    private int transactionCount;
    private static final int MAX_TRANSACTIONS = 100;

    public BankAccount() {
        this.balance = 0.0;
        this.transactions = new Transaction[MAX_TRANSACTIONS];
        this.transactionCount = 0;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            if (transactionCount < MAX_TRANSACTIONS) {
                transactions[transactionCount] = new Transaction(amount, "DEPOSIT");
                transactionCount++;
            }
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            if (transactionCount < MAX_TRANSACTIONS) {
                transactions[transactionCount] = new Transaction(amount, "WITHDRAW");
                transactionCount++;
            }
        }
    }

    public double getBalance() {
        return balance;
    }

    public void printTransactions() {
        if (transactionCount == 0) {
            System.out.println("Нет транзакций.");
            return;
        }
        for (int i = 0; i < transactionCount; i++) {
            System.out.print(i + 1 + ". ");
            transactions[i].print();
        }
    }

    public void searchTransactions(double amount) {
        boolean found = false;
        for (int i = 0; i < transactionCount; i++) {
            if (transactions[i].getAmount() == amount) {
                System.out.print("Найдено: ");
                transactions[i].print();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Транзакции с такой суммой не найдены.");
        }
    }
}

public class BankApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account = null;
        boolean running = true;

        while (running) {
            System.out.println("\n=== Банковский счёт ===");
            System.out.println("1. Открыть счёт");
            System.out.println("2. Положить деньги");
            System.out.println("3. Снять деньги");
            System.out.println("4. Показать баланс");
            System.out.println("5. Вывести список транзакций");
            System.out.println("6. Найти транзакцию по сумме");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    account = new BankAccount();
                    System.out.println("Счёт успешно открыт!");
                    break;

                case 2:
                    if (account == null) {
                        System.out.println("Сначала откройте счёт!");
                        break;
                    }
                    System.out.print("Введите сумму для пополнения: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    System.out.println("Счёт пополнен.");
                    break;

                case 3:
                    if (account == null) {
                        System.out.println("Сначала откройте счёт!");
                        break;
                    }
                    System.out.print("Введите сумму для снятия: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    System.out.println("Сумма снята.");
                    break;

                case 4:
                    if (account == null) {
                        System.out.println("Сначала откройте счёт!");
                        break;
                    }
                    System.out.println("Текущий баланс: " + account.getBalance());
                    break;

                case 5:
                    if (account == null) {
                        System.out.println("Сначала откройте счёт!");
                        break;
                    }
                    account.printTransactions();
                    break;

                case 6:
                    if (account == null) {
                        System.out.println("Сначала откройте счёт!");
                        break;
                    }
                    System.out.print("Введите сумму для поиска: ");
                    double searchAmount = scanner.nextDouble();
                    account.searchTransactions(searchAmount);
                    break;

                case 0:
                    running = false;
                    System.out.println("До свидания!");
                    break;

                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }

        scanner.close();
    }
}