package com.jsp.ets.batch;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
	public ResponseEntity<ResponseStructure<BatchResponse>> saveBatch(@RequestBody@Valid BatchRequest batchRequest) {
		BatchResponse batchResponse = batchService.saveBranch(batchRequest);
		return builder.success(HttpStatus.CREATED, "Batch created", batchResponse);
	}

}
