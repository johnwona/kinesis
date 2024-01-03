package com.br.notificacao.api.kinesis.consumer;

import java.util.UUID;

import org.springframework.stereotype.Component;

import software.amazon.awssdk.auth.credentials.SystemPropertyCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudwatch.CloudWatchAsyncClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.kinesis.KinesisAsyncClient;
import software.amazon.kinesis.common.ConfigsBuilder;
import software.amazon.kinesis.common.KinesisClientUtil;
import software.amazon.kinesis.coordinator.Scheduler;

@Component
public class SchedulerBuilder {
	
	public Scheduler buildScheduler(String accessKey, String secretKey) {
				
		System.setProperty("aws.accessKeyId", accessKey);
		System.setProperty("aws.secretAccessKey", secretKey);

		String schedulerId = UUID.randomUUID().toString();
		Region region = Region.US_EAST_1;

		SystemPropertyCredentialsProvider credentialsProvider = SystemPropertyCredentialsProvider.create();

		KinesisAsyncClient kinesisClient = KinesisClientUtil
				.createKinesisAsyncClient(KinesisAsyncClient.builder().region(region).credentialsProvider(credentialsProvider));

		DynamoDbAsyncClient dynamoClient = DynamoDbAsyncClient.builder().region(region).build();

		CloudWatchAsyncClient cwClient = CloudWatchAsyncClient.builder().region(region).build();

		ScoreRecordProcessorFactory scoreProcessorFactory = new ScoreRecordProcessorFactory();

		ConfigsBuilder configsBuilder = new ConfigsBuilder("PDD_Mock_Score", "PDDKinesis", kinesisClient, dynamoClient, cwClient,
				schedulerId, scoreProcessorFactory);

		Scheduler scheduler = new Scheduler(configsBuilder.checkpointConfig(), configsBuilder.coordinatorConfig(),
				configsBuilder.leaseManagementConfig(), configsBuilder.lifecycleConfig(),
				configsBuilder.metricsConfig(), configsBuilder.processorConfig(), configsBuilder.retrievalConfig());

		return scheduler;

	}

}
