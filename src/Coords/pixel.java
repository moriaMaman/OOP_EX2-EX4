package Coords;

/**
 * this class create a pixel object.
 * x and y of the picture pixels.
 * @author עטרה
 *
 */

public class pixel {
	
	private int x;
	private int y;
	//private map map;
	
	public pixel (int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public int getx () {
		return x;
	}
	
	public int gety () {
		return y;
	}
	public void setX(int x) {
		this.x=x;
	}
	public void setY(int y) {
		this.y=y;
	}
	public String toString() {
		return "("+getx()+","+gety()+")";
	}
}