package com.br.notificacao.api.dtos;

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
public class SolicitacaoScoreDTO {

	private String idSolicitacao;

	private String idNota;
	
	private String conteudoNota;
	
	private String conteudoDevedor;
	
	private String conteudoCredor;
 
	
}
