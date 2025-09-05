package com.zev.application.commandchain.createuser.command;

import com.zev.application.commandchain.createuser.CreateUserContext;
import com.zev.application.common.ErrorCode;
import com.zev.application.dto.request.user.CreateUserRequest;
import com.zev.application.exception.BusinessException;
import com.zev.application.model.AppPermission;
import com.zev.application.repository.AppPermissionRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CheckPermissionCmd implements Command {

    private final AppPermissionRepository permissionRepository;
    private final ModelMapper modelMapper;

    @Override
    public boolean execute(Context context) throws Exception {
        CreateUserContext ctx = (CreateUserContext) context;
        List<CreateUserRequest.RoleRequest.PermissionRequest> request = ctx.getRequest().getRole().getPermissions();
        List<AppPermission> notExistPermissions = new ArrayList<>();
        List<String> permissionKeys = request.stream().map(CreateUserRequest.RoleRequest.PermissionRequest::getPermissionKey).toList();
//        request.forEach(permission -> {
//            Optional<AppPermission> existPermission = permissionRepository.findAppPermissionByPermissionKey(permission.getPermissionKey());
//
//            if (existPermission.isEmpty()) {
////                AppPermission notExistPermission = modelMapper.map(permission, AppPermission.class);
////                notExistPermissions.add(notExistPermission);
//                throw new BusinessException(ErrorCode.PERMISSION_NOT_FOUND);
//            }
//        });
        List<AppPermission> permissions = permissionRepository.findAppPermissionByPermissionKeyIn(permissionKeys);
        if (permissions.size() != request.size()) throw new BusinessException(ErrorCode.PERMISSION_NOT_FOUND);
        ctx.setPermissions(permissionKeys);
        return false;
    }
}
