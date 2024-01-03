package com.br.notificacao.api.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseScoreDTO {

	private String idTransacao;
	
	private String numeroNfe;
	
	private List<ConstatacaoDTO> constatacao;

	
}
