package com.snack.applications;

import com.snack.entities.Product;
import com.snack.repositories.ProductRepository;
import com.snack.services.ProductService;

import java.util.List;

public class ProductApplication {
    private ProductRepository productRepository; // Repositório de produtos
    private ProductService productService; // Serviço de produtos

    // Construtor que inicializa o repositório e o serviço de produtos
    public ProductApplication(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    // **************

    // Método para obter todos os produtos
    public List<Product> getAll() {
        return this.productRepository.getAll();
    }

    // **************

    // Método para obter um produto pelo ID
    public Product getById(int id) {
        return this.productRepository.getById(id);
    }

    // **************

    // Método para verificar se um produto existe pelo ID
    public boolean exists(int id) {
        return this.productRepository.exists(id);
    }

    // **************

    // Método para adicionar um novo produto
    public void append(Product product) {
        this.productRepository.append(product);
        this.productService.save(product);
    }

    // **************

    // Método para remover um produto pelo ID
    public void remove(int id) {
        this.productRepository.remove(id);
        this.productService.remove(id);
    }

    // **************

    // Método para atualizar um produto existente
    public void update(int id, Product product) {
        this.productRepository.update(id, product);
        this.productService.update(product);
    }

    // **************

    // Método para vender um produto, retornando o valor total da venda
    public float sellProduct(int id, int quantity) {
        Product product = this.productRepository.getById(id);
        return product.sellProduct(quantity);
    }
}