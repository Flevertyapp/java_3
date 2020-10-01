package lesson1;

import lesson1.fruits.Apple;
import lesson1.fruits.Orange;
import java.util.ArrayList;
import java.util.Arrays;

public class MainHW1 {
    public static void main(String[] args) {
        String[] arr = {"chto","gde","kogda"};
        System.out.println(Arrays.toString(arr));
        swap(arr, 0, 2);
        System.out.println(Arrays.toString(arr));
        ArrayList<String> al= arrayToList(arr);
        System.out.println(al);

        BoxWithFruits<Apple> box1 = new BoxWithFruits<>();
        BoxWithFruits<Apple> box2 = new BoxWithFruits<>();

    }

    public static <T> ArrayList<T> arrayToList(T[] arr){ //преобразование массива в лист
        return new ArrayList<>(Arrays.asList(arr));
    }

    public static void swap(Object[] arr, int index1, int index2){  //замена индексов местами
        Object obj = arr[index1];
        arr[index1]= arr[index2];
        arr[index2]= obj;
    }


}
