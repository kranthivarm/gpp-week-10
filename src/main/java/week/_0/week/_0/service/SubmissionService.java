package week._0.week._0.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import week._0.week._0.dto.SubmissionResponseDTO;
import week._0.week._0.exception.ResourceNotFoundException;
import week._0.week._0.model.Submission;
import week._0.week._0.model.SubmissionStatus;
import week._0.week._0.model.Task;
import week._0.week._0.model.User;
import week._0.week._0.repository.SubmissionRepository;
import week._0.week._0.repository.TaskRepository;
import week._0.week._0.repository.UserRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

//    public Submission submitTask(Long userId, Long taskId) {
//
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
//
//        Task task = taskRepository.findById(taskId)
//                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
//
//        Submission submission = new Submission();
//        submission.setUser(user);
//        submission.setTask(task);
//        submission.setStatus(SubmissionStatus.UNDER_REVIEW);
//        submission.setSubmittedAt(LocalDateTime.now());
//
//        return submissionRepository.save(submission);
//    }
public SubmissionResponseDTO submitTask(Long userId, Long taskId) {

    User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

    Task task = taskRepository.findById(taskId)
            .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

    Submission submission = new Submission();
    submission.setUser(user);
    submission.setTask(task);
    submission.setStatus(SubmissionStatus.UNDER_REVIEW);
    submission.setSubmittedAt(LocalDateTime.now());

    Submission saved = submissionRepository.save(submission);

    return new SubmissionResponseDTO(
            saved.getId(),
            user.getId(),
            task.getId(),
            saved.getScore(),
            saved.getStatus(),
            saved.getSubmittedAt()
    );
}

}
