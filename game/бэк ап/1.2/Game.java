package TDGame;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 42;
	
	private static String NAME = "ver 1.2";
	
	private static int WIDTH = 1024;
	private static int HEIGHT = 512;

	private boolean running;
	private boolean paused;

	private static long timeDelay = 25;
	
	private GamePlot plot;
	private TowerBuilder towerControl;
	private KeyControl interfaceModerator;
	private int nextPlot = 0;
	
	private List<int[]> route;
	private List<int[]> orientation;
	
	private List<EnemyUnit> enemys;
	private List<ScriptUnit> bots;
	private List<TowerUnit> towers;
	private List<Shell> shells;
	
	private List<int[]> waweInfo;
	private List<int[]> scriptInfo;
	
	private LinkedList<ScriptEvent> events;
	private int eventsDelay;
	private int eventProgress;
	
	private List<Image[][]> enemysAnimation;
	
	private Image backGround;
	private Image dialogBackGround;
		
	private Image[] angarVariantsImage;
	private int[] angars;	
	
	private int round;
	
	private int baseHP;
	private int enemiesDestroyed;
	private boolean showInfo;
	
	private Color pausedBackground;
	private Color textMessageInfo;
	private Font pausedState;
	private Font messageInfo;
	
	//--------------------------------

	public void start() {
		this.running = true;
		new Thread(this).start();
	}

	public void run() {

		long delta;
		
		init();

		while (running) {

			delta = System.currentTimeMillis();
			update();
			render();
			
			if(paused) {
				while(paused) {
					try {
						Thread.sleep(250);
					} catch (InterruptedException e) {
						System.out.println("���� ������������ ����� o_O");
						e.printStackTrace();
					}
				}
			}

			long delay = timeDelay - System.currentTimeMillis() + delta;
			if (delay > 0) {

				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					System.out.println("���� ������������ �����");
					e.printStackTrace();
				}

			} else {
				//������� ����� ����� ����� �����������
				System.out.println("����������������");
			}

		}
	}
	
	private void loadLVL(int number) {
		
		this.plot = new GamePlot(number);
		this.towerControl = new TowerBuilder(plot.xCTB, plot.yCTB, plot.towersAnimation, plot.towerTypeCharacteristics);
		this.interfaceModerator = new KeyControl(plot.backGroundTacticMod, plot.score, plot.towerTypeCharacteristics, plot.towerConstructVariant, plot.towersAnimation, plot.xCTB, plot.yCTB);
		
		route = plot.route;
		orientation = plot.orientation;
		
		enemys = new ArrayList<EnemyUnit>();
		towers = new CopyOnWriteArrayList<TowerUnit>();
		shells = new LinkedList<Shell>();
		bots = new ArrayList<ScriptUnit>();
		
		backGround = plot.backGround;
		dialogBackGround = plot.dialogBackGround;
		angarVariantsImage = plot.angarVariantsImage;
		
		angars = plot.angars;
		
		enemysAnimation = plot.enemysAnimation;
		
		baseHP = plot.baseHP;
		enemiesDestroyed = 0;
		showInfo = false;
				
		paused = false;
		round = 0;
		
		waweInfo = plot.waweInfo;
		scriptInfo = plot.scriptInfo;
		
		events = plot.events;
		eventsDelay = 0;
		eventProgress = 1;
		
		nextPlot = plot.nextPlot;
		
		int count = -1;
		for(int index : plot.towerInit) {
			this.towerControl.towerInitialization(index, plot.towerInitType[++count], towers);
		}
		
		for(int i = 0; i < scriptInfo.size(); i++) {
			if(scriptInfo.get(i)[2] <= round) {
				bots.add(new ScriptUnit(enemysAnimation.get(scriptInfo.get(i)[0]), scriptInfo.get(i)[1], scriptInfo.get(i)[3], scriptInfo.get(i)[4], route.get(scriptInfo.get(i)[5]), orientation.get(scriptInfo.get(i)[5])));
			}
		}
	}

	private void init() {
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				
				int tempKeyCode = e.getKeyCode();
				//System.out.println(tempKeyCode);
				
				//������
				if (tempKeyCode == 32) {
					paused ^= true;
				}
				if(!paused) {
					//�����
					if (tempKeyCode == 61) {
						if(timeDelay >= 10) {
						timeDelay -= 5;
						}
					//�����
					} else if (tempKeyCode == 45) {
						timeDelay += 5;
					//�
					} else if (tempKeyCode == 73) {
						showInfo ^= true;
					//���
					} else if (tempKeyCode == 88) {
						paused ^= true;
					} else {
						interfaceModerator.getKeyCode(tempKeyCode, towerControl, towers);					
					}
				}
				
			}
		});
		
		loadLVL(nextPlot);
		
		pausedBackground = new Color(0, 0, 0, 180);
		textMessageInfo = new Color(0, 255, 0);
		pausedState = new Font("TimesRoman", Font.TYPE1_FONT, 24);
		messageInfo = new Font("Dialog", Font.ROMAN_BASELINE, 13);
		
	}

	public void render() {

		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(2);
			requestFocus();
			return;
		}

		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
				
		//�������
		
		g.setColor(Color.green);
		g.drawImage(backGround, 0, 0, 1024, 512, null);
		
		for(TowerUnit tower : towers) {
			tower.draw(g);
		}		
		for(EnemyUnit enemy : enemys) {
			enemy.draw(g);
		}
		for(ScriptUnit bot : bots) {
			bot.draw(g);
		}
		for(Shell ammo : shells) {
			ammo.draw(g);
		}
		
		for(int index : angars) {
			//�������� �������� ���� ����� �� ������ ���������
			g.drawImage(angarVariantsImage[orientation.get(waweInfo.get(index)[8])[0]], waweInfo.get(index)[6],  waweInfo.get(index)[7], null);
		}
		
		this.interfaceModerator.draw(g, towerControl);
		
		if(showInfo == true) {
			Color tempColor = g.getColor();
			g.setColor(new Color(0, 0, 255, 100));
			g.fillRect(900, 80, 1024, 150);
			g.setColor(tempColor);
			g.drawString("Base HP: " + baseHP, 910, 100);
			g.drawString("Enemies data:", 910, 120);
			g.drawString("Killed: " + enemiesDestroyed, 910, 140);
			g.drawString("Amt: ??", 910, 155);
			g.drawString("In this wave: ??", 910, 170);
		}
		
		if(paused) {
			
			g.setFont(this.pausedState);
			g.setColor(this.pausedBackground);
			
			g.fillRect(370, 200, 310, 90);
			g.setColor(this.textMessageInfo);
			
			g.drawString("Game paused", 450, 250);
			g.setFont(this.messageInfo);
			
		}
		
		if(eventProgress < events.size()) {
			if(eventsDelay == events.get(eventProgress).getDelay()) {
				eventsDelay = 0;
				events.get(eventProgress).draw(g);
				eventProgress++;
				paused = true;
			}
		}
		
		if(baseHP <= 0) {
			g.drawImage(dialogBackGround, 250, 100, null);
			events.get(0).draw(g);
		}
		
		//����� ��������
		
		g.dispose();
		bs.show();

	}

	public void update() {
		
		for(TowerUnit tower : towers) {
			tower.kill(enemys, shells);
		}
		
		round++;
		eventsDelay++;
		
		for(int i = 0; i < waweInfo.size(); i++) {
			if(waweInfo.get(i)[5] <= round) {
				if(waweInfo.get(i)[1] > 0) {
					if(round % waweInfo.get(i)[2] == 0) {
						enemys.add(new EnemyUnit(enemysAnimation.get(waweInfo.get(i)[0]), waweInfo.get(i)[3], waweInfo.get(i)[4], waweInfo.get(i)[6], waweInfo.get(i)[7], route.get(waweInfo.get(i)[8]), orientation.get(waweInfo.get(i)[8]), waweInfo.get(i)[9], waweInfo.get(i)[10]));
						waweInfo.get(i)[1]--;
					}
				}
			}
		}
		
		Iterator<EnemyUnit> it = enemys.iterator();
		while(it.hasNext()) {
			EnemyUnit tempEnemy = it.next();
			if(tempEnemy.getBaseAttack() == true) {
				baseHP -= tempEnemy.getDamageToBase();
				tempEnemy.kill();
			}
			if(tempEnemy.getState() == false) {
				this.interfaceModerator.upScore(tempEnemy.getBounty());
			}
			if(tempEnemy.getLifeState() == false) {
				if(tempEnemy.getHP() <= 0) {
					enemiesDestroyed++;
				}
				it.remove();
			}
		}
		
		Iterator<Shell> ammo = shells.iterator();
		while(ammo.hasNext()) {
			if(ammo.next().getLifeState() == false) {
				ammo.remove();
			}
		}
		
		/*boolean allDYES = true;
		for(EnemyUnit enemy : enemys) {
			allDYES &= !enemy.getLifeState();
		}*/
		
		//java layeved graphics paint
		//java graphics hints
		/*if(enemys.size() == 0) {
			if(round > waweInfo.get(0)[5] + waweInfo.get(0)[2]) {
				int notRespEnemyCount = 0;
				for(int[] tempMas : waweInfo) {
					notRespEnemyCount += tempMas[1];
				}
				if(notRespEnemyCount == 0) {
					System.out.println(round);
					this.paused = true;
				}
			}
		}*/
		
		
		if(baseHP <= 0) {
			System.out.println(round);
			timeDelay = 25;
			loadLVL(0);
		}
		
		if(events.size() == eventProgress) {
			System.out.println(round);
			timeDelay = 25;
			loadLVL(nextPlot);
		}
		
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		JFrame frame = new JFrame(Game.NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(game, BorderLayout.CENTER);
		//game.setBounds(100, 0, 640, 480);
		frame.pack();
		// frame.setResizable(false);
		frame.setVisible(true);
		game.start();
	}

}