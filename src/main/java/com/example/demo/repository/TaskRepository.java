package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.constants.TaskStatus;
import com.example.demo.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository <Task, Long> {

	@Query("from Task where status=:status")
	List<Task> getAllTaskByStatus(@Param(value = "status") TaskStatus status);
}
 