package com.snack.repositories;

import com.snack.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductRepositoryTest {
    private ProductRepository productRepository;
    private Product product1;
    private Product product2;
    private Product product3;

    @BeforeEach
    public void setUp() {
        productRepository = new ProductRepository() {
            private final List<Product> products = new ArrayList<>();

            @Override
            public void append(Product product) {
                products.add(product);
            }

            @Override
            public List<Product> getAll() {
                return products;
            }

            @Override
            public Product getById(int id) {
                return products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
            }

            @Override
            public boolean exists(int id) {
                return products.stream().anyMatch(p -> p.getId() == id);
            }

            @Override
            public void remove(int id) {
                products.removeIf(product -> product.getId() == id);
            }

            @Override
            public void update(int id, Product product) {
                Product productInDb = products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
                if (productInDb != null) {
                    productInDb.setDescription(product.getDescription());
                    productInDb.setPrice(product.getPrice());
                    productInDb.setImage(product.getImage());
                }
            }
        };
        product1 = new Product(1, "Hotdog", 4.00f, "src/main/resources/imagens/HotDog.jpg");
        product2 = new Product(2, "Burger", 5.00f, "src/main/resources/imagens/Burger.jpg");
        product3 = new Product(3, "Pizza", 6.00f, "src/main/resources/imagens/Pizza.jpg");
    }

    private void assertProductEquals(Product expected, Product actual) {
        assertAll("product",
                () -> assertEquals(expected.getId(), actual.getId(), "ID do produto deve ser igual."),
                () -> assertEquals(expected.getDescription(), actual.getDescription(), "Descrição do produto deve ser igual."),
                () -> assertEquals(expected.getPrice(), actual.getPrice(), "Preço do produto deve ser igual."),
                () -> assertEquals(expected.getImage(), actual.getImage(), "Imagem do produto deve ser igual.")
        );
    }

    @Test
    public void deveAdicionarProdutoCorretamenteAoRepositorio() {
        int initialSize = productRepository.getAll().size();
        productRepository.append(product1);
        productRepository.append(product2);
        List<Product> products = productRepository.getAll();

        assertAll("adicionarProduto",
                () -> assertEquals(initialSize + 2, products.size(), "O tamanho da lista de produtos deve aumentar em 2 após adicionar dois produtos."),
                () -> assertTrue(products.contains(product1), "A lista de produtos deve conter o primeiro produto adicionado."),
                () -> assertTrue(products.contains(product2), "A lista de produtos deve conter o segundo produto adicionado.")
        );
        System.out.println("Teste deveAdicionarProdutoCorretamenteAoRepositorio passou.");
    }

    @Test
    public void deveRecuperarProdutoCorretamentePorId() {
        productRepository.append(product1);
        productRepository.append(product2);
        Product retrievedProduct1 = productRepository.getById(product1.getId());
        Product retrievedProduct2 = productRepository.getById(product2.getId());

        assertAll("recuperarProduto",
                () -> assertProductEquals(product1, retrievedProduct1),
                () -> assertProductEquals(product2, retrievedProduct2)
        );
        System.out.println("Teste deveRecuperarProdutoCorretamentePorId passou.");
    }

    @Test
    public void deveConfirmarSeProdutoExisteNoRepositorio() {
        productRepository.append(product1);
        productRepository.append(product2);
        assertAll("produtoExiste",
                () -> assertTrue(productRepository.exists(product1.getId()), "O primeiro produto deve existir no repositório."),
                () -> assertTrue(productRepository.exists(product2.getId()), "O segundo produto deve existir no repositório.")
        );
        System.out.println("Teste deveConfirmarSeProdutoExisteNoRepositorio passou.");
    }

    @Test
    public void deveRemoverProdutoCorretamenteDoRepositorio() {
        productRepository.append(product1);
        productRepository.append(product2);
        productRepository.remove(product1.getId());
        assertAll("removerProduto",
                () -> assertFalse(productRepository.exists(product1.getId()), "O primeiro produto deve ser removido do repositório."),
                () -> assertTrue(productRepository.exists(product2.getId()), "O segundo produto deve ainda existir no repositório.")
        );
        System.out.println("Teste deveRemoverProdutoCorretamenteDoRepositorio passou.");
    }

    @Test
    public void deveAtualizarProdutoCorretamente() {
        productRepository.append(product1);
        Product updatedProduct = new Product(1, "Updated Hotdog", 5.00f, "src/main/resources/imagens/UpdatedHotDog.jpg");
        productRepository.update(product1.getId(), updatedProduct);
        Product retrievedProduct = productRepository.getById(product1.getId());

        assertProductEquals(updatedProduct, retrievedProduct);
        System.out.println("Teste deveAtualizarProdutoCorretamente passou.");
    }

    @Test
    public void deveRecuperarTodosOsProdutosArmazenados() {
        productRepository.append(product1);
        productRepository.append(product2);
        List<Product> products = productRepository.getAll();
        assertEquals(2, products.size(), "Deve haver 2 produtos no repositório.");
        System.out.println("Teste deveRecuperarTodosOsProdutosArmazenados passou.");
    }

    @Test
    public void deveVerificarComportamentoAoRemoverProdutoInexistente() {
        int initialSize = productRepository.getAll().size();
        productRepository.remove(999); // ID inexistente
        assertEquals(initialSize, productRepository.getAll().size(), "O tamanho da lista de produtos não deve mudar ao tentar remover um produto inexistente.");
        System.out.println("Teste deveVerificarComportamentoAoRemoverProdutoInexistente passou.");
    }

    @Test
    public void deveVerificarComportamentoAoAtualizarProdutoInexistente() {
        Product updatedProduct = new Product(999, "Nonexistent Product", 5.00f, "src/main/resources/imagens/NonexistentProduct.jpg");
        productRepository.update(999, updatedProduct); // ID inexistente
        assertNull(productRepository.getById(999), "O produto inexistente não deve ser adicionado ao repositório.");
        System.out.println("Teste deveVerificarComportamentoAoAtualizarProdutoInexistente passou.");
    }

    @Test
    public void deveVerificarSeRepositorioAceitaProdutosComIdsDuplicados() {
        productRepository.append(product1);
        Product duplicateProduct = new Product(1, "Duplicate Hotdog", 4.00f, "src/main/resources/imagens/DuplicateHotDog.jpg");
        productRepository.append(duplicateProduct);
        long count = productRepository.getAll().stream().filter(p -> p.getId() == product1.getId()).count();
        assertEquals(2, count, "O repositório não deve aceitar produtos com IDs duplicados.");
        System.out.println("Teste deveVerificarSeRepositorioAceitaProdutosComIdsDuplicados passou.");
    }

    @Test
    public void deveConfirmarQueRepositorioRetornaListaVaziaAoSerInicializado() {
        List<Product> products = productRepository.getAll();
        assertTrue(products.isEmpty(), "O repositório deve retornar uma lista vazia ao ser inicializado.");
        System.out.println("Teste deveConfirmarQueRepositorioRetornaListaVaziaAoSerInicializado passou.");
    }
}