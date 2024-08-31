package com.jsp.ets.batch;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.jsp.ets.user.Student;
import com.jsp.ets.user.Subject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BatchResponse {
	
    @Schema(description = "Unique identifier for the batch", example = "12345")
    private String batchId;

    @Schema(description = "Title of the batch", example = "Java Full Stack Batch")
    private String title;

    @Schema(description = "List of students enrolled in the batch")
    private List<Student> students;

    @Schema(description = "List of subjects covered in the batch", example = "[\"CORE_JAVA\", \"SPRING\", \"HIBERNATE\"]")
    private List<Subject> subjects;

    @Schema(description = "Starting date of the batch", example = "2024-09-01")
    private LocalDate startDate;

    @Schema(description = "Date when the batch was closed", example = "2024-12-01")
    private LocalDate closedDate;

    @Schema(description = "Time when the batch sessions begin", example = "09:00")
    private LocalTime beginsAt;

    @Schema(description = "Time when the batch sessions end", example = "17:00")
    private LocalTime endsAt;

    @Schema(description = "Current status of the batch", example = "ON_GOING")
    private BatchStatus status;
}
