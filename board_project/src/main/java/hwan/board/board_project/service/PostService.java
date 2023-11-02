package hwan.board.board_project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import hwan.board.board_project.dto.PostDto;
import hwan.board.board_project.repository.PostRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<PostDto> getPostList() {
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
        return postRepository.findAll().stream().map(PostDto::new).toList();
    }

}
