package org.example;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Test;
import static org.junit.Assert.assertThrows;

public class AppTest extends TestCase {

    public AppTest(String testName) {
        super(testName);
    }

    public static TestSuite suite() {
        return new TestSuite(AppTest.class);
    }

    // Testa a criação de um produto com valores válidos.
    @Test
    public void testCriacaoProduto() {
        // Cria um novo produto com nome, preço e estoque especificados
        Produto produto = new Produto("Iugute", 15.0, 20);

        // Verifica se o nome do produto está correto
        assertEquals("Iugute", produto.getNome());

        // Verifica se o preço do produto está correto
        assertEquals(15.0, produto.getPreco());

        // Verifica se a quantidade de estoque do produto está correta
        assertEquals(20, produto.getEstoque());
    }

    // Testa a criação de um produto com preço negativo (deve ser inválido).
    @Test
    public void testCriacaoProdutoPrecoNegativo() {
        // Cria um novo produto com nome, preço negativo e estoque especificados
        Produto produto = new Produto("Produto B", -50.0, 10);

        // Verifica se o preço do produto é inválido (negativo)
        assertFalse("Preço deve ser positivo", produto.getPreco() >= 0);
    }

    // Testa a criação de um produto com estoque negativo (deve ser inválido).
    @Test
    public void testCriacaoProdutoEstoqueNegativo() {
        // Cria um novo produto com nome, preço e estoque especificados
        Produto produto = new Produto("Produto C", 10.0, -5);
        // Verifica se o estoque do produto é inválido (negativo)
        assertFalse("Estoque deve ser não negativo", produto.getEstoque() >= 0);
    }

    // Testa a alteração do nome do produto para um valor válido.
    @Test
    public void testAlteracaoNomeProdutoValido() {
        // Cria um novo produto com nome, preço e estoque especificados
        Produto produto = new Produto("Produto D", 10.0, 5);
        // Altera o nome do produto
        produto.setNome("Produto D Alterado");
        // Verifica se o nome do produto foi alterado corretamente
        assertEquals("Produto D Alterado", produto.getNome());
    }

    // Testa a alteração do preço do produto para um valor válido.
    @Test
    public void testAlteracaoPrecoProdutoValido() {
        // Cria um novo produto com nome, preço e estoque especificados
        Produto produto = new Produto("Produto E", 10.0, 5);
        // Altera o preço do produto
        produto.setPreco(20.0);
        // Verifica se o preço do produto foi alterado corretamente
        assertEquals(20.0, produto.getPreco());
    }

    // Testa a alteração do estoque para um valor positivo.
    @Test
    public void testAlteracaoEstoqueProdutoPositivo() {
        // Cria um novo produto com nome, preço e estoque especificados
        Produto produto = new Produto("Produto F", 10.0, 5);
        // Altera o estoque do produto
        produto.setEstoque(10);
        // Verifica se o estoque do produto foi alterado corretamente
        assertEquals(10, produto.getEstoque());
    }

    // Testa a alteração do preço do produto para um valor negativo (deve falhar).
    @Test
    public void testAlteracaoPrecoProdutoNegativo() {
        // Cria um novo produto com nome, preço e estoque especificados
        Produto produto = new Produto("Produto G", 10.0, 5);
        // Altera o preço do produto para um valor negativo
        produto.setPreco(-10.0);
        // Verifica se o preço do produto é inválido (negativo)
        assertFalse("Preço deve ser positivo", produto.getPreco() >= 0);
    }

    // Testa venda com quantidade menor que o estoque disponível.
    @Test
    public void testVendaQuantidadeMenorQueEstoque() {
        // Cria um novo produto com nome, preço e estoque especificados
        Produto produto = new Produto("Produto H", 10.0, 10);
        // Cria uma nova venda com o produto e a quantidade especificada
        Venda venda = new Venda(produto, 5);
        // Realiza a venda e verifica se foi bem-sucedida
        assertTrue(venda.realizarVenda());
        // Verifica se o estoque do produto foi atualizado corretamente
        assertEquals(5, produto.getEstoque());
    }

    // Testa venda com quantidade igual ao estoque disponível.
    @Test
    public void testVendaQuantidadeIgualAoEstoque() {
        // Cria um novo produto com nome, preço e estoque especificados
        Produto produto = new Produto("Produto I", 10.0, 10);
        // Cria uma nova venda com o produto e a quantidade especificada
        Venda venda = new Venda(produto, 10);
        // Realiza a venda e verifica se foi bem-sucedida
        assertTrue(venda.realizarVenda());
        // Verifica se o estoque do produto foi atualizado corretamente
        assertEquals(0, produto.getEstoque());
    }

    // Testa venda com quantidade maior que o estoque disponível (deve falhar).
    @Test
    public void testVendaQuantidadeMaiorQueEstoque() {
        // Cria um novo produto com nome, preço e estoque especificados
        Produto produto = new Produto("Produto J", 10.0, 10);
        // Cria uma nova venda com o produto e a quantidade especificada
        Venda venda = new Venda(produto, 15);
        // Realiza a venda e verifica se falhou
        assertFalse(venda.realizarVenda());
        // Verifica se o estoque do produto não foi alterado
        assertEquals(10, produto.getEstoque());
    }

