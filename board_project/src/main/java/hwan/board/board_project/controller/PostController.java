package hwan.board.board_project.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hwan.board.board_project.domain.Post;
import hwan.board.board_project.dto.PostDTO;
import hwan.board.board_project.service.MemberService;
import hwan.board.board_project.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/freeboard")
public class PostController {

    private final PostService postService;
    private final MemberService memberServiceImpl;

    @GetMapping("")
    public String getFreeboard(Model model, Principal principal) {

        if(principal != null) {
            model.addAttribute("isSignedIn", true);
        }
        else {
            model.addAttribute("isSignedIn", false);
        }

        List<Post> boardList = postService.getPostList();

        model.addAttribute("list", boardList);

        return "/board/freeboard";
    }

    @GetMapping("/posting")
    public String getBoardWrite(Model model) {
        model.addAttribute("postDTO", new PostDTO());
        return "/board/postwrite";
    }

    @PostMapping("/posting")
    public String PostBoardWrite(@Valid PostDTO postDTO, Principal principal, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "/board/postwrite";
        }

        Long findMemberId = memberServiceImpl.findUsername(principal.getName()).getUserId();
        postService.posting(postDTO, findMemberId);

        return "redirect:/freeboard";
    }
}
