package ld37.ai;

import java.util.ArrayList;
import java.util.Random;

import ld37.Main;
import ld37.room.Board;
import pixgen.comp.Component;
import pixgen.math.Vector;
import pixgen.scene.Node;
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
		{
			if(targetPos == null)
			{
				ArrayList<Board> boards = new ArrayList<Board>();
				for(Node node : Main.game.layer1.getChildren())
					if(node instanceof Board)
						boards.add((Board) node);
				for(Node node : Main.game.layer2.getChildren())
					if(node instanceof Board)
						boards.add((Board) node);
				
//				Vector diff boards.get(0);
//				for(Board board : boards)
//				{
//					if((new Vector(parent.localTranslation).sub(board.localTranslation)).getLength() < diff.getLength())
//						diff = board.localTranslation;
//				}
				
				Random r = new Random();
				
				targetPos = boards.get(r.nextInt(boards.size())).localTranslation;
			}
		}
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
	}
}
