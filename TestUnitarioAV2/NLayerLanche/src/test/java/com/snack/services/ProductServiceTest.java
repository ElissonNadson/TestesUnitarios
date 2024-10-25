package com.snack.services;

import com.snack.entities.Product;
import com.snack.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {
    private ProductService productService;
    private ProductRepository productRepository;
    private Product product1;
    private Product product2;

    @BeforeEach
    public void setUp() {
        // Aqui eu estou inicializando o repositório e o serviço de produtos
        productRepository = new ProductRepository();
        productService = new ProductService();
        product1 = new Product(1, "Refrigerante", 4.00f, "src/main/resources/imagens/refrigerente.jpg");
        product2 = new Product(2, "Bauru", 5.00f, "src/main/resources/imagens/bauro.jpg");
    }
//1. Salvar um produto com imagem válida

    @Test
    public void testarSalvarProdutoComImagemValida() {
        // Arrange: Aqui eu estou preparando o produto com imagem válida
        Product produto = new Product(3, "Enroladinho de Salsicha", 3.50f, "src/main/resources/imagens/enroladinhodesalsinha.jpg");

        // Act: Aqui eu estou salvando o produto
        productService.save(produto);
        productRepository.append(produto);

        // Assert: Aqui eu estou verificando se o produto foi salvo corretamente
        assertTrue(productRepository.exists(3));
        System.out.println("Produto salvo com imagem válida: " + productRepository.getById(3));
    }


//2. Salvar um produto com imagem inexistente
@Test
public void testarSalvarProdutoComImagemInexistente() {
    // Arrange: Aqui eu estou preparando o produto com imagem inexistente
    Product produto = new Product(4, "Sonho", 2.50f, "src/main/resources/imagens/inexistente.jpg");

    // Act: Aqui eu estou tentando salvar o produto
    boolean resultado = productService.save(produto);

    // Assert: Aqui eu estou verificando se o produto não foi salvo
    assertFalse(resultado);
    assertFalse(productRepository.exists(4));
    System.out.println("Produto não salvo devido a imagem inexistente: " + productRepository.getById(4));
}
//3. Atualizar um produto existente

    @Test
    public void AtualizarProdutoExistente() {
        // Arrange: Aqui eu estou adicionando o produto original
        Product produtoOriginal = new Product(1, "Refrigerante", 4.00f, "src/main/resources/imagens/refrigerente.jpg");

        productService.save(produtoOriginal);
        System.out.println("Produto original: " + produtoOriginal);

        // Verifica se a imagem original foi salva corretamente
        assertNotNull(productService.getImagePathById(1));

        // Act: Aqui eu estou atualizando o produto
        Product produtoAtualizado = new Product(1, "Refrigerante", 4.00f, "src/main/resources/imagens/refrigerente_diet.jpg");
        productService.update(produtoAtualizado);

        // Assert: Aqui eu estou verificando se a imagem do produto foi atualizada corretamente
        String caminhoImagemAtualizada = productService.getImagePathById(1);
        assertTrue(caminhoImagemAtualizada.contains("src/main/resources/imagens/refrigerente_diet.jpg"));
        System.out.println("Caminho da imagem do produto atualizado: " + caminhoImagemAtualizada);
    }



    @Test
    public void testarRemoverProdutoExistente() {
        // Arrange: Aqui eu estou adicionando o produto ao repositório
        productService.save(product1);
        productRepository.append(product1);

        // Verifica se a imagem original foi salva corretamente
        assertTrue(productRepository.exists(1));
        assertDoesNotThrow(() -> productService.getImagePathById(1));

        // Act: Aqui eu estou removendo o produto
        productService.remove(1);

        // Assert: Aqui eu estou verificando se o produto foi removido corretamente
        assertFalse(productRepository.exists(1));
        System.out.println("Produto removido: " + product1);
    }

    @Test
    public void testarObterCaminhoImagemPorId() {
        // Arrange: Aqui eu estou adicionando o produto ao repositório
        productRepository.append(product1);

        // Act: Aqui eu estou obtendo o caminho da imagem pelo ID
        String caminhoImagem = product1.getImage();

        // Assert: Aqui eu estou verificando se o caminho da imagem foi obtido corretamente
        assertEquals("src/main/resources/imagens/refrigerente.jpg", caminhoImagem);
        System.out.println("Caminho da imagem do produto: " + caminhoImagem);
    }
}