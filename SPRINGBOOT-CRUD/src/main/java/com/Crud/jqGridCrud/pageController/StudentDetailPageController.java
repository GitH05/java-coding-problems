package com.Crud.jqGridCrud.pageController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentDetailPageController {
    @GetMapping("/")
    public String showPage() {
        return "index.html";
    }
}
