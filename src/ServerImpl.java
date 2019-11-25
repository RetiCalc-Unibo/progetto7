/**
 * ServerCongressoImpl.java
 * 		Implementazione del server
 * */

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements
    Server {
  // Costruttore
  public ServerImpl() throws RemoteException {
    super();
  }

  // Avvio del Server RMI
  public static void main(String[] args) {

    int registryRemotoPort = 1099;
    String registryRemotoName = "RegistryRemoto";
    String serviceName = "Server";

    // Controllo dei parametri della riga di comando
    if (args.length != 1 && args.length != 2) {
      System.out
          .println("Sintassi: ServerImpl NomeHostRegistryRemoto [registryPort], registryPort intero");
      System.exit(1);
    }
    String registryRemotoHost = args[0];
    if (args.length == 2) {
      try {
        registryRemotoPort = Integer.parseInt(args[1]);
      } catch (Exception e) {
        System.out
            .println("Sintassi: ServerImpl NomeHostRegistryRemoto [registryPort], registryPort intero");
        System.exit(2);
      }
    }

    // Impostazione del SecurityManager
    if (System.getSecurityManager() == null) {
      System.setSecurityManager(new RMISecurityManager());
    }

    // Registrazione del servizio RMI
    String completeRemoteRegistryName = "//" + registryRemotoHost + ":"
        + registryRemotoPort + "/" + registryRemotoName;

    try {
      RegistryRemotoServer registryRemoto = (RegistryRemotoServer) Naming
          .lookup(completeRemoteRegistryName);
      ServerImpl serverRMI = new ServerImpl();
      registryRemoto.aggiungi(serviceName, serverRMI);
      System.out.println("Server RMI: Servizio \"" + serviceName
          + "\" registrato");
    } catch (Exception e) {
      System.err.println("Server RMI \"" + serviceName + "\": "
          + e.getMessage());
      e.printStackTrace();
      System.exit(1);
    }
  }
}