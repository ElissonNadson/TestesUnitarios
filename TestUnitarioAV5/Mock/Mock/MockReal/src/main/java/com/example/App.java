package com.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Jogador jogador = new Jogador("Messi", 33);
        Jogo jogo = new Jogo(jogador);

        System.out.println(jogo.turno());
    }
}
