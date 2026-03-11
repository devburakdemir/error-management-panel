package org.example.controller;

import org.example.entity.CctResponseCodes;
import org.example.entity.SimaxErrors;
import org.example.service.CctResponseCodesService;
import org.example.service.SimaxErrorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private SimaxErrorsService simaxErrorsService;

    @Autowired
    private CctResponseCodesService cctResponseCodesService;

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        return "admin/dashboard";
    }

    // ============ SimaxErrors CRUD ============
    @GetMapping("/errors")
    public String listErrors(HttpSession session, Model model) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        List<SimaxErrors> errors = simaxErrorsService.findAll();
        model.addAttribute("errors", errors);
        return "admin/errors";
    }

    @GetMapping("/errors/create")
    public String createErrorForm(HttpSession session, Model model) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        model.addAttribute("error", new SimaxErrors());
        return "admin/error-form";
    }

    @PostMapping("/errors/save")
    public String saveError(@ModelAttribute("error") SimaxErrors error, HttpSession session, Model model) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        try {
            simaxErrorsService.save(error);
            return "redirect:/admin/errors";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", error);
            model.addAttribute("errorMessage", e.getMessage());
            return "admin/error-form";
        }
    }

    @GetMapping("/errors/edit/{id}")
    public String editErrorForm(@PathVariable("id") Integer id, HttpSession session, Model model) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        SimaxErrors error = simaxErrorsService.findById(id);
        model.addAttribute("error", error);
        return "admin/error-form";
    }

    @PostMapping("/errors/update")
    public String updateError(@ModelAttribute("error") SimaxErrors error, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        simaxErrorsService.update(error);
        return "redirect:/admin/errors";
    }

    @GetMapping("/errors/delete/{id}")
    public String deleteError(@PathVariable("id") Integer id, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        SimaxErrors error = simaxErrorsService.findById(id);
        simaxErrorsService.delete(error);
        return "redirect:/admin/errors";
    }

    // ============ CctResponseCodes CRUD ============
    @GetMapping("/codes")
    public String listCodes(HttpSession session, Model model) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        List<CctResponseCodes> codes = cctResponseCodesService.findAll();
        model.addAttribute("codes", codes);
        return "admin/codes";
    }

    @GetMapping("/codes/create")
    public String createCodeForm(HttpSession session, Model model) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        model.addAttribute("code", new CctResponseCodes());
        return "admin/code-form";
    }

    @PostMapping("/codes/save")
    public String saveCode(@ModelAttribute("code") CctResponseCodes code, HttpSession session, Model model) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        try {
            cctResponseCodesService.save(code);
            return "redirect:/admin/codes";
        } catch (IllegalArgumentException e) {
            model.addAttribute("code", code);
            model.addAttribute("errorMessage", e.getMessage());
            return "admin/code-form";
        }
    }

    @GetMapping("/codes/edit/{id}")
    public String editCodeForm(@PathVariable("id") Integer id, HttpSession session, Model model) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        CctResponseCodes code = cctResponseCodesService.findById(id);
        model.addAttribute("code", code);
        return "admin/code-form";
    }

    @PostMapping("/codes/update")
    public String updateCode(@ModelAttribute("code") CctResponseCodes code, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        cctResponseCodesService.update(code);
        return "redirect:/admin/codes";
    }

    @GetMapping("/codes/delete/{id}")
    public String deleteCode(@PathVariable("id") Integer id, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        CctResponseCodes code = cctResponseCodesService.findById(id);
        cctResponseCodesService.delete(code);
        return "redirect:/admin/codes";
    }

    // ============ Excel Upload ============
    @PostMapping("/upload")
    public String uploadExcel(@RequestParam("file") MultipartFile file, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        // Excel işleme kodu buraya gelecek
        return "redirect:/admin/dashboard";
    }

    private boolean isAdmin(HttpSession session) {
        String role = (String) session.getAttribute("userRole");
        return "Administrator".equals(role);
    }
}
