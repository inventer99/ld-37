package ld37;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import ld37.room.Board;
import ld37.room.Room;
import ld37.room.Sky;
import ld37.room.Spawner;
import pixgen.Game;
import pixgen.PixGen;
import pixgen.math.Vector;
import pixgen.scene.Node;
import pixgen.util.Config;

public class Main extends Game
{
	public static PixGen engine;
	public static Main game;
	
	public Config bindings;
	
	public boolean gameOver;
	
	public UI ui;
	
	public Node layerBack;
	public Node layer1;
	public Node layerPlay;
	public Node layer2;
	
	public Sky sky;
	
	public Room room;
	
	public Player player;
	
	public Spawner spawner;
	public Zombie zombie;
	
	public double timeSurvived;
	public long cash;
	
	@Override
	public void preInit()
	{
		engine.setConfig(new Config("cfg/pixgen2.cfg"), true);
		engine.getConfig().setString("window.title", "LD-37");
		
		bindings = new Config("cfg/bindings.cfg");
	}

	@SuppressWarnings("static-access")
	@Override
	public void init()
	{
		gameOver = false;
		
		ui = new UI();
		ui.init();
		
		timeSurvived = 1;
		
		cash = 1000;
	
		engine.getCamera().setZoom(10.0F);
		
		layerBack = new Node();
		rootNode.addChild(layerBack);
		
		layer1 = new Node();
		rootNode.addChild(layer1);
		
		layerPlay = new Node();
		rootNode.addChild(layerPlay);
		
		layer2 = new Node();
		rootNode.addChild(layer2);
		
		sky = new Sky();
//		rootNode.addChild(sky);
		
		room = new Room();
		
		layer1.addChild(new Board(11, -3));
		layer1.addChild(new Board(14, -3));
		layer1.addChild(new Board(19, -5));
		layer1.addChild(new Board(22, -5));
		layer1.addChild(new Board(27, -3));
		layer1.addChild(new Board(30, -3));
		layer2.addChild(new Board(11, -12));
		layer2.addChild(new Board(14, -12));
		layer2.addChild(new Board(27, -12));
		layer2.addChild(new Board(30, -12));
	
		player = new Player();
		player.localTranslation = new Vector(10, -6);
		layerPlay.addChild(player);
		
		zombie = new Zombie();
		
		spawner = new Spawner(zombie, 0.1F);
		spawner.localTranslation = new Vector(0.0F, 0.0F);
		layerPlay.addChild(spawner);
	}

	private boolean lastH = false;
	private boolean didPay = false;
	
	@Override
	public void update(float delta)
	{
		if(!gameOver)
		{
			timeSurvived += delta;
			
			if((long) timeSurvived % 60 == 0 && !didPay)
			{
				changeCash(1000);
				spawner.changeSpawnRate(0.05F);
				didPay = true;
			}
			if((long) timeSurvived % 60 == 1 && didPay)
				didPay = false;
				
			if(engine.getKeyManager().keyDown(KeyEvent.VK_H) && !lastH)
			{
				ui.doDrawControls = !ui.doDrawControls;
				lastH = true;
			}
			if(!engine.getKeyManager().keyDown(KeyEvent.VK_H))
			{
				lastH = false;
			}
		}
		
		ui.update(delta);
		
		if(engine.getKeyManager().keyDown(KeyEvent.VK_ESCAPE))
			engine.stop();
	}
	
	public void changeCash(int amount)
	{
		cash += amount;
		ui.changeCash(amount);
	}

	public void gameOver()
	{
		rootNode.removeChild(layerPlay);
		gameOver = true;
		ui.doDrawGameOver = true;
	}
	
	@Override
	public void render(Graphics2D g) {}
	
	@Override
	public void renderUI(Graphics2D g)
	{
		ui.draw(g);
	}

	@Override
	public void destroy() {}

	public static void main(String[] args)
	{
		engine = new PixGen();
		game = new Main();
		engine.start(game);
	}
}
