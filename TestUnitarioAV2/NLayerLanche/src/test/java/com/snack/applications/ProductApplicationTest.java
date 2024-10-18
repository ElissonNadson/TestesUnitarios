package com.snack.applications;

import com.snack.entities.Product;
import com.snack.repositories.ProductRepository;
import com.snack.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductApplicationTest {
    private ProductRepository productRepository;
    private ProductService productService;
    private ProductApplication productApplication;
    private Product product1;
    private Product product2;

    @BeforeEach
    public void configurar() {
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
        productService = new ProductService();
        productApplication = new ProductApplication(productRepository, productService);

        product1 = new Product(1, "Hotdog", 4.00f, "src/main/resources/imagens/HotDog.jpg");
        product2 = new Product(2, "Burger", 5.00f, "src/main/resources/imagens/Burger.jpg");
    }

    @Test
    public void deveListarTodosOsProdutosDoRepositorio() {
        productApplication.append(product1);
        productApplication.append(product2);
        List<Product> products = productApplication.getAll();
        assertEquals(2, products.size(), "Deve haver 2 produtos no repositório.");
    }

    @Test
    public void deveObterProdutoPorIdValido() {
        productApplication.append(product1);
        Product retrievedProduct = productApplication.getById(product1.getId());
        assertNotNull(retrievedProduct, "O produto deve ser recuperado por ID válido.");
        assertEquals(product1.getId(), retrievedProduct.getId(), "O ID do produto recuperado deve ser igual ao ID do produto adicionado.");
    }

    @Test
    public void deveRetornarNuloAoObterProdutoPorIdInvalido() {
        Product retrievedProduct = productApplication.getById(999);
        assertNull(retrievedProduct, "Deve retornar nulo ao tentar obter produto por ID inválido.");
    }

    @Test
    public void deveVerificarSeProdutoExistePorIdValido() {
        productApplication.append(product1);
        assertTrue(productApplication.exists(product1.getId()), "O produto deve existir no repositório.");
    }

    @Test
    public void deveRetornarFalsoAoVerificarExistenciaDeProdutoPorIdInvalido() {
        assertFalse(productApplication.exists(999), "Deve retornar falso ao verificar a existência de um produto com ID inválido.");
    }

    @Test
    public void deveAdicionarNovoProdutoESalvarImagemCorretamente() {
        productApplication.append(product1);
        File imageFile = new File(product1.getImage());
        assertTrue(imageFile.exists(), "A imagem do produto deve ser salva corretamente.");
    }

    @Test
    public void deveRemoverProdutoExistenteEDeletarImagem() {
        productApplication.append(product1);
        productApplication.remove(product1.getId());
        assertFalse(productApplication.exists(product1.getId()), "O produto deve ser removido do repositório.");
        File imageFile = new File(product1.getImage());
        assertFalse(imageFile.exists(), "A imagem do produto deve ser deletada.");
    }

    @Test
    public void naoDeveAlterarSistemaAoRemoverProdutoComIdInexistente() {
        int initialSize = productApplication.getAll().size();
        productApplication.remove(999); // ID inexistente
        assertEquals(initialSize, productApplication.getAll().size(), "O tamanho da lista de produtos não deve mudar ao tentar remover um produto inexistente.");
    }

    @Test
    public void deveAtualizarProdutoExistenteESubstituirImagem() {
        productApplication.append(product1);
        Product updatedProduct = new Product(1, "Updated Hotdog", 5.00f, "src/main/resources/imagens/UpdatedHotDog.jpg");
        productApplication.update(product1.getId(), updatedProduct);
        Product retrievedProduct = productApplication.getById(product1.getId());
        assertEquals(updatedProduct.getDescription(), retrievedProduct.getDescription(), "A descrição do produto deve ser atualizada.");
        assertEquals(updatedProduct.getPrice(), retrievedProduct.getPrice(), "O preço do produto deve ser atualizado.");
        assertEquals(updatedProduct.getImage(), retrievedProduct.getImage(), "A imagem do produto deve ser atualizada.");
    }
}