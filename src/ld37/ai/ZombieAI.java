package ld37.ai;

import ld37.Main;
import ld37.Zombie;
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
	
	public boolean enter = false;
	
	public boolean inHouse = false;
	
	private Vector targetPos;
	private Vector dist;
	
	@Override
	public void update(float delta)
	{
		if(!inHouse)
				targetPos = ((Zombie) parent).targetBoard.localTranslation;
		else
			targetPos = Main.game.player.localTranslation;
		
		dist = new Vector(parent.localTranslation.x - targetPos.x, parent.localTranslation.y - targetPos.y);
		
		if(dist.y < -0.5)
			up = true;
		else
			up = false;
		
		if(dist.y > 0.5)
			down = true;
		else
			down = false;
		
		if(dist.x > 0.5)
			left = true;
		else
			left = false;
		
		if(dist.x < -0.5)
			right = true;
		else
			right = false;
		
		if(!up && !down && !left && !right)
		{
			// Attack 75% of the time
			if(Math.random() * 10 >= 2.5)
			{
				if(inHouse)
					attack = true;
				else
					enter = true;
			}
		}
		else
		{
			attack = false;
			enter = false;
		}
	}
	
	public void kill()
	{
		up = false;
		down = false;
		left = false;
		right = false;
		attack = false;
		enter = false;
	}
}
