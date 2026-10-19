package by.yemelyanenko;

import java.util.Arrays;
import java.util.Objects;

public class ChangeElements<T>{

    private final T[] t;

    public ChangeElements(T[] t){
        this.t = Objects.requireNonNull(t,"Массив не может быть null");
    }

    public void swap(int firstElementPosition,int secondElementPosition){
        if(firstElementPosition<0 || firstElementPosition >= t.length
                || secondElementPosition < 0 || secondElementPosition >= t.length){
            throw  new IllegalArgumentException("Неправильное значение индексов.");
        }

        if( firstElementPosition == secondElementPosition) return;

        T el = t[firstElementPosition];
        t[firstElementPosition] = t[secondElementPosition];
        t[secondElementPosition] = el;
    }

    public T[] getArray() {
        T[] copy = this.t.clone();
        return copy;
    }
}
