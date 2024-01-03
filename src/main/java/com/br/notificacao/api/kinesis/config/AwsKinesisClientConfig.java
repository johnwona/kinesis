package com.br.notificacao.api.kinesis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.kinesis.producer.KinesisProducerConfiguration;

@Configuration
public class AwsKinesisClientConfig {

	@Value("${aws.config.accessKey}")
	private String accessKey;

	@Value("${aws.config.secretKey}")
	private String secretKey;

	public KinesisProducerConfiguration getKinesisClientConfig() {

		BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);

		KinesisProducerConfiguration producerConfig = new KinesisProducerConfiguration()
				.setCredentialsProvider(new AWSStaticCredentialsProvider(awsCredentials))
				.setRegion(Regions.US_EAST_1.getName());

		return producerConfig;
	}

}
