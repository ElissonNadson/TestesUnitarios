package com.example;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ClasseB classeB = new ClasseB();
        ClasseA classeA = new ClasseA(classeB);
        System.out.println("Resultado: " + classeA.sumValues());
    }
}
