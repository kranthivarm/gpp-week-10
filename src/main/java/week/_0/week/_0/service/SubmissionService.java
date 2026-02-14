package week._0.week._0.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import week._0.week._0.dto.SubmissionResponseDTO;
import week._0.week._0.exception.ResourceNotFoundException;
import week._0.week._0.kafka.producer.NotificationProducer;
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
    private final NotificationProducer notificationProducer;  // ✅ ADDED

    @Transactional
    public SubmissionResponseDTO submitTask(Long userId, Long taskId) {

        // 1️⃣ Validate User
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + userId));

        // 2️⃣ Validate Task
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found with id: " + taskId));

        // 3️⃣ Create Submission
        Submission submission = new Submission();
        submission.setUser(user);
        submission.setTask(task);
        submission.setStatus(SubmissionStatus.UNDER_REVIEW);
        submission.setScore(0.0);
        submission.setSubmittedAt(LocalDateTime.now());

        // 4️⃣ Save
        Submission saved = submissionRepository.save(submission);

        // 5️⃣ 🔥 Publish Kafka Event
        String eventMessage = "User " + user.getId() +
                " submitted task " + task.getId();

        notificationProducer.sendNotificationEvent(eventMessage);

        // 6️⃣ Return Response
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
