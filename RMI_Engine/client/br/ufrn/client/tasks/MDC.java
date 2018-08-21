package br.ufrn.client.tasks;

import java.io.Serializable;

import br.ufrn.server.Task;

public class MDC implements Task<Long>, Serializable {

	private Long secondNumber;
	private Long firstNumber;

	public MDC(Long firstNumber, Long secondNumber) {
		this.firstNumber = firstNumber;
		this.secondNumber = secondNumber;
	}
	
	@Override
	public Long execute() {
		Long rest = null;
		
		Long num1 = firstNumber;
		Long num2 = secondNumber;
		
		while(rest != 0l || rest != null) {
		    rest = num1 % num2;
		    num1  = num2;
		    num2  = rest;
		}
		
		return num1;
	}

}
