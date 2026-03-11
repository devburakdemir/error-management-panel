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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SimaxErrorsService simaxErrorsService;

    @Autowired
    private CctResponseCodesService cctResponseCodesService;

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {
        if (!isUser(session)) {
            return "redirect:/login";
        }
        return "user/dashboard";
    }

    @GetMapping("/errors")
    public String listErrors(HttpSession session, Model model) {
        if (!isUser(session)) {
            return "redirect:/login";
        }
        List<SimaxErrors> errors = simaxErrorsService.findAll();
        model.addAttribute("errors", errors);
        return "user/errors";
    }

    @GetMapping("/codes")
    public String listCodes(HttpSession session, Model model) {
        if (!isUser(session)) {
            return "redirect:/login";
        }
        List<CctResponseCodes> codes = cctResponseCodesService.findAll();
        model.addAttribute("codes", codes);
        return "user/codes";
    }

    @PostMapping("/upload")
    public String uploadExcel(@RequestParam("file") MultipartFile file, HttpSession session) {
        if (!isUser(session)) {
            return "redirect:/login";
        }
        // Excel okuma kodu buraya
        return "redirect:/user/dashboard";
    }

    private boolean isUser(HttpSession session) {
        String role = (String) session.getAttribute("userRole");
        return role != null;
    }
}
