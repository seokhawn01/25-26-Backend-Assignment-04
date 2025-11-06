package com.gdg.jwtexample.repository;

import com.gdg.jwtexample.domain.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Exercise, Long> {
}
