package com.zev.application.repository;

import com.zev.application.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    List<Exercise> findExerciseByClassRoomId(String classRoomId);
}
