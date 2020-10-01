package lesson4;

//wait, notify, notifyAll применяются только в синхронизированной области, нужен монитор

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ABC {
    static final Object mon = new Object();
    static volatile char currentLetter= 'A';
    static final int num = 5;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(() -> {
        printA();
        printB();
        printC();

        });

        executorService.shutdown();


    }
    public static void printA() {
        synchronized (mon) {
            try {
                for (int i = 0; i < num; i++) {
                    while (currentLetter != 'A') //проверяем, вдруг раньше времени проснется
                        mon.wait();                 //ждем
                    System.out.print("A");
                    currentLetter = 'B';
                    mon.notifyAll();                //сделали шрязное дело и будим всех
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void printB() {
        synchronized (mon) {
            try {
                for (int i = 0; i < num; i++) {
                    while (currentLetter != 'В')
                        mon.wait();
                    System.out.print("B");
                    currentLetter = 'C';
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void printC() {
        synchronized (mon) {
            try {
                for (int i = 0; i < num; i++) {
                    while (currentLetter != 'С')
                        mon.wait();
                    System.out.print("C");
                    currentLetter = 'A';
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
