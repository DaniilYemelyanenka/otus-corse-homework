package by.yemelyanenko;

import by.yemelyanenko.annotations.After;
import by.yemelyanenko.annotations.Before;
import by.yemelyanenko.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public final class TestRunner {
    private TestRunner(){}

    public static void run(Class<?> clazz){

        TestState state = new TestState();

        List<Method> beforeMethods = findMethods(clazz, Before.class);
        List<Method> testMethods = findMethods(clazz, Test.class);
        List<Method> afterMethods = findMethods(clazz, After.class);

        for(Method method : testMethods){
            runSingleMethod(method,beforeMethods,afterMethods,state,clazz);
        }
        printTestsRunResult(state);
    }

    private static void runSingleMethod(Method method,
                                        List<Method> beforeMethods,
                                        List<Method> afterMethods,
                                        TestState state,
                                        Class<?> clazz) {
        System.out.printf("--> Run %s test. ============\n",method.getName());

        Throwable error = null;
        try{
            Object instance = clazz.getConstructor().newInstance();
            try{
                for(Method m : beforeMethods){
                    m.invoke(instance);
                }
                method.invoke(instance);
            }catch (Throwable t){
                error = t;
            }finally{
                for(Method m : afterMethods){
                    m.invoke(instance);
                }
            }
        } catch (Throwable t) {
            if(error == null) error = t;
        }
        if(error == null) state.passed();
        else state.failed(method.getName(),error);
    }

    private static List<Method> findMethods(Class<?> clazz,Class<? extends Annotation> annotationClazz){
        List<Method> methodList = new ArrayList<>();
        Method[] methods = clazz.getMethods();
        for(Method method : methods){
            if(method.isAnnotationPresent(annotationClazz)) methodList.add(method);
        }
        return methodList;
    }

    private static void printTestsRunResult(TestState state) {
        System.out.println("========== total results ==============");
        System.out.printf("Total tests: %d; \nPassed tests: %d;\nFailed tests: %d - list of failed tests --->\n",
                state.getTotal(),
                state.getPassed(),
                state.getFailed());
        for(String s: state.getFailedList()){
            System.out.println(s);
        }
    }
}
