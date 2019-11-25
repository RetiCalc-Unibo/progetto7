import java.rmi.Remote;
import java.rmi.RemoteException;

public class RegistryRemotoTagImpl implements RegistryRemotoTagServer{
	
	public RegistryRemotoTagImpl() {
		
	}

	@Override
	public boolean aggiungi(String nomeLogico, Remote riferimento) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object[][] restituisciTutti() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean eliminaPrimo(String nomeLogico) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminaTutti(String nomeLogico) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Remote cerca(String nomeLogico) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Remote[] cercaTutti(String nomeLogico) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cercaTag(String tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void associaTag(String nome_logico_server, String tag) {
		// TODO Auto-generated method stub
		
	}

}