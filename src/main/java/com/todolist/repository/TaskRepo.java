package com.todolist.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todolist.model.Tag;
import com.todolist.model.Task;
import java.util.List;


public interface TaskRepo extends JpaRepository<Task, UUID> {
  
  List<Task> findByTag(Tag tag);

}
