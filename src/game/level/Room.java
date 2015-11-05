package game.level;

public class Room {
	
	public int x, y, w, h;
	
	public Room(int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public boolean intersects(Room room){
		return (x <= room.x+room.w && x+w >= room.x &&
	            y <= room.y+room.h && y+h >= room.y);
	}
	
	public int getCenterX(){
		return x+w/2;
	}
	public int getCenterY(){
		return y+h/2;
	}
	
}
