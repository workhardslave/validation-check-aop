package com.example.demo.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "test1", "abcd1234", "test1@gmail.com"));
        users.add(new User(2L, "test2", "abcd1234", "test2@gmail.com"));
        users.add(new User(3L, "test3", "abcd1234", "test3@gmail.com"));
        users.add(new User(4L, "test4", "abcd1234", "test4@gmail.com"));

        return users;
    }

    public User findById(Long id) {

        return new User(id, "test" + id, "abcd1234", "test" + id + "@gmail.com");
    }

    public void save(UserSaveRequestDto user) {

        System.out.println("DB에 insert 하기");
    }

    public void delete(Long id) {

        System.out.println("DB에 delete 하기");
    }

    public void update(Long id, UserUpdateRequestDto dto) {

        // DAO 연결해서 실행하다가 exception 터짐
//        throw new IllegalArgumentException("수정 도중 오류가 발생했습니다.");
        System.out.println("DB에 update 하기");
    }
}
