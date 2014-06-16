package TDGame;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class GamePlot {
	
	public Image backGround;
	public Image backGroundTacticMod;

	private int enemyVariant = 1;
	private int constructorEnemyUnitSize = 11;
		
	public List<Image[][]> enemysAnimation;
			
	public List<int[]> route;
	public List<int[]> orientation;
	
	public List<int[]> waweInfo;
	
		
	public int score;
	
	//
	
	GamePlot(int state) {
		switch(state) {
		case 0:
			
			BufferedImage sourceImage = null;

			try {
				sourceImage = ImageIO.read(new File("images/map1.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			backGround = Toolkit.getDefaultToolkit().createImage(sourceImage.getSource());
			//WIDTH = backGround.getWidth(null);
			//HEIGHT = backGround.getHeight(null);
			
			try {
				sourceImage = ImageIO.read(new File("images/map1v.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			backGroundTacticMod = Toolkit.getDefaultToolkit().createImage(sourceImage.getSource());
			
			//запоминание состояний для анимации
			
			enemysAnimation = new ArrayList<Image[][]>(enemyVariant);
						
			Image[][] tmpUnitImage;
			
			for(int k = 1; k <= enemyVariant; k++) {
			
			tmpUnitImage = new Image[5][3];
			
				for(int i = 0; i < 5; i++) {
					for(int j = 0; j < 3; j++) {
						try {
							sourceImage = ImageIO.read(new File("images/enemyTankLVL" + k + "/enemyTankLVL1v" + i + j + ".png"));
						} catch (IOException e) {
							e.printStackTrace();
						}
						tmpUnitImage[i][j] = Toolkit.getDefaultToolkit().createImage(sourceImage.getSource());
					}
				}
				
				enemysAnimation.add(tmpUnitImage);
			
			}
			
						
			//
			
			waweInfo = new ArrayList<int[]>();
			
			int[] tempMas;
			
			tempMas = new int[constructorEnemyUnitSize];
			tempMas[0] = 0; //type unit
			tempMas[1] = 2; // count
			tempMas[2] = 100; // creation delay
			tempMas[3] = 2; //speed
			tempMas[4] = 25; //hp
			tempMas[5] = 1; //delay
			tempMas[6] = 300; //startX
			tempMas[7] = 300; //startY
			tempMas[8] = 0; //distance & orientation wave
			tempMas[9] = 5; // bounty
			tempMas[10] = 10; // base damage
			waweInfo.add(tempMas);			
						
			route = new ArrayList<int[]>();
			orientation = new ArrayList<int[]>();
			
			route.add(new int[]{120, 320, 330, 50});
			orientation.add(new int[]{1, 0, 1, 2});
			
			score = 250;
			
			break;
		}
	}
	
}
