package com.jsp.ets.batch;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.jsp.ets.user.Subject;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BatchRequest {
	@NotNull
	@NotEmpty
	@Pattern(regexp = "^[A-Za-z0-9\\s]+$" ,message = "title can have only alphabets")
	@Schema(description = "Title of the batch", example = "Java Full Stack Batch")
	private String title;
	@NotNull
	@NotEmpty
	@Schema(description = "List of subjects included in the batch", example = "[\"CORE_JAVA\", \"SPRING\", \"HIBERNATE\"]")
	private List<Subject> subjects;
	
	@NotNull(message = "starting date cannot be null")
	 @Schema(description = "Starting date of the batch", example = "2024-09-01")
	private LocalDate startDate;
	
	@NotNull(message = "beginsAt cannot be null")
	@Schema(description = "Time when the batch sessions begin", example = "09:00")
	private LocalTime beginsAt;
	
	@NotNull(message = "endsAt cannot be null")
	@Schema(description = "Time when the batch sessions end", example = "17:00")
	private LocalTime endsAt;
	
	
	

}
