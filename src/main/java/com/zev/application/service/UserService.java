package com.zev.application.service;

import com.zev.application.commandchain.createuser.CreateUserChain;
import com.zev.application.commandchain.createuser.CreateUserContext;
import com.zev.application.dto.request.user.CreateUserRequest;
import com.zev.application.dto.response.user.CreateUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final CreateUserChain createUserChain;

    @Transactional
    public CreateUserResponse createUserWithRoleAndPermissions(CreateUserRequest request) throws Exception {
        CreateUserContext context = new CreateUserContext();
        context.setRequest(request);
        createUserChain.execute(context);
        return context.getResponse();
    }
}
