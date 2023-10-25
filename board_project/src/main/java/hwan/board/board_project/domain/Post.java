package hwan.board.board_project.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Post extends BaseTime{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long boardId;

    private String author;

    private String title;

    private String content;

    private Integer hitCount;


    //forign key
    @ManyToOne
    @JoinColumn(name = "userId")
    private Member member;

    private LocalDateTime createdTime;

    private LocalDateTime modifiedDate;
}
