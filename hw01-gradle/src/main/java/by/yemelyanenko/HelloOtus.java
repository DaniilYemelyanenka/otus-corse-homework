package by.yemelyanenko;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class HelloOtus {
    public static void main(String args[]){
        List<Integer> integerList = new ArrayList<>();
        int min = 0;
        int max = 100;
        for(int i = min; i < max; i++){
            integerList.add(i);
        }

        System.out.println(Lists.reverse(integerList));
    }
}
