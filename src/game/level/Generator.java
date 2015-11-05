package game.level;

import game.objects.State;
import main.Main;

public class Generator {
	
	private static int maxRooms = 200;
	private static int borderX = 20, borderY = 20;
	private static int minRoomSize = 10, maxRoomSize = 20;
	
	public static State[][] newLevel(int width, int height){
		State[][] states = new State[width][height];
		for(int i = 0; i < states.length; i++){
			for(int j = 0; j < states[0].length; j++){
				states[i][j] = new State(i*Main.uw, j*Main.uw, State.TYPE_SOLID);
			}
		}
		int boxes = maxRooms;
		Room[] room = new Room[boxes];
		for(int i = 0; i < boxes; i++){
			int w = Main.r.nextInt(minRoomSize)+(maxRoomSize-minRoomSize);
			int h = Main.r.nextInt(minRoomSize)+(maxRoomSize-minRoomSize);
			int x = Main.r.nextInt(width-w-2*borderX)+borderX;
			int y = Main.r.nextInt(height-h-2*borderY)+borderY;
			Room r = new Room(x, y, w, h);
			boolean valid = true;
			for(int j = 0; j < i; j++){
				if(r.intersects(room[j])){
					valid = false;
					i--;
					boxes--;
					break;
				}
			}
			if(valid){
				//carve new room
				room[i] = r;
				for(int j = x; j < x+w; j++){
					for(int k = y; k < y+h; k++){
						states[j][k] = new State(j*Main.uw, k*Main.uw, State.TYPE_OPEN);
					}
				}
				if(i > 0){
					int centerX1 = r.getCenterX();
					int centerY1 = r.getCenterY();
					Room r2 = room[i-1];
					int centerX2 = r2.getCenterX();
					int centerY2 = r2.getCenterY();
					if(Main.r.nextBoolean()){
						for(int j = Math.min(centerX1, centerX2); j <= Math.max(centerX1, centerX2); j++){
							states[j][centerY2] = new State(j*Main.uw, centerY2*Main.uw, State.TYPE_OPEN);
						}
						for(int j = Math.min(centerY1, centerY2); j <= Math.max(centerY1, centerY2); j++){
							states[centerX1][j] = new State(centerX1*Main.uw, j*Main.uw, State.TYPE_OPEN);
						}
					}else{
						for(int j = Math.min(centerY1, centerY2); j <= Math.max(centerY1, centerY2); j++){
							states[centerX1][j] = new State(centerX1*Main.uw, j*Main.uw, State.TYPE_OPEN);
						}
						for(int j = Math.min(centerX1, centerX2); j <= Math.max(centerX1, centerX2); j++){
							states[j][centerY2] = new State(j*Main.uw, centerY2*Main.uw, State.TYPE_OPEN);
						}
					}
				}
			}
		}
		return states;
	}
	
}
