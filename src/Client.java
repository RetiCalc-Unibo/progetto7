
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;

class Client {

  // Avvio del Client RMI
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		int registryRemotoPort = 1099;
		String registryRemotoHost = null;
		String registryRemotoName = "RegistryRemoto";
		String serviceName = "ServerCongresso";
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

		// Controllo dei parametri della riga di comando
		if (args.length != 1 && args.length != 2) {
			System.out.println("Sintassi: ClientCongresso NomeHostRegistryRemoto [registryPort], registryPort intero");
			System.exit(1);
		}
		registryRemotoHost = args[0];
		if (args.length == 2) {
			try {
				registryRemotoPort = Integer.parseInt(args[1]);
			} catch (Exception e) {
				System.out
					.println("Sintassi: ClientCongresso NomeHostRegistryRemoto [registryPort], registryPort intero");
				System.exit(1);
			}
		}
		
		// 	Impostazione del SecurityManager
		if (System.getSecurityManager() == null)
			System.setSecurityManager(new RMISecurityManager());

		// Connessione al servizio RMI remoto
		try {
			String completeRemoteRegistryName = "//" + registryRemotoHost + ":"
					+ registryRemotoPort + "/" + registryRemotoName;
			RegistryRemotoClient registryRemoto = 
					(RegistryRemotoClient) Naming.lookup(completeRemoteRegistryName);
			Server serverRMI = 
					(Server) registryRemoto.cerca(serviceName);
			System.out.println("ClientRMI: Servizio \"" + serviceName + "\" connesso");
			
			System.out.println("\nRichieste di servizio fino a fine file");
			
			String service;
			System.out.print("Servizio (R=Registrazione, P=Programma del congresso): ");
			
			while ((service = stdIn.readLine()) != null) {
				
			} // !EOF richieste utente

		} catch (Exception e) {
			System.err.println("ClientRMI: " + e.getMessage());
			e.printStackTrace();
			System.exit(2);
		}
	}
}