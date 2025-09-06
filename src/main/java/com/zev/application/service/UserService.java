package com.zev.application.service;

import com.zev.application.commandchain.createuser.CreateUserChain;
import com.zev.application.commandchain.createuser.CreateUserContext;
import com.zev.application.common.ErrorCode;
import com.zev.application.dto.request.user.CreateUserRequest;
import com.zev.application.dto.response.user.CreateUserResponse;
import com.zev.application.exception.BusinessException;
import com.zev.application.model.AppUser;
import com.zev.application.repository.AppUserRepository;
import com.zev.application.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final CreateUserChain createUserChain;
    private final AppUserRepository userRepository;

    @Transactional
    public CreateUserResponse createUserWithRoleAndPermissions(CreateUserRequest request) throws Exception {
        CreateUserContext context = new CreateUserContext();
        context.setRequest(request);
        createUserChain.execute(context);
        return context.getResponse();
    }

    public AppUser findUserById() throws Exception {
        Long id = SecurityUtils.getCurrentUserId();
        return userRepository.findById(id).orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_EXIST));
    }
}
