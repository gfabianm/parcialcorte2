package com.parcial.app.controller;

import com.parcial.app.entity.Administrador;
import com.parcial.app.entity.Coordinador;
import com.parcial.app.repository.AdministradorRepository;
import com.parcial.app.repository.CoordinadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/administradores")
public class AdministradorController {

    @Autowired
    private AdministradorRepository adminRepo;

    @Autowired
    private CoordinadorRepository coordRepo;

    // ==============================
    // LOGIN
    // ==============================
    @GetMapping("/login")
    public String mostrarLogin() {
        return "administradores/login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String correo,
                                @RequestParam String password,
                                HttpSession session,
                                Model model) {
        Administrador admin = adminRepo.findByCorreo(correo);

        if (admin != null && admin.getPassword().equals(password)) {
            session.setAttribute("adminActivo", admin);
            return "redirect:/administradores/panel";
        }

        model.addAttribute("error", "Credenciales incorrectas");
        return "administradores/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/administradores/login";
    }

    // ==============================
    // PANEL PRINCIPAL DEL ADMIN
    // ==============================
    @GetMapping("/panel")
    public String panel(Model model, HttpSession session) {
        Administrador adminActivo = (Administrador) session.getAttribute("adminActivo");

        if (adminActivo == null) {
            return "redirect:/administradores/login";
        }

        model.addAttribute("adminActivo", adminActivo);
        model.addAttribute("administradores", adminRepo.findAll());
        model.addAttribute("coordinadores", coordRepo.findAll());
        return "administradores/panel";
    }

    // ==============================
    // CRUD DE ADMINISTRADORES
    // ==============================
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("administrador", new Administrador());
        return "administradores/formulario";
    }

    @PostMapping
    public String guardar(@ModelAttribute Administrador administrador) {
        // Si el ID está vacío, lo seteamos a null para que Mongo genere uno nuevo
        if (administrador.getId() == null || administrador.getId().isEmpty()) {
            administrador.setId(null);
        }
        adminRepo.save(administrador);
        return "redirect:/administradores/panel";
    }


    @GetMapping("/editar/{id}")
    public String editar(@PathVariable String id, Model model) {
        model.addAttribute("administrador", adminRepo.findById(id).orElse(null));
        return "administradores/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id) {
        adminRepo.deleteById(id);
        return "redirect:/administradores/panel";
    }

    // ==============================
    // CRUD DE COORDINADORES (desde panel admin)
    // ==============================
    @GetMapping("/coordinador/nuevo")
    public String nuevoCoordinador(Model model) {
        model.addAttribute("coordinador", new Coordinador());
        return "administradores/formulario_coordinador";
    }

    // CREAR/ACTUALIZAR COORDINADOR (ya existía, solo asegúrate de este “fix” de id)
    @PostMapping("/coordinador")
    public String guardarCoordinador(@ModelAttribute Coordinador coordinador) {
        // si viene id vacío desde el formulario de “nuevo”, forzamos null para que Mongo genere uno
        if (coordinador.getId() == null || coordinador.getId().isEmpty()) {
            coordinador.setId(null);
        }
        coordRepo.save(coordinador);
        return "redirect:/administradores/panel";
    }

    @GetMapping("/coordinador/eliminar/{id}")
    public String eliminarCoordinador(@PathVariable String id) {
        coordRepo.deleteById(id);
        return "redirect:/administradores/panel";
    }
    @GetMapping
    public String redirigir() {
        return "redirect:/administradores/login";
    }
 // EDITAR COORDINADOR (cargar formulario con datos)
    @GetMapping("/coordinador/editar/{id}")
    public String editarCoordinador(@PathVariable String id, Model model, HttpSession session) {
        if (session.getAttribute("adminActivo") == null) {
            return "redirect:/administradores/login";
        }
        model.addAttribute("coordinador", coordRepo.findById(id).orElse(null));
        return "administradores/formulario_coordinador";
    }
}
