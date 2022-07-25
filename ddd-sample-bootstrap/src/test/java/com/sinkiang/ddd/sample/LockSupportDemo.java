package com.sinkiang.ddd.sample;

/**
 * @author dengxj
 * @date 2022/7/25 10:28
 */
import java.util.concurrent.locks.LockSupport;

/**
 * 开启两个线程A、B，打印1到10，线程A打印奇数（1、3、5、7、9），线程B打印偶数（2、4、6、8、10）
 */
public class LockSupportDemo {

    static Thread thread1;
    static Thread thread2;

    public static void main(String[] args) {
        thread1 = new Thread(() -> {
            for (int i = 1; i <= 99; i += 2) {
                System.out.println(thread1.getName() + "-" + i);
                LockSupport.unpark(thread2);
                LockSupport.park();
            }
        }, "thread_1");
        thread2 = new Thread(() -> {
            for (int i = 2; i <= 100; i = i + 2) {
                LockSupport.park();
                System.out.println(thread2.getName() + "-" + i);
                LockSupport.unpark(thread1);
            }
        }, "thread_2");
        thread1.start();
        thread2.start();
    }
}

