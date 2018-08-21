package br.ufrn.client.tasks;

import java.io.Serializable;

import br.ufrn.server.Task;

public class Pi implements Task<Double>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int precision;
	
	public Pi(int precisionInDigits) {
		super();
		this.precision = precisionInDigits;
	}



	@Override
	public Double execute() {
		
		double pi = 0.0;
		
		for ( int i = 0; i < precision; i++){
			pi += Math.pow(-1, 1)/(2*i)+1;
		}		
		
		return Double.valueOf(pi);
	}

}
