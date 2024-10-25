package com.snack;

import com.snack.applications.ProductApplication;
import com.snack.entities.Product;
import com.snack.facade.ProductFacade;
import com.snack.repositories.ProductRepository;
import com.snack.services.ProductService;


import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    private static ProductRepository productRepository; // Repositório de produtos
    private static ProductService productService; // Serviço de produtos
    private static ProductApplication productApplication; // Aplicação de produtos
    private static ProductFacade productFacade; // Fachada de produtos
    private static List<Product> products; // Lista de produtos
    private static Scanner scanner; // Scanner para entrada do usuário

    // **************

    // Método para resolver dependências
    public static void resolveDependencies() {
        productRepository = new ProductRepository();
        productService = new ProductService();
        productApplication = new ProductApplication(productRepository, productService);
        productFacade = new ProductFacade(productApplication);
        scanner = new Scanner(System.in);
    }

    // **************

    // Método para inicializar produtos
    public static void initializeProducts() {
        Product product1 = new Product(1, "Hotdog", 4.00f, "C:\\Users\\aluno\\Produtos\\HotDog.jpg");
        productFacade.append(product1);
    }

    // **************

    // Método para mostrar o menu
    public static void showMenu() {
        System.out.println("\n1 - New product");
        System.out.println("2 - Update product");
        System.out.println("3 - List products");
        System.out.println("4 - Sell");
        System.out.println("5 - Remove product");
        System.out.println("6 - Exit");
    }

    // **************

    // Método para obter a entrada do usuário
    public static int getUserInput() {
        System.out.println("Please indicate the option you want: ");
        return scanner.nextInt();
    }

    // **************

    // Método para listar todos os produtos
    public static void listAllProducts() {
        StringBuilder menu = new StringBuilder();
        String formatText = "%-10s %-20s %-20s%n";
        System.out.print(String.format(formatText, "Id", "Name", "Price"));
        productFacade.getAll().forEach(p -> {
            System.out.print(p);
        });
    }

    // **************

    // Método para adicionar um novo produto
    public static void newProduct() {
        System.out.println("Enter the product id: ");
        int id = scanner.nextInt();

        System.out.println("Enter the description product: ");
        String description = scanner.next();

        System.out.println("Enter the price product: ");
        float price = scanner.nextFloat();

        System.out.println("Enter the image path of product: ");
        String imagePath = scanner.next();

        Product product = new Product(id, description, price, imagePath);
        productFacade.append(product);

        System.out.println("Product registered successfully!");
    }

    // **************

    // Método para vender um produto
    public static void sellProduct() {
        System.out.println("What product do you want to buy? ");
        int id = scanner.nextInt();

        System.out.println("And the quantity? ");
        int quantity = scanner.nextInt();

        System.out.println("Total: " + productFacade.sellProduct(id, quantity));
    }

    // **************

    // Método para atualizar um produto
    public static void updateProduct() {
        System.out.println("What product do you want to update? ");
        int id = scanner.nextInt();

        System.out.println("Enter the description product: ");
        String description = scanner.next();

        System.out.println("Enter the price product: ");
        float price = scanner.nextFloat();

        System.out.println("Enter the image path of product: ");
        String imagePath = scanner.next();

        Product product = new Product(id, description, price, imagePath);
        productFacade.update(id, product);
    }

    // **************

    // Método para remover um produto
    public static void removeProduct() {
        System.out.println("What product do you want to remove? ");
        int id = scanner.nextInt();

        productFacade.remove(id);
    }

    // **************

    // Método para executar o aplicativo
    public static void run() {
        int menuOption;
        do {
            showMenu();
            menuOption = getUserInput();

            switch (menuOption) {
                case 1:
                    newProduct();
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    listAllProducts();
                    break;
                case 4:
                    sellProduct();
                    break;
                case 5:
                    removeProduct();
                    break;
            }

        } while (menuOption != 6);
    }

    // **************

    // Método principal
    public static void main(String[] args) {
        resolveDependencies();
        initializeProducts();
        run();
    }
}