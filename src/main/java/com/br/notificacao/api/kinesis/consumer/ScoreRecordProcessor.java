package com.br.notificacao.api.kinesis.consumer;

import com.br.notificacao.api.dtos.ResponseScoreDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import software.amazon.kinesis.exceptions.ShutdownException;
import software.amazon.kinesis.exceptions.ThrottlingException;
import software.amazon.kinesis.lifecycle.events.InitializationInput;
import software.amazon.kinesis.lifecycle.events.LeaseLostInput;
import software.amazon.kinesis.lifecycle.events.ProcessRecordsInput;
import software.amazon.kinesis.lifecycle.events.ShardEndedInput;
import software.amazon.kinesis.lifecycle.events.ShutdownRequestedInput;
import software.amazon.kinesis.processor.RecordProcessorCheckpointer;
import software.amazon.kinesis.processor.ShardRecordProcessor;
import software.amazon.kinesis.retrieval.KinesisClientRecord;

public class ScoreRecordProcessor implements ShardRecordProcessor {

	private String shardId;

	@Override
	public void initialize(InitializationInput initializationInput) {

		shardId = initializationInput.shardId();
		System.out.println(String.format("Inicializando leitura na shard %s @ sequence: %s", shardId,
				initializationInput.extendedSequenceNumber().toString()));

	}

	@Override
	public void processRecords(ProcessRecordsInput processRecordsInput) {

		ObjectMapper mapper = new ObjectMapper();

		for (KinesisClientRecord record : processRecordsInput.records()) {

			byte[] byteArr = new byte[record.data().remaining()];
			record.data().get(byteArr);
			System.out.println(new String(byteArr));
			try {
				ResponseScoreDTO score = mapper.readValue(new String(byteArr), ResponseScoreDTO.class);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public void leaseLost(LeaseLostInput leaseLostInput) {

	}

	@Override
	public void shardEnded(ShardEndedInput shardEndedInput) {

		System.out.println(String.format("Shard %s chegou ao fim.", shardId));

	}

	@Override
	public void shutdownRequested(ShutdownRequestedInput shutdownRequestedInput) {

	}

}
