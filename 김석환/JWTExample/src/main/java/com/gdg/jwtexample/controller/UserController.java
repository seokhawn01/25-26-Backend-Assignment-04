package com.gdg.jwtexample.controller;

import com.gdg.jwtexample.dto.user.UserInfoRes;
import com.gdg.jwtexample.dto.user.UserSignUpReq;
import com.gdg.jwtexample.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserSignUpReq request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signUp(request));
    }

    @GetMapping("/my")
    public ResponseEntity<UserInfoRes> getMyInfo(Principal principal) {
        return ResponseEntity.ok(userService.getMyInfo(principal.getName()));
    }

    @GetMapping("/info/{userId}")
    public ResponseEntity<UserInfoRes> getUserInfo(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserInfo(userId));
    }
}
