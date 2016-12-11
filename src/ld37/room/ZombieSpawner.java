package ld37.room;

import java.util.ArrayList;
import java.util.Random;

import ld37.Zombie;
import pixgen.math.Vector;
import pixgen.scene.Node;
import pixgen.util.Updateable;

public class ZombieSpawner implements Updateable
{
	private ArrayList<SpawnZone> zones;
	private float entitiesPerSec;
	
	private float spawnTime;
	private float time;
	
	private Node layer;
	
	private Random r;
	
	public ZombieSpawner(Node layer, float entitiesPerSec, ArrayList<SpawnZone> zones)
	{
		this.zones = zones;
		this.entitiesPerSec = entitiesPerSec;
		
		this.spawnTime = 1 / this.entitiesPerSec;
		this.time = 0;
		
		this.layer = layer;
		
		this.r = new Random();
	}
	
	@Override
	public void update(float delta)
	{
		time += delta;
		
		if(time >= spawnTime)
		{
			SpawnZone zone = zones.get(r.nextInt(zones.size()));
			
			Zombie z = new Zombie(zone.board);
			z.localTranslation = new Vector(zone.location);
			layer.addChild(z);
			
			time = 0;
		}
	}
	
	public void changeSpawnRate(float entitiesPerSec)
	{
		this.entitiesPerSec += entitiesPerSec;
		
		this.spawnTime = 1 / this.entitiesPerSec;
	}
	
	public static class SpawnZone
	{
		public Vector location;
		public Board board;
		
		public SpawnZone(Board board, Vector location)
		{
			this.board = board;
			this.location = location;
		}
	}
}
