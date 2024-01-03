package com.br.notificacao.api.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestSolicitacaoScorePOST {

	@JsonProperty(value = "data")
	private List<SolicitacaoScoreDTO> solicitacaoScore;
	
}
