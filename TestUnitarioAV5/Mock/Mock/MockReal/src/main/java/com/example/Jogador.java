package com.example;

public class Jogador {
    private String nome;
    private int idade;

    public Jogador(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNone() {
        return this.nome;
    }

    public int getIdade() {
        return this.idade;
    }
}
