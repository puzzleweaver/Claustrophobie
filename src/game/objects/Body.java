package game.objects;

import org.newdawn.slick.Graphics;

public abstract class Body {

	public int x, y;
	
	public Body(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public abstract void draw(Graphics g);
	
	public void moveTo(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public boolean isAt(int x, int y){
		return this.x == x && this.y == y;
	}
	
}
