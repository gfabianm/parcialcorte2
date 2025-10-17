package com.parcial.app.controller;

import com.parcial.app.entity.Calificacion;
import com.parcial.app.entity.Coordinador;
import com.parcial.app.entity.Estudiante;
import com.parcial.app.repository.CalificacionRepository;
import com.parcial.app.repository.CoordinadorRepository;
import com.parcial.app.repository.EstudianteRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/coordinadores")
public class CoordinadorController {

    @Autowired
    private CalificacionRepository calRepo;

    @Autowired
    private CoordinadorRepository coordRepo;

    @Autowired
    private EstudianteRepository estRepo;

    // ===================== LOGIN (Coordinador) =====================
    @GetMapping("/login")
    public String mostrarLogin() {
        return "coordinadores/login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String correo,
                                @RequestParam String password,
                                HttpSession session,
                                Model model) {
        Coordinador coord = coordRepo.findByCorreo(correo.trim());

        if (coord != null && coord.getPassword() != null
                && coord.getPassword().trim().equals(password.trim())) {
            session.setAttribute("coordActivo", coord);
            return "redirect:/coordinadores/panel";
        }

        model.addAttribute("error", "Credenciales incorrectas");
        return "coordinadores/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/coordinadores/login";
    }

    // ===================== PANEL =====================
    @GetMapping("/panel")
    public String panel(Model model, HttpSession session) {
        Coordinador coordActivo = (Coordinador) session.getAttribute("coordActivo");
        if (coordActivo == null) {
            return "redirect:/coordinadores/login";
        }

        model.addAttribute("coordActivo", coordActivo);
        model.addAttribute("estudiantes", estRepo.findAll());
        return "coordinadores/panel";
    }

    @GetMapping
    public String redirigirRaiz() {
        return "redirect:/coordinadores/login";
    }

    // ===================== CRUD DE ESTUDIANTES =====================
    @GetMapping("/estudiante/nuevo")
    public String nuevoEstudiante(Model model, HttpSession session) {
        Coordinador coordActivo = (Coordinador) session.getAttribute("coordActivo");
        if (coordActivo == null) return "redirect:/coordinadores/login";

        model.addAttribute("estudiante", new Estudiante());
        return "coordinadores/formulario_estudiante";
    }

    @PostMapping("/estudiante/guardar")
    public String guardarEstudiante(@ModelAttribute Estudiante estudiante, HttpSession session) {
        Coordinador coordActivo = (Coordinador) session.getAttribute("coordActivo");
        if (coordActivo == null) return "redirect:/coordinadores/login";

        if (estudiante.getId() == null || estudiante.getId().isEmpty()) {
            estudiante.setId(null);
        }
        estRepo.save(estudiante);
        return "redirect:/coordinadores/panel";
    }

    @GetMapping("/estudiante/editar/{id}")
    public String editarEstudiante(@PathVariable String id, Model model, HttpSession session) {
        Coordinador coordActivo = (Coordinador) session.getAttribute("coordActivo");
        if (coordActivo == null) return "redirect:/coordinadores/login";

        Estudiante estudiante = estRepo.findById(id).orElse(null);
        if (estudiante == null) return "redirect:/coordinadores/panel";

        model.addAttribute("estudiante", estudiante);
        return "coordinadores/formulario_estudiante";
    }

    @GetMapping("/estudiante/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable String id, HttpSession session) {
        Coordinador coordActivo = (Coordinador) session.getAttribute("coordActivo");
        if (coordActivo == null) return "redirect:/coordinadores/login";

        estRepo.deleteById(id);
        return "redirect:/coordinadores/panel";
    }

    // ===================== EDITAR CALIFICACIONES =====================
    @GetMapping("/estudiante/calificacion/{id}")
    public String editarCalificacion(@PathVariable String id, Model model, HttpSession session) {
        Coordinador coordActivo = (Coordinador) session.getAttribute("coordActivo");
        if (coordActivo == null) return "redirect:/coordinadores/login";

        Estudiante est = estRepo.findById(id).orElse(null);
        if (est == null) return "redirect:/coordinadores/panel";

        Calificacion cal = calRepo.findByNumeroDocumento(est.getNumeroDocumento());
        if (cal == null) {
            cal = new Calificacion();
            cal.setNumeroDocumento(est.getNumeroDocumento());
        }

        model.addAttribute("estudiante", est);
        model.addAttribute("calificacion", cal);
        return "coordinadores/editar_estudiante";
    }

    @PostMapping("/estudiante/calificacion")
    public String guardarCalificacion(@ModelAttribute Calificacion calificacion,
                                      HttpSession session) {
        Coordinador coordActivo = (Coordinador) session.getAttribute("coordActivo");
        if (coordActivo == null) return "redirect:/coordinadores/login";

        Calificacion existente = calRepo.findByNumeroDocumento(calificacion.getNumeroDocumento());
        if (existente != null) {
            calificacion.setId(existente.getId());
        }

        calRepo.save(calificacion);
        return "redirect:/coordinadores/panel";
    }
}
