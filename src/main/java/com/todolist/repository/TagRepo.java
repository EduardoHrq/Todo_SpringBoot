package com.todolist.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todolist.model.Tag;


public interface TagRepo extends JpaRepository<Tag, UUID> {

  Tag findByName(String name);

}
