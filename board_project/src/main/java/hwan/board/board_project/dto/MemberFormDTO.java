package hwan.board.board_project.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MemberFormDTO {
    
    @NotBlank(message = "이름은 필수 입력값입니다.")
    private String name;

    @NotBlank(message = "별명은 필수 입력값입니다.")
    private String nickname;

    @NotBlank(message = "아이디는 필수 입력값입니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    private String password;

    @Builder
    public MemberFormDTO(String name, String nickname, String username, String password) {
        this.name = name;
        this.nickname = nickname;
        this.username = username;
        this.password = password;
    }
}
