package com.zev.application.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "TestCase")
@Table(name = "tbl_testcase")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class TestCase extends AuditTable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;


    @Column(name = "exercise_id")
    private String exerciseId;

    @Column(name = "input")
    private String input;

    @Column(name = "expectOutput")
    private String expectOutput;
}
