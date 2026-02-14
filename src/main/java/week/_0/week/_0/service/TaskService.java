package week._0.week._0.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import week._0.week._0.model.Task;
import week._0.week._0.repository.TaskRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }
}
