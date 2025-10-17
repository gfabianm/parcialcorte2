package com.parcial.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.parcial.app.entity.Coordinador;
import com.parcial.app.repository.CoordinadorRepository;
import java.util.List;

@RestController
@RequestMapping("/api/coordinadores")
public class CoordinadorRestController {

    @Autowired
    private CoordinadorRepository repo;

    @GetMapping
    public List<Coordinador> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Coordinador create(@RequestBody Coordinador coordinador) {
        return repo.save(coordinador);
    }

    @PutMapping("/{id}")
    public Coordinador update(@PathVariable String id, @RequestBody Coordinador coordinador) {
        coordinador.setId(id);
        return repo.save(coordinador);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repo.deleteById(id);
    }
}
