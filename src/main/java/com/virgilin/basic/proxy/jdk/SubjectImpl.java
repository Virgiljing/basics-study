package com.virgilin.basic.proxy.jdk;

public final class  SubjectImpl implements Subject {
    @Override
    public void hello(String param) {
        System.out.println("hello " + param);
    }
}
