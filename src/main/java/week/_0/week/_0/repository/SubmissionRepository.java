package week._0.week._0.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import week._0.week._0.model.Submission;
import week._0.week._0.model.Task;
import week._0.week._0.model.User;

import java.util.List;

//interface UserRepository extends JpaRepository<User, Long> {}
//interface TaskRepository extends JpaRepository<Task, Long> {}
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findByUserId(Long userId);
}
