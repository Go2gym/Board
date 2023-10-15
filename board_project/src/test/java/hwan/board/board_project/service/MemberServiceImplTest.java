package hwan.board.board_project.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import hwan.board.board_project.domain.Member;
import hwan.board.board_project.repository.MemberRepository;

@SpringBootTest
@Transactional
public class MemberServiceImplTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberServiceImpl memberServiceImpl;
    

    @Test
    void testSaveMember() {
        Member member1 = new Member("12", "12", "12", "12");
        Member member = memberServiceImpl.saveMember(member1);
        Long mem1Check = memberRepository.findByUsername(member1.getUsername()).get().getUserId();
        
        Long checkUid = member.getUserId();
        assertThat(mem1Check).isEqualTo(checkUid);
    }

    @Test
    void 중복없는거체크() {
        Member member1 = new Member("3", "3", "3", "3");
        Optional<Member> checkValidate = memberRepository.findByUsername(member1.getUsername());

        assertThat(checkValidate).isEqualTo(Optional.empty());
    }

    @Test
    void 중복있는거체크() {
        Member member1 = new Member("a", "a", "a", "a");
        Optional<Member> checkValidate = memberRepository.findByUsername(member1.getUsername());

        assertThat(member1.getUsername()).isEqualTo(checkValidate.get().getUsername());
    }
}
