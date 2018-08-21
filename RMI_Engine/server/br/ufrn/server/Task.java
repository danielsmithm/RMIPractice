package br.ufrn.server;

import java.io.Serializable;

/**
 * Interface para uma tarefa que dever� ser executada no servidor. 
 * 
 * @author Daniel Smith
 *
 * @param <T>
 */
public interface Task<T> extends Serializable {
	T execute();
}
