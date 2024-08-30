package com.jsp.ets.mapper;

import org.springframework.stereotype.Component;

import com.jsp.ets.batch.Batch;
import com.jsp.ets.batch.BatchRequest;
import com.jsp.ets.batch.BatchResponse;

@Component
public class BatchMapper {
	
	public Batch mapToBatchEntity(BatchRequest batchRequest, Batch batch) {
		batch.setTitle(batchRequest.getTitle());
		batch.setSubjects(batchRequest.getSubjects());
		return batch;
	}

	public BatchResponse mapToBatchResponse(Batch batch) {
		BatchResponse batchResponse = new BatchResponse();
		batchResponse.setBatchId(batch.getBatchId());
		batchResponse.setTitle(batch.getTitle());
		batchResponse.setSubjects(batch.getSubjects());
		batchResponse.setCreatedDate(batch.getCreatedDate());
		batchResponse.setClosedDate(batch.getClosedDate());
		batchResponse.setStudents(batch.getStudents());
		return batchResponse;
	}
}
