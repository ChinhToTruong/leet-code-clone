package com.zev.application.dto.response.exercise;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateExerciseResponse {
    private Long id;

    private String classRoomId;

    private String tittle;

    private String hashtag;

    private String description;

    private String rank;
}
