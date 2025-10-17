package com.parcial.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.parcial.app.entity.Coordinador;

public interface CoordinadorRepository extends MongoRepository<Coordinador, String> {
    Coordinador findByCorreo(String correo);
}
