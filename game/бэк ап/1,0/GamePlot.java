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
	
	//public static int WIDTH;
	//public static int HEIGHT;
	
	//VENUS
	
	private int enemyVariant = 4;
	public int towerVariant = 4;
	private int angarVariant = 4;
	private int constructorEnemyUnitSize = 11;
		
	public List<Image[][]> enemysAnimation;
	public List<Image[]> towersAnimation;
	
	public int[] xCTB;
	public int[] yCTB;
	public int[] towerInit;
	public int[] towerInitType;
	public int[][] towerTypeCharacteristics;
	
	public List<int[]> route;
	public List<int[]> orientation;
	
	public List<int[]> waweInfo;
	
	public Image[] angarVariantsImage = new Image[angarVariant];
	public int[] angars;
	
	public int score;
	public int baseHP;
	
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
			
			for(int k = 0; k < angarVariant; k++) {
					try {
						sourceImage = ImageIO.read(new File("images/angar/angar" + k + ".png"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					angarVariantsImage[k] = Toolkit.getDefaultToolkit().createImage(sourceImage.getSource());
			}
			
			//запоминание состояний для анимации
			
			enemysAnimation = new ArrayList<Image[][]>(enemyVariant);
			towersAnimation = new ArrayList<Image[]>(towerVariant);
			
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
			
			Image[] tmpTowerImage;
			
			for(int k = 1; k <= towerVariant; k++) {
				
				tmpTowerImage = new Image[5];

				for (int i = 0; i < 5; i++) {
					
					try {
						sourceImage = ImageIO.read(new File("images/teslaTowerV" + k + "/tesla" + i + ".png"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					tmpTowerImage[i] = Toolkit.getDefaultToolkit().createImage(sourceImage.getSource());
					
				}
				
				towersAnimation.add(tmpTowerImage);
			
			}
			
			//
			
			waweInfo = new ArrayList<int[]>();
			
			int[] tempMas;
			
			tempMas = new int[constructorEnemyUnitSize];
			tempMas[0] = 0; //type unit
			tempMas[1] = 20; // count
			tempMas[2] = 25; // creation delay
			tempMas[3] = 2; //speed
			tempMas[4] = 25; //hp
			tempMas[5] = 1; //delay
			tempMas[6] = 130; //startX
			tempMas[7] = -25; //startY
			tempMas[8] = 0; //distance & orientation wave
			tempMas[9] = 5; // bounty
			tempMas[10] = 10; // base damage
			waweInfo.add(tempMas);
			
			tempMas = new int[constructorEnemyUnitSize];
			tempMas[0] = 3;
			tempMas[1] = 5;
			tempMas[2] = 120;
			tempMas[3] = 5;
			tempMas[4] = 1;
			tempMas[5] = 300;
			tempMas[6] = 727;
			tempMas[7] = -25;
			tempMas[8] = 1;
			tempMas[9] = 10;
			tempMas[10] = 10;
			waweInfo.add(tempMas);
			
			tempMas = new int[constructorEnemyUnitSize];
			tempMas[0] = 1;
			tempMas[1] = 10;
			tempMas[2] = 40;
			tempMas[3] = 3;
			tempMas[4] = 1;
			tempMas[5] = 1;
			tempMas[6] = 845;
			tempMas[7] = 530;
			tempMas[8] = 2;
			tempMas[9] = 25;
			tempMas[10] = 10;
			waweInfo.add(tempMas);
			
			tempMas = new int[constructorEnemyUnitSize];
			tempMas[0] = 2; //type unit
			tempMas[1] = 5; // count
			tempMas[2] = 50; // creation delay
			tempMas[3] = 1; //speed
			tempMas[4] = 100; //hp
			tempMas[5] = 500; //delay
			tempMas[6] = 130; //startX
			tempMas[7] = -25; //startY
			tempMas[8] = 0; //distance & orientation wave
			tempMas[9] = 20; // bounty
			tempMas[10] = 10;
			waweInfo.add(tempMas);
			
			tempMas = new int[constructorEnemyUnitSize];
			tempMas[0] = 2; //type unit
			tempMas[1] = 50; // count
			tempMas[2] = 40; // creation delay
			tempMas[3] = 5; //speed
			tempMas[4] = 110; //hp
			tempMas[5] = 800; //delay
			tempMas[6] = 845; //startX
			tempMas[7] = 530; //startY
			tempMas[8] = 2; //distance & orientation wave
			tempMas[9] = 25; // bounty
			tempMas[10] = 10;
			waweInfo.add(tempMas);
			
			//
			
			angars = new int[]{}; //на этой мапе вроде ненужны
			
			//
			
			route = new ArrayList<int[]>();
			orientation = new ArrayList<int[]>();
			
			route.add(new int[]{120, 320, 330, 50});
			orientation.add(new int[]{1, 0, 1, 2});
			
			route.add(new int[]{120, 285, 335, 35});
			orientation.add(new int[]{1, 2, 1, 2});
			
			route.add(new int[]{115, 160, 25, 100, 175, 180, 165, 315, 330, 50});
			orientation.add(new int[]{3, 2, 1, 2, 3, 0, 3, 2, 1, 2});
			
			//
			
			xCTB = new int[]{7, 420, 820, 277, 354, 540, 656, 694, 777};
			yCTB = new int[]{15, 16, 57, 197, 288, 202, 173, 340, 331};
			towerInit = new int[]{}; //начальные башенкиии не забыть переделать под тру мапу
			towerInitType = new int[]{};
			//скорее всего товер инит это позиция т е в реалиях 0.9
			//чиселко от 0 до 8, а товер инит тайп тип башни
			//т е от 0 до товервариант
			
			towerTypeCharacteristics = new int[towerVariant][10];
			//damage dist cooldown ammoCount maxDamage maxDist maxRate stepDamage stepDist stepRate
			towerTypeCharacteristics[0] = new int[]{30, 250, 50, 1, 150, 300, 20, 10, 10, 2};
			towerTypeCharacteristics[1] = new int[]{30, 250, 40, 1, 150, 300, 20, 10, 10, 2};
			towerTypeCharacteristics[2] = new int[]{10, 200, 50, 2, 50, 250, 10, 5, 5, 1};
			towerTypeCharacteristics[3] = new int[]{5, 150, 5, 1, 10, 200, 2, 1, 5, 1};
			//
			
			score = 250;
			baseHP = 1000;
			
			break;
		}
	}
	
	//Маст ду нау
	
	/*
	 * Считать стоимость апдейта и не забывать вычитать её
	 * подсветка при выборе башенки (!)
	 * добавить функцию пресс Икс ту вин (!)
	 * разные снаряды (!)
	 */
	
	//Баги
	
	/*
	 * чё делать с паблик плотом
	 * интерфейс для снарядов
	 * вылеты из-за башен ? проверить кейлистенер
	*/
	
	//Улучшения
	
	/*
	 * Завести константы для максимальных характеристик башен
	 * Добавить выбор сложности
	 * на изи тактик мод активирует паузу (анриал)
	 * перерисовать игровое поле (!)
	 * возможность сохранения/начала не сначала/выдачи кода
	 * заюзать возможность отображения дополнительных предметов (штаб, генераторы, и проч могущее взаимод)
	 * дерево исследований
	 * связь между заданиями
	 * * найти художника
	 * обхитрить художника заставив работать сутки наполёт за хлеб и воду
	 * нарисовать красивенькие окошечки
	 * реализовать интерфейс енеми юнит, для описания заскриптованных мобов
	 * запилить на них сюжетные сценки
	 * придумать сюжет
	 * уйти в продакшн
	 * ???
	 * PROFIT
	 */
	
}
