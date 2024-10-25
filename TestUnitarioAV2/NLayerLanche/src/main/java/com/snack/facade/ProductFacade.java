package com.snack.facade;

import com.snack.applications.ProductApplication;
import com.snack.entities.Product;

import java.util.List;

public class ProductFacade {
    private ProductApplication productApplication; // Instância da aplicação de produtos

    // Construtor que inicializa a aplicação de produtos
    public ProductFacade(ProductApplication productApplication) {
        this.productApplication = productApplication;
    }

    // Método para obter todos os produtos
    public List<Product> getAll() {
        return this.productApplication.getAll();
    }

    // Método para obter um produto pelo ID
    public Product getById(int id) {
        return this.productApplication.getById(id);
    }

    // Método para verificar se um produto existe pelo ID
    public boolean exists(int id) {
        return this.productApplication.exists(id);
    }

    // Método para adicionar um novo produto
    public void append(Product product) {
        this.productApplication.append(product);
    }

    // Método para remover um produto pelo ID
    public void remove(int id) {
        this.productApplication.remove(id);
    }

    // Método para atualizar um produto existente
    public void update(int id, Product product) {
        this.productApplication.update(id, product);
    }

    // Método para vender um produto, retornando o valor total da venda
    public float sellProduct(int id, int quantity) {
        return this.productApplication.sellProduct(id, quantity);
    }
}