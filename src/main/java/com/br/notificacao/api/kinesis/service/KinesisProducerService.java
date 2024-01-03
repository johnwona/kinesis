package com.br.notificacao.api.kinesis.service;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.UUID;

import org.apache.commons.lang.math.RandomUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.kinesis.producer.KinesisProducer;
import com.br.notificacao.api.dtos.RequestSolicitacaoScorePOST;
import com.br.notificacao.api.dtos.SolicitacaoScoreDTO;
import com.br.notificacao.api.kinesis.config.AwsKinesisClientConfig;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.auth.credentials.SystemPropertyCredentialsProvider;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kinesis.KinesisAsyncClient;
import software.amazon.awssdk.services.kinesis.model.PutRecordRequest;
import software.amazon.kinesis.common.KinesisClientUtil;

@Service
@Slf4j
public class KinesisProducerService {

	@Autowired
	private AwsKinesisClientConfig config;

	public void enviaRecord(RequestSolicitacaoScorePOST mensagem) throws UnsupportedEncodingException {

		System.setProperty("aws.accessKeyId", "AKIAXFU6OHZF47HGRZYU");
		System.setProperty("aws.secretAccessKey", "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");

		String schedulerId = UUID.randomUUID().toString();
		Region region = Region.US_EAST_1;

		SystemPropertyCredentialsProvider credentialsProvider = SystemPropertyCredentialsProvider.create();

		KinesisAsyncClient kinesisClient = KinesisClientUtil.createKinesisAsyncClient(
				KinesisAsyncClient.builder().region(region).credentialsProvider(credentialsProvider));

	//	KinesisProducer kinesis = new KinesisProducer(config.getKinesisClientConfig());

		for (SolicitacaoScoreDTO req : mensagem.getSolicitacaoScore()) {

			JSONObject json = new JSONObject();
			json.put("idSolicitacao", req.getIdSolicitacao());
			json.put("idNota", req.getIdNota());
			json.put("conteudoNota", req.getConteudoNota());
			json.put("conteudoDevedor", req.getConteudoDevedor());
			json.put("conteudoCredor", req.getConteudoCredor());

			System.out.println("Adicionando mensagem -> " + json.toString());
			SdkBytes data = SdkBytes.fromByteBuffer(ByteBuffer.wrap(json.toString().getBytes()));
			kinesisClient.putRecord(PutRecordRequest.builder().streamName("PDD_Mock_Solicitacao").data(data)
			.partitionKey(UUID.randomUUID().toString()).build()).join();

		}

	}
}
