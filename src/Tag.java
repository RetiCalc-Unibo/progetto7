public class Tag {
	public String [] tags = new String [10];
	
	public Tag() {
		tags[0] = "Bologna";
		tags[1] = "Milano";
		tags[2] = "Roma";
		tags[3] = "Torino";
		tags[4] = "Palermo";
		tags[5] = "Padova";
		tags[6] = "Firenze";
		tags[7] = "Genova";
		tags[8] = "Reggio Calabria";
		tags[9] = "Modena";
	}
	
	public boolean check(String str){
		for(int i = 0; i < 10; i++) {
			if(str.equals(tags[i])) return true;
		}
		return false;
	}

}
