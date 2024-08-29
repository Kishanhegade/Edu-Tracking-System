package com.jsp.ets.batch;

import org.springframework.stereotype.Service;

import com.jsp.ets.mapper.BatchMapper;


import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BatchService {
	
	private BatchRepository batchRepo;
	private BatchMapper batchMapper;
	
	public BatchResponse saveBranch(BatchRequest batchRequest) {
		Batch batch = batchMapper.mapToBatchEntity(batchRequest, new Batch());
		batch = batchRepo.save(batch);
		return batchMapper.mapToBatchResponse(batch);
	}

}
