package ld37;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import ld37.room.Board;
import pixgen.PixGen;
import pixgen.comp.Animation;
import pixgen.comp.Texture;
import pixgen.math.Vector;
import pixgen.phys.CircleCollider;
import pixgen.phys.Collider;
import pixgen.phys.Collision;
import pixgen.phys.PhysicsListener;
import pixgen.scene.Entity;

public class Player extends Entity implements PhysicsListener
{
	private Texture texture;
	private Animation animationUp;
	private Animation animationDown;
	private Animation animationLeft;
	private Animation animationRight;
	
	private Collider collider;
	
	private float speed;
	private Vector velocity;
	
	private float repairCooldown;
	private boolean canRepair;
	
	private float hitCooldown;
	private boolean canHit;
	
	private int health;
	
	@SuppressWarnings("static-access")
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
		
		collider = new CircleCollider(1.5F);
		addComponent(collider);
		Main.game.physicsManager.addChild(this);
		
		speed = 5.0F;
		velocity = new Vector();
		
		hitCooldown = 0;
		canHit = true;
		
		repairCooldown = 0;
		canRepair = true;
		
		health = 10;
		
		Main.engine.getCamera().follow(this, true, false);
	}
	
	@Override
	public void onUpdate(float delta)
	{
		boolean moved = false;
		
		float dspeed = speed * delta;
		
		velocity.mul(0);
		
		if(Main.engine.getKeyManager().keyDown(KeyEvent.VK_W) && !moved)
		{
			velocity.y += 1;
			moved = true;
			if(!animationUp.isPlaying()) animationUp.play();
		} else { animationUp.stop(); }
		if(Main.engine.getKeyManager().keyDown(KeyEvent.VK_S) && !moved)
		{
			velocity.y -= 1;
			moved = true;
			if(!animationDown.isPlaying()) animationDown.play();
		} else { animationDown.stop(); }
		if(Main.engine.getKeyManager().keyDown(KeyEvent.VK_A) && !moved)
		{
			velocity.x -= 1;
			moved = true;
			if(!animationLeft.isPlaying()) animationLeft.play();
		} else { animationLeft.stop(); }
		if(Main.engine.getKeyManager().keyDown(KeyEvent.VK_D) && !moved)
		{
			velocity.x += 1;
			moved = true;
			if(!animationRight.isPlaying()) animationRight.play();
		} else { animationRight.stop(); }
		
		velocity.mul(dspeed);
		
		localTranslation.add(velocity);
		
		// left wall
		if(localTranslation.x < 9)
			localTranslation.x = 9;
		// Right wall
		if(localTranslation.x > 32)
			localTranslation.x = 32;
		// Top left/right wall
		if(localTranslation.y > -4.5)
			localTranslation.y = -4.5F;
		// Bottom left/right wall
		if(localTranslation.y < -12.5)
			localTranslation.y = -12.5F;
		
		// Top center wall
		if(localTranslation.y > -6.5 && localTranslation.y < -6 && localTranslation.x > 16 && localTranslation.x < 25)
			localTranslation.y = -6.5F;
		// Bottom center wall
		if(localTranslation.y < -10.5 && localTranslation.y > -11 && localTranslation.x > 16 && localTranslation.x < 25)
			localTranslation.y = -10.5F;
		// Bottom center-left wall
		if(localTranslation.x > 16 && localTranslation.x < 16.5 && localTranslation.y > -14.5 && localTranslation.y < -10.5)
			localTranslation.x = 16F;
		// Bottom center-right wall
		if(localTranslation.x < 25 && localTranslation.x > 24.5 && localTranslation.y > -14.5 && localTranslation.y < -10.5)
			localTranslation.x = 25F;
		// Top center-left wall
		if(localTranslation.x > 16 && localTranslation.x < 16.5 && localTranslation.y > -6.5 && localTranslation.y < -2)
			localTranslation.x = 16F;
		// Top center-right wall
		if(localTranslation.x < 25 && localTranslation.x > 24.5 && localTranslation.y > -6.5 && localTranslation.y < -2)
			localTranslation.x = 25F;
		
		if(!canHit)
			hitCooldown += delta;
		
		if(hitCooldown >= 0.5)
		{
			canHit = true;
			hitCooldown = 0;
		}
		
		if(!canRepair)
			repairCooldown += delta;
		
		if(repairCooldown >= 1.5)
		{
			canRepair = true;
			repairCooldown = 0;
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
					(int) ((PixGen.getCamera().unit - 2) / 0.5 * hitCooldown),
					(int) (0.1 * PixGen.getCamera().unit) - 2);
		}
		
		if(!canRepair)
		{
			g.setColor(Color.BLACK);
			g.fillRect(
					(int) (-0.5 * PixGen.getCamera().unit),
					(int) (-1.9 * PixGen.getCamera().unit),
					(int) (PixGen.getCamera().unit),
					(int) (0.1 * PixGen.getCamera().unit));
			g.setColor(Main.game.ui.fixColor);
			g.fillRect(
					(int) (-0.5 * PixGen.getCamera().unit) + 1,
					(int) (-1.9 * PixGen.getCamera().unit) + 1,
					(int) ((PixGen.getCamera().unit - 2) / 1.5 * repairCooldown),
					(int) (0.1 * PixGen.getCamera().unit) - 2);
		}
		
		if(health < 10)
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
					(int) ((PixGen.getCamera().unit - 2) / 10 * health),
					(int) (0.1 * PixGen.getCamera().unit) - 2);
		}
	}
	
	@Override
	public void collided(Collision collision)
	{
		if(Main.engine.getMouseManager().mouseDown(1) && canHit)
		{
			if(collision.with instanceof Zombie)
			{
				if(!((Zombie) collision.with).isDead())
				{
					((Zombie) collision.with).hit();
					canHit = false;
					Main.game.changeCash(+200);
				}
			}
		}
		
		if(Main.engine.getMouseManager().mouseDown(3) && canRepair && Main.game.cash >= 100)
		{
			if(collision.with instanceof Board)
			{
				if(!((Board) collision.with).isFixed())
				{
					((Board) collision.with).fix();
					canRepair = false;
					Main.game.changeCash(-100);
				}
			}
		}
	}
	
	public void hit()
	{
		if(health > 0)
			health--;
		
		if(health == 0)
			Main.game.gameOver();
	}
}
