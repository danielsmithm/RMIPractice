package br.ufrn.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import br.ufrn.client.tasks.Fatorial;
import br.ufrn.client.tasks.MDC;
import br.ufrn.client.tasks.Pi;
import br.ufrn.server.Compute;
import br.ufrn.server.RmiConfiguration;

/**
 * Classe príncipal da aplicação cliente.
 * 
 * @author Daniel Smith 
 *
 */
public class ClientApplication {
	
	public static void main(String[] args) throws Exception{
		
		while(true){
			printSupportedTasks();
			
			System.out.print("Digite o número correspondente ao comando: ");
			
			String command = getUserInput();
			
			if(!executeTask(command)){
				System.out.print("Comando inválido");
				break;
			}			
			
		}
		
	}

	/**
	 * Executa a tarefa com o código passado.
	 * 
	 * @param code
	 * @return
	 * @throws Exception 
	 */
	private static boolean executeTask(String code) throws Exception {
		for(SupportedTasks task :SupportedTasks.values()){
			
			if(task.getCode().equals(code)){
				
				try {
					task.invokeTask();
				} catch (Exception e) {
					System.out.print(String.format("Erro ao executar chamada ao servidor. Erro: %s", e.getMessage()));
					throw e;
				}
				
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Imprime no console todas as tarefas possíveis de serem executadas.
	 * 
	 */
	private static void printSupportedTasks() {
		for(SupportedTasks task :SupportedTasks.values()){
			System.out.println(String.format("%s - %s ", task.getCode(), task.name()));			
		}
		
	}

	/**
	 * Retorna um valor do console.
	 * 
	 * @return
	 */
	private static String getUserInput() {
		try(Scanner scanner = new Scanner(System.in)){
			return scanner.next();
		}		
	}

	/**
	 * Efetua o lookup da instancia do compute engine no RMI Registry.
	 * 
	 * @return
	 * @throws RMIException 
	 * @throws NotBoundException
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	private static Compute lookupForComputeEngine() throws RMIException  {
		return (Compute) ServiceLocator.lookupFor(RmiConfiguration.URL_COMPUTE_ENGINE);
	}
	
	private static int getUserInputForInt() {
		String userInput = getUserInput();
			
		if(userInput == null  || !userInput.matches("\\d{1,10}")) {
			System.out.print("Digite um inteiro positivo.");
		}
			
		final int parseInt = Integer.parseInt(userInput);
		return parseInt;
	}
	
	/**
	 * Enumeração das tarefas que é possível executar e a implementação da sua execução.
	 * 
	 */
	public enum SupportedTasks{
		
		PI("1") {
			@Override
			public void invokeTask() throws Exception {
				System.out.print("Digite um inteiro positivo.");
				String userInput = getUserInput();
					
				if(userInput == null  || !userInput.matches("\\d{1,10}")) {
					System.out.print("Numero invalido.");
				}
					
				System.out.println(lookupForComputeEngine().executeTask(new Pi(Integer.parseInt(userInput))));				
			}
		},
		
		FATORIAL("2"){
			@Override
			public void invokeTask() throws Exception {
				System.out.print("Digite um inteiro positivo.");
				
				final int firstNumber = getUserInputForInt();		
				
				System.out.println(lookupForComputeEngine().executeTask(new Fatorial(firstNumber)));				
			}
			
		},
		
		MDC("3"){
			@Override
			public void invokeTask() throws Exception {
				System.out.print("Digite o primeiro inteiro positivo.");
				
				final Long firstNumber = Long.valueOf( getUserInputForInt());
				
				System.out.print("Digite um segundo inteiro positivo.");
						
				final Long secondNumber = Long.valueOf(getUserInputForInt());
				
				System.out.println(lookupForComputeEngine().executeTask(new MDC(firstNumber, secondNumber)));				
			}
			
		};
		
		
		
		private String code;		

		private SupportedTasks(String name){
			this.code = name;			
		}

		public String getCode() {
			return code;
		}		
		
		public abstract void invokeTask() throws Exception;
		
	}
	
}
