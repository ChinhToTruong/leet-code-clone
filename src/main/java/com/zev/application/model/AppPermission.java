package com.zev.application.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "AppPermission")
@Table(name = "tbl_permission")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class AppPermission extends AuditTable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "permission_name")
    private String permissionName;

    @Column(name = "permission_key")
    private String permissionKey;
}
