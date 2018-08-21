package br.ufrn.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import br.ufrn.server.RmiConfiguration;

public class ServiceLocator {

	public static <T> T lookupFor(String rmiUrl) throws RMIException{
		
		try {
			return (T) Naming.lookup(RmiConfiguration.URL_COMPUTE_ENGINE);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {			
			throw new RMIException("Não foi possível efetuar o lookup ao serviço remoto.", e);
		}
		
	}
}
