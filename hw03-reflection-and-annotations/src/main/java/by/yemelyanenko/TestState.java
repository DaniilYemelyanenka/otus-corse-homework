package by.yemelyanenko;

import java.util.ArrayList;
import java.util.List;

public class TestState {
    private int total;
    private int passed;
    private int failed;
    private final List<String> failedList = new ArrayList<>();

    public void passed(){
        total++;
        passed++;
    }

    public void failed(String name,Throwable t){
        total++;
        failed++;
        failedList.add(String.format("Test %s failed with exception %s, cause %s",
                name,
                t.getClass().getName(),
                t.getCause().getMessage()));
    }

    public int getTotal() {
        return total;
    }

    public int getPassed() {
        return passed;
    }

    public int getFailed() {
        return failed;
    }

    public List<String> getFailedList() {
        return failedList;
    }
}
