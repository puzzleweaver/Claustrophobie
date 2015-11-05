package main;

import java.util.Random;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import menus.Game;
import menus.Menu;

public class Main extends BasicGame {

	public static int w = 1920, h = 1080, border = (int) (w*0.25);
	
	public Main() {
		super("ARE YOUR JIMMIES NOT RUSTLED?");
	}

	public static final int uw = 16; //universal width
	public static final Random r = new Random();
	public static Menu menu = new Game();
	
	public static void main(String[] args) {
		try{
			AppGameContainer app = new AppGameContainer(new Main());
			app.setDisplayMode(1920, 1080, true);
			app.setMinimumLogicUpdateInterval(15);
			app.setVSync(true);
			//nothing after app.start() runs
			app.start();
		}catch(SlickException e) {
			System.out.println(e);
		}
	}
	
	public void render(GameContainer arg0, Graphics g) throws SlickException {
		menu.render(g);
	}

	public void init(GameContainer arg0) throws SlickException {
		
	}

	public void update(GameContainer arg0, int arg1) throws SlickException {
		System.out.println(Mouse.getDWheel());
		menu.update();
	}
	
}
