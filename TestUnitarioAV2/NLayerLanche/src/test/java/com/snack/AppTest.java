package com.snack;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final ByteArrayInputStream inputStreamCaptor = new ByteArrayInputStream("3\n".getBytes());
    private Scanner originalIn;

    @BeforeEach
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outputStreamCaptor));
        System.setIn(inputStreamCaptor);

        // Usar reflexão para acessar a variável privada 'scanner'
        Field scannerField = App.class.getDeclaredField("scanner");
        scannerField.setAccessible(true);
        originalIn = (Scanner) scannerField.get(null);
        scannerField.set(null, new Scanner(System.in));
    }

    @AfterEach
    public void tearDown() throws Exception {
        System.setOut(originalOut);
        System.setIn(System.in);

        // Restaurar a variável privada 'scanner' usando reflexão
        Field scannerField = App.class.getDeclaredField("scanner");
        scannerField.setAccessible(true);
        scannerField.set(null, originalIn);
    }

    @Test
    public void testShowMenu() {
        App.showMenu();
        String expectedOutput = "\n1 - New product\n" +
                "2 - Update product\n" +
                "3 - List products\n" +
                "4 - Sell\n" +
                "5 - Remove product\n" +
                "6 - Exit\n";
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    public void testGetUserInput() {
        int userInput = App.getUserInput();
        assertEquals(3, userInput);
    }
}