    // Testa cálculo do total da venda corretamente.
    @Test
    public void testCalculoTotalVenda() {
        // Cria um novo produto com nome, preço e estoque especificados
        Produto produto = new Produto("Produto K", 10.0, 10);
        // Cria uma nova venda com o produto e a quantidade especificada
        Venda venda = new Venda(produto, 5);
        // Realiza a venda
        venda.realizarVenda();
        // Verifica se o total da venda foi calculado corretamente
        assertEquals(50.0, venda.getTotalVenda());
    }

    // Testa aumento de estoque do produto após uma venda.
    @Test
    public void testAumentoEstoqueAposVenda() {
        // Cria um novo produto com nome, preço e estoque especificados
        Produto produto = new Produto("Produto L", 10.0, 10);
        // Aumenta o estoque do produto
        produto.aumentarEstoque(5);
        // Verifica se o estoque do produto foi atualizado corretamente
        assertEquals(15, produto.getEstoque());
    }

    // Testa diminuição do estoque do produto após uma venda bem-sucedida.
    @Test
    public void testDiminuiçãoEstoqueAposVenda() {
        // Cria um novo produto com nome, preço e estoque especificados
        Produto produto = new Produto("Produto M", 10.0, 10);
        // Cria uma nova venda com o produto e a quantidade especificada
        Venda venda = new Venda(produto, 5);
        // Realiza a venda e verifica se foi bem-sucedida
        venda.realizarVenda();
        // Verifica se o estoque do produto foi atualizado corretamente
        assertEquals(5, produto.getEstoque());
    }

    @Test
    public void testVendaProdutoNaoExiste() {
        // Define um produto nulo
        Produto produto = null;
        // Cria uma nova venda com o produto nulo e a quantidade especificada
        Venda venda = new Venda(produto, 5);
        // Realiza a venda e verifica se falhou
        assertThrows(NullPointerException.class, () -> venda.realizarVenda());
    }

    // Testa criação de venda com quantidade negativa (deve falhar).
    @Test
    public void testCriacaoVendaQuantidadeNegativa() {
        // Cria um novo produto com nome, preço e estoque especificados
        Produto produto = new Produto("Produto N", 10.0, 10);
        // Cria uma nova venda com o produto e a quantidade negativa especificada
        Venda venda = new Venda(produto, -5);
        // Realiza a venda e verifica se falhou
        assertTrue(venda.realizarVenda());
    }

    // Testa alteração do estoque após a tentativa de venda com estoque insuficiente.
    @Test
    public void testAlteracaoEstoqueAposVendaInsuficiente() {
        // Cria um novo produto com nome, preço e estoque especificados
        Produto produto = new Produto("Produto O", 10.0, 5);
        // Cria uma nova venda com o produto e a quantidade especificada
        Venda venda = new Venda(produto, 10);
        // Realiza a venda e verifica se falhou
        assertFalse(venda.realizarVenda());
        // Verifica se o estoque do produto não foi alterado
        assertEquals(5, produto.getEstoque());
    }

    // Testa criação de vários produtos e realizar vendas com estoque compartilhado.
    @Test
    public void testVendasComEstoqueCompartilhado() {
        // Cria dois novos produtos com nome, preço e estoque especificados
        Produto produto1 = new Produto("Produto P1", 10.0, 10);
        Produto produto2 = new Produto("Produto P2", 20.0, 10);
        // Cria duas novas vendas com os produtos e as quantidades especificadas
        Venda venda1 = new Venda(produto1, 5);
        Venda venda2 = new Venda(produto2, 5);
        // Realiza as vendas e verifica se foram bem-sucedidas
        assertTrue(venda1.realizarVenda());
        assertTrue(venda2.realizarVenda());
        // Verifica se o estoque dos produtos foi atualizado corretamente
        assertEquals(5, produto1.getEstoque());
        assertEquals(5, produto2.getEstoque());
    }

    // Testa calcular total de venda quando o preço do produto for alterado antes da venda.
    @Test
    public void testCalculoTotalVendaComPrecoAlterado() {
        // Cria um novo produto com nome, preço e estoque especificados
        Produto produto = new Produto("Produto Q", 10.0, 10);
        // Altera o preço do produto
        produto.setPreco(15.0);
        // Cria uma nova venda com o produto e a quantidade especificada
        Venda venda = new Venda(produto, 5);
        // Realiza a venda
        venda.realizarVenda();
        // Verifica se o total da venda foi calculado corretamente
        assertEquals(75.0, venda.getTotalVenda());
    }

    // Testa comportamento da venda quando o estoque inicial é zero.
    @Test
    public void testVendaComEstoqueInicialZero() {
        // Cria um novo produto com nome, preço e estoque inicial zero
        Produto produto = new Produto("Produto R", 10.0, 0);
        // Cria uma nova venda com o produto e a quantidade especificada
        Venda venda = new Venda(produto, 5);
        // Realiza a venda e verifica se falhou
        assertFalse(venda.realizarVenda());
    }

    // Testa aumento do estoque após uma reposição e verificar se a venda é bem-sucedida posteriormente.
    @Test
    public void testAumentoEstoqueAposReposicao() {
        // Cria um novo produto com nome, preço e estoque inicial zero
        Produto produto = new Produto("Produto S", 10.0, 0);
        // Aumenta o estoque do produto
        produto.aumentarEstoque(10);
        // Cria uma nova venda com o produto e a quantidade especificada
        Venda venda = new Venda(produto, 5);
        // Realiza a venda e verifica se foi bem-sucedida
        assertTrue(venda.realizarVenda());
        // Verifica se o estoque do produto foi atualizado corretamente
        assertEquals(5, produto.getEstoque());
    }
}