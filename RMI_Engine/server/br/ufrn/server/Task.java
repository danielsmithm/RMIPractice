package br.ufrn.server;

import java.io.Serializable;

public interface Task<T> extends Serializable {
	T execute();
}
