package ld37.ai;

import ld37.Main;
import pixgen.comp.Component;
import pixgen.math.Vector;
import pixgen.util.Updateable;

public class ZombieAI extends Component implements Updateable
{
	public boolean up = false;
	public boolean down = false;
	public boolean left = false;
	public boolean right = false;
	
	public boolean attack = false;
	
	Vector playerPos;
	Vector dist;
	
	@Override
	public void update(float delta)
	{
		playerPos = Main.game.player.localTranslation;
		
		dist = new Vector(parent.localTranslation.x - playerPos.x, parent.localTranslation.y - playerPos.y);
		
		if(dist.x > 1)
			left = true;
		else
			left = false;
		
		if(dist.x < -1)
			right = true;
		else
			right = false;
		
		if(dist.y < -1)
			up = true;
		else
			up = false;
		
		if(dist.y > 1)
			down = true;
		else
			down = false;
		
		if(!up && !down && !left && !right)
		{
			// Attack 75% of the time
			if(Math.random() * 10 >= 2.5)
				attack = true;
		}
		else
		{
			attack = false;
		}
	}
	
	public void kill()
	{
		up = false;
		down = false;
		left = false;
		right = false;
		attack = false;
	}
}
