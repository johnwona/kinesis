package com.br.notificacao.api.kinesis;
import com.br.notificacao.api.dtos.ResponseScoreDTO;
import com.br.notificacao.api.kinesis.consumer.ScoreRecordProcessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import software.amazon.kinesis.lifecycle.events.InitializationInput;
import software.amazon.kinesis.lifecycle.events.LeaseLostInput;
import software.amazon.kinesis.lifecycle.events.ProcessRecordsInput;
import software.amazon.kinesis.lifecycle.events.ShardEndedInput;
import software.amazon.kinesis.lifecycle.events.ShutdownRequestedInput;
import software.amazon.kinesis.processor.RecordProcessorCheckpointer;
import software.amazon.kinesis.retrieval.KinesisClientRecord;

import static org.mockito.Mockito.*;

import java.nio.ByteBuffer;
import java.util.Collections;

public class ScoreRecordProcessorTest {
	
	private ScoreRecordProcessor scoreRecordProcessor;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        scoreRecordProcessor = new ScoreRecordProcessor();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testInitialize() {
        InitializationInput initializationInput = mock(InitializationInput.class);
        when(initializationInput.shardId()).thenReturn("shard-1");
        when(initializationInput.extendedSequenceNumber()).thenReturn(null);

        scoreRecordProcessor.initialize(initializationInput);

        // Add assertions or verifications as needed
    }

    @Test
    void testProcessRecords() throws JsonProcessingException {
        KinesisClientRecord record = mock(KinesisClientRecord.class);
        when(record.data()).thenReturn(ByteBuffer.wrap("{'key':'value'}".getBytes()));

        ProcessRecordsInput processRecordsInput = mock(ProcessRecordsInput.class);
        when(processRecordsInput.records()).thenReturn(Collections.singletonList(record));

        scoreRecordProcessor.processRecords(processRecordsInput);

        // Add assertions or verifications as needed
    }


}
