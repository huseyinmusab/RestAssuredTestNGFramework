package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.listeners.RetryListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import java.lang.reflect.Method;


@Listeners(RetryListener.class)
public class BaseTest {

    @BeforeMethod
    public void beforeMethod(Method m){
        System.out.println("=================================");
        System.out.println("STARTING TEST: " + m.getName());
        System.out.println("THREAD ID: " + Thread.currentThread().getId());
        System.out.println("THREAD NAME: " + Thread.currentThread().getName());
        System.out.println("=================================");

    }
}
