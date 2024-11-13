package com.example.applications;

import com.example.entities.Product;
import com.example.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


// _____________________________________
@ExtendWith(MockitoExtension.class)
// _____________________________________

public class ProductApplicationTest {
//
    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductApplication productApplication;

    private Product product;
    // ______________________________________________________________________

    @Test
    public void deveSalvarImagemCorretamente() {
        // Arrange - Preparar cenário
        Product produto = any(Product.class);

        // Act - Realizar a ação
        productApplication.append(produto);


        // Assert -Checar o resultado
        Mockito.verify(productService, times(1)).save(product);

    }
    // ______________________________________________________________________

    @Test
    public void deveRemoverImagemCorretamente() {
        // Arrange - Preparar cenário
        int id = 1;

        // Act - Realizar a ação
        productApplication.remove(id);

        // Assert -Checar o resultado
        Mockito.verify(productService, times(1)).remove(id);
    }
    // ______________________________________________________________________



    //Deve atualizar a imagem corretamente.
    @Test
    public void deveAtualizarImagemCorretamente() {
        // Arrange - Preparar cenário
        int id = 1;

        Product produto = any(Product.class);

        // Act - Realizar a ação
        productApplication.update(id, produto);

        // Assert - Checar o resultado
        Mockito.verify(productService, times(1)).update(produto);
    }

}