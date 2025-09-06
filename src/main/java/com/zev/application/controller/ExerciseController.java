package com.zev.application.controller;


import com.zev.application.dto.request.exercise.CreateExerciseRequest;
import com.zev.application.dto.response.DataResponse;
import com.zev.application.exception.BusinessException;
import com.zev.application.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exercise")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @PostMapping("/create")
    public DataResponse<?> create(@RequestBody CreateExerciseRequest request) throws BusinessException {
        return DataResponse
                .builder()
                .data(exerciseService.create(request))
                .build();
    }
}
