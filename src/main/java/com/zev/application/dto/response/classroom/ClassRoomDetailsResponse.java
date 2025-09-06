package com.zev.application.dto.response.classroom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassRoomDetailsResponse {
    private String id;
    private String name;
    private TeacherClassRoomDetailsResponse teacher;
    private List<StudentClassRoomDetailsResponse> students;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TeacherClassRoomDetailsResponse {
        private String id;
        private String name;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StudentClassRoomDetailsResponse {
        private String id;
        private String name;
    }
}
