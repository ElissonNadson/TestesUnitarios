package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ClasseATest {
    @InjectMocks
    private ClasseA classA;

    @Mock
    private ClasseB classeB;

    @Test
    public void testTwoValues() {
        Mockito.when(classeB.getX()).thenReturn(15);

        Assertions.assertEquals(20, classA.sumValues());
    }
}
