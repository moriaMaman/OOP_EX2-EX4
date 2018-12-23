package Algorithm;

import java.util.ArrayList;
import java.util.Iterator;

import gameElements.Fruit;
import gameElements.Game;
import gameElements.Packman;

/**
 * this class is a function to calculate a path of each packman to the fruits.
 * so we will have the shortest path we have.
 * @author Atara Zohar and Moria Maman
 *
 */

public class ShortestPathAlgo {
	
	public static void pathAlgo(Game myGame){
		ArrayList<Fruit> newFruitList=new ArrayList<Fruit>();
		Iterator <Fruit> itF = myGame.fruits.iterator();
		while (itF.hasNext()) {//copy the new array list of fruits.
			Fruit f=itF.next();
			newFruitList.add(new Fruit (f.currentLocation(),f.weight()));
		}
		
		ArrayList<closestFruit> ans=new ArrayList<closestFruit>();//the array list we put the answer in   
		while(newFruitList.size()>0)// As long we have fruits
		{
			ArrayList<Path> gamePaths=new ArrayList<Path>();
			//builds a list of all paths for each Packman
			Iterator <Packman> itP = myGame.packmens.iterator();
			while (itP.hasNext()) {//build a array list of paths 
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
				if(newTime<min.getTime()) {
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
			min.getPackman().eatList.add(min.getFruit());
			newFruitList.remove(min.getFruit());
		}
		
	}

}

