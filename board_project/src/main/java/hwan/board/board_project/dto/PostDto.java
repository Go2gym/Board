package hwan.board.board_project.dto;

import java.time.LocalDateTime;

import hwan.board.board_project.domain.Post;
import lombok.Builder;


public class PostDto {
    
    private String author;

    private String title;

    private String content;

    private LocalDateTime createdDate;

    /**
     * 
     * @param p
     * p Post Entity
     * given param Post to PostDto
    */

    @Builder
    public PostDto(Post p) {
        this.author = p.getMember().getName();
        this.title = p.getTitle();
        this.content = p.getContent();
        this.createdDate = p.getCreatedDate();
    }


}