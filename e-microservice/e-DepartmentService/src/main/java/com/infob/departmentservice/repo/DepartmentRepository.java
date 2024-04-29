package com.infob.departmentservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infob.departmentservice.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{

    @Query("SELECT d FROM Department d JOIN FETCH d.children")
    List<Department> findAllDepartmentWithchildren();
    
    @Modifying
    @Query(value = "delete from department_children where children_id=:id", nativeQuery = true)
    void deleteDepartmentById(@Param("id") int id);
}
