package com.example.demo.service;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.domain.UserSaveRequestDto;
import com.example.demo.domain.UserUpdateRequestDto;
import com.example.demo.exception.EmailDuplicationException;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * 회원가입
     * @param dto
     */
    public void signUp(UserSaveRequestDto dto) {
        checkDuplication(dto.getEmail());
        dto.giveRole(Role.BASIC);
        userRepository.save(dto.toEntity());
    }

    /**
     * 회원 전체 조회
     * @return
     */
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * 특정 회원 조회
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다. : " + id));
    }

    /**
     * 회원 정보 수정
     * @param id
     * @param dto
     * @return
     */
    public User updateUser(Long id, UserUpdateRequestDto dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다. : " + id));
        user.update(dto);
        return user;
    }

    /**
     * 회원 탈퇴
     * @param id
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void checkDuplication(String email) {
        if(userRepository.findByEmail(email).isPresent()) {
            throw new EmailDuplicationException("이미 가입된 이메일입니다.");
        }
        System.out.println("회원가입 가능한 이메일입니다.");
    }
}
