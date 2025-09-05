package com.zev.application.dto.response.classroom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateClassRoomResponse {

    private String id;
    private String name;
    private TeacherResponse teacher;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TeacherResponse {
        private String id;
        private String name;
    }
}
