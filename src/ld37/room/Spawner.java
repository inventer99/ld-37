package ld37.room;

import pixgen.math.Vector;
import pixgen.scene.Entity;
import pixgen.scene.Node;

public class Spawner extends Node
{
	private Entity entity;
	private float entitiesPerSec;
	
	private float spawnTime;
	private float time;
	
	public Spawner(Entity entity, float entitiesPerSec)
	{
		this.entity = entity;
		this.entitiesPerSec = entitiesPerSec;
		
		this.spawnTime = 1 / this.entitiesPerSec;
		this.time = 0;
	}
	
	@Override
	public void onUpdate(float delta)
	{
		time += delta;
		
		if(time >= spawnTime)
		{
			try 
			{
				Entity e = entity.getClass().newInstance();
				e.localTranslation = new Vector(localTranslation);
				parent.addChild(e);
			}
			catch(InstantiationException | IllegalAccessException e)
			{
				e.printStackTrace();
			}
			
			time = 0;
		}
	}
	
	public void changeSpawnRate(float entitiesPerSec)
	{
		this.entitiesPerSec += entitiesPerSec;
		
		this.spawnTime = 1 / this.entitiesPerSec;
	}
}
