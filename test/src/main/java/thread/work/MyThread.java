package thread.work;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class MyThread extends Thread {
    //最小的中奖金额
    static final BigDecimal MIN = BigDecimal.valueOf(0.01);
    //共享数据
    //100块，分成3个包
    static BigDecimal money = BigDecimal.valueOf(100);
    static int count = 3;

    @Override
    public void run() {
        //循环
        //同步代码块
        //判断，共享数据是否到了末尾（已经到末尾）
        //判断，共享数据是否到了末尾（没有到末尾）
        synchronized (MyThread.class) {
            if (count == 0) {
                //判断，共享数据到了末尾
                System.out.println(getName() + "没有抢到红包");
            } else {
                BigDecimal prize;
                //判断，共享数据是否到了末尾
                if (count == 1) {
                    //表示此时是最后一个公报
                    //无需随机，剩余所有的钱都是中奖金额
                    prize = money;
                } else {
                    //表示第一次、第n次（随机）
                    Random r = new Random();
                    //100元  3个包
                    //第一个：99.98
                    //100-（3-1）*0.01
                    double bounds = money.subtract(BigDecimal.valueOf(count - 1).multiply(MIN)).doubleValue();
                    prize = BigDecimal.valueOf(r.nextDouble(bounds));
                }
                prize = prize.setScale(2, RoundingMode.HALF_DOWN);
                //从money当中，去掉当前中奖的金额
                money = money.subtract(prize);
                //红包的个数-1
                count--;
                //本次红包的信息进行打印
                System.out.println(getName() + "抢到了" + prize + "元");
            }
        }
    }
}
