package hwan.board.board_project.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hwan.board.board_project.domain.Member;
import hwan.board.board_project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Member saveMember(Member member) {
        duplicateDuplicateMember(member);

        return memberRepository.save(member);
    }

    @Override
    public void duplicateDuplicateMember(Member member) {
        Optional<Member> findMember = memberRepository.findByUsernameAndNickname(member.getUsername(), member.getNickname());
        if(findMember.isPresent()) {
            throw new IllegalStateException("이미 존재하는 정보입니다.");
        }
    }

    @Override
    public boolean usernameDuplicateCheck(String username) {
        return memberRepository.existsByUsername(username);
    }
    
    @Override
    public boolean nicknameDuplicateCheck(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }
}
