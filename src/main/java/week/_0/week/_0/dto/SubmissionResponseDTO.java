package week._0.week._0.dto;

import lombok.*;
import week._0.week._0.model.SubmissionStatus;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionResponseDTO {

    private Long submissionId;
    private Long userId;
    private Long taskId;
    private double score;
    private SubmissionStatus status;
    private LocalDateTime submittedAt;
}
