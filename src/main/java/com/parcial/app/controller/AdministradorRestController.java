package com.parcial.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.parcial.app.entity.Administrador;
import com.parcial.app.repository.AdministradorRepository;
import java.util.List;

@RestController
@RequestMapping("/api/administradores")
public class AdministradorRestController {

    @Autowired
    private AdministradorRepository repo;

    @GetMapping
    public List<Administrador> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Administrador create(@RequestBody Administrador admin) {
        return repo.save(admin);
    }

    @PutMapping("/{id}")
    public Administrador update(@PathVariable String id, @RequestBody Administrador admin) {
        admin.setId(id);
        return repo.save(admin);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repo.deleteById(id);
    }
}
