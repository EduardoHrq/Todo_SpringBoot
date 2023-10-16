package com.todolist.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "tb_tasks")
public class Task {

  @Id
  @GeneratedValue(generator = "UUID")
  public UUID id;

  private String title;
  private boolean status;
  private String priority;

  @ManyToOne
  @JoinColumn(name = "tag_id", nullable = true)
  private Tag tag;


}
