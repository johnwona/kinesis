package com.br.notificacao.api.kinesis.consumer;

import org.springframework.beans.factory.annotation.Lookup;

import software.amazon.kinesis.processor.ShardRecordProcessor;
import software.amazon.kinesis.processor.ShardRecordProcessorFactory;

public class ScoreRecordProcessorFactory implements ShardRecordProcessorFactory {

	@Override
	@Lookup
	public ShardRecordProcessor shardRecordProcessor() {	
		  return null;	
		
	}

}
