package com.bci.desafio.desafio.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bci.desafio.desafio.models.Usuario;
import com.bci.desafio.desafio.models.UsuarioDB;
import com.bci.desafio.desafio.repository.UsuarioDBRepository;
//import com.bci.desafio.desafio.repository.TelefonoDBRepository;
import com.bci.desafio.desafio.security.JwtGenerator;
import com.bci.desafio.desafio.service.UsuarioService;
import com.bci.desafio.desafio.utils.PasswordValidator;
import com.bci.desafio.desafio.models.ErrorResponse;
import com.bci.desafio.desafio.models.Telefono;
import com.bci.desafio.desafio.models.TelefonoDB;


@RestController
@RequestMapping("api/Usuario")
public class UsuarioController {
  @Autowired
  private UsuarioDBRepository usuarioRepository;
  //@Autowired
  //private TelefonoDBRepository telefonoRepository;
  @Autowired
  private UsuarioService usuarioService;

  @PostMapping("/create")
  public ResponseEntity<?> create(@RequestBody Usuario user) {
      //TODO: process POST request
      System.out.println("1.- Llegue al post...");
      System.out.println("2.- Nombre: "+user.getName());
      
      if(user.getName().equals("")){
        ErrorResponse error = new ErrorResponse("El nombre del usuario es obligatorio.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(error);
      }

      if (!usuarioService.isValidEmail(user.getEmail())){
        System.out.println("El formato del correo no es valido");
        ErrorResponse error = new ErrorResponse("El formato del correo no es valido");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(error);
      }

      boolean existe = usuarioService.existeCorreo(user.getEmail());
      if (existe) {
        //return ResponseEntity.ok("{\"mensaje\": \"El correo ya está registrado\"}");
        System.out.println("El correo ya está registrado");
        ErrorResponse error = new ErrorResponse("El correo ya registrado");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(error);
      } /*else {
        //return ResponseEntity.ok("{\"mensaje\": \"El correo no está registrado\"}");
        System.out.println("El correo no está registrado");
      }*/

      PasswordValidator validator = new PasswordValidator(8, 20, true, true, true, true);
      if (!validator.isValidPassword(user.getPassword())) {
        System.out.println("La contraseña NO es segura.");
        ErrorResponse error = new ErrorResponse("La contraseña NO es segura.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(error);
    }

      //generarToken()
      JwtGenerator jwtGenerator = new JwtGenerator();
      String token = jwtGenerator.generarToken();
      System.out.println("JWT generado: " + token);
      

      UsuarioDB usuarioDB = new UsuarioDB(user.getName(), user.getEmail());
      usuarioDB.setPassword(user.getPassword());
      usuarioDB.setToken(token);

      // Recorrer la lista de números de teléfono
      for (Telefono telefono : user.getPhones()) {
        // Crear un nuevo objeto Telefono para cada número
        TelefonoDB telefonoDB = new TelefonoDB(telefono.getNumber(), telefono.getCitycode(), telefono.getContrycode());
        
        // Añadir el teléfono al usuario
        usuarioDB.addTelefono(telefonoDB);
      }

      usuarioRepository.save(usuarioDB);

      // Retorna 201 Created con el usuario creado
      //return ResponseEntity.status(HttpStatus.CREATED)
      //  .body(user);
      return ResponseEntity.status(HttpStatus.CREATED)
        .body(usuarioDB);
  }
  
}
