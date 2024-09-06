package com.bci.desafio.desafio.service;

import java.util.Optional;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bci.desafio.desafio.models.UsuarioDB;
import com.bci.desafio.desafio.repository.UsuarioDBRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioDBRepository usuarioDBRepository;
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    // Método para verificar si un correo existe
    public boolean existeCorreo(String email) {
        return usuarioDBRepository.existsByEmail(email);
    }

    // Alternativa si quieres devolver el usuario si existe
    public Optional<UsuarioDB> obtenerUsuarioPorCorreo(String email) {
        return usuarioDBRepository.findByEmail(email);
    }

    public boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }
}
