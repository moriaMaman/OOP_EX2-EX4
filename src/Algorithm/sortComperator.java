package Algorithm;

import java.util.Comparator;

/** 
 * this class is a comparator to the closestFruit's list that compare the objects from the closest fruit to the farthest.
 * @author Atara Zohar and Moria Maman
 *
 */



public class sortComperator implements Comparator<closestFruit> {


	@Override
	public int compare(closestFruit arg0, closestFruit arg1) {
		if(arg0.getTime()>arg1.getTime()) {
			return 1;
		}
		else {
			if(arg1.getTime()>arg0.getTime()) {
				return -1;
			}
		}
		return 0;
	}

}
