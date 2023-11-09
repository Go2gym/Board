package hwan.board.board_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hwan.board.board_project.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
    List<Post> findAllByOrderByCreatedDateDesc();

    Post findByBoardId(Long boardId);
}
