package hwan.board.board_project.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hwan.board.board_project.domain.Member;
import hwan.board.board_project.dto.MemberFormDTO;
import hwan.board.board_project.service.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
    
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    
    @GetMapping("/signin/error")
    public String signinError(Model model) {
        System.out.println("errorHere");
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "member/signin";
    }
    
    @PostMapping("/signup")
    public String postSignUp(@Validated @ModelAttribute("memberFormDTO") MemberFormDTO memberFormDTO, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            return "member/signup";
        }
        
        Member member = Member.createMember(memberFormDTO, passwordEncoder);
        memberService.saveMember(member);

        return "redirect:/";
    }

    @PostMapping("/checkUsername.do")
    @ResponseBody
    public boolean usernameDuplicateCheck(@RequestParam(value="username") String username) {        
        boolean validateResult = memberService.usernameDuplicateCheck(username);
        return validateResult;
    }

    @PostMapping("/checkNickname.do")
    @ResponseBody
    public boolean nicknameDuplicateCheck(@RequestParam(value="nickname") String nickname) {        
        boolean validateResult = memberService.nicknameDuplicateCheck(nickname);
        return validateResult;
    }
}
