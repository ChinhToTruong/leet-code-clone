package com.zev.application.config;

import com.zev.application.model.AppPermission;
import com.zev.application.model.AppRole;
import com.zev.application.model.AppUser;
import com.zev.application.repository.AppPermissionRepository;
import com.zev.application.repository.AppRoleRepository;
import com.zev.application.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

//@Configuration
@RequiredArgsConstructor
public class MockData {
    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AppRoleRepository roleRepository;
    private final AppPermissionRepository permissionRepository;

    @Bean
    public CommandLineRunner init() {
        return args -> {
            // 1. Tạo Permissions
            Optional<AppPermission> temp1 = permissionRepository.findAppPermissionByPermissionKey("ADD_USER");
            Optional<AppPermission> temp2 = permissionRepository.findAppPermissionByPermissionKey("EDIT_USER");
            Optional<AppPermission> temp3 = permissionRepository.findAppPermissionByPermissionKey("DELETE_USER");
            Optional<AppPermission> temp4 = permissionRepository.findAppPermissionByPermissionKey("ADD_PROJECT");
            Optional<AppPermission> temp5 = permissionRepository.findAppPermissionByPermissionKey("EDIT_PROJECT");
            Optional<AppPermission> temp6 = permissionRepository.findAppPermissionByPermissionKey("DELETE_PROJECT");
            Optional<AppPermission> temp7 = permissionRepository.findAppPermissionByPermissionKey("ADD_TASK");
            Optional<AppPermission> temp8 = permissionRepository.findAppPermissionByPermissionKey("EDIT_TASK");
            Optional<AppPermission> temp9 = permissionRepository.findAppPermissionByPermissionKey("DELETE_TASK");

            if (temp1.isEmpty()) {
                AppPermission p1 = new AppPermission();
                p1.setPermissionName("ADD Users");
                p1.setPermissionKey("ADD_USER");

                permissionRepository.save(p1);
            }
            if (temp2.isEmpty()) {
                AppPermission p1 = new AppPermission();
                p1.setPermissionName("Edit Users");
                p1.setPermissionKey("EDIT_USER");

                permissionRepository.save(p1);
            }

            if (temp3.isEmpty()) {
                AppPermission p1 = new AppPermission();
                p1.setPermissionName("Delete Users");
                p1.setPermissionKey("DELETE_USER");

                permissionRepository.save(p1);
            }

            if (temp4.isEmpty()) {
                AppPermission p1 = new AppPermission();
                p1.setPermissionName("ADD Users");
                p1.setPermissionKey("ADD_PROJECT");

                permissionRepository.save(p1);
            }
            if (temp5.isEmpty()) {
                AppPermission p1 = new AppPermission();
                p1.setPermissionName("Edit Users");
                p1.setPermissionKey("EDIT_PROJECT");

                permissionRepository.save(p1);
            }
            if (temp6.isEmpty()) {
                AppPermission p1 = new AppPermission();
                p1.setPermissionName("Delete Users");
                p1.setPermissionKey("DELETE_PROJECT");

                permissionRepository.save(p1);
            }


            if (temp7.isEmpty()) {
                AppPermission p1 = new AppPermission();
                p1.setPermissionName("ADD Users");
                p1.setPermissionKey("ADD_TASK");

                permissionRepository.save(p1);
            }
            if (temp8.isEmpty()) {
                AppPermission p1 = new AppPermission();
                p1.setPermissionName("Edit Users");
                p1.setPermissionKey("EDIT_TASK");

                permissionRepository.save(p1);
            }

            if (temp9.isEmpty()) {
                AppPermission p1 = new AppPermission();
                p1.setPermissionName("Delete Users");
                p1.setPermissionKey("DELETE_TASK");

                permissionRepository.save(p1);
            }


            // 2. Tạo Roles
            Optional<AppPermission> p1 = permissionRepository.findAppPermissionByPermissionKey("ADD_USER");
            Optional<AppPermission> p2 = permissionRepository.findAppPermissionByPermissionKey("EDIT_USER");
            Optional<AppPermission> p3 = permissionRepository.findAppPermissionByPermissionKey("DELETE_USER");
            Optional<AppPermission> p4 = permissionRepository.findAppPermissionByPermissionKey("ADD_PROJECT");
            Optional<AppPermission> p5 = permissionRepository.findAppPermissionByPermissionKey("EDIT_PROJECT");
            Optional<AppPermission> p6 = permissionRepository.findAppPermissionByPermissionKey("DELETE_PROJECT");
            Optional<AppPermission> p7 = permissionRepository.findAppPermissionByPermissionKey("ADD_TASK");
            Optional<AppPermission> p8 = permissionRepository.findAppPermissionByPermissionKey("EDIT_TASK");
            Optional<AppPermission> p9 = permissionRepository.findAppPermissionByPermissionKey("DELETE_TASK");
            Optional<AppRole> r1 = roleRepository.findAppRoleByRoleKey("ADMIN");
            if (r1.isEmpty()) {
                AppRole r = new AppRole();
                r.setRoleName("Administrator");
                r.setRoleKey("ADMIN");
                r.setPermissions(
                        Set.of(
                                p1.get(),
                                p2.get(),
                                p3.get(),
                                p4.get(),
                                p5.get(),
                                p6.get(),
                                p7.get(),
                                p8.get(),
                                p9.get()
                        )
                );
                roleRepository.save(r);
            }



            Optional<AppRole> r2 = roleRepository.findAppRoleByRoleKey("USER");
            if (r2.isEmpty()) {
                AppRole r = new AppRole();
                r.setRoleName("Standard User");
                r.setRoleKey("USER");
                r.setPermissions(Set.of(
                        p8.get()
                ));
                roleRepository.save(r);
            }

            Optional<AppRole> r3 = roleRepository.findAppRoleByRoleKey("LEADER");
            if (r3.isEmpty()) {
                AppRole r = new AppRole();
                r.setRoleName("Standard User");
                r.setRoleKey("USER");
                r.setPermissions(Set.of(
                        p1.get(),
                        p2.get(),
                        p3.get(),
                        p4.get(),
                        p5.get(),
                        p6.get(),
                        p7.get(),
                        p8.get(),
                        p9.get()
                ));
                roleRepository.save(r);
            }


            // 3. Tạo Users

            Optional<AppUser> u1 = userRepository.findAppUserByUsernameLogin("admin");
            if (u1.isEmpty()) {

                AppUser admin = new AppUser();
                admin.setUsernameLogin("admin");
                admin.setPasswordLogin(passwordEncoder.encode("123456")); // mã hoá password
                admin.setFlagDel("1");
                admin.setStatus("ACTIVE");
                AppRole roles = roleRepository.findAppRoleByRoleKey("ADMIN").orElseThrow();
                admin.setRoles(Set.of(roles));
                userRepository.save(admin);
            }

            Optional<AppUser> u2 = userRepository.findAppUserByUsernameLogin("user");
            if (u2.isEmpty()) {

                AppUser normalUser = new AppUser();
                normalUser.setUsernameLogin("user");
                normalUser.setPasswordLogin(passwordEncoder.encode("123456"));
                normalUser.setFlagDel("1");
                normalUser.setStatus("ACTIVE");
                AppRole roles = roleRepository.findAppRoleByRoleKey("USER").orElseThrow();
                normalUser.setRoles(Set.of(roles));
            }


            Optional<AppUser> u3 = userRepository.findAppUserByUsernameLogin("user");
            if (u3.isEmpty()) {

                AppUser normalUser = new AppUser();
                normalUser.setUsernameLogin("leader");
                normalUser.setPasswordLogin(passwordEncoder.encode("123456"));
                normalUser.setFlagDel("1");
                normalUser.setStatus("ACTIVE");
                AppRole roles = roleRepository.findAppRoleByRoleKey("LEADER").orElseThrow();
                normalUser.setRoles(Set.of(roles));
            }

            System.out.println("=== Mock data initialized ===");
        };
    }
}
