package com.zev.application.service;

import com.zev.application.common.Constants;
import com.zev.application.common.ErrorCode;
import com.zev.application.dto.request.classroom.CreateClassRoomRequest;
import com.zev.application.dto.request.classroom.UpdateClassRoomRequest;
import com.zev.application.dto.response.classroom.CreateClassRoomResponse;
import com.zev.application.dto.response.classroom.UpdateClassRoomResponse;
import com.zev.application.exception.BusinessException;
import com.zev.application.model.AppUser;
import com.zev.application.model.ClassRoom;
import com.zev.application.repository.AppUserRepository;
import com.zev.application.repository.ClassRoomRepository;
import com.zev.application.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClassRoomService {

    private final ClassRoomRepository classRoomRepository;
    private final AppUserRepository userRepository;
    private final ModelMapper modelMapper;

    public CreateClassRoomResponse createClassRoom(CreateClassRoomRequest request) throws BusinessException {
        AppUser teacher = userRepository.findById(SecurityUtils.getCurrentUserId())
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_EXIST, Constants.Message.TEACHER_NOT_FOUND));
        Optional<ClassRoom> exist = classRoomRepository.findClassRoomByNameAndTeacherId(request.getName(), SecurityUtils.getCurrentUserId());
        if (exist.isPresent()) {
            throw new BusinessException(ErrorCode.CLASS_ROOM_NAME_ALREADY_EXIST_WITH_THIS_USER);
        }

        ClassRoom classRoom = modelMapper.map(request, ClassRoom.class);
        classRoom.setTeacherId(SecurityUtils.getCurrentUserId());
        classRoom = classRoomRepository.save(classRoom);

        CreateClassRoomResponse response = modelMapper.map(classRoom, CreateClassRoomResponse.class);
        CreateClassRoomResponse.TeacherResponse teacherResponse = modelMapper.map(teacher, CreateClassRoomResponse.TeacherResponse.class);
        response.setTeacher(teacherResponse);
        return response;
    }

    public UpdateClassRoomResponse updateClassRoom(UpdateClassRoomRequest request) throws BusinessException {
        ClassRoom classRoom = classRoomRepository.findById(request.getId())
                .orElseThrow(() ->  new BusinessException(ErrorCode.CLASS_ROOM_NOT_FOUND));

        if (!Objects.equals(classRoom.getTeacherId(), SecurityUtils.getCurrentUserId()))
            throw new BusinessException(ErrorCode.CLASS_ROOM_NOT_OWNER_BY_CURRENT_USER);

        classRoom.setName(request.getName());
        classRoom = classRoomRepository.save(classRoom);
        return modelMapper.map(classRoom, UpdateClassRoomResponse.class);
    }
}
