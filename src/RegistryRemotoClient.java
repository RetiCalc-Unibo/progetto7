/**
 *  Interfaccia remota Registry per il Cliente.
 *  	Cerca = ottiene il primo riferimento al server registrato con 'nomeLogico'.
 *  	CercaTutti = ottiene tutti i riferimenti ai server registrati con 'nomeLogico'.
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

//Interfaccia base del remote registry verso il client

public interface RegistryRemotoClient extends Remote {
	public Remote cerca(String nomeLogico) throws RemoteException;
	public Remote[] cercaTutti(String nomeLogico) throws RemoteException;
}
