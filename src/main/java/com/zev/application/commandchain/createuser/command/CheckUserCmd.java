package com.zev.application.commandchain.createuser.command;

import com.zev.application.commandchain.createuser.CreateUserContext;
import com.zev.application.common.ErrorCode;
import com.zev.application.dto.request.user.CreateUserRequest;
import com.zev.application.exception.BusinessException;
import com.zev.application.model.AppUser;
import com.zev.application.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CheckUserCmd implements Command {

    private final AppUserRepository appUserRepository;
    private final ModelMapper modelMapper;

    @Override
    public boolean execute(Context context) throws Exception {
        CreateUserContext ctx = (CreateUserContext) context;

        CreateUserRequest request = ctx.getRequest();

        Optional<AppUser> existUser =  appUserRepository.findAppUserByUsernameLogin(request.getEmail());

        if (existUser.isPresent()) throw new BusinessException(ErrorCode.USER_ALREADY_EXIST);

        return false;
    }
}
