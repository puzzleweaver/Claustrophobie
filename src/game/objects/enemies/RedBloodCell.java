package game.objects.enemies;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import game.objects.State;
import main.Main;
import menus.Game;

public class RedBloodCell extends Enemy {

	int x, y, timer;
	
	public RedBloodCell(){
		while(Game.states[x/Main.uw][y/Main.uw].type != State.TYPE_OPEN){
			x = Main.r.nextInt(Game.stateWidth)*Main.uw;
			y = Main.r.nextInt(Game.stateHeight)*Main.uw;
		}
	}
	
	public void draw(Graphics g) {
		int X = x-Game.sX, Y = y-Game.sY;
		if(X < 0 || X > 400 || Y < 0 || Y > 400) return;
		g.setColor(new Color(255, 0, 255));
		g.fillRect(X, Y, Main.uw, Main.uw);
	}

	public void update() {
		timer++;
		if(timer == 4){
			int h = Main.uw*(Main.r.nextInt(3)-1), v = Main.uw*(Main.r.nextInt(3)-1);
			if(Game.freeAt(x+h, y)) x += h;
			if(Game.freeAt(x, y+v)) y += v;
			timer = 0;
		}
	}

	public boolean isAt(int x, int y) {
		return this.x == x && this.y == y;
	}

	public void react() {
		dead = true;
	}

}
