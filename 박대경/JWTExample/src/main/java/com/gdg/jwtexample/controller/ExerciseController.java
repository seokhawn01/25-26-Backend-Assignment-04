package com.gdg.jwtexample.controller;

import com.gdg.jwtexample.dto.exercise.ExerciseCreateReq;
import com.gdg.jwtexample.dto.exercise.ExerciseInfoRes;
import com.gdg.jwtexample.dto.exercise.ExerciseUpdateReq;
import com.gdg.jwtexample.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final ExerciseService exerciseService;

    @PostMapping
    public ResponseEntity<ExerciseInfoRes> createPost(Principal principal, @RequestBody ExerciseCreateReq exerciseCreateReq) {
        return ResponseEntity.status(HttpStatus.CREATED).body(exerciseService.createPost(principal, exerciseCreateReq));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<ExerciseInfoRes> getPost(@PathVariable Long postId) {
        return ResponseEntity.ok(exerciseService.getPostInfo(postId));
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<ExerciseInfoRes> updatePost(Principal principal, @PathVariable Long postId, @RequestBody ExerciseUpdateReq exerciseUpdateReq) {
        return ResponseEntity.ok(exerciseService.updatePost(principal, postId, exerciseUpdateReq));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(Principal principal, @PathVariable Long postId) {
        exerciseService.deletePost(principal, postId);
        return ResponseEntity.noContent().build();
    }
}
