package Algorithm;

import java.util.Comparator;

/**
 * this class is a comperator to the arraylist of fruit we have so we can sort it from the closest to the farest.
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
