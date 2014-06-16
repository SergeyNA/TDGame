package TDGame;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

public class GamePlot {
	
	public Image backGround;
	public Image backGroundTacticMod;
	public Image dialogBackGround;
	
	private int enemyVariant = 4;
	private int towerVariant = 4;
	private int angarVariant = 4;
	private int constructorEnemyUnitSize = 11;
	private int constructorScriptUnitSize = 6;
		
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
	public List<int[]> scriptInfo;
	
	public Image[] angarVariantsImage;
	public int[] angars;
	
	public int score;
	public int baseHP;
	public int towerConstructVariant;
	public int nextPlot;
	
	public LinkedList<ScriptEvent> events;
	
	GamePlot(int state) {
		
		BufferedImage sourceImage = null;
		
		try {
			sourceImage = ImageIO.read(new File("images/eventDialog.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		dialogBackGround = Toolkit.getDefaultToolkit().createImage(sourceImage.getSource());
		
		angarVariantsImage = new Image[angarVariant];
		
		for(int k = 0; k < angarVariant; k++) {
				try {
					sourceImage = ImageIO.read(new File("images/angar/angar" + k + ".png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				angarVariantsImage[k] = Toolkit.getDefaultToolkit().createImage(sourceImage.getSource());
		}
		
		//����������� ��������� ��� ��������
		
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
		
		int[] tempMas;
		waweInfo = new ArrayList<int[]>();
		scriptInfo = new ArrayList<int[]>();
		events = new LinkedList<ScriptEvent>();
		
		switch(state) {
		case 1:
			
			try {
				sourceImage = ImageIO.read(new File("images/map1.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			backGround = Toolkit.getDefaultToolkit().createImage(sourceImage.getSource());
			
			try {
				sourceImage = ImageIO.read(new File("images/map1v.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			backGroundTacticMod = Toolkit.getDefaultToolkit().createImage(sourceImage.getSource());
			
			//
			tempMas = new int[constructorEnemyUnitSize];
			tempMas[0] = 0; //type unit
			tempMas[1] = 30; // count
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
			tempMas[1] = 25;
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
			tempMas[1] = 30;
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
			tempMas[1] = 20; // count
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
			
			angars = new int[]{}; //�� ���� ���� ����� �������
			
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
			towerInit = new int[]{}; //��������� ��������� �� ������ ���������� ��� ��� ����
			towerInitType = new int[]{};
			//������ ����� ����� ���� ��� ������� � � � ������� 0.9
			//������� �� 0 �� 8, � ����� ���� ���� ��� �����
			//� � �� 0 �� ������������
			
			towerTypeCharacteristics = new int[towerVariant][10];
			//damage dist cooldown ammoCount maxDamage maxDist maxRate stepDamage stepDist stepRate
			towerTypeCharacteristics[0] = new int[]{30, 250, 50, 1, 150, 300, 20, 10, 10, 2};
			towerTypeCharacteristics[1] = new int[]{30, 250, 40, 1, 150, 300, 20, 10, 10, 2};
			towerTypeCharacteristics[2] = new int[]{10, 200, 50, 2, 50, 250, 10, 5, 5, 1};
			towerTypeCharacteristics[3] = new int[]{5, 150, 5, 1, 10, 200, 2, 1, 5, 1};
			//
			
			score = 250;
			baseHP = 1000;
			towerConstructVariant = 4;
			nextPlot = 2;
			
			break;
			
		case 0:
			
			try {
				sourceImage = ImageIO.read(new File("images/tutor.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			backGround = Toolkit.getDefaultToolkit().createImage(sourceImage.getSource());
			
			try {
				sourceImage = ImageIO.read(new File("images/tutorv.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			backGroundTacticMod = Toolkit.getDefaultToolkit().createImage(sourceImage.getSource());
			
			//
			
			tempMas = new int[constructorEnemyUnitSize];
			tempMas[0] = 2; //type unit
			tempMas[1] = 50; // count
			tempMas[2] = 50; // creation delay
			tempMas[3] = 1; //speed
			tempMas[4] = 40; //hp
			tempMas[5] = 1; //delay
			tempMas[6] = -25; //startX
			tempMas[7] = 100; //startY
			tempMas[8] = 0; //distance & orientation wave
			tempMas[9] = 0; // bounty
			tempMas[10] = 10; // base damage
			waweInfo.add(tempMas);
			
			tempMas = new int[constructorEnemyUnitSize];
			tempMas[0] = 2; //type unit
			tempMas[1] = 30; // count
			tempMas[2] = 75; // creation delay
			tempMas[3] = 2; //speed
			tempMas[4] = 40; //hp
			tempMas[5] = 1; //delay
			tempMas[6] = -25; //startX
			tempMas[7] = 300; //startY
			tempMas[8] = 0; //distance & orientation wave
			tempMas[9] = 0; // bounty
			tempMas[10] = 10; // base damage
			waweInfo.add(tempMas);
			
			tempMas = new int[constructorEnemyUnitSize];
			tempMas[0] = 1; //type unit
			tempMas[1] = 32; // count
			tempMas[2] = 80; // creation delay
			tempMas[3] = 4; //speed
			tempMas[4] = 40; //hp
			tempMas[5] = 1; //delay
			tempMas[6] = -25; //startX
			tempMas[7] = 200; //startY
			tempMas[8] = 0; //distance & orientation wave
			tempMas[9] = 0; // bounty
			tempMas[10] = 10; // base damage
			waweInfo.add(tempMas);
			
			tempMas = new int[constructorEnemyUnitSize];
			tempMas[0] = 2; //type unit
			tempMas[1] = 32; // count
			tempMas[2] = 60; // creation delay
			tempMas[3] = 2; //speed
			tempMas[4] = 40; //hp
			tempMas[5] = 1; //delay
			tempMas[6] = -25; //startX
			tempMas[7] = 400; //startY
			tempMas[8] = 0; //distance & orientation wave
			tempMas[9] = 0; // bounty
			tempMas[10] = 10; // base damage
			waweInfo.add(tempMas);
			
			tempMas = new int[constructorEnemyUnitSize];
			tempMas[0] = 1; //type unit
			tempMas[1] = 29; // count
			tempMas[2] = 100; // creation delay
			tempMas[3] = 4; //speed
			tempMas[4] = 20; //hp
			tempMas[5] = 1; //delay
			tempMas[6] = -25; //startX
			tempMas[7] = 350; //startY
			tempMas[8] = 0; //distance & orientation wave
			tempMas[9] = 0; // bounty
			tempMas[10] = 10; // base damage
			waweInfo.add(tempMas);
			
			tempMas = new int[constructorEnemyUnitSize];
			tempMas[0] = 1; //type unit
			tempMas[1] = 34; // count
			tempMas[2] = 75; // creation delay
			tempMas[3] = 4; //speed
			tempMas[4] = 20; //hp
			tempMas[5] = 50; //delay
			tempMas[6] = -25; //startX
			tempMas[7] = 250; //startY
			tempMas[8] = 0; //distance & orientation wave
			tempMas[9] = 0; // bounty
			tempMas[10] = 10; // base damage
			waweInfo.add(tempMas);
			
			tempMas = new int[constructorEnemyUnitSize];
			tempMas[0] = 3; //type unit
			tempMas[1] = 2; // count
			tempMas[2] = 35; // creation delay
			tempMas[3] = 2; //speed
			tempMas[4] = 50; //hp
			tempMas[5] = 1050; //delay
			tempMas[6] = 1050; //startX
			tempMas[7] = 44; //startY
			tempMas[8] = 1; //distance & orientation wave
			tempMas[9] = 5; // bounty
			tempMas[10] = 10; // base damage
			waweInfo.add(tempMas);
			
			tempMas = new int[constructorEnemyUnitSize];
			tempMas[0] = 3; //type unit
			tempMas[1] = 8; // count
			tempMas[2] = 50; // creation delay
			tempMas[3] = 2; //speed
			tempMas[4] = 70; //hp
			tempMas[5] = 2300; //delay
			tempMas[6] = 1050; //startX
			tempMas[7] = 44; //startY
			tempMas[8] = 1; //distance & orientation wave
			tempMas[9] = 10; // bounty
			tempMas[10] = 10; // base damage
			waweInfo.add(tempMas);
			
			tempMas = new int[constructorEnemyUnitSize];
			tempMas[0] = 2; //type unit
			tempMas[1] = 3; // count
			tempMas[2] = 100; // creation delay
			tempMas[3] = 3; //speed
			tempMas[4] = 30; //hp
			tempMas[5] = 2400; //delay
			tempMas[6] = 1050; //startX
			tempMas[7] = 44; //startY
			tempMas[8] = 1; //distance & orientation wave
			tempMas[9] = 10; // bounty
			tempMas[10] = 10; // base damage
			waweInfo.add(tempMas);
			
			//
			
			tempMas = new int[constructorScriptUnitSize];
			tempMas[0] = 0; //type unit
			tempMas[1] = 2; //speed
			tempMas[2] = 0; //delay
			tempMas[3] = 310; //startX
			tempMas[4] = 450; //startY
			tempMas[5] = 2; //distance & orientation wave
			scriptInfo.add(tempMas);
			
			tempMas = new int[constructorScriptUnitSize];
			tempMas[0] = 0; //type unit
			tempMas[1] = 2; //speed
			tempMas[2] = 0; //delay
			tempMas[3] = 310; //startX
			tempMas[4] = 20; //startY
			tempMas[5] = 3; //distance & orientation wave
			scriptInfo.add(tempMas);
			
			//
			
			events.add(new ScriptEvent(dialogBackGround, 400, new String[]{"��������� ������� �� �� ������� ���������� �� ���� ����������.",
					"� ����������� ��� ���������� �������� �������, ������� ������ ������������",
					"����������. ������, ��� ��� �� ������� ���������� ����� ���������."}));
			events.add(new ScriptEvent(dialogBackGround, 100, new String[]{"������ ����, ����������. ���, ��� ����������?",
					"", "", "", "", "( ��� ����������� ����������� <������> )"}));
			events.add(new ScriptEvent(dialogBackGround, 30, new String[]{"��� ������ ��� �? �� �� ���� ������� ������ ���� �� �����������",
					"������� ���� HY-453, ������� ��� ���� ������� �� ��������� �������.",
					"��� �� ���� � ��� ����� ���� � ���������, �� ��, ��������, �� ��������",
					"����� ������ ��������, �� � ���� ������ �� �� ����� ������ ..."}));
			events.add(new ScriptEvent(dialogBackGround, 50, new String[]{"��� ������ ��� �������� ������� ����� �������?",
					"� ����� �� ������� ��� ������� �������� �� ������? ������� ��� �� �����!",
					"� ��� �������� � ���� �������� ����������� �����. ��� ���� ������� ������",
					"��� ����������."}));
			events.add(new ScriptEvent(dialogBackGround, 10, new String[]{"( ���-�� �������� ������, � �� ������ ���������� ������� �������� )",
					"",
					"� ������������������ ������������� �� ���������� ���������. ������ ���",
					"������������� ���������� ���������� ������ ������ ���������, � ��� ��",
					"������� ������������ �������� � ����������� ������������� � ��������������",
					"������. ��� ���� �������� ���������, �������� ��� �� �������, �, ��",
					"�����������, �������������� ����������� ����������� ��� ������������",
					"� ���������� ���������. ��� ��� � ���� � ���� �� �����, ����������.",
					"���� ���� ����� ���������� ��� ������, �, ���������� �� ���������� ������,",
					"������� ��� � ����."}));
			events.add(new ScriptEvent(dialogBackGround, 10, new String[]{"� �� ����������, ������ �� ������ �����������.",
					"���������� � ���� ��� ������� ������� ������. � �� ��������������� ��",
					"���������� ���������! ��� ���� ����������� �������� ������ ��� ���������.",
					"�� �� �� ����� �� ������ � ������ �������� �� ���� �����������. ��� ��",
					"�������������� ��� ����� �������! ������, ���� ����� ����������� ��������",
					"��� ����� �����������, � ���� �������� ������� ������, ������� ����������",
					"��� � ������, ��� �� �������� ������ ��������� ����� ���������� �����.",
					"������� ��� ����� ��������, ��� ��� ���������� ���� ����������� ���������",
					"��� ����� ������ ���������, � �, �������, ����� ���������� � ������ �����",
					"����������� ���������. ��. ��. ��. ��. ���... � ��� ��� �?"}));
			events.add(new ScriptEvent(dialogBackGround, 10, new String[]{"���� �� �������������� ������ �����, �� ����������� �������",
					"������������� ������. ������� �� �� �� ����� ���������� �� ��� ������ ����.",
					"� ����� ��� ��� ��� ������������ ��� ������ �������������. � ��� � ���������",
					"���� ��������� �������� ������ � ����������� ���������� ������� ��������",
					"���������, ���� � �� ��������� �� ����� ���������� �������� ������� �",
					"������������������ � ������� �������� �, ��� �������, ����������������� � �����",
					"��������. � � �� ��������� ����� ������ ���� ����� � �������� ���������������",
					"����������. � ����� ���� �������� � ������� 950 �������� � ������������ �������",
					"��������� ������� ������ ������ �� �������� ����� ������������ �����. �����",
					"������ ����. �������, ������� ���������� ���� � ���� ������������, �����������",
					"��� ������������ �������������� ��������� ��������������� ��� ������."}));
			events.add(new ScriptEvent(dialogBackGround, 10, new String[]{"���� �������� ��� ���� �� ��������,",
					"����� �������� ��� ����� �������������� ����, ��������� �������������",
					"���������� � ���-�� ������� ������. ��� ��� ������ �������� � ���������",
					"��� �� ������������? ����� ����, ���� �� �� ��� ����, �� �� ����� ������",
					"����������� �� � �������������� ������� ������-������ ����� ��������",
					"������������ �����. �������, �������. ������� ������� � ����."}));
			events.add(new ScriptEvent(dialogBackGround, 10, new String[]{"����� �� ��� �������� �������� �� ��� ��������� ������ ������,",
					"� ��������� ����� ������� ���-�� �������. ���� � ���, ��� � ��� �� �������",
					"�����-�� ������� ������ ��������� ������ � ����� ������ �������� �������",
					"������ ����. � ������������ ��� ����������� ����� ������������ �������.",
					"���� ������ �������� ��������, �� �������� ���������� ����� � ����, ����",
					"����� � ���� �������� ����� �����������. �� �����-�� ���������� �������,",
					"����� ��� ������������ ����� ��������� ��� ��������� ������� ������������",
					"��������������� ��������, ������? �������, �� ���� ����� ���������",
					"����������� ����� � �������. ��� ���� ���������� � ������ ����������. ��",
					"��������� � ����� ���������� ������� � ������ ��������� ���������."}));
			events.add(new ScriptEvent(dialogBackGround, 10, new String[]{"��� ������ ������� <V>",
					"��� ���������� ����������� �����, � ������� �������� �� ������� �������",
					"��������� ����� ��� ���������� ����������. ��� �� � ���� �� ������",
					"����� �������� ��� ����������� ����� ��� �������, ���� �� ������ � ��������"}));
			events.add(new ScriptEvent(dialogBackGround, 150, new String[]{"������ � ��� �������� ������ ����",
					"������������ ����� ������� �� ��������������� ����� � ����. ��� ��� ���",
					"��������� �������� ��������, ������� ������� ����� ������ ����������,",
					"������ ��������� ��� �� ��� �� ����� ������. ����� ������� ����� ���",
					"����� �������� ����������, ������� ����� ��������������� ���.",
					"� ������ ������ �������� ������������ ������� <1>"}));
			events.add(new ScriptEvent(dialogBackGround, 100, new String[]{"���������. �������, ��� ��������� ������� ������������.",
					"������ ����� ���� ���� ������ ���� ����������. ������ �������� ����",
					"���� �����. ��������� �! "}));
			events.add(new ScriptEvent(dialogBackGround, 100, new String[]{"������ ��������� �������� ������������� ����������.",
					"������ ����� ���������� ���������, ����������� ��� ���������. ����",
					"������� �������� ������ ����� ���������� � ������� ����� ����. ���������",
					"��� ������ �������� �� ����, ��������� � ����� ����������� ��������������.",
					"�������� ��������, ��� ��� ���� ������ ��� ���������� � ���� ���������, ���",
					"����������� ����������. ������ ����� � ������ �������� �� ������������",
					"�������� ����� ��������� ������� ����� ������� � �������� ��������� ����������.",
					"������� ������� ������� ������������� ������ ������. ������� <����>"}));
			events.add(new ScriptEvent(dialogBackGround, 50, new String[]{"�������! ����� ���������. �������, ��� ��������� ��� �������",
					"������ �����������. �� ������ �������� �� �� ������� ������������� �����.",
					"� ������ ����� ��������� ����������� ����� ��������� �������� <V> ���",
					"<��������> � ����������� ����������� ������� �������� ������."}));
			events.add(new ScriptEvent(dialogBackGround, 380, new String[]{"���� ������� ������� ������ ����� ������������ ���������!",
					"��� ������������ ����� � ���. � ��� �������� �������������� ������� ��",
					"�������� ����������� �����. �� ���� �������� ��� ������ ��������� �����.",
					"��� �� � ��� ������������, ����������."}));
			events.add(new ScriptEvent(dialogBackGround, 300, new String[]{"���������, �� ������ �� ����! ������, ��� � ��� ���������",
					"������� �������� �����������. � ��� ������ ������, ������� �����������",
					"������� ���� ���������� � �������� ��� ����� ��������� ��� �������.",
					"������ ���� �������������, ����������. ��� ��� ���� �������� ����������.",
					"�������� ���� ��� ���������� � ������������ ����. �����, ����� �� ���������.",
					"��������� �������������� ��������. ������� � ����������� �����, ���� ��",
					"����� ����� �� ����."}));
			events.add(new ScriptEvent(dialogBackGround, 50, new String[]{"������ ����� �������� �� �������, ��� ������� ������� ����������.",
					"��������� ���� ������ �����������. ������� �� ������� � ������ ���������,",
					"���������� ������������ �������� ��������������? ������ �� ����� ��",
					"����������."}));
			events.add(new ScriptEvent(dialogBackGround, 100, new String[]{"������ �������� ����������� ��������� � ������� ������ ��������������.",
					"�� ������ ������� �, ��������� ������� <�����> � <����>. ����� ����������",
					"�������� ����� ��������������, ����������� ������� <�����> � <������>",
					"�������, ����� ��������� ��������� ���� ������ ��������� ��� �������!",
					"����������� ������������ ������� ��� �������� �����."}));
			events.add(new ScriptEvent(dialogBackGround, 400, new String[]{"���� �� ��� �� ����������� ���������, �� ������� <����>",
					"������ ��� ������� ���������, ��� ������������ ��� �������� ����������,",
					"����� �������� �� ������."}));
			events.add(new ScriptEvent(dialogBackGround, 300, new String[]{"��� ����! ��������������� ��������, ������� ��������� ������."}));
			events.add(new ScriptEvent(dialogBackGround, 900, new String[]{"���������, ����������. ������������� ����� ������������ �����",
					"������� �������� �� ����� ���������. ��� ������� ��������, ��� �� �������",
					"���������� ����������� ��������, ����������� �������������, ������ ��� ����",
					"������ ������ ������ � �����. �� ���� ������ ����������. � � ��� ������ ����,",
					"�������, ����������� ����������� �������� ������� ����. No pasaran, Comandante"}));
			
			//
			
			angars = new int[]{}; //�� ���� ���� ����� �������
			
			//
			
			route = new ArrayList<int[]>();
			orientation = new ArrayList<int[]>();
			
			route.add(new int[]{400});
			orientation.add(new int[]{0});
			
			route.add(new int[]{280, 200});
			orientation.add(new int[]{2, 1});
			
			route.add(new int[]{80, 160, 160, 80});
			orientation.add(new int[]{2, 3, 1, 0});
			
			route.add(new int[]{80, 160, 160, 80});
			orientation.add(new int[]{2, 1, 3, 0});
			
			//
			
			xCTB = new int[]{900, 339, 339};
			yCTB = new int[]{170, 90, 366};
			towerInit = new int[]{1, 2}; //��������� ��������� �� ������ ���������� ��� ��� ����
			towerInitType = new int[]{3, 2};
			//������ ����� ����� ���� ��� ������� � � � ������� 0.9
			//������� �� 0 �� 8, � ����� ���� ���� ��� �����
			//� � �� 0 �� ������������
			
			towerTypeCharacteristics = new int[towerVariant][10];
			//damage dist cooldown ammoCount maxDamage maxDist maxRate stepDamage stepDist stepRate
			towerTypeCharacteristics[0] = new int[]{20, 200, 50, 1, 150, 300, 20, 10, 10, 2};
			towerTypeCharacteristics[1] = new int[]{5, 300, 2, 1, 5, 350, 3, 0, 0, 0};
			towerTypeCharacteristics[2] = new int[]{100, 350, 20, 1, 100, 400, 30, 0, 0, 0};
			towerTypeCharacteristics[3] = new int[]{5, 300, 3, 1, 5, 350, 3, 0, 0, 0};
			//
			
			score = 2500;
			baseHP = 1;
			towerConstructVariant = 1;
			nextPlot = 1;
			
			break;
			
		default:
			
			backGround = null;
			backGroundTacticMod = null;
			dialogBackGround = null;
			
			waweInfo = null;
			scriptInfo = null;
			events = null;
			
			angars = null;
			
			route = null;
			orientation = null;
			
			xCTB = null;
			yCTB = null;
			towerInit = null;
			towerInitType = null;
			
			enemysAnimation = null;
			towersAnimation = null;
			angarVariantsImage = null;
			
			towerTypeCharacteristics = null;
			
			score = 0;
			baseHP = 0;
			towerConstructVariant = 0;
			nextPlot = 0;
			
		}
		
	}
	
	//���� �� ���
	
	/*
	 * ����� ���������!!1
	 */
	
	//����
	
	/*
	 * ���������������� �������� �� ��������� ����� � � ���������� � �����������
	*/
	
	//���������
	
	/*
	 * �������� ����� ���������
	 * ����������� ����������/������ �� �������/������ ����
	 * ������� ����������� ����������� �������������� ��������� (����, ����������, � ���� ������� �������)
	 * ������ ������������
	 * ������� �����
	 * ����� ����� ���������
	 * * ����� ���������
	 * ��������� ��������� �������� �������� ����� ������ �� ���� � ����
	 * ���������� ������������ ��������
	 * �������� ������� ����� ��� �� ���
	 * ����������� ��������� ����� ����, ��� �������� ��������������� �����
	 * �������� �� ��� �������� ������
	 * ��������� �����
	 * ���� � ��������
	 * ???
	 * PROFIT
	 */
	
}
