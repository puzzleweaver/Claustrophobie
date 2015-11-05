package game.objects;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import game.objects.amoeba.Amoeba;
import game.objects.amoeba.AmoebaBody;
import game.objects.enemies.RedBloodCell;
import game.objects.enemies.whiteBloodCell.WhiteBloodCell;
import main.Main;
import menus.Game;

public class Player extends Amoeba {
	
	public int up, right, left, down;
	
	public Player(int up, int right, int left, int down){
		super(50);
		this.up = up;
		this.right = right;
		this.down = down;
		this.left = left;
	}
	
	public void update() {
		if(Keyboard.isKeyDown(up)) up();
		else if(Keyboard.isKeyDown(down)) down();
		else if(Keyboard.isKeyDown(right)) right();
		else if(Keyboard.isKeyDown(left)) left();
	}
	
	public void right(){
		move(1, 0);
	}
	public void left(){
		move(-1, 0);
	}
	public void up(){
		move(0, -1);
	}
	public void down(){
		move(0, 1);
	}

	public void draw(Graphics g) {
		AmoebaBody b;
		for(int i = 0; i < bodies.size(); i++){
			b = bodies.get(i);
			drawBody(g, b);
			Game.states[b.x/Main.uw][b.y/Main.uw].d++;
		}
	}

	private void drawBody(Graphics g, AmoebaBody a){
		g.setColor(new Color(a.t, 255, 128));
		g.fillRect(a.x-Game.sX, a.y-Game.sY, Main.uw, Main.uw);
	}

	public boolean freeAt(int x, int y) {
		for(int i = 0; i < Game.enemies.size(); i++){
			if(Game.enemies.get(i).isAt(x, y)){
				Game.enemies.get(i).react();
				if(/*bodies.size() < 60 &&*/Game.enemies.get(i) instanceof RedBloodCell){
					bodies.add(new AmoebaBody(bodies.get(0).x, bodies.get(0).y));
				}else if(Game.enemies.get(i) instanceof WhiteBloodCell){
					bodies.remove(0);
				}
			}
		}
		return Game.freeAt(x, y);
	}

}
