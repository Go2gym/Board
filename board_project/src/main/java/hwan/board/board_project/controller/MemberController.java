package hwan.board.board_project.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hwan.board.board_project.domain.Member;
import hwan.board.board_project.dto.MemberFormDTO;
import hwan.board.board_project.service.MemberServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
    
    private final MemberServiceImpl memberServiceImpl;
    private final PasswordEncoder passwordEncoder;
    
    @PostMapping("/signup")
    public String memberForm(@Valid @ModelAttribute("memberFormDTO") MemberFormDTO memberFormDTO, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            return "member/signup";
        }
        try {

            Member member = Member.createMember(memberFormDTO, passwordEncoder);
            memberServiceImpl.saveMember(member);
        }
        catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "member/signup";
        }

        return "redirect:/";
    }

    @PostMapping("/checkUsername.do")
    @ResponseBody
    public boolean usernameDuplicateCheck(@RequestParam(value="username") String username) {        
        boolean validateResult = memberServiceImpl.usernameDuplicateCheck(username);
        return validateResult;
    }

    @PostMapping("/checkNickname.do")
    @ResponseBody
    public boolean nicknameDuplicateCheck(@RequestParam(value="nickname") String nickname) {        
        boolean validateResult = memberServiceImpl.nicknameDuplicateCheck(nickname);
        return validateResult;
    }
}
