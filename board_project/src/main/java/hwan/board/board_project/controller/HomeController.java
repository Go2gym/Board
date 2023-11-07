package hwan.board.board_project.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import hwan.board.board_project.domain.Post;
import hwan.board.board_project.dto.MemberFormDTO;
import hwan.board.board_project.service.PostService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {
    
    @GetMapping("/")
    public String home(Authentication authentication, Model model, Principal principal) {
        
        if(principal != null) {
            
            model.addAttribute("isSignedIn", true);
        }
        else {
            model.addAttribute("isSignedIn", false);
        }       
        
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

    

    @GetMapping("/dietrecommendation")
    public String getDietRecommendation() {        

        return "/dietAPI/dietRecommendation";
    }
}
