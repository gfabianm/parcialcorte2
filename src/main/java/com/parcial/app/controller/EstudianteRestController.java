package com.parcial.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.parcial.app.entity.Estudiante;
import com.parcial.app.repository.EstudianteRepository;
import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteRestController {

    @Autowired
    private EstudianteRepository repo;

    @GetMapping
    public List<Estudiante> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Estudiante create(@RequestBody Estudiante estudiante) {
        return repo.save(estudiante);
    }

    @PutMapping("/{id}")
    public Estudiante update(@PathVariable String id, @RequestBody Estudiante estudiante) {
        estudiante.setId(id);
        return repo.save(estudiante);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repo.deleteById(id);
    }
}
