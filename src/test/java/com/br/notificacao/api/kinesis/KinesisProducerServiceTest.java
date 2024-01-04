package com.br.notificacao.api.kinesis;

import com.br.notificacao.api.dtos.RequestSolicitacaoScorePOST;
import com.br.notificacao.api.kinesis.service.KinesisProducerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import software.amazon.awssdk.services.kinesis.KinesisAsyncClient;
import software.amazon.awssdk.services.kinesis.model.PutRecordRequest;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class KinesisProducerServiceTest {
	
	 @Mock
	    private KinesisAsyncClient kinesisAsyncClient;

	    @InjectMocks
	    private KinesisProducerService kinesisProducerService;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.initMocks(this);
	    }

	    @Test
	    void testEnviaRecord() throws UnsupportedEncodingException {
	        // Mocking
	        RequestSolicitacaoScorePOST mensagem = new RequestSolicitacaoScorePOST();
	        mensagem.setSolicitacaoScore(Collections.emptyList());

	        when(kinesisAsyncClient.putRecord(any(PutRecordRequest.class))).thenReturn(CompletableFuture.completedFuture(null));

	        // Test
	        kinesisProducerService.enviaRecord(mensagem);

	        // Verify
	        verify(kinesisAsyncClient, times(mensagem.getSolicitacaoScore().size())).putRecord(any(PutRecordRequest.class));
	    }

}
