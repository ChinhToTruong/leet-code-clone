package com.zev.application.service;

import com.zev.application.model.AppPermission;
import com.zev.application.model.AppRole;
import com.zev.application.model.AppUser;
import com.zev.application.repository.AppPermissionRepository;
import com.zev.application.repository.AppRoleRepository;
import com.zev.application.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AppUserRepository userRepository;
    private final AppRoleRepository roleRepository;
    private final AppPermissionRepository permissionRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AppUser user = userRepository.findAppUserByUsernameLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        Set<AppRole> roles = roleRepository.getAppRoleByUserAppId(user.getId())
                .stream().peek(r -> {
                    Set<AppPermission> permissions = new HashSet<>(permissionRepository.getPermissionByRoleId(r.getId()));
                    r.setPermissions(permissions);
                }).collect(Collectors.toSet());
        user.setRoles(roles);
        return user;
    }
}
