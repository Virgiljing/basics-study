package com.virgilin.basic.proxy.jdk;

import java.lang.reflect.Proxy;

public class SubjectMain {

    public static void main(String[] args) {
        Subject subject = new SubjectImpl();
        SubjectProxy proxy = new SubjectProxy(subject);
        Subject subjectProxy = (Subject) Proxy.newProxyInstance(proxy.getClass().getClassLoader(), subject.getClass().getInterfaces(), proxy);
        subjectProxy.hello("world");


    }
}
