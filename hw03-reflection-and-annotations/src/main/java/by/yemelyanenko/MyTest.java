package by.yemelyanenko;

import by.yemelyanenko.annotations.After;
import by.yemelyanenko.annotations.Before;
import by.yemelyanenko.annotations.Test;

public class MyTest {


    @After
    public void after(){
        System.out.println("After");
    }

    @Test
    public void testOk(){
        System.out.println("test ok!");
    }

    @Before
    public void before(){
        System.out.println("before");
    }

    @Test
    public void testFailed(){
        System.out.println("test failed.....");
        throw new RuntimeException("Simulate error");
    }
}
