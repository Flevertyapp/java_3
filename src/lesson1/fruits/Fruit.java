package lesson1.fruits;

public class Fruit {
    private float weight;

    public float getWeight() {
        return weight;
    }

    public Fruit(float weight) { //через конструктор будет записываться вес фрукта в наследниках
        this.weight = weight;
    }
}
