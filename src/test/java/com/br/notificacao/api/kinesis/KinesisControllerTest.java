package com.br.notificacao.api.kinesis;

import com.br.notificacao.api.dtos.RequestSolicitacaoScorePOST;
import com.br.notificacao.api.kinesis.service.KinesisProducerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class KinesisControllerTest {
	
	 @Mock
	    private KinesisProducerService kinesisProducerService;

	    @InjectMocks
	    private KinesisController kinesisController;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.initMocks(this);
	    }

	    @Test
	    void testEnviaDadosStream() throws InterruptedException, UnsupportedEncodingException {
	        // Mocking
	        RequestSolicitacaoScorePOST mensagem = new RequestSolicitacaoScorePOST();
	        doNothing().when(kinesisProducerService).enviaRecord(any(RequestSolicitacaoScorePOST.class));

	        // Test
	        ResponseEntity<Object> responseEntity = kinesisController.enviaDadosStream(mensagem);

	        // Verify
	        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	        assertEquals(mensagem, responseEntity.getBody());
	        verify(kinesisProducerService, times(1)).enviaRecord(mensagem);
	    }

}
