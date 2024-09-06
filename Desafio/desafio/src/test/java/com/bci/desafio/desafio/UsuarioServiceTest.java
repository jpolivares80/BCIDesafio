package com.bci.desafio.desafio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.bci.desafio.desafio.service.*;

class UsuarioServiceTest {

    private final UsuarioService usuarioService = new UsuarioService();

    /*@Test
    void testExisteCorreo() {
        boolean resultado = usuarioService.existeCorreo("usuario@gmail.com");
        assertEquals(false, resultado); // Verifica que el resultado sea correcto
    }*/

    @Test
    void testIsValidEmail() {
        boolean resultado = usuarioService.isValidEmail("usuario@gmail.com");
        assertEquals(true, resultado); // Verifica que el resultado sea correcto
    }

    @Test
    void testNotIsValidEmail() {
        boolean resultado = usuarioService.isValidEmail("usuario@gmailcom");
        assertEquals(false, resultado); // Verifica que el resultado sea correcto
    }
}
