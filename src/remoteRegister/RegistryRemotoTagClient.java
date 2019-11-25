package remoteRegister;
import java.rmi.Remote;
import java.rmi.RemoteException;

//Interfaccia che aggiunge alla precedente il metodo cercaTag

public interface RegistryRemotoTagClient extends RegistryRemotoClient{
	
	public Remote[] cercaTag(Tag tag) throws RemoteException;

}
