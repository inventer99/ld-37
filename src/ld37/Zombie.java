package ld37;

import java.awt.Color;
import java.awt.Graphics2D;

import ld37.ai.ZombieAI;
import pixgen.PixGen;
import pixgen.comp.Animation;
import pixgen.comp.Texture;
import pixgen.math.Vector;
import pixgen.phys.CircleCollider;
import pixgen.phys.Collider;
import pixgen.phys.Collision;
import pixgen.phys.PhysicsListener;
import pixgen.scene.Entity;

public class Zombie extends Entity implements PhysicsListener
{
	private ZombieAI ai;
	
	private Texture texture;
	private Animation animationUp;
	private Animation animationDown;
	private Animation animationLeft;
	private Animation animationRight;
	
	private Collider collider;
	
	private float speed;
	private Vector velocity;
	
	private float hitCooldown;
	private boolean canHit;
	
	private int health;
	
	@Override
	public void onInit()
	{
		super.onInit();
		
		texture = new Texture();
		texture.add(Main.game.assetManager.loadImage("res/player/test_u0-u2.png"));
		texture.add(Main.game.assetManager.loadImage("res/player/test_u1.png"));
		texture.add(Main.game.assetManager.loadImage("res/player/test_u0-u2.png"));
		texture.add(Main.game.assetManager.loadImage("res/player/test_u3.png"));
		
		texture.add(Main.game.assetManager.loadImage("res/player/test_d0-d2.png"));
		texture.add(Main.game.assetManager.loadImage("res/player/test_d1.png"));
		texture.add(Main.game.assetManager.loadImage("res/player/test_d0-d2.png"));
		texture.add(Main.game.assetManager.loadImage("res/player/test_d3.png"));
		
		texture.add(Main.game.assetManager.loadImage("res/player/test_l0-l2.png"));
		texture.add(Main.game.assetManager.loadImage("res/player/test_l1.png"));
		texture.add(Main.game.assetManager.loadImage("res/player/test_l0-l2.png"));
		texture.add(Main.game.assetManager.loadImage("res/player/test_l3.png"));
		
		texture.add(Main.game.assetManager.loadImage("res/player/test_r0-r2.png"));
		texture.add(Main.game.assetManager.loadImage("res/player/test_r1.png"));
		texture.add(Main.game.assetManager.loadImage("res/player/test_r0-r2.png"));
		texture.add(Main.game.assetManager.loadImage("res/player/test_r3.png"));
		addComponent(texture);
		
		animationUp = new Animation(0.1F, 0, 3);
		addComponent(animationUp);
		animationDown = new Animation(0.1F, 4, 7);
		addComponent(animationDown);
		animationLeft = new Animation(0.1F, 8, 11);
		addComponent(animationLeft);
		animationRight = new Animation(0.1F, 12, 15);
		addComponent(animationRight);
		
		renderOffset.y = 0.5F;
		
		ai = new ZombieAI();
		addComponent(ai);
		
		speed = 2.5F;
		velocity = new Vector();
		
		collider = new CircleCollider(1);
		addComponent(collider);
		Main.game.physicsManager.addChild(this);
		
		health = 5;
	}
	
	@Override
	public void onUpdate(float delta)
	{
		float dspeed = speed * delta;
		
		velocity.mul(0);
		
		if(ai.up)
		{
			velocity.y += 1;
			if(!animationUp.isPlaying()) animationUp.play();
		} else { animationUp.stop(); }
		if(ai.down)
		{
			velocity.y -= 1;
			if(!animationDown.isPlaying()) animationDown.play();
		} else { animationDown.stop(); }
		if(ai.left)
		{
			velocity.x -= 1;
			if(!animationLeft.isPlaying()) animationLeft.play();
		} else { animationLeft.stop(); }
		if(ai.right)
		{
			velocity.x += 1;
			if(!animationRight.isPlaying()) animationRight.play();
		} else { animationRight.stop(); }
		
		velocity.mul(dspeed);
		
		localTranslation.add(velocity);
		
		if(!canHit)
			hitCooldown += delta;
		
		if(hitCooldown >= 1.0)
		{
			canHit = true;
			hitCooldown = 0;
		}
	}
	
	@Override
	public void onRender(Graphics2D g)
	{
		if(!canHit)
		{
			g.setColor(Color.BLACK);
			g.fillRect(
					(int) (-0.5 * PixGen.getCamera().unit),
					(int) (-1.7 * PixGen.getCamera().unit),
					(int) (PixGen.getCamera().unit),
					(int) (0.1 * PixGen.getCamera().unit));
			g.setColor(Main.game.ui.hitColor);
			g.fillRect(
					(int) (-0.5 * PixGen.getCamera().unit) + 1,
					(int) (-1.7 * PixGen.getCamera().unit) + 1,
					(int) ((PixGen.getCamera().unit - 2) / 1.0 * hitCooldown),
					(int) (0.1 * PixGen.getCamera().unit) - 2);
		}
		
		if(health < 5)
		{
			g.setColor(Color.BLACK);
			g.fillRect(
					(int) (-0.5 * PixGen.getCamera().unit),
					(int) (-1.5 * PixGen.getCamera().unit),
					(int) (PixGen.getCamera().unit),
					(int) (0.1 * PixGen.getCamera().unit));
			g.setColor(Main.game.ui.healthColor);
			g.fillRect(
					(int) (-0.5 * PixGen.getCamera().unit) + 1,
					(int) (-1.5 * PixGen.getCamera().unit) + 1,
					(int) ((PixGen.getCamera().unit - 2) / 5 * health),
					(int) (0.1 * PixGen.getCamera().unit) - 2);
		}
	}
	
	public void hit()
	{
		if(health > 0)
			health--;
		
		if(health == 0)
		{
			removeComponent(ai);
			ai.kill();
		}
	}

	@Override
	public void collided(Collision collision)
	{
		if(ai.attack && canHit)
		{
			if(collision.with instanceof Player)
			{
				((Player) collision.with).hit();
				canHit = false;
			}
		}
	}
	
	public boolean isDead()
	{
		return health == 0;
	}
}
