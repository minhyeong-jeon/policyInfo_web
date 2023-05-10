package com.policyInfo.policyInfo.join;

import com.policyInfo.policyInfo.member.Member;
import com.policyInfo.policyInfo.member.MemberRole;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@Getter @Setter
public class UserDto {
    /*회원가입 화면으로부터 넘어오는 가입정보를 담을 DTO*/

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    private String username;

    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    /*@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,30}$",
            message = "비밀번호는 8~30 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")*/
    @Length(min = 4, max = 16, message = "비밀번호는 4자 이상, 16자 이하로 입력해주세요.")
    private String password;

    private String lifeCycle;

    private String lifeType;

    private String checkedPassword;


    private MemberRole role;

    /*@NotEmpty(message = "주소는 필수 입력 값입니다.")
    private String address;*/

    /*@Builder
    public UserDto(String nickName, String email, String password, String lifeCycle, String lifeType) {
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.lifeCycle = lifeCycle;
        this.lifeType = lifeType;
    }*/

    @Builder
    public Member toEntity(){
        return Member.builder()
                .email(email)
                .username(username)
                .lifeCycle(lifeCycle)
                .lifeType(lifeType)
                .password(password)
                .role(MemberRole.USER)
                .build();
    }
}
