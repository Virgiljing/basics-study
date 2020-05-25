package com.virgilin.basic.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;

public class CglibMain {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CGSubject.class);
        enhancer.setCallback(new HelloInterceptor());
        CGSubject cgSubject = (CGSubject) enhancer.create();
        cgSubject.sayHello();
    }
}
