package hwan.board.board_project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hwan.board.board_project.dto.PostDto;
import hwan.board.board_project.service.PostService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/freeboard")
public class PostController {

    private final PostService postService;
    
    @GetMapping("/")
    public String boardList(Model model) {
        List<PostDto> boardList = postService.getPostList(); 
        
        model.addAttribute("list", boardList);
        return "/board/freeboard";
    }

    @GetMapping("/posting")
    public String boardWrite() {
        return "/board/postwrite";
    }
}
