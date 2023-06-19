package thread.concurrent.synchronize;

import java.util.Arrays;

public class Bank {
    private final double[] accounts;

    /**
     * Constructs the bank
     *
     * @param n
     * @param initialBalance
     */
    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
    }

    /**
     * Transfers money from one account to another.
     *
     * @param from
     * @param to
     * @param amount
     * @throws InterruptedException
     */
    public synchronized void transfer(int from, int to, int amount) throws InterruptedException {
        while (accounts[from] < amount) {
            wait();
        }
        accounts[from] -= amount;
        accounts[to] += amount;
        notifyAll();
    }

    /**
     * Gets the sum of all account balances
     *
     * @return the total balance
     */
    public synchronized double getTotalBalance() {
        double sum = 0;
        for (double a : accounts) {
            sum += a;
        }
        return sum;
    }

    /**
     * Gets the number of accounts in the bank
     *
     * @return the number of accounts
     */
    public int size() {
        return accounts.length;
    }
}
