package com.bci.desafio.desafio.models;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UsuarioDB {
  
  @Id
  private UUID id;
  private String name;
  private String email;
  private String password;
  private LocalDateTime created;
  private LocalDateTime modified;
  private LocalDateTime last_login;
  private String token;
  private boolean isactive;

  // Relación uno a muchos con la entidad Telefono
  @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<TelefonoDB> telefonos = new ArrayList<>();
  
  // Constructor adicional si no estás usando Lombok
  public UsuarioDB(String name, String email) {
    this.id = UUID.randomUUID(); // Genera un UUID automáticamente

    this.name = name;
    this.email = email;

    this.created = LocalDateTime.now();
    this.modified = LocalDateTime.now();
    this.last_login = LocalDateTime.now();
    this.isactive = true;
  }

  // Métodos para añadir y eliminar teléfonos
  public void addTelefono(TelefonoDB telefono) {
    telefonos.add(telefono);
    telefono.setUsuario(this);
  }

  public void removeTelefono(TelefonoDB telefono) {
    telefonos.remove(telefono);
    telefono.setUsuario(null);
  }
}
