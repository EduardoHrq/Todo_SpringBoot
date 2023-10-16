package com.todolist.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.todolist.model.Tag;
import com.todolist.model.Task;
import com.todolist.repository.TagRepo;
import com.todolist.repository.TaskRepo;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class Controle {

  @Autowired
  private TaskRepo tarefa;

  @Autowired
  private TagRepo categoria;

  @RequestMapping
  public String home(Model modelo) {

    var query1 = this.tarefa.findAll();

    var query2 = this.categoria.findAll();

    modelo.addAttribute("tarefas", query1);
    modelo.addAttribute("categorias", query2);

    return "index";
  }

  @RequestMapping("/filter")
  public String filter(Model modelo, @RequestParam("tag") String tag) {

    var query = this.categoria.findByName(tag);

    var query1 = this.categoria.findAll();

    var query2 = this.tarefa.findByTag(query);


    modelo.addAttribute("tarefas", query2);
    modelo.addAttribute("categorias", query1);
    modelo.addAttribute("tag", tag);

    return "index";
  }

  @RequestMapping("/tags")
  public String tag() {
    return "addTag";
  }

  @PostMapping("/data/create")
  public String create(@ModelAttribute Task tarefa, HttpServletRequest request, Tag tags) {

    System.out.println(tarefa);

    var tagName = request.getParameter("tag_name");

    if (!tagName.isEmpty()) {
      var resultado = this.categoria.findByName(tagName);

      if (resultado == null) {
        System.out.println("NOT FOUND");
        tags.setName(tagName);
        var savedTag = this.categoria.save(tags);

        tarefa.setTag(savedTag);
      } else {
        tarefa.setTag(resultado);
      }

      if (!tarefa.getTitle().isEmpty()) {
        this.tarefa.save(tarefa);
      }

    } else if (!tarefa.getTitle().isEmpty()) {
      this.tarefa.save(tarefa);
    }

    return "redirect:/";

  }

}
