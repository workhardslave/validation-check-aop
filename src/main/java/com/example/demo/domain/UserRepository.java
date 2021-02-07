package com.example.demo.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "test1@gmail.com", "test1", "abcd1234", "01012345678", "1995-01-01", Role.BASIC));
        users.add(new User(2L, "test2@gmail.com", "test2", "abcd1234", "01042345679", "1995-02-02", Role.VIP));
        users.add(new User(3L, "test3@gmail.com", "test3", "abcd1234", "01052345670", "1995-03-03", Role.BASIC));
        users.add(new User(4L, "test4@gmail.com", "test4", "abcd1234", "01062345672", "1995-04-04", Role.VIP));

        return users;
    }

    public User findById(Long id) {

        return new User(id, "test1@gmail.com", "test1", "abcd1234", "01012345678", "1995-01-01", Role.BASIC);
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
