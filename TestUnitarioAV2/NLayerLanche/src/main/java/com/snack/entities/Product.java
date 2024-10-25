package com.snack.entities;

public class Product {
    private int id; // ID do produto
    private String description; // Descrição do produto
    private float price; // Preço do produto
    private String image; // Caminho da imagem do produto

    // Construtor que inicializa os atributos do produto
    public Product(int id, String description, float price, String image) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    // **************

    // Método para obter o ID do produto
    public int getId() {
        return id;
    }

    // **************

    // Método para definir o ID do produto
    public void setId(int id) {
        this.id = id;
    }

    // **************

    // Método para obter a descrição do produto
    public String getDescription() {
        return description;
    }

    // **************

    // Método para definir a descrição do produto
    public void setDescription(String description) {
        this.description = description;
    }

    // **************

    // Método para obter o preço do produto
    public float getPrice() {
        return price;
    }

    // **************

    // Método para definir o preço do produto
    public void setPrice(float price) {
        this.price = price;
    }

    // **************

    // Método para obter o caminho da imagem do produto
    public String getImage() {
        return image;
    }

    // **************

    // Método para definir o caminho da imagem do produto
    public void setImage(String imagePath) {
        this.image = imagePath;
    }

    // **************

    // Método para calcular o valor total da venda do produto
    public float sellProduct(int quantity) {
        return this.price * quantity;
    }

    // **************

    // Método para representar o produto como string
    @Override
    public String toString() {
        String formatText = "%-10s %-20s %-20s%n";
        return String.format(formatText, this.id, this.description, String.format("$ %.2f", this.price));
    }
}