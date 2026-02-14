package week._0.week._0.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import week._0.week._0.model.Submission;
import week._0.week._0.model.SubmissionStatus;
import week._0.week._0.model.Task;
import week._0.week._0.model.User;
import week._0.week._0.repository.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionRepository submissionRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @PostMapping("/submit")
    public ResponseEntity<?> submitTask(
            @RequestParam Long userId,
            @RequestParam Long taskId
    ) {

        User user = userRepository.findById(userId).orElseThrow();
        Task task = taskRepository.findById(taskId).orElseThrow();

        Submission submission = new Submission();
        submission.setUser(user);
        submission.setTask(task);
        submission.setStatus(SubmissionStatus.UNDER_REVIEW);
        submission.setSubmittedAt(LocalDateTime.now());

        submissionRepository.save(submission);

        return ResponseEntity.ok("Task submitted successfully");
    }
}
