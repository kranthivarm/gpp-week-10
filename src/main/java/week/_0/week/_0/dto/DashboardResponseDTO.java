package week._0.week._0.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardResponseDTO {

    private double totalScore;
    private double averageScore;
    private long completedTasks;
    private long underReview;
    private long changesRequested;
}
