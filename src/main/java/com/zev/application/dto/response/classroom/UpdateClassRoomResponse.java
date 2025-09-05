package com.zev.application.dto.response.classroom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateClassRoomResponse {

    private String id;
    private String name;
}
