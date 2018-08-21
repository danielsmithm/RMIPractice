package br.ufrn.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface para o executor de tarefas. 
 * 
 * @author Daniel Smith
 *
 */
public interface Compute extends Remote {
	<T> T executeTask(Task<T> task) throws RemoteException;
}
