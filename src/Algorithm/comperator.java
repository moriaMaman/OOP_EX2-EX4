package Algorithm;

import java.util.Comparator;

public class comperator implements Comparator<closestFruitAlgo> {

	@Override
	public int compare(closestFruitAlgo arg0, closestFruitAlgo arg1) {
		if(arg0.getDistance()>arg1.getDistance()) {
			return 1;
		}
		else {
			if(arg1.getDistance()>arg0.getDistance()) {
				return -1;
			}
		}
		return 0;
	}

}
