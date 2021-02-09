package com.example.demo.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String phone;

    @Column
    private String birth;

    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * Builder Pattern : dto -> entity로 변환 시 사용
     * @param id
     * @param email
     * @param username
     * @param password
     * @param phone
     * @param birth
     * @param role
     */
    @Builder
    public User(Long id, String email, String username, String password, String phone, String birth, Role role) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.birth = birth;
        this.role = role;
    }

    public void update(UserUpdateRequestDto dto) {
        this.username = dto.getUsername();
        this.password = dto.getPassword();
        this.phone = dto.getPhone();
        this.birth = dto.getBirth();
    }
}
