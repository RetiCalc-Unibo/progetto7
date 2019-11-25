package remoteRegister;

import java.io.Serializable;

public class Tag implements Serializable{
	 
	private static final long serialVersionUID = 1L;
	public String [] tags = new String [10];
	
	public Tag() {
		tags[0] = "Supermercato";
		tags[1] = "Copisteria";
		tags[2] = "Ferramenta";
		tags[3] = "Farmacia";
		tags[4] = "Azienda";
		tags[5] = "Pasticceria";
		tags[6] = "Bar";
		tags[7] = "Ristorante";
		tags[8] = "Hotel";
		tags[9] = "Autolavaggio";
	}
	

}
