package com.zev.application.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserResponse {
    private String username;
    private RoleResponse role;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RoleResponse {
        private String roleKey;
        private String roleDescription;
        private List<PermissionResponse> permissions;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class PermissionResponse {
            private String permissionKey;
            private String permissionDescription;
        }
    }
}
