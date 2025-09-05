package com.zev.application.dto.request.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateUserRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotNull
    private RoleRequest role;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class RoleRequest {
        @NotBlank
        private String roleKey;
        @NotBlank
        private String roleDescription;

        @NotNull
        @Size(min = 1)
        private List<@Valid PermissionRequest> permissions;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class PermissionRequest {
            @NotBlank
            private String permissionKey;
            @NotBlank
            private String permissionDescription;
        }
    }

}
