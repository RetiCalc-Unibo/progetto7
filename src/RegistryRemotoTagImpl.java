import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


//Remote Registry, implementazione completa
// IMPLEMENTA SOLO LA RegistryRemoteTagServer CHE CONTIENE ANCHE LA PARTE CLIENT

public class RegistryRemotoTagImpl extends UnicastRemoteObject implements
RegistryRemotoTagServer {

// num. entry [nomelogico][ref]
final int tableSize = 100;

// Tabella: la prima colonna contiene i nomi, la seconda i riferimenti remoti, la terza i tag
Object[][] table = new Object[tableSize][3];

static Tag itag;

public RegistryRemotoTagImpl() throws RemoteException {
	super();
	for (int i = 0; i < tableSize; i++) {
		table[i][0] = null;
		table[i][1] = null;
		table[i][2] = null;
	}
}

/** Aggiunge la coppia nella prima posizione disponibile */
public synchronized boolean aggiungi(String nomeLogico, Remote riferimento)
		throws RemoteException {
	// Cerco la prima posizione libera e la riempio
	boolean risultato = false;
	if( (nomeLogico == null) || (riferimento == null) )
		return risultato;
	for (int i = 0; i < tableSize; i++)
		if (table[i][0] == null) {
			table[i][0] = nomeLogico;
			table[i][1] = riferimento;
			risultato = true;
			break;
		}
	return risultato;
}

/** Restituisce il riferimento remoto cercato, oppure null */
public synchronized Remote cerca(String nomeLogico) throws RemoteException {
	Remote risultato = null;
	if( nomeLogico == null ) return null;
	for (int i = 0; i < tableSize; i++)
		if ( nomeLogico.equals((String) table[i][0]) ) {
			risultato = (Remote) table[i][1];
			break;	
		}
	return risultato;
}

/** Restituisce tutti i riferimenti corrispondenti ad un nome logico */
public synchronized Remote[] cercaTutti(String nomeLogico)
		throws RemoteException {
	int cont = 0;
	if( nomeLogico == null ) return new Remote[0];
	for (int i = 0; i < tableSize; i++)
		if ( nomeLogico.equals((String) table[i][0]) )
			cont++;
	Remote[] risultato = new Remote[cont];
	// Ora lo uso come indice per il riempimento
	cont = 0;
	for (int i = 0; i < tableSize; i++)
		if ( nomeLogico.equals((String) table[i][0]) )
			risultato[cont++] = (Remote) table[i][1];
	return risultato;
}

/** Restituisce tutti i riferimenti corrispondenti ad un nome logico */
public synchronized Object[][] restituisciTutti() throws RemoteException {
	int cont = 0;
	for (int i = 0; i < tableSize; i++)
		if (table[i][0] != null) cont++;
	Object[][] risultato = new Object[cont][3];
	// Ora lo uso come indice per il riempimento
	cont = 0;
	for (int i = 0; i < tableSize; i++)
		if (table[i][0] != null) {
			risultato[cont][0] = table[i][0];
			risultato[cont][1] = table[i][1];
			risultato[cont][2] = table[i][2];
		}
	return risultato;
}

/** Elimina la prima entry corrispondente al nome logico indicato */
public synchronized boolean eliminaPrimo(String nomeLogico)
		throws RemoteException {
		boolean risultato = false;
		if( nomeLogico == null ) return risultato;    
		for (int i = 0; i < tableSize; i++)
			if ( nomeLogico.equals((String) table[i][0]) ) {
				table[i][0] = null;
				table[i][1] = null;
				table[i][2] = null;
				risultato = true;
				break;
			}
		return risultato;
}

public synchronized boolean eliminaTutti(String nomeLogico)
		throws RemoteException {
	boolean risultato = false;
	if( nomeLogico == null ) return risultato;    
	for (int i = 0; i < tableSize; i++)
		if ( nomeLogico.equals((String) table[i][0]) ) {
			if (risultato == false)
    	risultato = true;
			table[i][0] = null;
			table[i][1] = null;
			table[i][2] = null;
		}
	return risultato;
}

// Avvio del Server RMI
@SuppressWarnings("deprecation")
public static void main(String[] args) {

	int registryRemotoPort = 1099;
	String registryRemotoHost = "localhost";
	String registryRemotoName = "RegistryRemoto";
	itag = new Tag();
	// Controllo dei parametri della riga di comando
	if (args.length != 0 && args.length != 1) {
		System.out.println("Sintassi: ServerImpl [registryPort]");
		System.exit(1);
	}
	if (args.length == 1) {
		try {
			registryRemotoPort = Integer.parseInt(args[0]);
		} catch (Exception e) {
			System.out
			.println("Sintassi: ServerImpl [registryPort], registryPort intero");
			System.exit(2);
		}
	}

	// Impostazione del SecurityManager
	if (System.getSecurityManager() == null)
		System.setSecurityManager(new RMISecurityManager());

	// Registrazione del servizio RMI
	String completeName = "//" + registryRemotoHost + ":" + registryRemotoPort
			+ "/" + registryRemotoName;
	try {
		RegistryRemotoTagImpl serverRMI = new RegistryRemotoTagImpl();
		Naming.rebind(completeName, serverRMI);
		System.out.println("Server RMI: Servizio \"" + registryRemotoName
				+ "\" registrato");
	} catch (Exception e) {
		System.err.println("Server RMI \"" + registryRemotoName + "\": "
				+ e.getMessage());
		e.printStackTrace();
		System.exit(1);
	}
}



//NUOVI METODI AGGIUNTI 

	public synchronized boolean associaTag(String nome_logico_server, String tag) {
		boolean risultato = false;
		if( (nome_logico_server == null) || !itag.check(tag) ) return risultato;    
		for (int i = 0; i < tableSize; i++)
			if ( nome_logico_server.equals((String) table[i][0]) ) {
				risultato = true;
				table[i][2] = tag;
				break;
			}
		return risultato;
	}
	
	public synchronized String[] cercaTag(String tag) throws RemoteException {
		int cont = 0;
		if( tag == null ) return new String[0];
		for (int i = 0; i < tableSize; i++)
			if ( tag.equals((String) table[i][2]) )
				cont++;
		String[] risultato = new String[cont];
		// Ora lo uso come indice per il riempimento
		cont = 0;
		for (int i = 0; i < tableSize; i++)
			if ( tag.equals((String) table[i][2]) )
				risultato[cont++] = (String) table[i][0];
		return risultato;
	}
}