package com.zev.application.repository;

import com.zev.application.model.AppRole;
import com.zev.application.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Long> {

    @Query(value = "select * from tbl_role r " +
            "join tbl_user_role ur " +
            "on r.id = ur.role_id " +
            "where ur.user_id = (:userId)",
            nativeQuery = true)
    Set<AppRole> getAppRoleByUserAppId(@Param("userId") Long userAppId);

    Optional<AppRole> findAppRoleByRoleKey(String roleKey);
}
