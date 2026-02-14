package week._0.week._0.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import week._0.week._0.dto.SubmissionRequestDTO;
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

    @PostMapping

    public ResponseEntity<?> submitTask(@RequestBody SubmissionRequestDTO request) {

        User user = userRepository.findById(request.getUserId()).orElseThrow();
        Task task = taskRepository.findById(request.getTaskId()).orElseThrow();

        Submission submission = new Submission();
        submission.setUser(user);
        submission.setTask(task);
        submission.setStatus(SubmissionStatus.UNDER_REVIEW);
        submission.setSubmittedAt(LocalDateTime.now());

        submissionRepository.save(submission);

        return ResponseEntity.ok("Task submitted successfully");
    }
}
