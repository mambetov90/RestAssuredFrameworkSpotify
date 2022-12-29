package com.spotify.oauth2.tests;

import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class BaseTest {
    //Run for parallel execution in cli: mvn test (creates Threads for each TC)
    @BeforeMethod
    public void beforeMethod(Method m) {
        System.out.println("STARTING TEST: " + m.getName());
        System.out.println("THREAD ID: " + Thread.currentThread());
    }


}
