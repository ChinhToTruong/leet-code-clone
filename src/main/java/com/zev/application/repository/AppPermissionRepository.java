package com.zev.application.repository;

import com.zev.application.model.AppPermission;
import com.zev.application.model.AppRole;
import com.zev.application.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface AppPermissionRepository extends JpaRepository<AppPermission, Long> {

    @Query(value = "select * from tbl_permission p " +
            "join tbl_role_permission rp " +
            "on p.id = rp.permission_id " +
            "where rp.role_id = (:roleId)",
            nativeQuery = true)
    List<AppPermission> getPermissionByRoleId(@Param("roleId") Long appRoleId);

    Optional<AppPermission> findAppPermissionByPermissionKey(String permissionKey);

    List<AppPermission> findAppPermissionByPermissionKeyIn(List<String> permissionKeys);
}
