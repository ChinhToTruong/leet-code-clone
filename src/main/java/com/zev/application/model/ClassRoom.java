package com.zev.application.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "ClassRoom")
@Table(name = "tbl_class_room")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class ClassRoom extends AuditTable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;


    private String name;

    private Long teacherId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_class_room_student", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "class_room_id"))
    private Set<AppUser> students;
}
