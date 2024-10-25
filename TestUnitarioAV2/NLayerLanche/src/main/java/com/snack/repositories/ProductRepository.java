package com.snack.repositories;

import com.snack.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private List<Product> products; // Lista de produtos

    // Construtor que inicializa a lista de produtos
    public ProductRepository() {
        this.products = new ArrayList<>();
    }

    // **************

    // Método para obter todos os produtos
    public List<Product> getAll() {
        return new ArrayList<>(this.products);
    }

    // **************

    // Método para obter um produto pelo ID
    public Product getById(int id) {
        return this.products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // **************

    // Método para verificar se um produto existe pelo ID
    public boolean exists(int id) {
        return this.products.stream()
                .anyMatch(product -> product.getId() == id);
    }

    // **************

    // Método para adicionar um novo produto
    public void append(Product product) {
        this.products.add(product);
    }

    // **************

    // Método para remover um produto pelo ID
    public void remove(int id) {
        this.products.removeIf(product -> product.getId() == id);
    }

    // **************

    // Método para atualizar um produto existente
    public void update(int id, Product updatedProduct) {
        for (int i = 0; i < this.products.size(); i++) {
            if (this.products.get(i).getId() == id) {
                this.products.set(i, updatedProduct);
                break;
            }
        }
    }
}