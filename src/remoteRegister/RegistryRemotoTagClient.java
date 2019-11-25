package remoteRegister;
import java.rmi.RemoteException;

//Interfaccia che aggiunge alla precedente il metodo cercaTag

public interface RegistryRemotoTagClient extends RegistryRemotoClient{
	
	public void cercaTag(String tag) throws RemoteException;

}
