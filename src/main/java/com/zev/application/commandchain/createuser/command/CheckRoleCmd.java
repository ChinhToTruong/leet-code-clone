package com.zev.application.commandchain.createuser.command;

import com.zev.application.commandchain.createuser.CreateUserContext;
import com.zev.application.common.ErrorCode;
import com.zev.application.dto.request.user.CreateUserRequest;
import com.zev.application.exception.BusinessException;
import com.zev.application.model.AppRole;
import com.zev.application.repository.AppRoleRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CheckRoleCmd implements Command {

    private final AppRoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Override
    public boolean execute(Context context) throws Exception {
        CreateUserContext ctx = (CreateUserContext) context;
        CreateUserRequest.RoleRequest request = ctx.getRequest().getRole();

        Optional<AppRole> role = roleRepository.findAppRoleByRoleKey(request.getRoleKey());
//        if (role.isEmpty()){
//            AppRole notExistRole = modelMapper.map(request, AppRole.class);
//            roleRepository.save(notExistRole);
//        }
        if (role.isEmpty())throw new BusinessException(ErrorCode.ROLE_NOT_EXIST);

        return false;
    }
}
