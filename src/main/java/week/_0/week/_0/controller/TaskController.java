package week._0.week._0.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import week._0.week._0.model.Task;
import week._0.week._0.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }
}
