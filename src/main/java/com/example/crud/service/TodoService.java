package com.example.crud.service;

import com.example.crud.dto.RequestTodoDto;
import com.example.crud.dto.TodoDto;

import java.util.List;

public interface TodoService {
    List<TodoDto> getAllTodos();
    void createNewTodo(String title);
    void deleteTodoById(Long id);
    void updateTodo(Long id, String title);
}
