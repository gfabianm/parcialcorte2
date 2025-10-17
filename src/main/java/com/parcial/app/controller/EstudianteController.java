package com.parcial.app.controller;

import com.parcial.app.entity.Estudiante;
import com.parcial.app.entity.Calificacion;
import com.parcial.app.repository.CalificacionRepository;
import com.parcial.app.repository.EstudianteRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteRepository repo;

    @Autowired
    private CalificacionRepository calRepo;

    // ================= INDEX =================
    @GetMapping
    public String index() {
        return "estudiantes/index";
    }

    // ================= LOGIN =================
    @GetMapping("/login")
    public String loginForm() {
        return "estudiantes/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String numeroDocumento,
                        @RequestParam String password,
                        Model model,
                        HttpSession session) {
        Estudiante e = repo.findByNumeroDocumento(numeroDocumento);

        // ✅ Evita error si la contraseña en BD es null
        if (e != null && e.getPassword() != null && e.getPassword().equals(password)) {
            session.setAttribute("estudianteActivo", e);
            return "redirect:/estudiantes/panel";
        } else {
            model.addAttribute("error", "Documento o contraseña incorrectos");
            return "estudiantes/login";
        }
    }

    // ================= REGISTRO =================
    @GetMapping("/registro")
    public String registroForm(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        return "estudiantes/registro";
    }

    @PostMapping("/registro")
    public String registro(@ModelAttribute("estudiante") Estudiante estudiante) {
        if (estudiante.getId() == null || estudiante.getId().isEmpty()) {
            estudiante.setId(null);
        }

        repo.save(estudiante);
        return "redirect:/estudiantes/login";
    }

    // ================= PANEL =================
    @GetMapping("/panel")
    public String panel(Model model, HttpSession session) {
        Estudiante activo = (Estudiante) session.getAttribute("estudianteActivo");
        if (activo == null) return "redirect:/estudiantes/login";

        model.addAttribute("estudiante", activo);
        return "estudiantes/panel";
    }

    // ================= ACTUALIZAR DATOS =================
    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Estudiante estudiante, HttpSession session) {
        Estudiante original = (Estudiante) session.getAttribute("estudianteActivo");
        if (original == null) return "redirect:/estudiantes/login";

        estudiante.setId(original.getId());
        repo.save(estudiante);
        session.setAttribute("estudianteActivo", estudiante);
        return "redirect:/estudiantes/panel";
    }

    // ================= VER CALIFICACIONES =================
    @GetMapping("/detalles")
    public String detalles(Model model, HttpSession session) {
        Estudiante activo = (Estudiante) session.getAttribute("estudianteActivo");
        if (activo == null) return "redirect:/estudiantes/login";

        // Buscar calificaciones por número de documento
        Calificacion calificacion = calRepo.findByNumeroDocumento(activo.getNumeroDocumento());

        model.addAttribute("estudiante", activo);
        model.addAttribute("calificacion", calificacion);

        return "estudiantes/detalles";
    }

    // ================= LOGOUT =================
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/estudiantes/login";
    }
}
