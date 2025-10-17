package com.parcial.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.parcial.app.entity.Estudiante;

public interface EstudianteRepository extends MongoRepository<Estudiante, String> {
    Estudiante findByNumeroDocumento(String numeroDocumento);
}
