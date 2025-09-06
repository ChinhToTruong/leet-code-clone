package com.zev.application.dto.request.exercise;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateExerciseRequest {
    private Long id;

    private String tittle;

    private String hashtag;

    private String description;

    private String rank;
}
