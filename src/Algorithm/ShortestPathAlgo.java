package Algorithm;

import java.util.ArrayList;
import java.util.Iterator;

import gameElements.Fruit;
import gameElements.Game;
import gameElements.Packman;



/**
 * this class represents the algorithm of the shortest fruit's Path (as much as possible) for each packman.
 * @author Atara Zohar and Moria Maman
 *
 */

public class ShortestPathAlgo {
	
	public static void pathAlgo(Game myGame){
		ArrayList<Fruit> newFruitList=new ArrayList<Fruit>();
		Iterator <Fruit> itF = myGame.fruits.iterator();
		while (itF.hasNext()) {//copy to a new fruit's array list 
			Fruit f=itF.next();
			newFruitList.add(new Fruit (f.currentLocation(),f.weight()));
		}
		
		ArrayList<closestFruit> ans=new ArrayList<closestFruit>();
		while(newFruitList.size()> 0&&myGame.packmens.size()>0)//as long as there were fruit left in the game
		{
			ArrayList<Path> gamePaths=new ArrayList<Path>();
			//builds a list of all paths for each Packman
			Iterator <Packman> itP = myGame.packmens.iterator();
			while (itP.hasNext()) {//build a new array list of paths each time
				Packman p=itP.next();
				gamePaths.add(new Path(p,newFruitList));
			}
			Fruit minFruit=gamePaths.get(0).getPriorityList().get(0).getFruit();
			Packman minPackman=gamePaths.get(0).getPackman();
			double minTime=gamePaths.get(0).getPriorityList().get(0).getTime();
			closestFruit min=new closestFruit(minFruit,minPackman,minTime);
			for(int i=1;i<gamePaths.size();i++) {
				Fruit newFruit=gamePaths.get(i).getPriorityList().get(0).getFruit();
				Packman newPackman=gamePaths.get(i).getPriorityList().get(0).getPackman();
				double newTime=gamePaths.get(i).getPriorityList().get(0).getTime();
				if(newTime<min.getTime()) {//If there is a closer fruit 
				min.setFruit(newFruit);
				min.setPackman(newPackman);
				min.setTime(newTime);
				}
			}
			min.getFruit().setTime(min.getTime());
			min.getPackman().setNewLocation(min.getFruit().currentLocation());
			min.getPackman().setTime(min.getTime()+min.getPackman().time());
			min.getFruit().setTime(min.getPackman().time());
			ans.add(min);
			min.getPackman().eatList.add(min.getFruit());//add the fruit to an "eat list" of the packman that is more close to him
			newFruitList.remove(min.getFruit());//remove the fruit of the total fruits list
		}
		
	}

}

