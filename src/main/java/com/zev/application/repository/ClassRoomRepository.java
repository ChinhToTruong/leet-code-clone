package com.zev.application.repository;

import com.zev.application.model.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {
    Optional<ClassRoom> findClassRoomByNameAndTeacherId(String name, Long teacherId);
    List<ClassRoom> findClassRoomsByTeacherId(Long teacherId);

    @Modifying
    @Query(value = "delete ClassRoom " +
            "where id = (:id)")
    int deleteClassRoomById(@Param("id") Long id);
}
