package by.yemelyanenko;

import by.yemelyanenko.Data.Pet;

public class Main {
    public static void main(String[] args){
        /*
        * Тестируем
        * Задание 1 : Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);
        *
         */
        Pet pet1 = new Pet("Bobik");
        Pet pet2 = new Pet("Sharik");
        Pet pet3 = new Pet("Barsik");

        ChangeElements<Pet> petChangeElements = new ChangeElements<>(new Pet[]{pet1,pet2,pet3});

        petChangeElements.swap(0,2);
        System.out.println("swapped: -> ");
        for (Pet pet: petChangeElements.getArray()){
            System.out.print(pet.toString() + ", ");
        }
        System.out.println(" ");

        petChangeElements.swap(1,2);
        System.out.println("swapped: -> ");
        for (Pet pet: petChangeElements.getArray()){
            System.out.print(pet.toString() + ", ");
        }
        System.out.println(" ");
        /*
        * конец задания 1
        *
        * Задание 2 : Написать метод, который преобразует массив в ArrayList;
        * + берем массив петов из задания 1
         */

        MyArrayList<Pet> petArrayList = new MyArrayList<>();

        System.out.println(petArrayList.fromArray(petChangeElements.getArray()));
        System.out.println(petArrayList.fromArray(petChangeElements.getArray()).getClass());
        /*
         * конец задания 2
         *
         * Задание 3 : Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
         *             Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
         *             Посчитать, сколько раз встречается каждое слово.
         */

        WordArray wordArray = new WordArray(new String[]{
                "Anna", "Ivan", "Maria", "Petr", "Olga",
                "Anna", "Sergey", "Maria", "Dmitry", "Elena",
                "Ivan", "Anna", "Olga", "Maxim", "Maria",
                "Sergey", "Elena", "Petr", "Dmitry", "Anna"
        });

        System.out.println(wordArray.findWordFrequencies());
        System.out.println(wordArray.findUnique());
    }
}
