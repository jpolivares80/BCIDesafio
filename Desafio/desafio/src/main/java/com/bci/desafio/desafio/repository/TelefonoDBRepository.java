package com.bci.desafio.desafio.repository;

import com.bci.desafio.desafio.models.TelefonoDB;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface TelefonoDBRepository extends JpaRepository<TelefonoDB, UUID> {
}
