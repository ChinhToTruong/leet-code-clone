package com.zev.application.controller;

import com.zev.application.dto.request.classroom.CreateClassRoomRequest;
import com.zev.application.dto.response.DataResponse;
import com.zev.application.exception.BusinessException;
import com.zev.application.service.ClassRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/class")
@RequiredArgsConstructor
public class ClassRoomController {

    private final ClassRoomService classRoomService;

    @PostMapping("/create")
    public DataResponse<?> createClassRoom(@RequestBody CreateClassRoomRequest request) throws BusinessException {
        return DataResponse.builder()
                .data(classRoomService.createClassRoom(request))
                .build();
    }

}
