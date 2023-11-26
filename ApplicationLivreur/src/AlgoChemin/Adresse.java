package AlgoChemin;

public class Adresse {
	private int numero;
	private String nomAdresse;
	private int CP;
	private String ville;
	private String pays;
	private Position p;
	
	public Adresse (int n, String sadresse, int cp, String v, String pays) {
		this.numero=n;
		this.nomAdresse = sadresse;
		this.CP=cp;
		this.ville=v;
		this.pays=pays;
		String adresse = this.numero + "+" + API.formateAdresse(sadresse) + ",+" + this.CP + "+" + API.formateAdresse(ville) + ",+" + this.pays;
		p = API.getGPS(adresse) ;
	}
	
	public Position getPosition() {
		return this.p;
	}
	
	public static void main(String[] args) {
		Adresse a1 = new Adresse(13, "Av. des Sciences", 91190, "Gif sur Yvette", "France");
		System.out.println(a1.getPosition());
	}
}
