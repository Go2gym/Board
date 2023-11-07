package hwan.board.board_project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import hwan.board.board_project.domain.Member;
import hwan.board.board_project.domain.Post;
import hwan.board.board_project.dto.PostDTO;
import hwan.board.board_project.repository.PostRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final MemberService memberService;
    private final PostRepository postRepository;

    public List<Post> getPostList() {
        /**
        List<Post> all  = postRepository.findAll();
        List<PostDto> res = new ArrayList<>();
        

        for (Post p: all) {
            res.add(PostDto.builder()
                        .title(p.getTitle())
                        .author(p.getMember().getName())
                        .content(p.getTitle())
                        .createdDate(p.getCreatedDate())
                        .hitCount(0L)
                        .build());
        }
        **/
        //return postRepository.findAll().stream().map(PostDTO::new).toList();
        return postRepository.findAllByOrderByCreatedDateDesc();
    }

    public void posting(PostDTO postDTO, Long userId) {
        Member member = memberService.findUserId(userId);
        Post posting = Post.builder()
                            .author(member.getNickname())
                            .title(postDTO.getTitle())
                            .content(postDTO.getContent())
                            .member(member)
                            .build();
        postRepository.save(posting);
    }

}
