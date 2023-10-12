package me.zhengjie.modules.system.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UiController {

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return "index";
    }

    @GetMapping("/webui/**")
    public String webui(Model model) {
        return "index";
    }
    
    @GetMapping("/auth/logout")
    public String logout(Model model) {
        return "index";
    }
  
}