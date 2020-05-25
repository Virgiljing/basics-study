package com.virgilin.basic.proxy.cglib;

/**
 * final
 * java.lang.IllegalArgumentException: Cannot subclass final class com.virgilin.basic.proxy.cglib.CGSubject
 */
public  class CGSubject {
    public void sayHello(){
        System.out.println("hello world");
    }
}
