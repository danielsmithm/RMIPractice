package br.ufrn.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ComputeEngine extends UnicastRemoteObject implements Compute {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected ComputeEngine() throws RemoteException {
		super();
	}

	@Override
	public <T> T executeTask(Task<T> task) {

		if(task == null){
			throw new IllegalArgumentException("Argumento task não pode ser nulo");
		}
		
		return task.execute();
	}

	static ComputeEngine createComputeEngine() throws RemoteException {
		return new ComputeEngine();
	}

}
