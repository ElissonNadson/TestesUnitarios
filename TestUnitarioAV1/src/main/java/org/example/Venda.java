package org.example;

public class Venda {
    private Produto produto;
    private int quantidadeVendida;
    private double totalVenda;

    public Venda(Produto produto, int quantidadeVendida) {
        this.produto = produto;
        this.quantidadeVendida = quantidadeVendida;
    }
//Se o produto diminuir o estoque pela quantidade vendida, calcula o total da venda e retorna verdadeiro; caso contrário, retorna falso.
    public boolean realizarVenda() {
        if (produto.diminuirEstoque(quantidadeVendida)) {
            totalVenda = produto.getPreco() * quantidadeVendida;
            return true;
        }
        return false;
    }

//aqui retorn o valor total da venda.
    public double getTotalVenda() {
        return totalVenda;
    }

//Retorna o produto associado à venda.
    public Produto getProduto() {
        return produto;
    }

//Retorna a quantidade de produtos vendidos.
    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }
}
