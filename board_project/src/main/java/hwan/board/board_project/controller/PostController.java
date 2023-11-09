package hwan.board.board_project.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hwan.board.board_project.domain.Post;
import hwan.board.board_project.dto.PostDTO;
import hwan.board.board_project.service.MemberService;
import hwan.board.board_project.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
    public String PostBoardWrite(@Validated @ModelAttribute PostDTO postDTO, Principal principal, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "/board/postwrite";
        }

        Long findMemberId = memberServiceImpl.findUsername(principal.getName()).getUserId();
        postService.posting(postDTO, findMemberId);

        return "redirect:/freeboard";
    }

    //상세보기
    @GetMapping("/detail/{boardId}")
    public String PostDetail(@PathVariable("boardId") Long boardId, Model model) {
        
        Post post = postService.findPostDetail(boardId);
        model.addAttribute("post", post);

        return "/board/postdetail";
    }


    /**
     * 게시글 수정
     */
    @GetMapping("/update/{boardId}")
    public String GetPostUpdate(@PathVariable("boardId") Long boardId, Model model, Principal principal) {

        Post post = postService.findPostDetail(boardId);
        Long findSignMemberId = memberServiceImpl.findUsername(principal.getName()).getUserId();

        if(findSignMemberId != post.getMember().getUserId()) {
            model.addAttribute("msg", "해당 게시글의 작성자가 아닙니다.");
            model.addAttribute("url", "/freeboard/detail/"+boardId);
            
            return "alertmessage";
        }

        PostDTO postDTO = new PostDTO(post.getTitle(), post.getContent());

        model.addAttribute("board", boardId);
        model.addAttribute("postDTO", postDTO);

        return "/board/postupdate";

    }

    @PostMapping("/update/{boardId}")
    public String PostUpdate(@PathVariable("boardId") Long boardId, @Valid @ModelAttribute PostDTO postDTO) {
        postService.updatePost(postDTO, boardId);
        return "redirect:/freeboard/detail/" + boardId;
    }

    @PostMapping("/delete/{boardId}")
    public String PostDelete(@PathVariable("boardId") Long boardId, Principal principal, Model model) {
        
        Post post = postService.findPostDetail(boardId);
        Long findSignMemberId = memberServiceImpl.findUsername(principal.getName()).getUserId();

        if(findSignMemberId != post.getMember().getUserId()) {
            model.addAttribute("msg", "해당 게시글의 작성자가 아닙니다.");
            model.addAttribute("url", "/freeboard/detail/"+boardId);
            
            return "alertmessage";
        }
        
        log.info(boardId + "posting delete");
        postService.deletePost(boardId);
        return "redirect:/freeboard";
    }
}
