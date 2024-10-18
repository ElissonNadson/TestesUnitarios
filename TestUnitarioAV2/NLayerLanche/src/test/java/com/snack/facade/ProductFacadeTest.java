package com.snack.facade;

import com.snack.applications.ProductApplication;
import com.snack.entities.Product;
import com.snack.repositories.ProductRepository;
import com.snack.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductFacadeTest {
    private ProductRepository productRepository;
    private ProductService productService;
    private ProductApplication productApplication;
    private ProductFacade productFacade;
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
        productFacade = new ProductFacade(productApplication);

        product1 = new Product(1, "Hotdog", 4.00f, "src/main/resources/imagens/HotDog.jpg");
        product2 = new Product(2, "Burger", 5.00f, "src/main/resources/imagens/Burger.jpg");
    }

    @Test
    public void deveRetornarListaCompletaDeProdutos() {
        productFacade.append(product1);
        productFacade.append(product2);
        List<Product> products = productFacade.getAll();
        assertEquals(2, products.size(), "Deve haver 2 produtos na lista.");
    }

    @Test
    public void deveRetornarProdutoCorretoPorId() {
        productFacade.append(product1);
        Product retrievedProduct = productFacade.getById(product1.getId());
        assertNotNull(retrievedProduct, "O produto deve ser recuperado por ID válido.");
        assertEquals(product1.getId(), retrievedProduct.getId(), "O ID do produto recuperado deve ser igual ao ID do produto adicionado.");
    }

    @Test
    public void deveRetornarTrueParaIdExistenteEFalseParaIdInexistente() {
        productFacade.append(product1);
        assertTrue(productFacade.exists(product1.getId()), "O produto deve existir no repositório.");
        assertFalse(productFacade.exists(999), "O produto não deve existir no repositório.");
    }

    @Test
    public void deveAdicionarNovoProdutoCorretamente() {
        productFacade.append(product1);
        List<Product> products = productFacade.getAll();
        assertTrue(products.contains(product1), "A lista de produtos deve conter o produto adicionado.");
    }

    @Test
    public void deveRemoverProdutoExistentePorId() {
        productFacade.append(product1);
        productFacade.remove(product1.getId());
        assertFalse(productFacade.exists(product1.getId()), "O produto deve ser removido do repositório.");
    }
}