package week._0.week._0.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import week._0.week._0.model.Task;
import week._0.week._0.model.User;


public interface TaskRepository extends JpaRepository<Task, Long> {}