package com.jsp.ets.batch;

import com.jsp.ets.exception.BatchNotFoundByIdException;
import com.jsp.ets.mapper.BatchMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class BatchService {
	
	private BatchRepository batchRepo;
	private BatchMapper batchMapper;
	
	public BatchResponse saveBatch(BatchRequest batchRequest) {
		Batch batch = batchMapper.mapToBatchEntity(batchRequest, new Batch());
		batch.setStatus(BatchStatus.CREATED);
		batch = batchRepo.save(batch);
		return batchMapper.mapToBatchResponse(batch);
	}

	public BatchResponse updateBatch(String batchId, BatchRequest batchRequest) {
		return batchRepo.findById(batchId).map(batch->{
			batchMapper.mapToBatchEntity(batchRequest, batch);
			batch=batchRepo.save(batch);
			return batchMapper.mapToBatchResponse(batch);
		}).orElseThrow(()->new BatchNotFoundByIdException("Failed to update batch"));
	}

	public BatchResponse updateBatchStatus(String batchId, BatchStatus status) {
		return batchRepo.findById(batchId).map(batch->{
			if(status.equals(BatchStatus.CANCELLED))
				batch.setStatus(BatchStatus.CANCELLED);
			
			else if(status.equals(BatchStatus.CLOSED)) {
				batch.setStatus(status);
				batch.setClosedDate(LocalDate.now());
			}
			batch=batchRepo.save(batch);
			return batchMapper.mapToBatchResponse(batch);
		}).orElseThrow(()->new BatchNotFoundByIdException("Failed to update batch status"));
	}

	

}
