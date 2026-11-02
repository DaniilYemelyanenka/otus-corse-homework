package by.yemelyanenko.Data;

public class Pet {
    public int age;
    private String name;

    public Pet(String name,int x) {
        this.age = x;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                '}';
    }
}
