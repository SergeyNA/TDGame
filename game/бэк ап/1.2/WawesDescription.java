package TDGame;

import java.util.ArrayList;

public class WawesDescription {
	
	private ArrayList<ArrayList<int[]>> wawes;
	private int waweNumber;
	private ArrayList<Integer> retention;
	
	static int deathmatchCount = 0;
	
	WawesDescription() {
		wawes = new ArrayList<ArrayList<int[]>>();
		retention = new ArrayList<Integer>();
		waweNumber = 0;
	}
	
	public void generateNextWawe(int number) {
		
	}
	
	public ArrayList<int[]> getWawe() {
		return wawes.get(waweNumber);
	}
	
	public int getRetenrion() {
		return retention.get(waweNumber);
	}
	
	public void waweIsOver() {
		deathmatchCount++;
	}
	
}
