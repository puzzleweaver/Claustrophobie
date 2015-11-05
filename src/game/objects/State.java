package game.objects;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import main.Main;
import menus.Game;

public class State {

	public static final int TYPE_SOLID = 0, TYPE_OPEN = 1;
	
	public int x, y, type, t;
	public double d = 1;
	
	public State(int x, int y, int type){
		this.x = x;
		this.y = y;
		this.type = type;
		t = Main.r.nextInt(255);
	}
	
	public void draw(Graphics g){
		finish();
		t += Main.r.nextInt(17)-8;
		t = Math.min(Math.max(t, 0), 255);
		Color c;
		if(type == TYPE_OPEN) c = new Color((int) (128+t)-(int)Math.min(d, 125), (int)Math.min(d+t*d*0.1, 128), 0);
		else c = new Color((int) (63+t/10)-(int)Math.min(d, 70), (int)Math.min(d+t*d*0.1, 70), 0);
		g.setColor(c);
		g.fillRect(x-Game.sX, y-Game.sY, Main.uw, Main.uw);
	}
	
	public void finish() {
		if(Keyboard.isKeyDown(Keyboard.KEY_1)) {
			BufferedImage img = new BufferedImage(Game.stateWidth, Game.stateHeight, BufferedImage.TYPE_4BYTE_ABGR);
			java.awt.Graphics g = img.getGraphics();
			java.awt.Color c;
			for(int i = 0; i < Game.stateWidth; i++) {
				for(int j = 0; j < Game.stateHeight; j++) {
					Game.states[i][j].t = Math.min(Math.max(Game.states[i][j].t, 0), 255);
					if(Game.states[i][j].type == TYPE_OPEN) c = new java.awt.Color((int) Math.max(0, Math.min(255, (128+Game.states[i][j].t)-(int)Math.min(Game.states[i][j].d, 125))),
							(int)Math.min(Game.states[i][j].d+Game.states[i][j].t*Game.states[i][j].d*0.1, 128), 0);
					else c = new java.awt.Color(Math.max(0, Math.min(255, (int) (63+Game.states[i][j].t/10)-(int)Math.min(Game.states[i][j].d, 70))),
							(int)Math.min(Game.states[i][j].d+Game.states[i][j].t*Game.states[i][j].d*0.1, 70), 0);
					g.setColor(c);
					g.fillRect(i, j, 1, 1);
				}
			}
			try {
				ImageIO.write(img, "png", new File("map.png"));
			} catch (IOException e) {
				System.out.println("WHY");
			}
			System.exit(1);
		}
	}
	
}
