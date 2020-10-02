import com.google.gson.Gson;

import processing.core.PApplet;

public class Servidor extends PApplet implements Observer{

	private TCPSingleton singleton;
	private int posX1,posY1;
    private String name;
    private int r1,g1,b1;
    
	public static void main(String[] args) {
		PApplet.main(Servidor.class.getName());
	}

	

	public void settings() {
		size(400, 400);
	}
	
	
	public void setup() {
		posX1=50;
		posY1=50;
		name="nombre";
		r1=0;
		g1=0;
		b1=0;
		
		
		singleton = TCPSingleton.getInstance();
		singleton.setObserver(this);
		singleton.start();
		
	
	}
	
	public void draw() {
		background(255);
		fill(0);
		text(name,posX1,posY1-30);
		fill(r1,g1,b1);
		ellipse(posX1,posY1,40,40);
		
		
	}
	
	
	@Override
	public void mensajeRecibido(String line) {
		System.out.println(line);
		Gson gson = new Gson();
		Circulo infoJugador = gson.fromJson(line, Circulo.class);
		int posX=infoJugador.getPosX();
		int posY=infoJugador.getPosY();
		int r=infoJugador.getR();
		int g=infoJugador.getG();
		int b=infoJugador.getB();
		String name1=infoJugador.getNombre();
		r1=r;
		g1=g;
		b1=b;
		name=name1;
		posX1=posX;
		posY1=posY;
		
		
	}
	
}
