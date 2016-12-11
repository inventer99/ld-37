package ld37;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import ld37.room.Board;
import ld37.room.Room;
import ld37.room.Sky;
import ld37.room.ZombieSpawner;
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
	
	public ZombieSpawner spawner;
	
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
		
		Board board1;
		Board board2;
		Board board3;
		Board board4;
		Board board5;
		Board board6;
		Board board7;
		Board board8;
		Board board9;
		Board board10;
		
		layer1.addChild((board1 = new Board(11, -3)));
		layer1.addChild((board2 = new Board(14, -3)));
		layer1.addChild((board3 = new Board(19, -5)));
		layer1.addChild((board4 = new Board(22, -5)));
		layer1.addChild((board5 = new Board(27, -3)));
		layer1.addChild((board6 = new Board(30, -3)));
		layer2.addChild((board7 = new Board(11, -12)));
		layer2.addChild((board8 = new Board(14, -12)));
		layer2.addChild((board9 = new Board(27, -12)));
		layer2.addChild((board10 = new Board(30, -12)));
	
		player = new Player();
		player.localTranslation = new Vector(10, -6);
		layerPlay.addChild(player);
		
		ArrayList<ZombieSpawner.SpawnZone> zones = new ArrayList<ZombieSpawner.SpawnZone>();
		
		zones.add(new ZombieSpawner.SpawnZone(board1, new Vector(10.0F, 0.0F)));
		zones.add(new ZombieSpawner.SpawnZone(board2, new Vector(14.0F, 0.0F)));
		zones.add(new ZombieSpawner.SpawnZone(board3, new Vector(20.0F, 0.0F)));
		zones.add(new ZombieSpawner.SpawnZone(board4, new Vector(21.0F, 0.0F)));
		zones.add(new ZombieSpawner.SpawnZone(board5, new Vector(27.0F, 0.0F)));
		zones.add(new ZombieSpawner.SpawnZone(board6, new Vector(31.0F, 0.0F)));
		zones.add(new ZombieSpawner.SpawnZone(board7, new Vector(11.0F, -15.0F)));
		zones.add(new ZombieSpawner.SpawnZone(board8, new Vector(15.0F, -15.0F)));
		zones.add(new ZombieSpawner.SpawnZone(board9, new Vector(25.0F, -15.0F)));
		zones.add(new ZombieSpawner.SpawnZone(board10, new Vector(31.0F, -15.0F)));

		spawner = new ZombieSpawner(layerPlay, 0.1F, zones);
	}

	private boolean lastH = false;
	private boolean didPay = false;
	
	@Override
	public void update(float delta)
	{
		if(!gameOver)
		{
			timeSurvived += delta;
			
			spawner.update(delta);
			
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
