package by.yemelyanenko;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class MyArrayList<T> {

    public ArrayList<T> fromArray(T[] t){
        Objects.requireNonNull(t,"Массив не может быть null");
        return new ArrayList<>(Arrays.asList(t));
    }
}
