package ld37;

import java.awt.event.KeyEvent;

import pixgen.comp.Texture;
import pixgen.math.Vector;
import pixgen.scene.Entity;

public class Player extends Entity
{
	Texture texture;
	
	private float speed;
	private Vector velocity;
	
	@SuppressWarnings("static-access")
	@Override
	public void onInit()
	{
		super.onInit();
		
		texture = new Texture(Main.game.assetManager.loadImage("res/player/test.png"));
		addComponent(texture);
		
		renderOffset.y = 0.5F;
		
		speed = 5.0F;
		velocity = new Vector();
		
		Main.engine.getCamera().follow(this, true, false);
	}
	
	@Override
	public void onUpdate(float delta)
	{
		float dspeed = speed * delta;
		
		velocity.mul(0);
		
		if(Main.engine.getKeyManager().keyDown(KeyEvent.VK_W))
			velocity.y += 1;
		if(Main.engine.getKeyManager().keyDown(KeyEvent.VK_S))
			velocity.y -= 1;
		if(Main.engine.getKeyManager().keyDown(KeyEvent.VK_A))
			velocity.x -= 1;
		if(Main.engine.getKeyManager().keyDown(KeyEvent.VK_D))
			velocity.x += 1;
		
		velocity.mul(dspeed);
		
		localTranslation.add(velocity);
		
		if(localTranslation.x > 3)
			localTranslation.x = 3;
		if(localTranslation.x < -4)
			localTranslation.x = -4;
		if(localTranslation.y > 3)
			localTranslation.y = 3;
		if(localTranslation.y < -4)
			localTranslation.y = -4;
	}
}
