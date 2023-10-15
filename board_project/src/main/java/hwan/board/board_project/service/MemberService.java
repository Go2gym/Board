package hwan.board.board_project.service;

import hwan.board.board_project.domain.Member;

public interface MemberService {
    
    /*
     * Membercontroller의 signup 로직에 사용
     */
    public Member saveMember(Member member);
    public void duplicateDuplicateMember(Member member);

    public boolean usernameDuplicateCheck(String username);
    public boolean nicknameDuplicateCheck(String nickname);
}
