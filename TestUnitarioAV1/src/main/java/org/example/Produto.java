package org.example;

public class Produto {
    private String nome;
    private double preco;
    private int estoque;


    //Construtor que inicializa o produto com nome, preço e estoque fornecidos.
    public Produto(String nome, double preco, int estoque) {
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }


    //Retorna o nome do produto.
    public String getNome() {
        return nome;
    }

    //Define o nome do produto.
    public void setNome(String nome) {
        this.nome = nome;
    }

    //Retorna o preço do produto.
    public double getPreco() {
        return preco;
    }

    //Define o preço do produto.
    public void setPreco(double preco) {
        this.preco = preco;
    }

    //Retorna a quantidade de estoque do produto.
    public int getEstoque() {
        return estoque;
    }

    //Define a quantidade de estoque do produto.
    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    //Se o estoque for suficiente, diminui a quantidade especificada e retorna verdadeiro; caso contrário, retorna falso.
    public boolean diminuirEstoque(int quantidade) {
        if (estoque >= quantidade) {
            estoque -= quantidade;
            return true;
        }
        return false;
    }

    //Aumenta a quantidade de estoque do produto pela quantidade especificada.
    public void aumentarEstoque(int quantidade) {
        estoque += quantidade;
    }
}
