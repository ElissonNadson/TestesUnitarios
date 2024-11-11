package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class JogoTest {
    @InjectMocks
    Jogo jogo;

    @Mock
    Jogador jogador;

    @Test
    public void testTurno() {
        String expected = "Começou o turno do jogador: Seu Jorge com idade de 33";

        Mockito.when(jogador.getNone()).thenReturn("Seu Jorge");
        Mockito.when(jogador.getIdade()).thenReturn(33);

        Assertions.assertEquals(expected, jogo.turno());

        Mockito.verify(jogador, times(1)).getNone();

    }
}
