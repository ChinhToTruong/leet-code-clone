package com.zev.application.controller;

import com.zev.application.dto.request.user.CreateUserRequest;
import com.zev.application.dto.response.DataResponse;
import com.zev.application.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public DataResponse<?> createUser(@RequestBody @Valid CreateUserRequest request) throws Exception {
        return DataResponse.builder()
                .data(userService.createUserWithRoleAndPermissions(request))
                .build();
    }

    @PostMapping("/get-details")
    public DataResponse<?> getDetails() throws Exception {
        return DataResponse.builder()
                .data(userService.findUserById())
                .build();
    }
}
