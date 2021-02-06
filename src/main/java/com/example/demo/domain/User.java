package com.example.demo.domain;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;
    private String username;
    private String password;
    private String email;

}
