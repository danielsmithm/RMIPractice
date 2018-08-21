package br.ufrn.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ServerApplication {
	
	public static void main(String[] args) throws RemoteException, MalformedURLException {
		LocateRegistry.createRegistry(RmiConfiguration.RMI_PORT);
		
		ComputeEngine computeEngine = ComputeEngine.createComputeEngine();
		
		Naming.rebind(RmiConfiguration.URL_COMPUTE_ENGINE, computeEngine);
	}
}
