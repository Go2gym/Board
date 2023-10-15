package hwan.board.board_project.Validate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import hwan.board.board_project.dto.MemberFormDTO;
import hwan.board.board_project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CheckUsernameValidator extends AbstractValidator<MemberFormDTO>{

    private final MemberRepository memberRepository;
    @Override
    protected void doValidate(MemberFormDTO dto, Errors errors) {
        
    }
    
}
