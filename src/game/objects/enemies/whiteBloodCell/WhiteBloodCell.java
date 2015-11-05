package game.objects.enemies.whiteBloodCell;

import org.newdawn.slick.Graphics;

import game.objects.enemies.Enemy;
import main.Main;
import menus.Game;

public class WhiteBloodCell extends Enemy {

	WhiteAmoeba a = new WhiteAmoeba();
	int h, v, timer;
	
	public void update(){
		h = 0; v = 0;
		double dist = Math.hypot(Game.player.bodies.get(0).x-a.bodies.get(0).x,
				Game.player.bodies.get(0).y-a.bodies.get(0).y);
		if(dist < 100*Main.uw){
			timer = 0;
			if(Math.abs(Game.player.bodies.get(0).x-a.bodies.get(0).x) >
					Math.abs(Game.player.bodies.get(0).y-a.bodies.get(0).y))
				h = (int) Math.signum(Game.player.bodies.get(0).x-a.bodies.get(0).x);
			else v = (int) Math.signum(Game.player.bodies.get(0).y-a.bodies.get(0).y);
		}else{
			timer++;
			if(timer == 100){
				h = Main.r.nextInt(3)-1;
				v = Main.r.nextInt(3)-1;
				timer = 0;
			}
		}
		if(dist < 50) a.move(h, v);
		a.move(h, v);
	}
	
	public void draw(Graphics g) {
		a.draw(g);
	}

	public boolean isAt(int x, int y) {
		for(int i = 0; i < a.bodies.size(); i++){
			if(a.bodies.get(i).isAt(x, y)) return true;
		}
		return false;
	}

	public void react() {
		
	}

}
