package com.zev.application.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "Comment")
@Table(name = "tbl_comment")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class Comment extends AuditTable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "exercise_id")
    private String exerciseId;

    @Column(name = "user_id")
    private String userId;
}
