package lesson5;

import java.util.concurrent.CountDownLatch;

public class Car implements Runnable {
    private static int CARS_COUNT;

    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {  //блок подготовки участников, делаем через CDL, может через CB надо было, но бред получается
        final CountDownLatch cdl = new CountDownLatch(CARS_COUNT);
        for (int i = 0; i < CARS_COUNT; i++) {
            new Thread(() -> {
                try {
                    System.out.println(this.name + " готовится"); //адовое дублирование вывода
                    Thread.sleep(500 + (int) (Math.random() * 800));
                    System.out.println(this.name + " готов");  //аналогично
                    cdl.countDown();
                }  catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
        try {
            cdl.await();   //ждем всех
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Все участники готовы!!!"); //может здесь некорректно выводить?
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        }

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
    }
}
