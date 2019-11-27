/**
 * ServerImpl.java
 * 		Implementazione del ServerImpl
 * */

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements
    Server {
  static Programma prog[];

  // Costruttore
  public ServerImpl() throws RemoteException {
    super();
  }

  // Richiede una prenotazione
  public int registrazione(int giorno, String sessione, String speaker)
      throws RemoteException {
    int numSess = -1;
    System.out.println("ServerImpl RMI: richiesta registrazione con parametri");
    System.out.println("giorno   = " + giorno);
    System.out.println("sessione = " + sessione);
    System.out.println("speaker  = " + speaker);

    if (sessione.startsWith("S")) {
      try {
        numSess = Integer.parseInt(sessione.substring(1)) - 1;
      } catch (NumberFormatException e) {
      }
    }

    // Se i dati sono sbagliati significa che sono stati trasmessi male e quindi
    // solleva una eccezione
    if (numSess == -1)
      throw new RemoteException();
    if (giorno < 1 || giorno > 3)
      throw new RemoteException();

    return prog[giorno - 1].registra(numSess, speaker);
  }

  // Ritorno il campo
  public Programma programma(int giorno) throws RemoteException {
    System.out.println("ServerImpl RMI: richiesto programma del giorno " + giorno);
    return prog[giorno - 1];
  }

  // Avvio del ServerImpl RMI
  public static void main(String[] args) {

    // creazione programma
    prog = new Programma[3];
    for (int i = 0; i < 3; i++)
      prog[i] = new Programma();
    int registryRemotoPort = 1099;
    String registryRemotoName = "RegistryRemoto";
    String serviceName = null;

    // Controllo dei parametri della riga di comando
    if (args.length != 2 && args.length != 3) {
      System.out
          .println("Sintassi: ServerImpl NomeServizio NomeHostRegistryRemoto [registryPort], registryPort intero");
      System.exit(1);
    }
    serviceName = args[0];
    String registryRemotoHost = args[1];
    if (args.length == 3) {
      try {
        registryRemotoPort = Integer.parseInt(args[1]);
      } catch (Exception e) {
        System.out
            .println("Sintassi: ServerImpl NomeServizio NomeHostRegistryRemoto [registryPort], registryPort intero");
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
      RegistryRemotoTagServer registryRemoto = (RegistryRemotoTagImpl) Naming
          .lookup(completeRemoteRegistryName);
      ServerImpl ServerImplRMI = new ServerImpl();
      registryRemoto.aggiungi(serviceName, ServerImplRMI);
      System.out.println("ServerImpl RMI: Servizio \"" + serviceName
          + "\" registrato");
    } catch (Exception e) {
      System.err.println("ServerImpl RMI \"" + serviceName + "\": "
          + e.getMessage());
      e.printStackTrace();
      System.exit(1);
    }
  }
}