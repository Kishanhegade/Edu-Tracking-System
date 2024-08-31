package com.jsp.ets.batch;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ets.utility.AppResponseBuilder;
import com.jsp.ets.utility.ErrorStructure;
import com.jsp.ets.utility.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@Tag(name = "Batch Controller", description = "APIs for managing batches.")
public class BatchController {

	private AppResponseBuilder builder;
	private BatchService batchService;

	@Operation(summary = "Create a new batch")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Batch created",
					content = {@Content(mediaType = "application/json",
					schema = @Schema(implementation = BatchResponse.class))
			}),
			@ApiResponse(responseCode = "400", description = "Bad Request, invalid inputs", content = @Content(schema = @Schema(anyOf = ErrorStructure.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(anyOf = RuntimeException.class)))
	})
	@PostMapping("/batches")
	public ResponseEntity<ResponseStructure<BatchResponse>> saveBatch(@RequestBody @Valid BatchRequest batchRequest) {
		BatchResponse batchResponse = batchService.saveBatch(batchRequest);
		return builder.success(HttpStatus.CREATED, "Batch created", batchResponse);
	}

	@Operation(summary = "Update an existing batch")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Batch updated",
					content = {@Content(mediaType = "application/json",
					schema = @Schema(implementation = BatchResponse.class))})
	})
	@PutMapping("/batches/{batchId}")
	public ResponseEntity<ResponseStructure<BatchResponse>> updateBatch(@RequestBody @Valid BatchRequest batchRequest,@PathVariable String batchId) {
		BatchResponse batchResponse = batchService.updateBatch(batchId, batchRequest);
		return builder.success(HttpStatus.OK, "batch updated", batchResponse);
	}

	@Operation(summary = "Update batch status to Cancelled")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Batch status updated to Cancelled",
					content = {@Content(mediaType = "application/json",
					schema = @Schema(implementation = BatchResponse.class))}),
			@ApiResponse(responseCode = "400", description = "Bad Request, invalid inputs", content = @Content(mediaType = "application/json",schema = @Schema(anyOf = ErrorStructure.class))),
			@ApiResponse(responseCode = "404", description = "Batch not found", content = @Content(mediaType = "application/json",schema = @Schema(anyOf = ErrorStructure.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json",schema = @Schema(anyOf = RuntimeException.class)))

	})
	@PatchMapping("/batches/{batchId}/cancel")
	public ResponseEntity<ResponseStructure<BatchResponse>> updateBatchStatusToCancelled(@PathVariable String batchId) {
		BatchResponse batchResponse = batchService.updateBatchStatus(batchId, BatchStatus.CANCELLED);
		return builder.success(HttpStatus.OK, "batch status updated to cancelled", batchResponse);
	}

	@Operation(summary = "Update batch status to Closed")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Batch status updated to Closed",
					content = {@Content(mediaType = "application/json",
					schema = @Schema(implementation = BatchResponse.class))}),
			@ApiResponse(responseCode = "400", description = "Bad Request, invalid inputs", content = @Content(mediaType = "application/json",schema = @Schema(anyOf = ErrorStructure.class))),
			@ApiResponse(responseCode = "404", description = "Batch not found", content = @Content(mediaType = "application/json",schema = @Schema(anyOf = ErrorStructure.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json",schema = @Schema(anyOf = RuntimeException.class)))

	})
	@PatchMapping("/batches/{batchId}/close")
	public ResponseEntity<ResponseStructure<BatchResponse>> updateBatchStatusToClosed(@PathVariable String batchId) {
		BatchResponse batchResponse = batchService.updateBatchStatus(batchId, BatchStatus.CLOSED);
		return builder.success(HttpStatus.OK, "Batch status updated to closed", batchResponse);
	}

}
