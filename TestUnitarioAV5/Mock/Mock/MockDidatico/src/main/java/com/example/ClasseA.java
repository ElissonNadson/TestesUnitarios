package com.example;

public class ClasseA {
    private final ClasseB classeB;
    private int y = 5;

    public ClasseA(ClasseB classeB) {
        this.classeB = classeB;
    }

    public int sumValues() {
        int x = classeB.getX();
        return this.y + x;
    }
}
