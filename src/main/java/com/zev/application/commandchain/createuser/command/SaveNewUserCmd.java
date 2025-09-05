package com.zev.application.commandchain.createuser.command;

import com.zev.application.commandchain.createuser.CreateUserContext;
import com.zev.application.common.Constants;
import com.zev.application.common.ErrorCode;
import com.zev.application.dto.request.user.CreateUserRequest;
import com.zev.application.dto.response.user.CreateUserResponse;
import com.zev.application.exception.BusinessException;
import com.zev.application.model.AppPermission;
import com.zev.application.model.AppRole;
import com.zev.application.model.AppUser;
import com.zev.application.repository.AppPermissionRepository;
import com.zev.application.repository.AppRoleRepository;
import com.zev.application.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class SaveNewUserCmd implements Command {
    private final AppUserRepository userRepository;
    private final AppRoleRepository roleRepository;
    private final AppPermissionRepository permissionRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean execute(Context context) throws Exception {

        CreateUserContext ctx = (CreateUserContext) context;
        List<AppPermission> permissions = permissionRepository.findAppPermissionByPermissionKeyIn(ctx.getPermissions());

        CreateUserRequest request = ctx.getRequest();
        AppRole role = roleRepository
                .findAppRoleByRoleKey(request.getRole().getRoleKey())
                .orElseThrow(() -> new BusinessException(ErrorCode.ROLE_NOT_EXIST));
        role.setPermissions(Set.copyOf(permissions));
        AppUser user = modelMapper.map(request, AppUser.class);
        user.setUsername(request.getUsername());
        user.setUsernameLogin(request.getEmail());
        user.setPasswordLogin(passwordEncoder.encode(request.getPassword()));
        user.setStatus(Constants.AppStatus.ACTIVE);
        user.setFlagDel(Constants.AppFlag.enable);
        user.setRoles(Set.of(role));
        userRepository.save(user);

        CreateUserResponse response = modelMapper.map(request, CreateUserResponse.class);
        ctx.setResponse(response);
        return false;
    }
}
