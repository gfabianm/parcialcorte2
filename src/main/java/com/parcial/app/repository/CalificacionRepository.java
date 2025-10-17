package com.parcial.app.repository;

import com.parcial.app.entity.Calificacion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CalificacionRepository extends MongoRepository<Calificacion, String> {
    Calificacion findByNumeroDocumento(String numeroDocumento);
}
