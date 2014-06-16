package TDGame;

import java.awt.Image;
import java.util.List;

public class TowerBuilder {
	
	private int[] xTowerBuildC;
	private int[] yTowerBuildC;
	private List<Image[]> towersAnimation;
	private boolean[] nowBuild;
	private int[][] towerTypeCharacteristics;
	private int[] towerInPosition;
	
	TowerBuilder(int[] xC, int[] yC, List<Image[]> towersView, int[][] TTC) {
		
		xTowerBuildC = xC;
		yTowerBuildC = yC;
		towersAnimation = towersView;
		nowBuild = new boolean[xC.length];
		towerInPosition = new int[xC.length];
		towerTypeCharacteristics = TTC;
		for(int i = 0; i < xC.length; i++) {
			nowBuild[i] = false;
			towerInPosition[i] = 100; //вроде как можно обойтись без него, сделав массив наубилд интовским
		}
		
	}
	
	public void towerInitialization(int numberPosition, int TT, List<TowerUnit> towers) {
		nowBuild[numberPosition] = true;
		towers.add(new TowerUnit(towersAnimation.get(TT), xTowerBuildC[numberPosition], yTowerBuildC[numberPosition], towerTypeCharacteristics[TT][0], towerTypeCharacteristics[TT][1], towerTypeCharacteristics[TT][2], towerTypeCharacteristics[TT][3], TT));
	}
	
	public void buildTower(int numberPosition, List<TowerUnit> towers, int towerType) {
		if(nowBuild[numberPosition] == true) {
			upgradeTower(numberPosition);
		} else {
			nowBuild[numberPosition] = true;
			//img xCoord yCoord damage dist cooldown ammoCount
			towers.add(new TowerUnit(towersAnimation.get(towerType), xTowerBuildC[numberPosition], yTowerBuildC[numberPosition], towerTypeCharacteristics[towerType][0], towerTypeCharacteristics[towerType][1], towerTypeCharacteristics[towerType][2], towerTypeCharacteristics[towerType][3], towerType));
			towerInPosition[numberPosition] = towers.size() - 1;
		}
	}
	
	public int upgradeTower(int numberPosition) {//удалить нафиг проверив что ничего не сломается
		System.out.println("Напиши функцию апгрейда!");
		return 0;
	}
	
	public boolean freeSlot(int position) {
		return nowBuild[position];
	}
	
	public int getTower(int position) {
		return towerInPosition[position];
	}
	
	public int priceChanges(int dmgN, int rangeN, int cdN, int dmgO, int rangeO, int cdO, int ammoD, int towerType, boolean upTower) {
		int price;
		if(upTower) {
			if(ammoD < 100) {
				price = (int) Math.pow((dmgN * rangeN * ammoD / (float) cdN), 1.2);
				price -= (int) Math.pow((dmgO * rangeO * ammoD / (float) cdO), 1.2);
			} else {
				price = (int) Math.pow((dmgN * rangeN / (float) cdN), 1.7);
				price -= (int) Math.pow((dmgO * rangeO / (float) cdO), 1.7);
			}
			switch(towerType) {
			case 0:
				price *= 10;
				break;
			case 1:
				price *= 50;
				break;
			case 2:
				price *= 3;
				break;
			case 3:
				price *= 6;
				break;
			}
		} else {
			if(ammoD < 100) {
				price = (int) (dmgN * rangeN * ammoD / cdN);
			} else {
				price = (int) (dmgN * rangeN / cdN);
			}
			switch(towerType) {
			case 0:
				price *= 10;
				break;
			case 1:
				price *= 50;
				break;
			case 2:
				price *= 8;
				break;
			case 3:
				price *= 45;
				break;
			}
		}
		return price;
	}
	
}
