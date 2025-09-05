package com.zev.application.repository;

import com.zev.application.model.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {
    Optional<ClassRoom> findClassRoomByNameAndTeacherId(String name, Long teacherId);
}
