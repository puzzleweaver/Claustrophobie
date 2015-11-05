package game.objects.enemies.whiteBloodCell;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import game.objects.amoeba.Amoeba;
import game.objects.amoeba.AmoebaBody;
import main.Main;
import menus.Game;

public class WhiteAmoeba extends Amoeba {

	public WhiteAmoeba() {
		super(40);
	}

	public boolean freeAt(int x, int y) {
		return Game.nobodyAt(x, y);
	}

	public void draw(Graphics g) {
		for(int i = 0; i < bodies.size(); i++){
			drawBody(bodies.get(i), g);
		}
	}
	private void drawBody(AmoebaBody a, Graphics g){
		g.setColor(new Color(127+a.t/2, 127+a.t/2, 127+a.t/2));
		g.fillRect(a.x-Game.sX, a.y-Game.sY, Main.uw, Main.uw);
	}

}
