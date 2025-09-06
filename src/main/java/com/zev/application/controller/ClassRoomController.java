package com.zev.application.controller;

import com.zev.application.dto.request.classroom.CreateClassRoomRequest;
import com.zev.application.dto.request.classroom.UpdateClassRoomRequest;
import com.zev.application.dto.response.DataResponse;
import com.zev.application.exception.BusinessException;
import com.zev.application.service.ClassRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/update")
    public DataResponse<?> updateClassRoom(@RequestBody UpdateClassRoomRequest request) throws BusinessException {
        return DataResponse.builder()
                .data(classRoomService.updateClassRoom(request))
                .build();
    }

    @GetMapping("/get-list")
    public DataResponse<?> getClassRoomList() throws BusinessException {
        return DataResponse.builder()
                .data(classRoomService.getListClassRoom())
                .build();
    }

    @GetMapping("/{id}")
    public DataResponse<?> getClassRoomById(@PathVariable Long id) throws BusinessException {
        return DataResponse.builder()
                .data(classRoomService.getClassRoomById(id))
                .build();
    }

    @DeleteMapping("/{id}")
    public DataResponse<?> deleteClassRoomById(@PathVariable Long id) throws BusinessException {
        return DataResponse.builder()
                .data(classRoomService.deleteClassRoom(id))
                .build();
    }

}
