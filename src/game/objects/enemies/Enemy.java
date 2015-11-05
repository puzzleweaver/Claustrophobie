package game.objects.enemies;

import org.newdawn.slick.Graphics;

public abstract class Enemy {

	public boolean dead;
	
	public abstract void draw(Graphics g);
	public abstract void update();
	public abstract boolean isAt(int x, int y);
	public abstract void react();
	
}
