package Algorithm;



import Geom.Point3D;
import gameElements.Fruit;
import gameElements.Player;

public class closestFruitAlgo {
	public Player _player;
	public Fruit _fruit;
	private double _distance;
	
	public closestFruitAlgo(Player player,Fruit fruit) {
		this._player=player;
		this._fruit=fruit;
		Point3D p=new Point3D(this._player.getCurrentLocation().x(),this._player.getCurrentLocation().y());
		this._distance=(p.distance2D(this._fruit.currentLocation()));
	}
	
	public double getDistance() {
		return _distance;
	}

	public void setDistance(double _distance) {
		this._distance = _distance;
	}


}
