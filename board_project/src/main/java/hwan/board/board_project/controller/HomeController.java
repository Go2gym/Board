package hwan.board.board_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import hwan.board.board_project.dto.MemberFormDTO;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String home() {  
        return "/home";
    }

    @GetMapping("/signin")
    public String signin() {
        return "/member/signin";
    }

    @GetMapping("/signup")
    public String signup(Model model) {  
        model.addAttribute("memberFormDTO", new MemberFormDTO());
        return "member/signup";
    }
}
