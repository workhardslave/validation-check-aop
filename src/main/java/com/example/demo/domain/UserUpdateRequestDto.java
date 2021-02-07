package com.example.demo.domain;


import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserUpdateRequestDto {

    @NotBlank(message = "이름을 입력하세요.")
    @Size(max = 15, message = "이름 길이를 초과하였습니다.")
    private String username;

    @NotBlank(message = "비밀번호를 입력하세요.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    private String password;

    @NotBlank(message = "휴대폰번호를 입력하세요.")
    @Pattern(regexp = "[0-9]{10,11}", message = "휴대폰번호는 10 ~ 11자의 숫자여야 합니다.")
    private String phone;

    @NotBlank(message = "생년월일을 입력하세요.")
    private String birth;

}
