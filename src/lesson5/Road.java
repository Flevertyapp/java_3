package lesson5;

import static lesson5.MainHW5.CARS_COUNT;

public class Road extends Stage {
        public Road(int length) {
            this.length = length;
            this.description = "Дорога " + length + " метров";
        }
        @Override
        public void go(Car c) {
            for (int i = 0; i < CARS_COUNT; i++) {
                try {
                    System.out.println(c.getName() + " начал этап: " + description);
                    Thread.sleep(length / c.getSpeed() * 1000);
                    System.out.println(c.getName() + " закончил этап: " + description);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (i == 0){
                    System.out.println(c.getName() + "!!!!!!!!!! Победил в гонке!!! "); //может через лок переписать???
                }
            }

        }
    }
