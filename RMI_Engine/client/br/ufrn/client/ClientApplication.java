package br.ufrn.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import br.ufrn.client.tasks.Pi;
import br.ufrn.server.Compute;
import br.ufrn.server.RmiConfiguration;

public class ClientApplication {
	
	public static void main(String[] args){
		
		while(true){
			printSupportedTasks();
			
			System.out.print("Digite o número correspondente ao comando: ");
			
			String command = getUserInput();
			
			if(!executeCommand(command)){
				System.out.print("Comando inválido");
				break;
			}			
			
		}
		
	}

	private static boolean executeCommand(String command) {
		for(SupportedTasks task :SupportedTasks.values()){
			
			if(task.getName().equals(command)){
				task.invokeTask();
				return true;
			}
		}
		
		return false;
	}

	private static void printSupportedTasks() {
		for(SupportedTasks task :SupportedTasks.values()){
			System.out.println(String.format("%s - %s ", task.getName(), task.name()));			
		}
		
	}

	private static String getUserInput() {
		try(Scanner scanner = new Scanner(System.in)){
			return scanner.next();
		}		
	}

	private static Compute getComputeEngine() throws NotBoundException, MalformedURLException, RemoteException {
		return (Compute) Naming.lookup(RmiConfiguration.URL_COMPUTE_ENGINE);
	}
	
	public enum SupportedTasks{
		
		PI("1") {
			@Override
			public void invokeTask() {				
				try {
					System.out.println(getComputeEngine().executeTask(new Pi(78888)));
				} catch (RemoteException | MalformedURLException | NotBoundException e) {
					System.out.print(String.format("Erro ao executar chamada ao servidor. Erro: %s", e.getMessage()));					
				}
			}
		};
		
		private String name;		

		private SupportedTasks(String name){
			this.name = name;			
		}

		public String getName() {
			return name;
		}		
		
		public abstract void invokeTask();
		
	}
		
	
}
