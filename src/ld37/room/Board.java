package ld37.room;

import ld37.Main;
import pixgen.comp.Texture;
import pixgen.math.Vector;
import pixgen.phys.AABBCollider;
import pixgen.scene.Entity;

public class Board extends Entity
{
	private Texture texture;
	
	public Board(float x, float y)
	{
		this.localTranslation = new Vector(x, y);
	}
	
	@Override
	public void onInit()
	{
		super.onInit();
		
		texture = new Texture();
		texture.add(Main.game.assetManager.loadImage("res/tiles/rubble/board_0.png"));
		texture.add(Main.game.assetManager.loadImage("res/tiles/rubble/board_1.png"));
		texture.add(Main.game.assetManager.loadImage("res/tiles/rubble/board_2.png"));
		texture.add(Main.game.assetManager.loadImage("res/tiles/rubble/board_3.png"));
		texture.add(Main.game.assetManager.loadImage("res/tiles/rubble/board_4.png"));
		addComponent(texture);
		
		textureIndex = 2;
		
		addComponent(new AABBCollider(1, 1));
		Main.game.physicsManager.addChild(this);
	}
	
	public void damage()
	{
		if(textureIndex > 0)
			textureIndex--;
	}
	
	public void fix()
	{
		if(textureIndex < 4)
			textureIndex++;
	}
	
	public boolean isFixed()
	{
		return textureIndex == 4;
	}
	
	public boolean isBroke()
	{
		return textureIndex == 0;
	}
}
