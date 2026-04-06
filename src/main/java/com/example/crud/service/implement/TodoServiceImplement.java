package com.example.crud.service.implement;

import com.example.crud.dto.RequestTodoDto;
import com.example.crud.dto.TodoDto;
import com.example.crud.entity.Todo;
import com.example.crud.repository.TodoRepo;
import com.example.crud.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImplement implements TodoService {

    private final TodoRepo todoRepo;
    private final ModelMapper modelMapper;

    @Override
    public List<TodoDto> getAllTodos() {
        List<Todo> todoList = todoRepo.findAll();
        return todoList
                .stream()
                .map(todo -> modelMapper.map(todo, TodoDto.class))
                .toList();
//        todo -> new TodoDto(todo.getId(), todo.getTitle())
    }

    @Override
    public void createNewTodo(String title) {
        Todo todo = new Todo();
        todo.setTitle(title);
        todoRepo.save(todo);
    }

    @Override
    public void deleteTodoById(Long id) {
        if(!todoRepo.existsById(id)){
            throw new IllegalArgumentException("Todo does not exists by id: " + id);
        }
        todoRepo.deleteById(id);
    }

    @Override
    public void updateTodo(Long id, String title) {
        Todo todo = todoRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo not found with id: " + id));
        todo.setTitle(title);
        todoRepo.save(todo);
    }
}
