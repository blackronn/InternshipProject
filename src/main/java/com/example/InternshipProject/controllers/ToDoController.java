package com.example.InternshipProject.controllers;

import com.example.InternshipProject.entities.concretes.Todo;
import com.example.InternshipProject.repositories.TodoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin
public class ToDoController {

    private final TodoRepository todoRepo;

    public ToDoController(TodoRepository todoRepo) {
        this.todoRepo = todoRepo;
    }

    @GetMapping("/byEmail/{email}")
    public List<Todo> getTodos(@PathVariable String email) {
        return todoRepo.findByInternEmail(email);
    }

    @PostMapping
    public Todo addTodo(@RequestBody Todo todo) {
        return todoRepo.save(todo);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        todoRepo.deleteById(id);
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Long id, @RequestBody Todo updated) {
        Todo existing = todoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
        existing.setDone(updated.isDone());
        return todoRepo.save(existing);
    }
}
