package ld37;

import java.awt.event.KeyEvent;

import pixgen.comp.Animation;
import pixgen.comp.Texture;
import pixgen.math.Vector;
import pixgen.scene.Entity;

public class Player extends Entity
{
	private Texture texture;
	private Animation animationUp;
	private Animation animationDown;
	private Animation animationLeft;
	private Animation animationRight;
	
	private float speed;
	private Vector velocity;
	
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
		
		speed = 5.0F;
		velocity = new Vector();
		
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
		
		if(localTranslation.x > 16)
			localTranslation.x = 16;
		if(localTranslation.x < 9)
			localTranslation.x = 9;
		if(localTranslation.y > -4.5)
			localTranslation.y = -4.5F;
		if(localTranslation.y < -14)
			localTranslation.y = -14;
	}
}
