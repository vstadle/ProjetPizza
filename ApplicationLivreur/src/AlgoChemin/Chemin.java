package AlgoChemin;

import java.util.ArrayList;

public class Chemin {
	int TEMPSKM = 5;
	
	//Liste des addresses
	ArrayList<Position> chemin;
	//ArrayList<Pizza> pizzaenPrep = new ArrayList<Position>();
		
	//Critères de livraison ratio Temps/Trajet
	double tempsLivraison;
	int nbPizza;
	double distanceLivraison;
	
	public Chemin () {
		this.nbPizza = 0;
		this.distanceLivraison = 0;
		this.tempsLivraison = 15;
		this.chemin = new ArrayList<>();
	}
	
	public Chemin (ArrayList<Position> adresse) {
		this.chemin = adresse;
		this.nbPizza = adresse.size();
		this.tempsLivraison = this.calculTemps();
		this.distanceLivraison = this.calculDistance();
	}
	
	//On cherche à calculer le ration Temps/Distance
	public double rationTempsPizza() {
		return this.tempsLivraison/this.distanceLivraison;
	}
	
	public double calculDistance() {
		double distance = 0;
		for(int i=0 ; i<this.chemin.size();i++) {
			distance = distance + this.chemin.get(i).distance(this.chemin.get(i+1));
		}
		return distance;
	}
	
	public double calculTemps() {
		return this.distanceLivraison*TEMPSKM;
	}
	
	public Chemin calculCheminDyna(ArrayList<Position> listeAdresse) {
		Chemin c = new Chemin();
		try {
			if(this.rationTempsPizza()<c.rationTempsPizza()) {
				
			}
		}
		catch(Exception e){
		
		}
		return new Chemin();
	}
}
