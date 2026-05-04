package es.unican.is2.practica5b;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConjuntoOrdenadoTest {
    
    private ConjuntoOrdenado<Integer> sut;

    @BeforeEach
    public void setUp() {
        sut = new ConjuntoOrdenado<>();
    }

    @Test
    public void testAdd() {
        // CP1: Añadir a conjunto vacío (AVL)
        assertTrue(sut.add(10));
        assertEquals(1, sut.size());
        assertEquals(10, sut.get(0));

        // CP2: Añadir elemento que debe ir al principio (Orden natural)
        sut.add(20);
        assertTrue(sut.add(5)); 
        assertEquals(5, sut.get(0)); // Debería fallar aquí si el orden está invertido

        // CP3: Añadir duplicado (Debe retornar false)
        assertFalse(sut.add(10));
        
        // CP4: Añadir nulo (Excepción)
        assertThrows(NullPointerException.class, () -> sut.add(null));
    }

    @Test
    public void testGetYRemove() {
        sut.add(10);
        sut.add(20);
        
        // CP5: Acceso válido
        assertEquals(10, sut.get(0));
        
        // CP6: Índices fuera de rango (AVL)
        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(2));
        
        // CP7: Borrado válido
        assertEquals(10, sut.remove(0));
        assertEquals(1, sut.size());
    }
}