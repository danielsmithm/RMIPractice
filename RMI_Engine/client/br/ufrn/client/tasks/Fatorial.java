package br.ufrn.client.tasks;

import java.io.Serializable;

import br.ufrn.server.Task;

public class Fatorial  implements Task<Long>, Serializable {

	
	private int number;

	public Fatorial(int number) {
		this.number = number;
	}

	@Override
	public Long execute() {

		long result = 1;

        for (int factor = 2; factor <= number; factor++) {
            result *= factor;
        }
		
		return result;
	}

}
