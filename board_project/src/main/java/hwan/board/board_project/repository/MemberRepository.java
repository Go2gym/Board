package hwan.board.board_project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hwan.board.board_project.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
    
    Boolean existsByUsername(String username);
    Boolean existsByNickname(String nickname);
    
    Member findByUsername(String username);

    Optional<Member> findByUsernameAndNickname(String username, String nickname);
}
