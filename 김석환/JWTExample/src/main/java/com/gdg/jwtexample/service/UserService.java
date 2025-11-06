package com.gdg.jwtexample.service;

import com.gdg.jwtexample.domain.User;
import com.gdg.jwtexample.dto.user.UserInfoRes;
import com.gdg.jwtexample.dto.user.UserSignUpReq;
import com.gdg.jwtexample.jwt.TokenProvider;
import com.gdg.jwtexample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    // 회원가입
    public String signUp(UserSignUpReq request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("이미 가입된 이메일입니다.");
        }

        User user = User.of(
                request.email(),
                passwordEncoder.encode(request.password()),
                request.name()
        );

        userRepository.save(user);

        return tokenProvider.createAccessToken(user);
    }

    // 내 정보 조회
    public UserInfoRes getMyInfo(String email) {
        User user = getByEmail(email);
        return UserInfoRes.from(user);
    }

    // 남 정보 조회
    public UserInfoRes getUserInfo(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));
        return UserInfoRes.from(user);
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
    }
}
