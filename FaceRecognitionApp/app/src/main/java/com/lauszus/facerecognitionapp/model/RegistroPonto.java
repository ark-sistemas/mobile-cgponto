package com.lauszus.facerecognitionapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistroPonto {
	
	private Long idRegistro;
	private String data;
	private Long idFuncionario;
	private Long idcodigoJornadaTrabalho;
	private String primeiraEntrada;
	private String email;
	private String primeiraSaida;
	private String segundaEntrada;
	private String segundaSaida;
	private Long saldo;
	
	
}