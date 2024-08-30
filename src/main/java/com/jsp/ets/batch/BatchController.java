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
import com.jsp.ets.utility.ResponseStructure;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class BatchController {
	
	private AppResponseBuilder builder;
	private BatchService batchService;
	
	@PostMapping("/batches")
	public ResponseEntity<ResponseStructure<BatchResponse>> saveBatch(@RequestBody @Valid BatchRequest batchRequest) {
		BatchResponse batchResponse = batchService.saveBatch(batchRequest);
		return builder.success(HttpStatus.CREATED, "Batch created", batchResponse);
	}

	@PutMapping("/batches/{batchId}")
	public ResponseEntity<ResponseStructure<BatchResponse>> updateBatch(@RequestBody @Valid BatchRequest batchRequest,@PathVariable String batchId) {
		BatchResponse batchResponse = batchService.updateBatch(batchId, batchRequest);
		return builder.success(HttpStatus.OK, "batch updated", batchResponse);
	}
	
	@PatchMapping("/batches/{batchId}/cancelled")
	public ResponseEntity<ResponseStructure<BatchResponse>> updateBatchStatusToCancelled(@PathVariable String batchId) {
		BatchResponse batchResponse = batchService.updateBatchStatus(batchId, BatchStatus.CANCELLED);
		return builder.success(HttpStatus.OK, "batch status updated to cancelled", batchResponse);
	}
	
	@PatchMapping("/batches/{batchId}/closed")
	public ResponseEntity<ResponseStructure<BatchResponse>> updateBatchStatusToClosed(@PathVariable String batchId) {
		BatchResponse batchResponse = batchService.updateBatchStatus(batchId, BatchStatus.CLOSED);
		return builder.success(HttpStatus.OK, "batch status updated to closed", batchResponse);
	}
	
}
