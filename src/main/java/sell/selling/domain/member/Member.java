package sell.selling.domain.member;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class Member {
    private Long id;
    @NotEmpty(message ="아이디를 입력해주세요.")
    private String loginId;
    @NotEmpty(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp ="[A-Za-z\\d@$!%*?&]{8,}$",
            message = "영문, 숫자, 특수문자를 포함한 8글자 이상으로 입력해주세요.")
    private String password;

}
