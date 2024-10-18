package com.snack.services;

import com.snack.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {
    private ProductService productService;
    private Product product1;
    private Product product2;

    @BeforeEach
    public void configurar() {
        productService = new ProductService();
        product1 = new Product(1, "Hotdog", 4.00f, "src/main/resources/imagens/HotDog.jpg");
        product2 = new Product(2, "Burger", 5.00f, "src/main/resources/imagens/NonExistent.jpg");
    }

    @Test
    public void deveSalvarProdutoComImagemValida() {
        File imageFile = new File(product1.getImage());
        if (!imageFile.exists()) {
            fail("A imagem do produto não existe no caminho especificado.");
        }
        productService.save(product1);
    }

    @Test
    public void deveSalvarProdutoComImagemInexistente() {
        File imageFile = new File(product2.getImage());
        if (imageFile.exists()) {
            fail("A imagem do produto não deveria existir no caminho especificado.");
        }
        productService.save(product2);
    }

    @Test
    public void deveAtualizarProdutoExistente() {
        productService.save(product1);
        Product updatedProduct = new Product(1, "Updated Hotdog", 5.00f, "src/main/resources/imagens/UpdatedHotDog.jpg");
        productService.update(updatedProduct);
    }

    @Test
    public void deveRemoverProdutoExistente() {
        productService.save(product1);
        productService.remove(product1.getId());
    }

    @Test
    public void deveObterCaminhoDaImagemPorId() {
        productService.save(product1);
        String imagePath = productService.getImagePathById(product1.getId());
        assertEquals(product1.getImage(), imagePath);
    }
}