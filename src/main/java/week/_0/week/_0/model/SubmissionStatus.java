package week._0.week._0.model;

public enum SubmissionStatus {

    ACTIVE,              // Task assigned but not submitted
    SUBMITTED,           // User submitted
    UNDER_REVIEW,        // Reviewer checking
    CHANGES_REQUESTED,   // Reviewer wants changes
    APPROVED,            // Approved successfully
    OVERDUE              // Deadline passed
}
