package br.ufrn.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Implementa��o do excecutor de tarefas.
 * 
 * @author Daniel Smith
 *
 */
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
			throw new IllegalArgumentException("Argumento task n�o pode ser nulo");
		}
		
		return task.execute();
	}

	/**
	 * M�todo f�brica est�tico de visibilidade protected para permitir instancia��o do compute engine apenas no servidor.
	 * 
	 * @return
	 * @throws RemoteException
	 */
	
	protected static ComputeEngine createComputeEngine() throws RemoteException {
		return new ComputeEngine();
	}

}
