package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    private Produto produto;
        assertEquals("Produto D Alterado", produto.getNome());
    }

    @Test
    public void testAlteracaoPrecoProdutoValido() {
        produto.setPreco(20.0);
        assertEquals(20.0, produto.getPreco(), 0.001);
    }

    @Test
    public void testAlteracaoEstoqueProdutoPositivo() {
        produto.setEstoque(10);
        assertEquals(10, produto.getEstoque());
    }

    @Test
    public void testAlteracaoPrecoProdutoNegativo() {
        produto.setPreco(-10.0);
        assertFalse("Preço deve ser positivo", produto.getPreco() >= 0);
    }

    @Test
    public void testVendaQuantidadeMenorQueEstoque() {
        Venda venda = new Venda(produto, 5);
        assertTrue(venda.realizarVenda());
        assertEquals(5, produto.getEstoque());
    }

    @Test
    public void testVendaQuantidadeIgualAoEstoque() {
        Venda venda = new Venda(produto, 10);
        assertTrue(venda.realizarVenda());
        assertEquals(0, produto.getEstoque());
    }

    @Test
    public void testVendaQuantidadeMaiorQueEstoque() {
        Venda venda = new Venda(produto, 15);
        assertFalse(venda.realizarVenda());
        assertEquals(10, produto.getEstoque());
    }

    @Test
    public void testCalculoTotalVenda() {
        Venda venda = new Venda(produto, 5);
        venda.realizarVenda();
        assertEquals(50.0, venda.getTotalVenda(), 0.001);
    }

    @Test
    public void testAumentoEstoqueAposVenda() {
        produto.aumentarEstoque(5);
        assertEquals(15, produto.getEstoque());
    }

    @Test
    public void testDiminuiçãoEstoqueAposVenda() {
        Venda venda = new Venda(produto, 5);
        venda.realizarVenda();
        assertEquals(5, produto.getEstoque());
    }

    @Test
    public void testVendaProdutoNaoExiste() {
        Produto produto = null;
        Venda venda = new Venda(produto, 5);
        assertThrows(NullPointerException.class, () -> venda.realizarVenda());
    }

    @Test
    public void testCriacaoVendaQuantidadeNegativa() {
        Venda venda = new Venda(produto, -5);
        assertFalse(venda.realizarVenda());
    }

    @Test
    public void testAlteracaoEstoqueAposVendaInsuficiente() {
        Venda venda = new Venda(produto, 10);
        assertFalse(venda.realizarVenda());
        assertEquals(10, produto.getEstoque());
    }

    @Test
    public void testVendasComEstoqueCompartilhado() {
        Produto produto1 = new Produto("Produto P1", 10.0, 10);
        Produto produto2 = new Produto("Produto P2", 20.0, 10);
        Venda venda1 = new Venda(produto1, 5);
        Venda venda2 = new Venda(produto2, 5);
        assertTrue(venda1.realizarVenda());
        assertTrue(venda2.realizarVenda());
        assertEquals(5, produto1.getEstoque());
        assertEquals(5, produto2.getEstoque());
    }

    @Test
    public void testCalculoTotalVendaComPrecoAlterado() {
        produto.setPreco(15.0);
        Venda venda = new Venda(produto, 5);
        venda.realizarVenda();
        assertEquals(75.0, venda.getTotalVenda(), 0.001);
    }

    @Test
    public void testVendaComEstoqueInicialZero() {
        Produto produto = new Produto("Produto R", 10.0, 0);
        Venda venda = new Venda(produto, 5);
        assertFalse(venda.realizarVenda());
    }

    @Test
    public void testAumentoEstoqueAposReposicao() {
        Produto produto = new Produto("Produto S", 10.0, 0);
        produto.aumentarEstoque(10);
        Venda venda = new Venda(produto, 5);
        assertTrue(venda.realizarVenda());
        assertEquals(5, produto.getEstoque());
    }
}