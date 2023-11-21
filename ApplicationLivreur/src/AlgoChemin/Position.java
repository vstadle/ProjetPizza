package AlgoChemin;

import java.util.ArrayList;

public class Position {
	private int x;
	private int y;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public Position(int x, int y)
	{
		this.x=x;
		this.y=y;
	}
	
	public double distance(Position p)
	{
		return Math.sqrt((this.getX()-p.getX())*(this.getX()-p.getX())+(this.getY()-p.getY())*(this.getY()-p.getY()));
	}
	
	//renvoie Ã  partir d'une ArrayList de positions une ArrayList de positions triÃ©e dans l'ordre des postions Ã  parcourir pour avoir le chemin le plus optimale
	public static ArrayList<Position> cheminOptiGlouton(ArrayList<Position> positions, Position depart) 
	{
		ArrayList<Position> chemin = new ArrayList<Position>();
		chemin.add(depart);
		if (positions.size()==0)
		{
			return chemin;
		}
		else
		{
			double distMax = 0;
			int indMax = 0;
			for(int i=0; i<positions.size(); i++)
			{
				if(depart.distance(positions.get(i))>distMax) 
				{
					indMax= i;
					distMax = depart.distance(positions.get(i));
				}
			}
			Position etapeSuivante = positions.get(indMax);
			positions.remove(indMax);
			chemin = cheminOptiGlouton(positions, depart);	
			chemin.add(etapeSuivante);
			return chemin;
		}
	}
	
	//(0;0)->(5;8)->(1;9)->(3;7)->(2;6)->(3;4)->
	public static int sommeDist(ArrayList<Position> positions)
	{
		int somme = 0;
		for(int i=0; i<positions.size()-1; i++)
		{
			somme+= positions.get(i).distance(positions.get(i+1));
		}
		return somme;		
	}
	
	
	public static void afficherPositions(ArrayList<Position> positions)
	{
		String s = "";
		for(Position p : positions)
		{
			s+= "("+p.x+";"+p.y+")->";
		}
		System.out.println(s);
	}
	
	public static void main(String[] args) {
		ArrayList<Position> positions = new ArrayList<Position>();
		positions.add(new Position(3,4));
		positions.add(new Position(1,9));
		positions.add(new Position(2,6));
		positions.add(new Position(5,8));
		positions.add(new Position(3,7));
		afficherPositions(positions);
		Position depart = new Position(0,0);
		//ArrayList<Position> chemin = cheminOpti(positions,depart);
		//afficherPositions(chemin);
		System.out.println(depart.distance(new Position(5,8)));
		System.out.println(depart.distance(new Position(3,4)));
	}
	
}
