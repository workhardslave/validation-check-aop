package com.example.demo.controller;

import com.example.demo.domain.*;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    // http://localhost:8080/users
    @GetMapping("/users")
    public CommonDto<List<User>> findAll() {
        System.out.println("findAll()");

        return new CommonDto<>(HttpStatus.OK.value(), userService.findAll()); // MessageConverter => Java Object -> JSON String
    }

    // http://localhost:8080/users/1
    @GetMapping("/users/{id}")
    public CommonDto<User> findById(@PathVariable Long id) {
        System.out.println("findById() : " + id);

        return new CommonDto<>(HttpStatus.OK.value(), userService.findUser(id));
    }

    @CrossOrigin
    // http://localhost:8080/users/
    @PostMapping("/users")
    // x-www-form-urlencoded => request.getParameter()
    // text/plain => @RequestBody 어노테이션
    // application/json => @RequestBody어노테이션 + 오브젝트
    // 에러가 있을 때와 없을 때 리턴하는 타입이 다르므로, 응답할 때 정하겠다는 의미로 '?' 사용
    public CommonDto<?> save(@Valid @RequestBody UserSaveRequestDto dto, BindingResult bindingResult) {
        System.out.println("save()");
        System.out.println("dto = " + dto);
        userService.signUp(dto);

        return new CommonDto<>(HttpStatus.CREATED.value());
    }

    // http://localhost:8080/users/1
    @DeleteMapping("/users/{id}")
    public CommonDto<?> delete(@PathVariable Long id) {
        System.out.println("delete() : " + id);
        userService.deleteUser(id);

        return new CommonDto(HttpStatus.OK.value());
    }

    // http://localhost:8080/users/1
    @PutMapping("/users/{id}")
    public CommonDto<?> update(@PathVariable Long id, @Valid @RequestBody UserUpdateRequestDto dto, BindingResult bindingResult) {
        System.out.println("update() : " + id);
        userService.updateUser(id, dto);

        return new CommonDto<>(HttpStatus.OK.value());
    }
}