package com.example.demo.domain;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserSaveRequestDto {

    @NotBlank(message = "아이디를 입력하세요.")
    @Size(max = 20, message = "아이디 길이를 초과하였습니다.")
    private String username;

    @NotBlank(message = "비밀번호를 입력하세요.")
    private String password;

    @NotBlank(message = "이메일을 입력하세요.")
    @Email(message = "이메일 양식이 아닙니다.")
    private String email;
}
