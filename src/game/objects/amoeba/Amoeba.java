package game.objects.amoeba;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import game.objects.State;
import main.Main;
import menus.Game;

public abstract class Amoeba {

	public ArrayList<AmoebaBody> bodies = new ArrayList<AmoebaBody>();
	
	public abstract boolean freeAt(int x, int y);
	
	public Amoeba(int members){
		int x = 0, y = 0;
		while(Game.states[x/Main.uw][y/Main.uw].type != State.TYPE_OPEN){
			x = Main.r.nextInt(Game.stateWidth)*Main.uw;
			y = Main.r.nextInt(Game.stateHeight)*Main.uw;
		}
		for(int i = 0; i < members; i++){
			bodies.add(new AmoebaBody(x, y));
		}
	}
	
	@SuppressWarnings("unchecked")
	public void move(int h, int v){
		bodies = shuffle(bodies);
		int index = findIndex(bodies, -h, -v);
		AmoebaBody moved = bodies.get(index), t;
		ArrayList<AmoebaBody> temp = (ArrayList<AmoebaBody>) bodies.clone();
		ArrayList<Place> freePlaces = new ArrayList<Place>();
		temp.remove(index);
		int originalSize = temp.size()/3;
		for(int i = 0; i < originalSize; i+=2){
			index = findIndex(temp, h, v);
			t = temp.get(index);
			temp.remove(index);
			addFreeSpaces(freePlaces, t, h, v);
		}
		if(freePlaces.size() == 0) return;
		moved.moveTo(freePlaces.get(Main.r.nextInt(freePlaces.size())));
	}
	private void addFreeSpaces(ArrayList<Place> spaces, AmoebaBody t, int h, int v){
		int w = Main.uw;
		if(freeAt(t.x+w, t.y) && h != -1){
			spaces.add(new Place(t.x+w, t.y));
		}if(freeAt(t.x-w, t.y) && h != 1){
			spaces.add(new Place(t.x-w, t.y));
		}if(freeAt(t.x, t.y+w) && v != -1){
			spaces.add(new Place(t.x, t.y+w));
		}if(freeAt(t.x, t.y-w) && v != 1){
			spaces.add(new Place(t.x, t.y-w));
		}
	}
	
	private int findIndex(ArrayList<AmoebaBody> bodies, int h, int v){
		int farthest = -Integer.MAX_VALUE, t, farthestIndex = 0;
		for(int i = 0; i < bodies.size(); i++){
			t = bodies.get(i).x*h+bodies.get(i).y*v;
			if(t >= farthest){
				farthest = bodies.get(i).x*h+bodies.get(i).y*v;
				farthestIndex = i;
			}
		}
		return farthestIndex;
	}
	
	public abstract void draw(Graphics g);
	
	private ArrayList<AmoebaBody> shuffle(ArrayList<AmoebaBody> bodies){
		ArrayList<AmoebaBody> temp = new ArrayList<AmoebaBody>();
		int n = bodies.size(), index;
		while(bodies.size() > 0){
			index = Main.r.nextInt(n);
			temp.add(bodies.get(index));
			bodies.remove(index);
			n--;
		}
		return temp;
	}
	
}
