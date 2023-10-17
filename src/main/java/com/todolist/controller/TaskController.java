package com.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.model.Tag;
import com.todolist.model.Task;
import com.todolist.repository.TagRepo;
import com.todolist.repository.TaskRepo;

@RestController
@RequestMapping("/rest")
public class TaskController {

  @Autowired
  private TaskRepo tarefas;

  @Autowired
  private TagRepo tags;

  @GetMapping("/lists")
  public ResponseEntity list() {

    var tasks = this.tarefas.findAll();

    var cat = this.tags.findAll();

    return ResponseEntity.status(HttpStatus.OK).body(tasks.toString() + cat.toString());

  }

  @GetMapping("/lists/tasks")
  public ResponseEntity listTasks() {

    var tasks = this.tarefas.findAll();

    return ResponseEntity.status(HttpStatus.OK).body(tasks);

  }

  @GetMapping("/lists/tags")
  public ResponseEntity listTags() {

    Iterable<Tag> cat = this.tags.findAll();

    cat.forEach(n -> System.out.println(n.toString()));

    return ResponseEntity.status(HttpStatus.OK).body(cat);

  }

  @PostMapping("/tags")
  public ResponseEntity createTags(@RequestBody Tag categoria) {

    var query = this.tags.save(categoria);

    return ResponseEntity.status(HttpStatus.OK).body(query);

  }

  @PostMapping("/tasks")
  public ResponseEntity createTasks(@RequestBody Task tarefa) {

    var query = this.tarefas.save(tarefa);

    return ResponseEntity.status(HttpStatus.OK).body(query);

  }

}
