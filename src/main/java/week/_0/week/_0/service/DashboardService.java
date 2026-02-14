package week._0.week._0.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import week._0.week._0.model.Submission;
import week._0.week._0.model.SubmissionStatus;
import week._0.week._0.repository.SubmissionRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final SubmissionRepository submissionRepository;

    public Map<String, Object> getDashboard(Long userId) {

        List<Submission> submissions =
                submissionRepository.findByUserId(userId);

        double totalScore = submissions.stream()
                .filter(s -> s.getStatus() == SubmissionStatus.APPROVED)
                .mapToDouble(Submission::getScore)
                .sum();

        long completed = submissions.stream()
                .filter(s -> s.getStatus() == SubmissionStatus.APPROVED)
                .count();

        long underReview = submissions.stream()
                .filter(s -> s.getStatus() == SubmissionStatus.UNDER_REVIEW)
                .count();

        long changesRequested = submissions.stream()
                .filter(s -> s.getStatus() == SubmissionStatus.CHANGES_REQUESTED)
                .count();

        double avgScore = submissions.stream()
                .mapToDouble(Submission::getScore)
                .average()
                .orElse(0);

        Map<String, Object> result = new HashMap<>();
        result.put("totalScore", totalScore);
        result.put("completed", completed);
        result.put("underReview", underReview);
        result.put("changesRequested", changesRequested);
        result.put("averageScore", avgScore);

        return result;
    }
}
