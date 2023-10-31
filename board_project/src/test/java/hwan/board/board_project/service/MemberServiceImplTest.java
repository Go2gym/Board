package hwan.board.board_project.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import hwan.board.board_project.domain.Member;
import hwan.board.board_project.repository.MemberRepository;

@SpringBootTest
public class MemberServiceImplTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberServiceImpl memberServiceImpl;
    
    @BeforeEach
    void addMember() {
        Member member1 = new Member("z", "z", "z", "z");
        memberServiceImpl.saveMember(member1);
    }

    @AfterEach
    void deleteRepository() {
        memberRepository.deleteAll();
    }

    @Test
    void testSaveMember() {
        Member member1 = new Member("t", "t", "t", "t");
        Member member = memberServiceImpl.saveMember(member1);
        Long mem1Check = memberRepository.findByUsernameOrNickname(member1.getUsername(), member1.getNickname()).get().getUserId();
        
        Long checkUid = member.getUserId();
        assertThat(mem1Check).isEqualTo(checkUid);
    }

    @Test
    void 중복없는거체크() {
        Member member1 = new Member("3", "3", "3", "3");
        Optional<Member> checkValidate = memberRepository.findByUsernameOrNickname(member1.getUsername(), member1.getNickname());

        assertThat(checkValidate).isEqualTo(Optional.empty());
    }

    @Test
    void 둘중하나라도중복있는거체크() {
        Member member1 = new Member("a", "z", "a", "a"); 
        Optional<Member> checkValidate = memberRepository.findByUsernameOrNickname(member1.getUsername(), member1.getNickname());
        assertThat(member1.getNickname()).isEqualTo(checkValidate.get().getNickname());

        Member member2 = new Member("y", "y", "z", "y");
        Optional<Member> checkValidate2 = memberRepository.findByUsernameOrNickname(member2.getUsername(), member2.getNickname());
        assertThat(member2.getUsername()).isEqualTo(checkValidate2.get().getUsername());
    }
}
