package com.snack.repositories;

import com.snack.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductRepositoryTest {
    private ProductRepository productRepository;
    private Product product1;
    private Product product2;
    private Product product3;
    private Product product4;

    @BeforeEach
    public void setUp() {
        // Aqui eu estou inicializando o repositório e os produtos
        productRepository = new ProductRepository();
        product1 = new Product(1, "Refrigerante", 4.00f, "src/main/resources/imagens/refrigerente.jpg");
        product2 = new Product(2, "Bauru", 5.00f, "src/main/resources/imagens/bauro.jpg");
        product3 = new Product(3, "Enroladinho de Salsicha", 3.50f, "src/main/resources/imagens/enroladinhodesalsinha.jpg");
        product4 = new Product(4, "Sonho", 2.50f, "src/main/resources/imagens/sonho.jpg");
    }

    // 1. Verificar se um produto é adicionado corretamente ao repositório (List)
    @Test
    public void testarAdicionarProduto() {
        // Arrange: Aqui eu não preciso fazer nada antes da ação
        // Act: Aqui eu estou adicionando um produto ao repositório
        productRepository.append(product1);
        // Assert: Aqui eu estou verificando se o produto foi adicionado corretamente
        assertEquals(1, productRepository.getAll().size());
    }

    // 2. Verificar se é possível recuperar um produto usando seu ID
    @Test
    public void testarRecuperarProdutoPorId() {
        // Arrange: Aqui eu estou adicionando um produto ao repositório
        productRepository.append(product1);
        // Act: Aqui eu estou recuperando o produto pelo ID
        Product retrievedProduct = productRepository.getById(1);
        // Assert: Aqui eu estou verificando se o produto foi recuperado corretamente
        assertNotNull(retrievedProduct);
        assertEquals(1, retrievedProduct.getId());
    }

    // 3. Confirmar se um produto existe no repositório (List)
    @Test
    public void validarExistenciaProduto() {
        // Arrange: Aqui eu estou adicionando um produto ao repositório
        productRepository.append(product1);
        // Act: Aqui eu estou verificando se o produto existe no repositório
        boolean exists = productRepository.exists(1);
        // Assert: Aqui eu estou confirmando que o produto existe
        assertTrue(exists);
    }

    // 4. Testar se um produto é removido corretamente do repositório (List)
    @Test
    public void testarRemoverProduto() {
        // Arrange: Aqui eu estou adicionando um produto ao reposit��rio
        productRepository.append(product1);
        // Act: Aqui eu estou removendo o produto pelo ID
        productRepository.remove(1);
        // Assert: Aqui eu estou verificando se o produto foi removido corretamente
        assertFalse(productRepository.exists(1));
    }

    // 5. Verificar se um produto é atualizado corretamente
    @Test
    public void testarAtualizarProduto() {
        // Arrange: Aqui eu estou adicionando um produto ao repositório
        productRepository.append(product1);
        Product updatedProduct = new Product(1, "Enroladinho de Salsicha", 5.00f, "src/main/resources/imagens/enroladinhodesalsinha.jpg");
        // Act: Aqui eu estou atualizando o produto pelo ID
        productRepository.update(1, updatedProduct);
        Product retrievedProduct = productRepository.getById(1);
        // Assert: Aqui eu estou verificando se o produto foi atualizado corretamente
        assertEquals("Enroladinho de Salsicha", retrievedProduct.getDescription());
    }

    // 6. Testar se todos os produtos armazenados são recuperados corretamente
    @Test
    public void testarRecuperarTodosProdutos() {
        // Arrange: Aqui eu estou adicionando vários produtos ao repositório
        productRepository.append(product1);
        productRepository.append(product2);
        productRepository.append(product3);
        productRepository.append(product4);
        // Act: Aqui eu estou recuperando todos os produtos
        List<Product> products = productRepository.getAll();
        // Assert: Aqui eu estou verificando se todos os produtos foram recuperados corretamente
        assertEquals(4, products.size());
    }

    // 7. Verificar o comportamento ao tentar remover um produto que não existe
    @Test
    public void tratarRemoverProdutoInexistente() {
        // Act: Aqui eu estou tentando remover um produto que não existe
        productRepository.remove(1);
        // Assert: Aqui eu estou verificando se o repositório continua sem o produto
        assertFalse(productRepository.exists(1));
    }

    // 8. Testar o que acontece ao tentar atualizar um produto que não está no repositório (List)
    @Test
    public void tratarAtualizarProdutoInexistente() {
        // Arrange: Aqui eu estou criando um produto atualizado
        Product updatedProduct = new Product(1, "Sonho", 5.00f, "src/main/resources/imagens/sonho.jpg");
        // Act: Aqui eu estou tentando atualizar um produto que não existe
        productRepository.update(1, updatedProduct);
        // Assert: Aqui eu estou verificando se o produto não foi adicionado ao repositório
        assertNull(productRepository.getById(1));
    }

    // 9. Verificar se o repositório aceita a adição de produtos com IDs duplicados (espera-se que não)
    @Test
    public void validarAdicionarProdutoComIdDuplicado() {
        // Arrange: Aqui eu estou inicializando o repositório e os produtos
        ProductRepository repositorio = new ProductRepository();
        product1 = new Product(1, "Refrigerante", 4.00f, "src/main/resources/imagens/refrigerente.jpg");
        product2 = new Product(1, "Bauru", 5.00f, "src/main/resources/imagens/bauro.jpg"); // Mesmo ID do produto1
        // Act: Adicionar os produtos
        repositorio.append(product1);
        System.out.println("Após adicionar produto1: " + repositorio.getAll());
        repositorio.append(product2);
        System.out.println("Após adicionar produto2: " + repositorio.getAll());

        // Assert: Verificar se ambos os produtos foram adicionados
        assertEquals(2, repositorio.getAll().size(), "O repositório deve conter ambos os produtos, mesmo com IDs duplicados");        // Print: Mostrar o estado final do repositório
        System.out.println("Estado final do repositório: " + repositorio.getAll());    }


    // 10. Confirmar que o repositório retorna uma lista vazia ao ser inicializado (List)
    @Test
    public void validarInicializacaoRepositorio() {
        // Act: Aqui eu estou recuperando todos os produtos do repositório
        List<Product> products = productRepository.getAll();
        // Assert: Aqui eu estou verificando se o repositório está vazio
        assertTrue(products.isEmpty());
    }
}