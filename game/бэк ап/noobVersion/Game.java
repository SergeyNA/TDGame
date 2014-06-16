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

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 42;
	
	private static String NAME = "ver 0.9";
	
	private static int WIDTH = 1024;
	private static int HEIGHT = 512;

	private boolean running;
	private boolean paused;

	private static long timeDelay = 25;
	
	private GamePlot plot;
	private KeyControl interfaceModerator;
	private int nextPlot = 0;
	
	private List<int[]> route;
	private List<int[]> orientation;
	
	private List<EnemyUnit> enemys;
	
	private List<int[]> waweInfo = new ArrayList<int[]>();
	
	private List<Image[][]> enemysAnimation;
	
	private Image backGround;
		
	private int round;
	
	public void start() {
		this.running = true;
		new Thread(this).start();
	}

	public void run() {

		long delta;
		
		this.plot = new GamePlot(nextPlot);
		
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
						System.out.println("Тред отказывается спать o_O");
						e.printStackTrace();
					}
				}
			}

			long delay = timeDelay - System.currentTimeMillis() + delta;
			if (delay > 0) {

				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					System.out.println("Тред отказывается спать");
					e.printStackTrace();
				}

			} else {
				//удалить ветвь нафиг перед продакшоном
				System.out.println("Таймингопроблемы");
			}

		}
	}

	public void init() {
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				
				int tempKeyCode = e.getKeyCode();
				//System.out.println(tempKeyCode);
				
				//пробел
				if (tempKeyCode == 32) {
					paused ^= true;
				}
				if(!paused) {
					//спид контроль
					//WTF где мой плюс? Фигли не пашет?
					//upd фиг с плюсиком, пускай будет рoвно
					if (tempKeyCode == 61) {
						if(timeDelay >= 10) {
						timeDelay -= 5;
						}
					} else if (e.getKeyCode() == 45) {
						timeDelay += 5;
					} else {
						interfaceModerator.getKeyCode(tempKeyCode, towerControl, towers);					
					}
				}
				
			}
		});
		
		route = plot.route;
		orientation = plot.orientation;
		
		enemys = new ArrayList<EnemyUnit>();
		
		backGround = plot.backGround;
		
		enemysAnimation = plot.enemysAnimation;
		
				
		paused = false;
		round = 0;
		
	}

	public void render() {

		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(2);
			requestFocus();
			return;
		}

		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
				
		//рисовач
		
		g.setColor(Color.green);
		g.drawImage(backGround, 0, 0, 1024, 512, null);
				
		for(EnemyUnit enemy : enemys) {
			enemy.draw(g);
		}
				
		if(paused) {
			
			g.fillRect(370, 200, 310, 90);			
			g.drawString("Game paused", 450, 250);
			
		}
		
		//конец рисовача
		
		g.dispose();
		bs.show();

	}

	public void update() {
		
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
			if(tempEnemy.getLifeState() == false) {
				it.remove();
			}
		}
		
		
		if(enemys.size() == 0) {
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