package com.br.notificacao.api.kinesis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import software.amazon.awssdk.awscore.client.builder.AwsClientBuilder;
import software.amazon.awssdk.services.kinesis.KinesisAsyncClient;
import software.amazon.awssdk.services.kinesis.KinesisAsyncClientBuilder;
import software.amazon.awssdk.utils.builder.Buildable;
import software.amazon.kinesis.common.ConfigsBuilder;
import software.amazon.kinesis.coordinator.Scheduler;
import com.br.notificacao.api.kinesis.consumer.SchedulerBuilder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SchedulerBuilderTest {
	private SchedulerBuilder schedulerBuilder;

    @BeforeEach
    void setUp() {
        schedulerBuilder = new SchedulerBuilder();
    }

    @SuppressWarnings("unchecked")
	@Test
    void testBuildScheduler() {
        // Mocking
        KinesisAsyncClient kinesisClientMock = mock(KinesisAsyncClient.class);
        when(KinesisAsyncClient.builder()).thenReturn(KinesisAsyncClient.builder());
        ((Buildable) when(((AwsClientBuilder<KinesisAsyncClientBuilder, KinesisAsyncClient>) kinesisClientMock).region(any())).thenReturn(KinesisAsyncClient.builder())).build();

        // Test
        Scheduler scheduler = schedulerBuilder.buildScheduler("accessKey", "secretKey");

        verify(kinesisClientMock);
		// Verify
        KinesisAsyncClient.builder();
    }
}
