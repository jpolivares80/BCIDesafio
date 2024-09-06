package com.bci.desafio.desafio;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.bci.desafio.desafio.repository.UsuarioDBRepository;
import com.bci.desafio.desafio.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.bci.desafio.desafio.controllers.UsuarioController;
import com.bci.desafio.desafio.models.UsuarioDB;

//@WebMvcTest
@WebMvcTest(controllers = UsuarioController.class)
public class UsuarioControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UsuarioDBRepository usuarioDBRepository; // Mock del repositorio

  @MockBean
  private UsuarioService usuarioService; // Si el servicio es llamado por el controlador

  @Test
  void testCrearUsuario() throws Exception {
    // JSON de ejemplo
    String nuevoUsuarioJson = """
      {
        "name": "Nombre del Usuario",
        "email": "usuario@gmail.com",
        "password": "AA11##bbPass",
        "phones": [
          {
            "number": "98765432",
            "citycode": "9",
            "contrycode": "+56"
          },
          {
            "number": "12345678",
            "citycode": "9",
            "contrycode": "+56"
          }
        ]
      }
    """;

    // Simula el comportamiento del servicio o repositorio
    when(usuarioService.isValidEmail(anyString())).thenReturn(true);
    when(usuarioService.existeCorreo(anyString())).thenReturn(false);
    when(usuarioDBRepository.save(any())).thenReturn(new UsuarioDB());

    mockMvc.perform(post("/api/Usuario/create")
      .contentType("application/json")
      .content(nuevoUsuarioJson))
      .andExpect(status().isCreated());
    }
}