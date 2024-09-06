package com.bci.desafio.desafio;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.bci.desafio.desafio.repository.UsuarioDBRepository;
import com.bci.desafio.desafio.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

//import com.bci.desafio.desafio.service.*;


class UsuarioServiceTest {

    @Mock
    private UsuarioDBRepository usuarioDBRepository;  // Mock del repositorio

    @InjectMocks
    private UsuarioService usuarioService;  // Servicio con inyección de dependencias simuladas

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Inicializa los mocks
    }

    @Test
    void testExisteCorreo() {
        String email = "test@example.com";

        // Simular el comportamiento del repositorio
        when(usuarioDBRepository.existsByEmail(email)).thenReturn(true);

        // Llamada al servicio
        boolean resultado = usuarioService.existeCorreo(email);

        // Verificación
        assertTrue(resultado);
    }

    @Test
    void testExisteCorreo1() {
        boolean resultado = usuarioService.existeCorreo("usuario@gmail.com");
        assertEquals(false, resultado); // Verifica que el resultado sea correcto
    }

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


