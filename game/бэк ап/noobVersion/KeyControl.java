package TDGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;

public class KeyControl {
	
	private Image backgroundTacticMod;
	private boolean tacticActive;
	private boolean enterWaiting;
	private boolean showBuyDialog;
	private int score;
	private int gamerPositionChoice;
	private String message;
	private String altMessage;
	private int newTowerType;
	private boolean towerTypeC;
	private int[][] towerTypeCharacteristics;
	private int towerVariant;
	
	KeyControl(Image tacticModImage, int startScore, int[][] TTC, int TV) {
		score = startScore;
		backgroundTacticMod = tacticModImage;
		tacticActive = false;
		enterWaiting = false;
		towerTypeC = false;
		towerTypeCharacteristics = TTC;
		towerVariant = TV;
		altMessage = "";
		message = "";
	}
	
	public void upScore(int add) {
		score += add;
	}
	
	public void getKeyCode(int code, TowerBuilder towerControl, List<TowerUnit> towers) {
		
		//тактик мод
		if (code == 86) {
			if(tacticActive) {
				gamerPositionChoice = -1;
				enterWaiting = false;
				towerTypeC = false;
				showBuyDialog = false;
			}
			tacticActive ^= true;
		}
		
		//бэкспейс
		if (code == 8) {
			if(tacticActive) {
				//понавешать ифов в зависимости от текущего состояния
				tacticActive ^= true;
				towerTypeC = false;
				enterWaiting = false;
				gamerPositionChoice = -1;
				showBuyDialog = false;
			}
		}
		
		//циферка не 0 и не на боковой клаве
		if (code - 48 > 0 && code - 48 < 10) {
			if(tacticActive) {
				if(!towerTypeC) {
					//сделать константу для числа позиций у башен
					if(code - 48 < 10) {
						gamerPositionChoice = code - 49; //0 - 48, posit in mas - 0	
						showBuyDialog = true;
						message = "Выберите тип новой башни:";
						altMessage = "возможные значения: 1 - " + towerVariant;
						towerTypeC = true;
					} else {
						showBuyDialog = true;
						message = "неверная позиция башни, повторите";
						altMessage = "";
					}
				} else {
					if(code - 49 < towerVariant) {
						enterWaiting = true;
						newTowerType = code - 49;
						message = "итоговая стоимость: ";
						altMessage = "вы можете сменить тип";
					} else {
						message = "неверный тип башни, повторите";
						altMessage = "";
					}
				}
			}
		}
		
		//ентер
		if (code == 10) {
			if(enterWaiting) {
				int tempCost = towerControl.priceChanges(towerTypeCharacteristics[newTowerType][0], towerTypeCharacteristics[newTowerType][1], towerTypeCharacteristics[newTowerType][2], towerTypeCharacteristics[newTowerType][3], false);
				if(score >= tempCost) {
					towerControl.buildTower(gamerPositionChoice, towers, newTowerType);
					score -= tempCost;
					enterWaiting = false;
					showBuyDialog = false;
					towerTypeC= false;
				} else {
					message = "Нехватает очков";
					enterWaiting = false;
					towerTypeC= false;
				}
			}					
		}
		
	}
	
	public void draw(Graphics2D g, TowerBuilder towerControl) {
		
		g.setFont(g.getFont().deriveFont(Font.BOLD).deriveFont( 15f ));
		g.drawString("SCORE: " + String.valueOf(score), 920, 25);
		
		if(tacticActive) {
			g.drawImage(backgroundTacticMod, 0, 0, null);
		}
		
		g.setFont(g.getFont().deriveFont(Font.BOLD).deriveFont(13f));
		if(showBuyDialog) {
			Color tempColor = g.getColor();
			g.setColor(new Color(0, 0, 255, 100));
			g.fillRect(400, 200, 300, 200);
			g.setColor(tempColor);
			if(enterWaiting && !message.substring(0, 3).equals("нев")) {
				g.drawString(message + String.valueOf(towerControl.priceChanges(towerTypeCharacteristics[newTowerType][0], towerTypeCharacteristics[newTowerType][1], towerTypeCharacteristics[newTowerType][2], towerTypeCharacteristics[newTowerType][3], false)), 450, 250);
			} else {
				g.drawString(message, 450, 250);
			}
			g.drawString(altMessage, 450, 270);
		}
	}
	
}
