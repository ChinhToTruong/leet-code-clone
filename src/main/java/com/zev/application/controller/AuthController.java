package com.zev.application.controller;

import com.zev.application.dto.request.AuthRequest;
import com.zev.application.dto.response.DataResponse;
import com.zev.application.exception.BusinessException;
import com.zev.application.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/generate-token")
    public DataResponse<?> authenticateAndGetToken(@RequestBody AuthRequest authRequest) throws BusinessException {
        return DataResponse.builder()
                .data(authService.login(authRequest))
                .build();
    }

}
