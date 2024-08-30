package com.jsp.ets.batch;

import org.springframework.stereotype.Service;

import com.jsp.ets.mapper.BatchMapper;
import com.jsp.ets.exception.BatchNotFoundByIdException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BatchService {
	
	private BatchRepository batchRepo;
	private BatchMapper batchMapper;
	
	public BatchResponse saveBatch(BatchRequest batchRequest) {
		Batch batch = batchMapper.mapToBatchEntity(batchRequest, new Batch());
		batch = batchRepo.save(batch);
		return batchMapper.mapToBatchResponse(batch);
	}

	public BatchResponse updateBatch(String batchId, @Valid BatchRequest batchRequest) {
		return batchRepo.findById(batchId).map(batch->{
			batchMapper.mapToBatchEntity(batchRequest, batch);
			batch=batchRepo.save(batch);
			return batchMapper.mapToBatchResponse(batch);
		}).orElseThrow(()->new BatchNotFoundByIdException("Failed to update batch"));
	}

}
