package com.zev.application.service;

import com.zev.application.common.ErrorCode;
import com.zev.application.dto.request.exercise.CreateExerciseRequest;
import com.zev.application.dto.request.exercise.UpdateExerciseRequest;
import com.zev.application.dto.response.exercise.CreateExerciseResponse;
import com.zev.application.dto.response.exercise.ExerciseDetailsResponse;
import com.zev.application.dto.response.exercise.UpdateExerciseResponse;
import com.zev.application.exception.BusinessException;
import com.zev.application.model.Exercise;
import com.zev.application.repository.ClassRoomRepository;
import com.zev.application.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ClassRoomRepository classRoomRepository;
    private final ModelMapper modelMapper;

    public CreateExerciseResponse create(CreateExerciseRequest request) throws BusinessException {

        classRoomRepository.findById(Long.parseLong(request.getClassRoomId()))
                .orElseThrow(() -> new BusinessException(ErrorCode.CLASS_ROOM_NOT_FOUND));
        Exercise exercise = modelMapper.map(request, Exercise.class);

        exercise = exerciseRepository.save(exercise);

        return modelMapper.map(exercise, CreateExerciseResponse.class);
    }

    public UpdateExerciseResponse create(UpdateExerciseRequest request) throws BusinessException {

        Exercise exercise = exerciseRepository.findById(request.getId())
                .orElseThrow(() -> new BusinessException(ErrorCode.EXERCISE_NOT_FOUND));
        exercise.setDescription(request.getDescription());
        exercise.setHashtag(request.getHashtag());
        exercise.setRank(request.getRank());
        exercise.setTittle(request.getTittle());
        exercise = exerciseRepository.save(exercise);

        return modelMapper.map(exercise, UpdateExerciseResponse.class);
    }


    public ExerciseDetailsResponse getDetails(Long id) throws BusinessException {

        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.EXERCISE_NOT_FOUND));
        return modelMapper.map(exercise, ExerciseDetailsResponse.class);
    }

    public String deleteExercise(Long id) throws BusinessException {
        exerciseRepository.deleteById(id);
        return "Exercise deleted";
    }

    public List<ExerciseDetailsResponse> getAllExercisesByClassRoomId(Long classRoomId) throws BusinessException {
        return exerciseRepository.findExerciseByClassRoomId(String.valueOf(classRoomId))
                .stream()
                .map(exercise -> modelMapper.map(exercise, ExerciseDetailsResponse.class))
                .collect(Collectors.toList());

    }

}
