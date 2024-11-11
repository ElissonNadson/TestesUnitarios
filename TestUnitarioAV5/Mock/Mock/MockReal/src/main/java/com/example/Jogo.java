package com.example;

public class Jogo {
    private final Jogador jogador;

    public Jogo(Jogador jogador) {
        this.jogador = jogador;
    }

    public String turno() {
        String test = this.jogador.getNone();
        return "Começou o turno do jogador: " + this.jogador.getNone() + " com idade de " + this.jogador.getIdade();
    }

}
