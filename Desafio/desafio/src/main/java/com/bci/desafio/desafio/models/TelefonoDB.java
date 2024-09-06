package com.bci.desafio.desafio.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TelefonoDB {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  private String number;
  private String citycode;
  private String contrycode;

  // Relación muchos a uno con la entidad Usuario
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "usuario_id")
  @JsonIgnore  // Evita la recursividad en la serialización
  private UsuarioDB usuario;

  // Constructor
  public TelefonoDB(String number, String citycode, String contrycode) {
    this.number = number;
    this.citycode = citycode;
    this.contrycode = contrycode;
  }
}
