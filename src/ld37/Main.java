package ld37;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import ld37.room.Room;
import pixgen.Game;
import pixgen.PixGen;
import pixgen.util.Config;

public class Main extends Game
{
	public static PixGen engine;
	public static Main game;
	
	public Config bindings;
	
	public UI ui;
	
	public Room room;
	
	public Player player;
	
	public double timeSurvived;
	public double cash;
	
	@Override
	public void preInit()
	{
		engine.setConfig(new Config("cfg/pixgen2.cfg"), true);
		
		bindings = new Config("cfg/bindings.cfg");
	}

	@SuppressWarnings("static-access")
	@Override
	public void init()
	{
		ui = new UI();
		ui.init();
		
		// Set the time to Day 0, 12:00 AM
		timeSurvived = 60 * 11;
		
		cash = 1950600;
	
		engine.getCamera().setZoom(10.0F);
		
		room = new Room();
		rootNode.addChild(room);
	
		player = new Player();
		rootNode.addChild(player);
	}

	@Override
	public void update(float delta)
	{
		timeSurvived += delta;
		cash += 100 * delta;
		
		if(engine.getKeyManager().keyDown(KeyEvent.VK_ESCAPE))
			engine.stop();
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
