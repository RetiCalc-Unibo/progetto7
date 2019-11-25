package remoteRegister;

import java.rmi.Remote;

//Interfaccia che RINUNISCE lato server e client del remote register
// ----> INTERFACCIA COMPLETA DA IMPLEMENTARE NEL REMOTE REGISTER
public interface RegistryRemotoTagServer extends RegistryRemotoServer, RegistryRemotoTagClient {
	
	public boolean associaTag(String nome_logico_server, Tag tag);
}
