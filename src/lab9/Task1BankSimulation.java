package lab9;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
public class Task1BankSimulation {

    public static class Account {
        private final int id;
        private int balance;

        public Account(int id, int initialBalance) {
            this.id = id;
            this.balance = initialBalance;
        }

        public int getId() {
            return id;
        }

        public synchronized int getBalance() {
            return balance;
        }

        public void withdraw(int amount) {
            this.balance -= amount;
        }

        public void deposit(int amount) {
            this.balance += amount;
        }
    }

    public static class Bank {
        public void transfer(Account from, Account to, int amount) {
            if (from == to || amount <= 0) {
                return;
            }


            Account firstLock = from.getId() < to.getId() ? from : to;
            Account secondLock = from.getId() < to.getId() ? to : from;

            synchronized (firstLock) {
                synchronized (secondLock) {
                    if (from.getBalance() >= amount) {
                        from.withdraw(amount);
                        to.deposit(amount);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int numberOfAccounts = 100;
        int totalTransfers = 100_000;
        int threadPoolSize = 32;

        List<Account> accounts = new ArrayList<>(numberOfAccounts);
        Random random = new Random();

        for (int i = 0; i < numberOfAccounts; i++) {
            accounts.add(new Account(i, 1_000 + random.nextInt(9_000)));
        }

        long initialTotalBalance = accounts.stream().mapToLong(Account::getBalance).sum();
        System.out.println("Початковий загальний баланс банку: " + initialTotalBalance);

        Bank bank = new Bank();
        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);

        for (int i = 0; i < totalTransfers; i++) {
            executor.submit(() -> {
                Account from = accounts.get(random.nextInt(numberOfAccounts));
                Account to = accounts.get(random.nextInt(numberOfAccounts));
                int amount = 1 + random.nextInt(500);
                bank.transfer(from, to, amount);
            });
        }

        executor.shutdown();
        boolean completed = executor.awaitTermination(30, TimeUnit.SECONDS);

        long finalTotalBalance = accounts.stream().mapToLong(Account::getBalance).sum();
        System.out.println("Завершення переказів: " + (completed ? "успішно" : "за таймаутом"));
        System.out.println("Кінцевий загальний баланс банку:    " + finalTotalBalance);
        System.out.println("Коректність інваріанту: " + (initialTotalBalance == finalTotalBalance));
    }
}