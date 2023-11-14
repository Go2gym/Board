package hwan.board.board_project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import hwan.board.board_project.domain.Member;
import hwan.board.board_project.domain.Post;
import hwan.board.board_project.dto.PostDTO;
import hwan.board.board_project.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final MemberService memberService;
    private final PostRepository postRepository;

    public List<Post> getPostList() {
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

    public Post findPostDetail(Long boardId) {
        return postRepository.findByBoardId(boardId);
    }

    @Transactional
    public void updatePost(PostDTO postDTO, Long boardId) {
        Post post = postRepository.findByBoardId(boardId);

        post.update(postDTO.getTitle(), postDTO.getContent());
    }

    public void deletePost(Long boardId) {
        postRepository.deleteById(boardId);
    }

}
