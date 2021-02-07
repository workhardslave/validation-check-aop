package com.example.demo.domain;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;
    private String email;
    private String username;
    private String password;
    private String phone;
    private String birth;
    private Role role;

}
