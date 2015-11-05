package game.objects.amoeba;

import org.newdawn.slick.Graphics;

import game.objects.Body;
import main.Main;

public class AmoebaBody extends Body {
	
	public int t;
	
	public AmoebaBody(int x, int y) {
		super(x, y);
		t = Main.r.nextInt(255);
	}
	
	public void moveTo(Place place){
		x = place.x;
		y = place.y;
	}
	
	public void draw(Graphics g){}
	
}
