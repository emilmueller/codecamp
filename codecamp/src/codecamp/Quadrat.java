package codecamp;

public class Quadrat {
	//static --> Klassen-Eigenschaften
	static public String typ="Das ist ein Quadrat";
	
	
	//Instanz-Eigenschaften
	String name;
	int s;
	int x;
	int y;
	String color;
	
	
	//Konstruktor
	public Quadrat(String name){
		this.name=name;
	}
	
	public Quadrat(String name, int x, int y, int s){
		this.name=name;
		this.x=x;
		this.y=y;
		this.s=s;
	}
	
	public void setColor(String color){
		this.color=color;
	}
	
	
	
	public String getName(){
		return name;
	}
	
	public void setName(String n){
		name=n;
	}
	
	public double diagonale(){
		return Math.sqrt(2)*this.s;
	}
}
