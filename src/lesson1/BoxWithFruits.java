package lesson1;

import lesson1.fruits.Fruit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoxWithFruits<T extends Fruit> {  //Т-фрукт дибо наследники
    private List<T> container;   //ссылка по интерфейсу

    public BoxWithFruits() {
        this.container = new ArrayList<>();
    }

    public BoxWithFruits(T...fruits){
        this.container = new ArrayList<>(Arrays.asList(fruits));
    }

    public float getWeight(){   //вес коробки, проходим по коллекции и добавдем вес каждого фрукта
        float w = 0.0f;
        for (T fruit : container) {
            w += fruit.getWeight();
        }
        return w;
    }

    public boolean sameAvg (BoxWithFruits<?> another){ //сравнивалка двух коробок //вопрос для сравнивания любых типов фруктов по массе
        return  Math.abs(this.getWeight() - another.getWeight()) < 0.001; //не забывать про погрешность округления
    }

    public void transfer (BoxWithFruits<? super T> another){ //пересыпание в другую коробку //супер для работы с несколькими видами фруктов
        if (another == this){ //защита от пересыпания коробки в себя, подсмотрено на уроке)
            return;
        }
        another.container.addAll(this.container);
        this.container.clear();  //очищение ящика!
    }

    public void add(T fruit){ //добавление в коробку
        container.add(fruit);
    }
}
