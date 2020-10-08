package lesson5;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainHW5 {
    public static final int CARS_COUNT = 4;


    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }


        Lock lock = new ReentrantLock();  //наверное так не хорошо делать, но работает же)
        new Thread(() -> {
            for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
            //System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            lock.lock();
            try {
                Thread.sleep(30000);        //заглушка со слипом, с локом не хочет работать

            } catch (Exception e) {
                e.printStackTrace();
            }
            lock.unlock();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");

        }).start();


    }
}

