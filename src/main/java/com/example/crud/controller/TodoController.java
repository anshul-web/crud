package com.example.crud.controller;

import com.example.crud.dto.RequestTodoDto;
import com.example.crud.dto.TodoDto;
import com.example.crud.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
//@RequestMapping("/api")
public class TodoController {
    private final TodoService todoService;

    @GetMapping
    public String getTodo(Model model) {
         List<TodoDto> tasks = todoService.getAllTodos();
         model.addAttribute("tasks", tasks);
         return "tasks";
    }

    @PostMapping
    public String createNewTodo(@RequestParam String title){
        todoService.createNewTodo(title);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteTodo(@PathVariable Long id) {
        todoService.deleteTodoById(id);
        return "redirect:/";
    }

    @PutMapping("/update")
    public String updateTodo(@RequestParam Long id, @RequestParam String title) {
        todoService.updateTodo(id, title);
        return "redirect:/";
    }


}
