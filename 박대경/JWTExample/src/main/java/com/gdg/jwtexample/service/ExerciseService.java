package com.gdg.jwtexample.service;

import com.gdg.jwtexample.domain.Exercise;
import com.gdg.jwtexample.dto.exercise.ExerciseCreateReq;
import com.gdg.jwtexample.dto.exercise.ExerciseInfoRes;
import com.gdg.jwtexample.dto.exercise.ExerciseUpdateReq;
import com.gdg.jwtexample.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class PostService {

    private final ExerciseRepository exerciseRepository;
    private final UserService userService;

    @Transactional
    public ExerciseInfoRes createPost(Principal principal, ExerciseCreateReq exerciseCreateReq) {
        Exercise exercise = exerciseRepository.save(Exercise.builder()
                        .title(exerciseCreateReq.title())
                        .content(exerciseCreateReq.content())
                        .user(userService.getUserEntity(Long.parseLong(principal.getName())))
                        .build());

        return ExerciseInfoRes.fromEntity(exercise);
    }

    @Transactional(readOnly = true)
    public ExerciseInfoRes getPostInfo(Long postId) {
        return ExerciseInfoRes.fromEntity(getPost(postId));
    }

    @Transactional
    public ExerciseInfoRes updatePost(Principal principal, Long postId, ExerciseUpdateReq exerciseUpdateReq) {
        Exercise exercise = getPost(postId);
        validateAuthor(principal, exercise);

        exercise.update(
                exerciseUpdateReq.title() == null ? exercise.getTitle() : exerciseUpdateReq.title(),
                exerciseUpdateReq.content() == null ? exercise.getContent() : exerciseUpdateReq.content());
        return ExerciseInfoRes.fromEntity(exercise);
    }

    @Transactional
    public void deletePost(Principal principal, Long postId) {
        Exercise exercise = getPost(postId);
        validateAuthor(principal, exercise);

        exerciseRepository.delete(exercise);
    }

    private Exercise getPost(Long postId) {
        return exerciseRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시물입니다."));
    }

    private void validateAuthor(Principal principal, Exercise exercise) {
        if (!exercise.getUser().getId().equals(Long.parseLong(principal.getName()))) {
            throw new RuntimeException("해당 게시물에 접근할 권한이 없습니다.");
        }
    }
}
