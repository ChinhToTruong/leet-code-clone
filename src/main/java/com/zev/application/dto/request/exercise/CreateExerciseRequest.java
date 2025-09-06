package com.zev.application.dto.request.exercise;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateExerciseRequest {

    private String classRoomId;

    private String tittle;

    private String hashtag;

    private String description;

    private String rank;
}
