package menus;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Graphics;

import game.level.Generator;
import game.objects.Body;
import game.objects.Player;
import game.objects.State;
import game.objects.enemies.Enemy;
import game.objects.enemies.RedBloodCell;
import game.objects.enemies.whiteBloodCell.WhiteBloodCell;
import main.Main;

public class Game implements Menu {

	public static int stateWidth = 200, stateHeight = 200;
	public static int sX, sY; // x and y offsets
	public static State[][] states = new State[stateWidth][stateHeight];
	public static Player player;
	public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	
	public Game(){
		states = Generator.newLevel(stateWidth, stateHeight);
		player = new Player(Keyboard.KEY_W, Keyboard.KEY_D, Keyboard.KEY_A, Keyboard.KEY_S);
		for(int i = 0; i < 3; i++) enemies.add(new WhiteBloodCell());
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < Main.w/Main.uw + 1; i++){
			for(int j = 0; j < Main.h/Main.uw + 1; j++){
				getState(sX/Main.uw, sY/Main.uw, i, j).draw(g);
			}
		}
		player.draw(g);
		for(int i = 0; i < enemies.size(); i++){
			enemies.get(i).draw(g);
		}
		
		g.drawString(enemies.size()-5 + " red blood cells", 20, 40);
	}
	
	public void update() {
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
			System.exit(1);
		updateAndCull();
		int k1 = 10000, k2 = 100, k;
		for(int i = 1; i < stateWidth-1; i++) {
			for(int j = 1; j < stateHeight-1; j++) {
				k = states[i][j].type == State.TYPE_OPEN ? k1:k2;
				if(states[i][j].d < states[i+1][j].d)
					states[i][j].d = (states[i][j].d*k+states[i+1][j].d)/(k+1);
				if(states[i][j].d < states[i-1][j].d)
					states[i][j].d = (states[i][j].d*k+states[i-1][j].d)/(k+1);
				if(states[i][j].d < states[i][j+1].d)
					states[i][j].d = (states[i][j].d*k+states[i][j+1].d)/(k+1);
				if(states[i][j].d < states[i][j-1].d)
					states[i][j].d = (states[i][j].d*k+states[i][j-1].d)/(k+1);
			}
		}
		// useInput();
		player.update();
		scroll();
		enemies.add(new RedBloodCell());
	}
	private void updateAndCull(){
		for(int i = 0; i < enemies.size(); i++){
			enemies.get(i).update();
			if(enemies.get(i).dead) enemies.remove(i);
		}
	}
	
	public static boolean freeAt(int x, int y){
		if(states[x/Main.uw][y/Main.uw].type == State.TYPE_SOLID) return false;
		return nobodyAt(x, y);
	}
	public static boolean nobodyAt(int x, int y){
		for(int i = 0; i < player.bodies.size(); i++){
			if(player.bodies.get(i).isAt(x, y)) return false;
		}
		for(int i = 0; i < Game.enemies.size(); i++){
			if(Game.enemies.get(i).isAt(x, y)) return false;
		}
		return true;
	}
	
	public void scroll(){
		int x = sX, y = sY;
		double ax = 0, ay = 0;
		Body b;
		for(int i = 0; i < player.bodies.size(); i++){
			b = player.bodies.get(i);
			ax += b.x;
			ay += b.y;
		}
		ax /= player.bodies.size();
		ay /= player.bodies.size();
		if(ax-sX < Main.border) sX = (int) ax-Main.border;
		if(ax-sX > Main.w-Main.border) sX = (int) ax-Main.w+Main.border;
		if(ay-sY < Main.border) sY = (int) ay-Main.border;
		if(ay-sY > Main.h-Main.border) sY = (int) ay-Main.h+Main.border;
		sX = (sX+x)/2;
		sY = (sY+y)/2;
	}
	
	public State getState(int x, int y, int i, int j){
		if(x+i < 0 || x+i >= stateWidth || y+j < 0 || y+j >= stateHeight) return new State(0, 0, State.TYPE_SOLID);
		return states[x+i][y+j];
	}

}
