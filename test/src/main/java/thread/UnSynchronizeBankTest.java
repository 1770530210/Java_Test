package thread;

public class UnSynchronizeBankTest {
    public static final int DELAY = 10;
    public static final int NAC_COUNTS = 100;
    //    public static final int NAC_COUNTS = 0;
    public static final double MAX_AMOUNT = 1000;
    public static final double INITIAL_BALANCE = 1000;

    public static void main(String[] args) {
        var bank = new Bank(NAC_COUNTS, INITIAL_BALANCE);
        for (int i = 0; i < NAC_COUNTS; i++) {
            int fromAccount = i;
            Runnable runnable = () -> {
                try {
                    while (true) {
                        int toAccount = (int) (bank.size() * Math.random());
                        double amount = MAX_AMOUNT * Math.random();
                        bank.transfer(fromAccount, toAccount, amount);
                        Thread.sleep((int) (DELAY * Math.random()));
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            };
            var t = new Thread(runnable);
            t.start();
        }
    }
}
