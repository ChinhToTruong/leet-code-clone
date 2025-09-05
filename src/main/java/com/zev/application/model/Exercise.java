package com.zev.application.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "Exercise")
@Table(name = "tbl_exercise")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class Exercise extends AuditTable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "user_id")
    private String user_id;

    @Column(name = "tittle")
    private String tittle;

    @Column(name = "hashtag")
    private String hashtag;

    @Column(name = "description")
    private String description;

    @Column(name = "answer")
    private String answer;

    @Column(name = "status")
    private String status;

    @Column(name = "rank")
    private String rank;
}
